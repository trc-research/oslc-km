package com.reusecompany.oslc.km.services.facade.impl;

import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.hp.hpl.jena.ontology.BooleanClassDescription;
import com.hp.hpl.jena.ontology.Individual;
import com.hp.hpl.jena.ontology.OntClass;
import com.hp.hpl.jena.ontology.OntDocumentManager;
import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.ontology.OntModelSpec;
import com.hp.hpl.jena.ontology.OntProperty;
import com.hp.hpl.jena.ontology.OntResource;
import com.hp.hpl.jena.ontology.Ontology;
import com.hp.hpl.jena.ontology.Restriction;
import com.hp.hpl.jena.rdf.model.Literal;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.rdf.model.RDFNode;
import com.hp.hpl.jena.rdf.model.RSIterator;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.rdf.model.Statement;
import com.hp.hpl.jena.rdf.model.StmtIterator;
import com.hp.hpl.jena.util.iterator.ExtendedIterator;
import com.hp.hpl.jena.vocabulary.OWL;
import com.hp.hpl.jena.vocabulary.OWL2;
import com.hp.hpl.jena.vocabulary.RDF;
import com.hp.hpl.jena.vocabulary.RDFS;
import com.reusecompany.oslc.km.exceptions.ConceptNotFoundException;
import com.reusecompany.oslc.km.srl.Artifact;
import com.reusecompany.oslc.km.srl.ArtifactType;
import com.reusecompany.oslc.km.srl.MetaProperty;
import com.reusecompany.oslc.km.srl.RSHP;
import com.reusecompany.oslc.km.srl.RSHPType;
import com.reusecompany.oslc.km.srl.Term;

public class OWL2SRLVisitor {

	protected static Logger logger = Logger.getLogger(OWL2SRLVisitor.class);

	Artifact model;
	OntModel ontModel;
	Map<String,Artifact> classes;
	Map<String,Artifact> instances;
	Map<String,Artifact> properties;
	boolean createMetas = Boolean.FALSE;

	public Object visit(OntModel ontModel) throws Exception{
		return this.visit(ontModel,Boolean.FALSE);
	}
	
	public Object visit(OntModel ontModel, boolean createMetas) throws Exception{
		this.ontModel = ontModel;
		this.createMetas = createMetas;
		this.model = new Artifact("Model of ontologies "+System.currentTimeMillis());
		this.classes = new HashMap<String,Artifact>();
		this.instances = new HashMap<String,Artifact>();
		this.properties = new HashMap<String,Artifact>();
		Artifact resource;
		ArtifactType artifactType;
		OntClass clazz;
		Individual instance;
		List<Artifact> ownedArtifacts = this.model.getOwnedArtifactsAsList();
		//Processing ontologies
		ExtendedIterator<Ontology> it = ontModel.listOntologies();
		artifactType = new ArtifactType(Ontology.class.getSimpleName());
		while(it.hasNext()){
			resource = (Artifact) visit(it.next());
			resource.setArtifactType(artifactType);
			ownedArtifacts.add(resource);
		}
		//Create classes
		ExtendedIterator<OntClass> itClass = ontModel.listClasses();
		artifactType = new ArtifactType(OntClass.class.getSimpleName());
		while(itClass.hasNext()){
			clazz = itClass.next();
			if(!clazz.isAnon()){
				resource = createArtifactResource(clazz);
				resource.setArtifactType(artifactType);
				this.classes.put(resource.getAbout().toString(), resource);	
			}else{
				logger.debug("Annon class: "+clazz);
			}
		}
		//Describe classes
		itClass = ontModel.listClasses();
		while(itClass.hasNext()){
			clazz = itClass.next();
			if(!clazz.isAnon()){
				resource = visit(clazz);
				ownedArtifacts.add(resource);	
			}else{
				logger.debug("Annon class: "+clazz);
			}
		}

		//Create properties
		OntProperty ontProperty;
		ExtendedIterator<OntProperty> itProperty = ontModel.listAllOntProperties();
		artifactType = new ArtifactType(OntProperty.class.getSimpleName());
		while(itProperty.hasNext()){
			ontProperty = itProperty.next();
			if(!ontProperty.isAnon()){
				resource = createArtifactResource(ontProperty);
				resource.setArtifactType(artifactType);
				this.properties.put(resource.getAbout().toString(), resource);	
			}else{
				logger.debug("Annon property: "+ontProperty);
			}
		}
		//Describe properties
		itProperty = ontModel.listAllOntProperties();
		while(itProperty.hasNext()){
			ontProperty = itProperty.next();
			resource = visit(ontProperty);
			if(!ontProperty.isAnon()){
				ownedArtifacts.add(resource);	
			}
		}

		//Create Instances
		ExtendedIterator<Individual> itInstances = ontModel.listIndividuals();
		artifactType = new ArtifactType(Individual.class.getSimpleName());
		while(itInstances.hasNext()){
			instance = itInstances.next();
			resource = createArtifactResource(instance);
			resource.setArtifactType(artifactType);
			this.instances.put(resource.getAbout().toString(), resource);	
		}

		//Describe Instances
		itInstances = ontModel.listIndividuals();
		while(itInstances.hasNext()){
			instance = itInstances.next();
			resource = visit(instance);
			if(resource != null){
				ownedArtifacts.add(resource);	
			}

		}

		//Clean
		this.classes.clear();
		this.classes = null;
		this.instances.clear();
		this.instances = null;
		this.properties.clear();
		this.properties = null;
		this.ontModel.removeAll();
		this.ontModel = null;
		return model;
	}

