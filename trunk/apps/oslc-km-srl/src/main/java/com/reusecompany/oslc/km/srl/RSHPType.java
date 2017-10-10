package com.reusecompany.oslc.km.srl;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import org.eclipse.lyo.oslc4j.core.annotation.OslcDescription;
import org.eclipse.lyo.oslc4j.core.annotation.OslcName;
import org.eclipse.lyo.oslc4j.core.annotation.OslcNamespace;
import org.eclipse.lyo.oslc4j.core.annotation.OslcOccurs;
import org.eclipse.lyo.oslc4j.core.annotation.OslcPropertyDefinition;
import org.eclipse.lyo.oslc4j.core.annotation.OslcRange;
import org.eclipse.lyo.oslc4j.core.annotation.OslcReadOnly;
import org.eclipse.lyo.oslc4j.core.annotation.OslcRepresentation;
import org.eclipse.lyo.oslc4j.core.annotation.OslcResourceShape;
import org.eclipse.lyo.oslc4j.core.annotation.OslcTitle;
import org.eclipse.lyo.oslc4j.core.annotation.OslcValueShape;
import org.eclipse.lyo.oslc4j.core.annotation.OslcValueType;
import org.eclipse.lyo.oslc4j.core.model.AbstractResource;
import org.eclipse.lyo.oslc4j.core.model.Occurs;
import org.eclipse.lyo.oslc4j.core.model.OslcConstants;
import org.eclipse.lyo.oslc4j.core.model.Representation;
import org.eclipse.lyo.oslc4j.core.model.ValueType;

import com.hp.hpl.jena.vocabulary.RDFS;
import com.reusecompany.oslc.km.vocabs.SKOS;
import com.reusecompany.oslc.km.vocabs.SRL;

@OslcNamespace(SRL.NAMESPACE)
@OslcName(SRL.SEMANTIC_CLUSTER)
@OslcResourceShape(title = "RSHP Semantics Resource Shape", describes = SKOS.CONCEPT )
public class RSHPType extends AbstractResource {
	String name = "";;
	String type = "";
	String domain = RDFS.Resource.getURI().toString();
	String range = RDFS.Resource.getURI().toString();
	Term term;
	Artifact artifact;


	private String identifier = "";
	private URI serviceProvider;

	
	/**Inherited from OWL properties**/
	boolean AnnotationProperty = false;
	boolean DataRange = false;
	boolean DataTypeProperty = false;
	boolean FunctionalProperty = false;
	boolean InverseFunctionalProperty = false;
	boolean ObjectProperty = false;
	boolean SymmetricProperty = false;
	boolean TransitiveProperty = false;


	public RSHPType() {

	}
	
	public RSHPType(String name, String uri) {
		this.term = new Term(name);
		try {
			this.setAbout(new URI(uri));
		} catch (URISyntaxException e) {
			//Not should be here
		}
	}
	

	public RSHPType(String name) {
		this.term = new Term(name);
	}
	
	public RSHPType(Artifact ke) {
		this.name = ke.getAbout().toString();
		this.term = ke.getTerm();
	}

	@OslcDescription("The scope of a resource is a URI for the resource's OSLC Service Provider.")
	@OslcOccurs(Occurs.ExactlyOne)
	@OslcPropertyDefinition(OslcConstants.OSLC_CORE_NAMESPACE + "serviceProvider")
	@OslcRange(OslcConstants.TYPE_SERVICE_PROVIDER)
	@OslcReadOnly
	@OslcTitle("Service Provider")
	public URI getServiceProvider() {
		return serviceProvider;
	}
	public void setServiceProvider(URI value) {
		this.serviceProvider = value;
	}

