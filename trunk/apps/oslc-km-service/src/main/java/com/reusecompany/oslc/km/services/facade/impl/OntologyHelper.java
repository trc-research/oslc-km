package com.reusecompany.oslc.km.services.facade.impl;



import java.net.URI;
import java.net.URISyntaxException;
import java.util.Iterator;

import org.apache.log4j.Logger;

import com.hp.hpl.jena.ontology.Individual;
import com.hp.hpl.jena.ontology.OntClass;
import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.ontology.OntResource;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.rdf.model.Statement;
import com.hp.hpl.jena.shared.PrefixMapping;
import com.hp.hpl.jena.vocabulary.RDFS;
import com.reusecompany.oslc.km.exceptions.ConceptNotFoundException;
import com.reusecompany.oslc.km.srl.Artifact;
import com.reusecompany.oslc.km.srl.ArtifactType;
import com.reusecompany.oslc.km.srl.Term;

public abstract class OntologyHelper {

	public static Artifact createArtifactResource(Resource ontResource) throws Exception {   
		if(ontResource == null){
			throw new ConceptNotFoundException("Concept can not be built because resource is "+ontResource);
		}
		Artifact currentArtifactDescription = OntologyHelper.createArtifactDescription(ontResource);
		return currentArtifactDescription;
	}
	
	
	
	public static boolean isUserConcept(OntResource ontResource) {
		if(ontResource.canAs(OntClass.class)){
			OntClass ontClass = ontResource.asClass();
			return  (!ontClass.isRestriction() && !ontClass.isAnon());
		}
		return  (ontResource.isIndividual());
	}

	/**
	 * This method checks if a class is restriction type, and if the restriction cames
	 * from a certain property, and if the property has a certain name
	 * 
	 * @param ontClass The class to study
	 * @param propertyUri The property to define
	 * @return The concept populated with the info in the class or null is the class
	 * is not a restriction or it is not related by the property
	 */
	public static boolean checkRestriction(OntClass ontClass, String propertyUri) {        
		return ontClass.isRestriction() &&
				ontClass.asRestriction().isSomeValuesFromRestriction() &&                    
				ontClass.asRestriction().getOnProperty().getURI().equals(propertyUri);
	}       


	public static String getPublicName(Resource resource) {
		Statement labelProp = resource.getProperty(RDFS.label);
		if (labelProp != null && labelProp.getString().length() > 0) {
			return labelProp.getString();
		} else {
			//return (resource.getURI()!=null?prefixesFor(resource).shortForm(resource.getURI()):"Not available");
			return (resource.getURI()!=null?extractLabelFromURI(resource.getURI()):"Not available");
		}
	}

	public static String prettifyURI(String uri){
		return (uri!=null?extractLabelFromURI(uri):"Not available");
	}
	public static String getDescription(Resource resource) {
		Statement commentProp = resource.getProperty(RDFS.comment);
		if (commentProp != null) {
			return commentProp.getString();
		} else {
			//return (resource.getURI()!=null?prefixesFor(resource).shortForm(resource.getURI()):"Not available");
			return (resource.getURI()!=null?extractLabelFromURI(resource.getURI()):"Not available");
		}
	}

	public static Artifact createArtifactDescription(Resource concept) throws URISyntaxException{
		String name = OntologyHelper.getPublicName(concept);
		String description = OntologyHelper.getPublicName(concept);
		String uri = concept.getURI();
		//String type = concept.getClass().getSimpleName();
		
		Artifact artifactDescription = new Artifact();
		artifactDescription.setTerm(new Term(name));
		artifactDescription.setDescription(description);
		try {
			artifactDescription.setAbout(new URI(uri));
		} catch (URISyntaxException e) {
			throw new IllegalArgumentException("Parameter should be a valid URI  " + uri);
		}
		//artifactDescription.setArtifactType(new ArtifactType(type));     
		return artifactDescription;
	}


	public static String getSubstringAfterSharp(String uri) {
		if (uri.lastIndexOf('#') == -1) {
			throw new IllegalArgumentException(
					"Parameter should be an URI with the '#' chararacter, but is " +
							uri);
		}

		return uri.substring(uri.lastIndexOf('#'),uri.length());
	}

	protected static PrefixMapping prefixesFor( Resource n ) {
		return n.getModel().getGraph().getPrefixMapping();
	}


	public static void showStats(OntModel ontModel, org.apache.log4j.Logger logger){
		logger.info("OntModel loaded: ");
		logger.info("All different: "+ontModel.listAllDifferent().toList().size());
		//        showIterator(ontModel.listAllDifferent(),logger,"\t All ");
		logger.info("Data Ranges: "+ontModel.listDataRanges().toList().size());
		//        showIterator(ontModel.listDataRanges(),logger,"\t Data Ranges ");
		logger.info("Annotations Properties: "+ontModel.listAnnotationProperties().toList().size());
		showIterator(ontModel.listAnnotationProperties(),logger,"\t Annotation ");
		logger.info("Importing ontologies: "+ontModel.listImportedOntologyURIs().size());
		for(Iterator it = ontModel.listImportedOntologyURIs().iterator(); it.hasNext();){
			logger.info("\t Ontology "+it.next().toString());
		}
		logger.info("Ontologies "+ontModel.listOntologies().toList().size());
		showIterator(ontModel.listOntologies(),logger,"\t Ontologies ");
		logger.info("Classes: "+ontModel.listClasses().toList().size());
		//        showIterator(ontModel.listClasses(),logger,"\t OntClass ");
		logger.info("\tEnumerated Classes: "+ontModel.listEnumeratedClasses().toList().size());
		//        showIterator(ontModel.listEnumeratedClasses(),logger,"\t\t Enumerated ");
		logger.info("\tBoolean Union Classes: "+ontModel.listUnionClasses().toList().size());
		//        showIterator(ontModel.listUnionClasses(),logger,"\t\t Union ");
		logger.info("\tBoolean Complement Classes: "+ontModel.listComplementClasses().toList().size());
		//        showIterator(ontModel.listComplementClasses(),logger,"\t\t Complement ");
		logger.info("\tBoolean Intersection Classes: "+ontModel.listIntersectionClasses().toList().size());
		//        showIterator(ontModel.listIntersectionClasses(),logger,"\t\t Intersection ");
		logger.info("\tRestrictions "+ontModel.listRestrictions().toList().size());
		//        showIterator(ontModel.listRestrictions(),logger,"\t\t Restriction ");
		logger.info("Instances: "+ontModel.listIndividuals().toList().size());
		//showIterator(ontModel.listIndividuals(),logger,"\t\t Instance ", printer);
		logger.info("OntProperties "+ontModel.listOntProperties().toList().size());
		//        showIterator(ontModel.listOntProperties(),logger,"\t\t OntProperty ");               
	}

	public static void showIterator(Iterator iterator,Logger logger,String ident){
		OntResource ontResource;
		for(;iterator.hasNext();){            
			ontResource = (OntResource) iterator.next();            
			logger.info(ident+"Resource "+ontResource.getURI()+"  "+ontResource.getNameSpace());
		}
	}

	public static String extractLabelFromURI(String uri) {
		if (uri.lastIndexOf('#') == -1) {
			if(uri.lastIndexOf('/') == -1){
				return "DEFAULT";
			}else{
				return uri.substring(uri.lastIndexOf('/')+1,uri.length());
			}
		}else{
			return uri.substring(uri.lastIndexOf('#')+1,uri.length());
		}
	}
}