	private Object visit(Ontology ontology) throws Exception{
		Artifact ontologyArtifact = createArtifactResource(ontology);
		List<MetaProperty> metas = ontologyArtifact.getMetaPropertiesAsList();
		//Describe metaproperties
		metas=createMetaProperties(ontology);
		ontologyArtifact.getMetaPropertiesAsList().addAll(metas);
		return ontologyArtifact;
	}

	private Artifact visit(OntClass clazz) throws ConceptNotFoundException, URISyntaxException {
		Artifact classArtifact = this.classes.get(clazz.getURI());
		List<RSHP> rshps;
		List<MetaProperty> metas;
		if(classArtifact != null && clazz!=null){
			//Describe Superclasses
			rshps = createRSHPS(classArtifact, clazz.listSuperClasses(true), new RSHPType(RDFS.subClassOf.getURI()));
			this.model.getRshpsAsList().addAll(rshps);
			//Describe Subclasses and restrictions
			rshps = createRSHPS(classArtifact, clazz.listSubClasses(true), new RSHPType(Type.SUBCLASS.name()));
			this.model.getRshpsAsList().addAll(rshps);
			//Describe Equivalent classes
			rshps = createRSHPS(classArtifact, clazz.listEquivalentClasses(), new RSHPType(OWL.equivalentClass.getURI()));
			this.model.getRshpsAsList().addAll(rshps);
			//Describe Properties
			rshps = createRSHPS(classArtifact, clazz.listDeclaredProperties(), new RSHPType(RDF.Property.getURI()));
			this.model.getRshpsAsList().addAll(rshps);
			//Describe same As
			rshps = createRSHPS(classArtifact, clazz.listSameAs(), new RSHPType(OWL.sameAs.getURI()));
			this.model.getRshpsAsList().addAll(rshps);
			//Describe different From
			rshps = createRSHPS(classArtifact, clazz.listDifferentFrom(), new RSHPType(OWL.differentFrom.getURI()));
			this.model.getRshpsAsList().addAll(rshps);
			//Describe disjoint with
			rshps = createRSHPS(classArtifact, clazz.listDisjointWith(), new RSHPType(OWL.disjointWith.getURI()));
			this.model.getRshpsAsList().addAll(rshps);
			//Describe disjoint union of
			rshps = createRSHPS(classArtifact, clazz.listProperties(OWL2.disjointUnionOf), new RSHPType(OWL2.disjointUnionOf.getURI()));
			this.model.getRshpsAsList().addAll(rshps);
			//FIXME: Only if available-Describe MetaProperties
			if(this.createMetas){
				metas=createMetaProperties(clazz);
				classArtifact.getMetaPropertiesAsList().addAll(metas);	
			}

		}
		return classArtifact;
	}