	@OslcDescription("A unique identifier for a resource. Assigned by the service provider when a resource is created. Not intended for end-user display.")
	@OslcOccurs(Occurs.ExactlyOne)
	@OslcPropertyDefinition(OslcConstants.DCTERMS_NAMESPACE + "identifier")
	@OslcReadOnly
	@OslcTitle("Identifier")
	public String getIdentifier() {
		return identifier;
	}

	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}


	@OslcDescription("The resource type URIs.")
	@OslcName("type")
	@OslcPropertyDefinition(OslcConstants.RDF_NAMESPACE + "type")
	@OslcTitle("Types")
	public URI[] getRdfTypes() {
		return this.getTypes().toArray(new URI[this.getTypes().size()]);
	}

	public void setRdfTypes(URI[] rdfTypes) {
		this.getTypes().addAll(Arrays.asList(rdfTypes));
	}



	@OslcDescription("The term that describes this semantic cluster.")
	@OslcPropertyDefinition(SRL.NAMESPACE + "term")
	@OslcReadOnly
	@OslcRepresentation(Representation.Inline)
	@OslcTitle("The Term of the Semantic Cluster")
	@OslcValueShape(OslcConstants.PATH_RESOURCE_SHAPES + "/" + OslcConstants.PATH_PUBLISHER)
	@OslcValueType(ValueType.LocalResource)
	public Term getTerm() {
		return term;
	}

	public void setTerm(Term term) {
		this.term = term;
	}

	public void setName(String uri) {
		this.name = uri;

	}

	@OslcDescription("Kind of Semantics")
	@OslcOccurs(Occurs.ExactlyOne)
	@OslcPropertyDefinition(SRL.NAMESPACE + "name")
	@OslcReadOnly
	@OslcTitle("Kind of Semantics")
	@OslcName("name")
	public String getName() {
		return name;
	}


	@OslcDescription("True if the semantics is an annotation property, false otherwise.")
	@OslcOccurs(Occurs.ExactlyOne)
	@OslcPropertyDefinition(SRL.NAMESPACE + "AnnotationProperty")
	@OslcReadOnly
	@OslcTitle("Annotation Property")
	@OslcName("AnnotationProperty")
	public boolean getAnnotationProperty() {
		return this.AnnotationProperty;
	}
	public void setAnnotationProperty(boolean isAnnotationProperty) {
		this.AnnotationProperty = isAnnotationProperty;
	}

	@OslcDescription("True if the semantics is a data range property, false otherwise.")
	@OslcOccurs(Occurs.ExactlyOne)
	@OslcPropertyDefinition(SRL.NAMESPACE + "DataRange")
	@OslcReadOnly
	@OslcTitle("Data Range")
	@OslcName("DataRange")
	public boolean getDataRange() {
		return this.DataRange;
	}
	public void setDataRange(boolean isDataRange) {
		this.DataRange = isDataRange;
	}

	@OslcDescription("True if the semantics is a data type property, false otherwise.")
	@OslcOccurs(Occurs.ExactlyOne)
	@OslcPropertyDefinition(SRL.NAMESPACE + "DataTypeProperty")
	@OslcReadOnly
	@OslcTitle("Data Type Property")
	@OslcName("DataTypeProperty")
	public boolean getDataTypeProperty() {
		return this.DataTypeProperty;
	}
	public void setDataTypeProperty(boolean isDataTypeProperty) {
		this.DataTypeProperty = isDataTypeProperty;
	}


	@OslcDescription("True if the semantics is a functional property, false otherwise.")
	@OslcOccurs(Occurs.ExactlyOne)
	@OslcPropertyDefinition(SRL.NAMESPACE + "FunctionalProperty")
	@OslcReadOnly
	@OslcTitle("Functional Property")
	@OslcName("FunctionalProperty")
	public boolean getFunctionalProperty() {
		return this.FunctionalProperty;
	}
	public void setFunctionalProperty(boolean isFunctionalProperty) {
		this.FunctionalProperty = isFunctionalProperty;
	}


	@OslcDescription("True if the semantics is an inverse functional property, false otherwise.")
	@OslcOccurs(Occurs.ExactlyOne)
	@OslcPropertyDefinition(SRL.NAMESPACE + "InverseFunctionalProperty")
	@OslcReadOnly
	@OslcTitle("Inverse Functional Property")
	@OslcName("InverseFunctionalProperty")
	public boolean getInverseFunctionalProperty() {
		return this.InverseFunctionalProperty;
	}
	public void setInverseFunctionalProperty(boolean isInverseFunctionalProperty) {
		this.InverseFunctionalProperty = isInverseFunctionalProperty;
	}

	@OslcDescription("True if the semantics is an object property, false otherwise.")
	@OslcOccurs(Occurs.ExactlyOne)
	@OslcPropertyDefinition(SRL.NAMESPACE + "ObjectProperty")
	@OslcReadOnly
	@OslcTitle("Object Property")
	@OslcName("ObjectProperty")
	public boolean getObjectProperty() {
		return this.ObjectProperty;
	}
	public void setObjectProperty(boolean isObjectProperty) {
		this.ObjectProperty = isObjectProperty;
	}

	@OslcDescription("True if the semantics is an symmetric property, false otherwise.")
	@OslcOccurs(Occurs.ExactlyOne)
	@OslcPropertyDefinition(SRL.NAMESPACE + "SymmetricProperty")
	@OslcReadOnly
	@OslcTitle("Symmetric Property")
	@OslcName("SymmetricProperty")
	public boolean getSymmetricProperty() {
		return this.SymmetricProperty;
	}
	public void setSymmetricProperty(boolean isSymmetricProperty) {
		this.SymmetricProperty = isSymmetricProperty;
	}

	@OslcDescription("True if the semantics is an transitive property, false otherwise.")
	@OslcOccurs(Occurs.ExactlyOne)
	@OslcPropertyDefinition(SRL.NAMESPACE + "TransitiveProperty")
	@OslcReadOnly
	@OslcTitle("Transitive Property")
	@OslcName("TransitiveProperty")
	public boolean getTransitiveProperty() {
		return this.TransitiveProperty;
	}
	public void setTransitiveProperty(boolean isTransitiveProperty) {
		this.TransitiveProperty = isTransitiveProperty;
	}
	
	
	@OslcDescription("The URI of the domain.")
	@OslcOccurs(Occurs.ExactlyOne)
	@OslcPropertyDefinition(SRL.NAMESPACE + "domain")
	@OslcReadOnly
	@OslcTitle("Domain")
	public String getDomain() {
		return this.domain;
	}
	public void setDomain(String domain) {
		this.domain = domain;
	}

	@OslcDescription("The URI of the range.")
	@OslcOccurs(Occurs.ExactlyOne)
	@OslcPropertyDefinition(SRL.NAMESPACE + "range")
	@OslcReadOnly
	@OslcTitle("Range")
	public String getRange() {
		return this.range;
	}

	public void setRange(String range) {
		this.range = range;
	}
	

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@OslcDescription("Describes the element of this semantics.")
	@OslcPropertyDefinition(SRL.NAMESPACE + "artifact")
	@OslcReadOnly
	@OslcRepresentation(Representation.Inline)
	@OslcTitle("The element or resource of this semantics")
	@OslcValueShape(OslcConstants.PATH_RESOURCE_SHAPES + "/" + OslcConstants.PATH_PUBLISHER)
	@OslcValueType(ValueType.LocalResource)
	public Artifact getArtifact() {
		return this.artifact;
	}

	public void setArtifact(Artifact artifact) {
		this.artifact = artifact;
	}

	@Override
	public String toString() {
		return "RSHPType [term=" + term.getPrefLabel() + ", type=" + type + "]";
	}


}