	private Artifact visit(OntProperty ontProperty) throws URISyntaxException, ConceptNotFoundException{
		Artifact propertyArtifact = this.properties.get(ontProperty.getURI());
		List<RSHP> rshps;
		if(propertyArtifact != null && ontProperty != null){
			logger.debug(propertyArtifact.getTerm());
			//FIXME: Conversion exception is thrown
			//rshps = createRSHPS(propertyArtifact, ontProperty.listSuperProperties(true), new RSHPType(Type.SUPERCLASS.name()));
			//this.model.getRshpsAsList().addAll(rshps);
			//Describe Subclasses and restrictions
			rshps = createRSHPS(propertyArtifact, ontProperty.listSubProperties(true), new RSHPType(RDFS.subPropertyOf.getURI()));
			this.model.getRshpsAsList().addAll(rshps);
			//Describe Equivalent classes
			rshps = createRSHPS(propertyArtifact, ontProperty.listEquivalentProperties(), new RSHPType(OWL.equivalentProperty.getURI()));
			this.model.getRshpsAsList().addAll(rshps);
			//Describe Properties
			rshps = createRSHPS(propertyArtifact, ontProperty.listDeclaringClasses(), new RSHPType(Type.DECLARED.name()));
			this.model.getRshpsAsList().addAll(rshps);
			//Describe domain
			rshps = createRSHPS(propertyArtifact, ontProperty.listDomain(), new RSHPType(RDFS.domain.getURI()));
			this.model.getRshpsAsList().addAll(rshps);
			//Describe range
			rshps = createRSHPS(propertyArtifact, ontProperty.listRange(), new RSHPType(RDFS.range.getURI()));
			this.model.getRshpsAsList().addAll(rshps);
			//Describe DifferentFrom
			rshps = createRSHPS(propertyArtifact, ontProperty.listDifferentFrom(), new RSHPType(OWL.differentFrom.getURI()));
			this.model.getRshpsAsList().addAll(rshps);
			//Describe Inverse of
			rshps = createRSHPS(propertyArtifact, ontProperty.listInverseOf(), new RSHPType(OWL.inverseOf.getURI()));
			this.model.getRshpsAsList().addAll(rshps);			
			//Describe Inverse of
			rshps = createRSHPS(propertyArtifact, ontProperty.listIsDefinedBy(), new RSHPType(Type.DEFINED_BY.name()));
			this.model.getRshpsAsList().addAll(rshps);				
			//FIXME: Only if available-Describe MetaProperties
			if(this.createMetas){
				propertyArtifact.getMetaPropertiesAsList().addAll(createMetaProperties(ontProperty));
			}
		}
		return propertyArtifact;
	}


	private Artifact visit(Individual instance) throws Exception {
		Artifact instanceArtifact = this.instances.get(instance.getURI());
		List<RSHP> rshps;
		List<MetaProperty> metas;
		if(instanceArtifact != null && instance!=null){
			logger.debug(instanceArtifact.getTerm());
			rshps = createRSHPSForInstance(instanceArtifact, instance.listRDFTypes(true), new RSHPType(RDFS.subClassOf.getURI()));
			this.model.getRshpsAsList().addAll(rshps);
			//	rshps = createRSHPS(instanceArtifact, instance.listOntClasses(true), new RSHPType(Type.IS_A.name()));
			//	instanceArtifact.getRshpsAsList().addAll(rshps);
			//Describe different from
			rshps = createRSHPS(instanceArtifact, instance.listDifferentFrom(), new RSHPType(OWL.AllDifferent.getURI()));
			this.model.getRshpsAsList().addAll(rshps);
			if(this.createMetas){
				metas=createMetaProperties(instance);
				instanceArtifact.getMetaPropertiesAsList().addAll(metas);
			}
		}
		return instanceArtifact;
	}


	/**Helper methods**/

	private static MetaProperty createMetaFromProperty(Statement stm) throws URISyntaxException{
		Artifact tag;
		Artifact value;
		Term ttag;
		Term tvalue;
		tag = new Artifact();
		tag.setAbout(new URI(stm.getPredicate().getURI()));
		ttag = new Term();
		ttag.setPrefLabel(OntologyHelper.extractLabelFromURI(stm.getPredicate().getURI()));
		tvalue = new Term();
		value = new Artifact();
		if(stm.getObject().isLiteral()){
			tvalue.setPrefLabel(stm.getObject().asLiteral().getLexicalForm());
			//FIXME: Type of the literal is lost
		}else if(stm.getObject().isResource()){
			if(!stm.getObject().asResource().isAnon()){
				value.setAbout(new URI(stm.getObject().asResource().getURI()));
				tvalue.setPrefLabel(OntologyHelper.extractLabelFromURI(value.getAbout().toString()));
			}else{
				tvalue.setPrefLabel("DEFAULT ANNON ID");
			}
		}else if(stm.getObject().isURIResource()){
			value.setAbout(new URI(stm.getObject().toString()));
			tvalue.setPrefLabel(OntologyHelper.extractLabelFromURI(value.getAbout().toString()));
		}else{
			//Blank id
		}
		tag.setTerm(ttag);
		value.setTerm(tvalue);
		logger.debug("Creating meta with tag: "+ttag.getPrefLabel()+" value: "+tvalue.getPrefLabel());
		return new MetaProperty(tag,value);
	}


	private List<MetaProperty> createMetaProperties(OntResource ontResource) throws URISyntaxException{
		List<MetaProperty> metas = new LinkedList<MetaProperty>();
		StmtIterator it = ontResource.listProperties();
		while(it.hasNext()){
			Statement stm = it.next();
			metas.add(createMetaFromProperty(stm));
		}
		return metas;
	}


	private List<RSHP> createRSHPSForInstance(Artifact instanceArtifact, ExtendedIterator<Resource> listRDFTypes,
			RSHPType rshpType) throws Exception {
		List<RSHP> rshps = new LinkedList<RSHP>();
		Resource relatedResource;
		RSHP rshp;
		Artifact relatedArtifact;
		if(listRDFTypes != null){
			while(listRDFTypes.hasNext()){
				relatedResource = listRDFTypes.next();
				if(!relatedResource.isAnon()){
					relatedArtifact =this.classes.get(relatedResource.getURI());
					if(relatedArtifact==null){
						//OWLNamedIndividual
						relatedArtifact = createArtifactResource(relatedResource);
						this.classes.put(relatedArtifact.getAbout().toString(), relatedArtifact);	
					}
					rshp = new RSHP();
					rshp.setFrom(instanceArtifact);
					rshp.setSemantics(rshpType);
					rshps.add(rshp);
					rshp.setTo(relatedArtifact);
				}
			}	
		}
		return rshps;
	}


	/**Processing OWL constructors
	 * @param rshp **/

	private  void collectRelatedClassDescription(OntClass relatedClass, RSHP rshp) throws ConceptNotFoundException, URISyntaxException {
		if (relatedClass.isUnionClass()) {
			logger.debug("It is union class");
			createBooleanClassDescription(relatedClass.asUnionClass());
		}
		else if (relatedClass.isIntersectionClass()) {
			logger.debug("It is intersection class");
			createBooleanClassDescription(relatedClass.asIntersectionClass());    
		}
		else if (relatedClass.isComplementClass()) {
			logger.debug("It is complement class");
			createBooleanClassDescription(relatedClass.asComplementClass());
		}
		else if (relatedClass.isRestriction()) {
			logger.debug("It is restriction class");
			createRestrictionClassDescription(relatedClass.asRestriction(),rshp);
		}
		else if (!relatedClass.isAnon()) {
			logger.debug("It is user defined class");
			//Artifact relatedConceptDescription = OntologyHelper.createArtifactDescription(relatedClass); 
			//relation.getConceptDescriptions().add(relatedConceptDescription);
			if(rshp != null) {
				rshp.setTo(this.classes.get(relatedClass.getURI()));
			}
		}else{
			logger.debug("It is an ANNON class");
		}
	}


	private void createRestrictionClassDescriptionElem(RDFNode value, RSHP rshp) throws ConceptNotFoundException, URISyntaxException {
		if (value.canAs(OntClass.class)) {  
			logger.debug("The element is an OntClass..."+value.as(OntClass.class));
			this.collectRelatedClassDescription((OntClass) value.as(OntClass.class), rshp);
		}else if (value.canAs(OntProperty.class)) { 
			logger.debug("The element is an OntProperty..."+value.toString());
			this.fill(value.as(OntProperty.class),rshp);
		}else if (value.canAs(Literal.class)) {
			logger.debug("The element is a literal...");
			logger.debug( ((Literal) value).getLexicalForm() );
		}else if (value.canAs(Resource.class)) {
			logger.debug("The element is a resource...");
			logger.debug( ((Resource) value).getURI() );
		} else {
			logger.debug(value);
		}
	}

	private void fill(OntProperty ontProperty, RSHP rshp) throws URISyntaxException {
		if(rshp!=null && rshp.getSemantics()!=null){
			RSHPType type = rshp.getSemantics();
			type.setAbout(new URI(ontProperty.getURI()));
			type.setName(ontProperty.getLocalName());
			if(ontProperty.getDomain()!=null){
				type.setDomain(ontProperty.getDomain().toString());
			}
			if(ontProperty.getRange()!=null){
				type.setRange(ontProperty.getRange().toString());
			}
			type.setFunctionalProperty(ontProperty.isFunctionalProperty());
			type.setTransitiveProperty(ontProperty.isTransitiveProperty());
			type.setSymmetricProperty(ontProperty.isSymmetricProperty());
			type.setAnnotationProperty(ontProperty.isAnnotationProperty());
			type.setObjectProperty(ontProperty.isObjectProperty());
			type.setDataTypeProperty(ontProperty.isDatatypeProperty());
		}
	}

	private RSHP createRestrictionClassDescription(Restriction restriction, RSHP rshp) throws ConceptNotFoundException, URISyntaxException {
		if(rshp==null){
			rshp = new RSHP();
		}
		RSHPType type = createTypeFromRestriction(restriction);
		rshp.setSemantics(type);
		logger.debug("Restriction on property: "+restriction.getOnProperty());
		createRestrictionClassDescriptionElem(restriction.getOnProperty(),rshp);
		if (restriction.isAllValuesFromRestriction()) {
			logger.debug("Restriction is: "+OWL.allValuesFrom.getLocalName());
			createRestrictionClassDescriptionElem(
					restriction.asAllValuesFromRestriction().getAllValuesFrom(),rshp);        
		} else if (restriction.isSomeValuesFromRestriction()) {
			logger.debug("Restriction is: "+OWL.someValuesFrom.getLocalName());
			createRestrictionClassDescriptionElem(restriction.asSomeValuesFromRestriction().getSomeValuesFrom(),rshp);        
		} else if (restriction.isHasValueRestriction()) {
			logger.debug("Restriction is: "+OWL.hasValue.getLocalName());
			createRestrictionClassDescriptionElem(restriction.asHasValueRestriction().getHasValue(),rshp);        
		}else if (restriction.isCardinalityRestriction() || 
				restriction.isMinCardinalityRestriction() ||
				restriction.isMaxCardinalityRestriction()) {
			logger.debug("Cardinality restriction...extracting cardinality.");
			//FIXME
			//rshp.setYValue(extractCardinality(restriction));
		}else{
			logger.debug( "Processing restriction on property:     " + restriction.getPropertyValue(OWL2.onProperty));
			logger.debug( "(1) on class:        " + restriction.getPropertyValue(OWL2.onClass));
			createRestrictionClassDescriptionElem(restriction.getPropertyValue(OWL2.onClass),rshp);   
			//FIXME
			//rshp.setYValue(extractCardinality(restriction));
			logger.debug( "Cardinality has been added...");
		}

		return rshp;
	}

	private int extractCardinality(Restriction restriction){
		int cardinality = -1;
		if(restriction!=null){
			if(restriction.getPropertyValue(OWL2.minQualifiedCardinality)!=null){
				cardinality = restriction.getPropertyValue(OWL2.minQualifiedCardinality).asLiteral().getInt();
			}else if(restriction.getPropertyValue(OWL2.maxQualifiedCardinality)!=null){
				cardinality = restriction.getPropertyValue(OWL2.maxQualifiedCardinality).asLiteral().getInt();
			}else if(restriction.getPropertyValue(OWL2.qualifiedCardinality)!=null){
				cardinality = restriction.getPropertyValue(OWL2.qualifiedCardinality).asLiteral().getInt();
			}else if(restriction.getPropertyValue(OWL.minCardinality)!=null){
				cardinality = restriction.getPropertyValue(OWL.minCardinality).asLiteral().getInt();
			}else if(restriction.getPropertyValue(OWL.maxCardinality)!=null){
				cardinality = restriction.getPropertyValue(OWL.maxCardinality).asLiteral().getInt();
			}else if(restriction.getPropertyValue(OWL.cardinality)!=null){
				cardinality = restriction.getPropertyValue(OWL.cardinality).asLiteral().getInt();
			}else{
				logger.debug("It is not a qualified restriction");
			}
		}
		return cardinality;
	}

	private RSHPType createTypeFromRestriction(Restriction restriction) {
		RSHPType type = new RSHPType();
		if(restriction!=null){
//			type.setAllValuesFrom(restriction.isAllValuesFromRestriction());
//			type.setSomeValuesFrom(restriction.isSomeValuesFromRestriction());
//			type.setMinCardinality(restriction.isMinCardinalityRestriction() || 
//					restriction.getPropertyValue(OWL2.minQualifiedCardinality)!=null);
//			type.setMaxCardinality(restriction.isMaxCardinalityRestriction() || 
//					restriction.getPropertyValue(OWL2.maxQualifiedCardinality)!=null);
//			type.setExactCardinality(restriction.isCardinalityRestriction()|| 
//					restriction.getPropertyValue(OWL2.qualifiedCardinality)!=null);
//			type.setHasValue(restriction.isHasValueRestriction());
		}
		return type;
	}


	private  void createBooleanClassDescription(BooleanClassDescription boolClass) throws ConceptNotFoundException, URISyntaxException {
		Iterator i = boolClass.listOperands();
		while (i.hasNext()) {
			collectRelatedClassDescription((OntClass) i.next(),null);
		}        
	}

	private List<RSHP> createRSHPS(Artifact classArtifact, ExtendedIterator it, RSHPType type) throws ConceptNotFoundException, URISyntaxException{
		List<RSHP> rshps = new LinkedList<RSHP>();
		OntClass relatedClass;
		RSHP rshp;
		OntResource resource;
		Object o;
		if(it != null){
			while(it.hasNext()){
				o = it.next();
				System.out.println(o.getClass());
				resource = (OntResource) o;
				if(resource.canAs(OntClass.class)){
					relatedClass = resource.asClass();
					rshp = new RSHP();
					rshp.setFrom(classArtifact);
					if(!relatedClass.isAnon()){
						rshp.setTo(this.classes.get(relatedClass.getURI()));
						rshp.setSemantics(type);
					}else{
						//COLLECT: Restrictions, etc.
						logger.debug("Trying to create RSHP from Anon class...");
						collectRelatedClassDescription(relatedClass, rshp);
						logger.debug("RSHP has been created and filled...");
					}
					rshps.add(rshp);
				}else if(resource.canAs(OntProperty.class)){
					OntProperty p = resource.asProperty();
					logger.debug("RSHP FROM PROPERTY: "+p.getURI());
				}
			}	
		}
		return rshps;
	}


	/**Concept Builder
	 * @throws URISyntaxException **/

	private Artifact createArtifactResource(Resource ontResource) throws Exception {   
		if(ontResource == null){
			throw new ConceptNotFoundException("Concept can not be built because resource is "+ontResource);
		}
		logger.debug("Tryting to create artifact for resource " +ontResource.getURI());
		Artifact currentArtifactDescription = OntologyHelper.createArtifactDescription(ontResource);
		logger.debug("Created artifact for resource " +currentArtifactDescription.getAbout().toString());
		return currentArtifactDescription;
	}

	private static boolean isOwlInternalClass(OntClass ontClass){
		return (!ontClass.isAnon()) && (ontClass.getURI() != null) && 
				(ontClass.getURI().startsWith(OWL.getURI()));
	}

	private RSHPType visitAsType(OntProperty ontProperty) throws URISyntaxException{
		RSHPType type = null;
		if(ontProperty != null){
			type = new RSHPType();
			type.setAbout(new URI(ontProperty.getRDFType().getURI()));
			type.setName(ontProperty.getLocalName());
			type.setDomain(ontProperty.getDomain().toString());
			type.setRange(ontProperty.getRange().toString());
			type.setFunctionalProperty(ontProperty.isFunctionalProperty());
			type.setTransitiveProperty(ontProperty.isTransitiveProperty());
			type.setSymmetricProperty(ontProperty.isSymmetricProperty());
			type.setAnnotationProperty(ontProperty.isAnnotationProperty());
			type.setObjectProperty(ontProperty.isObjectProperty());
			type.setDataTypeProperty(ontProperty.isDatatypeProperty());
		}
		return type;
	}


	/**
	 * To be extracted out
	 * @param args
	 */
	public static void main(String []args){
		String format = "TURTLE";
		InputStream is = Thread.currentThread().getContextClassLoader().
				getResourceAsStream("test_ontologies/cp-11.ttl");
		OntDocumentManager dm = OntDocumentManager.getInstance();
		dm.setProcessImports(false);
		OntModelSpec spec = new OntModelSpec(OntModelSpec.OWL_DL_MEM);
		spec.setDocumentManager(dm);        
		//spec.setReasoner(reasoner);
		OntModel ontModel = ModelFactory.createOntologyModel( spec, null );
		ontModel.read(is,"",format);
		try {
			StmtIterator it = ontModel.listStatements();
			System.out.println("STATEMENTS");
			Statement st;
			while(it.hasNext()){
				st = it.next();
				if(st.getSubject().isAnon() || st.getObject().isAnon()){
					
				}else{
					System.out.println(it.next());	
				}
				
			}
			System.out.println("REIFIED STATEMENTS");
			RSIterator it2 = ontModel.listReifiedStatements();
			while(it2.hasNext()){
				System.out.println(it2.next());
			}
			
			
		}  catch (Exception e) {
			logger.error(e);
		}

	}
}
