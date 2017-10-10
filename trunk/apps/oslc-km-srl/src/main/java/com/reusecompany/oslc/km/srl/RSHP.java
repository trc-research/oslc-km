package com.reusecompany.oslc.km.srl;

import java.net.URI;
import java.util.Arrays;

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

import com.reusecompany.oslc.km.vocabs.SKOS;
import com.reusecompany.oslc.km.vocabs.SRL;

@OslcNamespace(SRL.NAMESPACE)
@OslcName(SRL.RSHP)
@OslcResourceShape(title = "RSHP Relationship Resource Shape", 
	describes =  SKOS.CONCEPT)
public class RSHP extends AbstractResource {

	URI uri;
	Artifact from = Artifact.EMPTY_ARTIFACT; //FIXME: list
	Artifact to  = Artifact.EMPTY_ARTIFACT; //FIXME: list
	RSHPType semantics;
	RSHPType subType;

	URI serviceProvider;
	String identifier = "";
	
	int xvalue = 1;
	int yvalue = 1;
	

	public RSHP() {
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

	public URI getUri() {
		return uri;
	}
	public void setUri(URI uri) {
		this.uri = uri;
	}

	@OslcDescription("Describes from where the relationship is created.")
	@OslcPropertyDefinition(SRL.NAMESPACE + "from")
	@OslcReadOnly
	@OslcRepresentation(Representation.Inline)
	@OslcTitle("Element in concept order 1")
	@OslcName("from")
	@OslcValueShape(OslcConstants.PATH_RESOURCE_SHAPES + "/" + OslcConstants.PATH_PUBLISHER)
	@OslcValueType(ValueType.LocalResource)
	public Artifact getFrom() {
		return from;
	}
	public void setFrom(Artifact from) {
		this.from = from;
	}
	@OslcDescription("Describes to which element the relationship finishes.")
	@OslcPropertyDefinition(SRL.NAMESPACE + "to")
	@OslcReadOnly
	@OslcRepresentation(Representation.Inline)
	@OslcTitle("Element in concept order 2")
	@OslcName("to")
	@OslcValueShape(OslcConstants.PATH_RESOURCE_SHAPES + "/" + OslcConstants.PATH_PUBLISHER)
	@OslcValueType(ValueType.LocalResource)
	public Artifact getTo() {
		return to;
	}
	public void setTo(Artifact to) {
		this.to = to;
	}

	@OslcDescription("Describes the semantics of the relationship.")
	@OslcPropertyDefinition(SRL.NAMESPACE + "semantics")
	@OslcReadOnly
	@OslcRepresentation(Representation.Inline)
	@OslcTitle("Semantics of the relationship")
	@OslcName("semantics")
	@OslcValueShape(OslcConstants.PATH_RESOURCE_SHAPES + "/" + OslcConstants.PATH_PUBLISHER)
	@OslcValueType(ValueType.LocalResource)
	public RSHPType getSemantics() {
		return semantics;
	}
	public void setSemantics(RSHPType semantics) {
		this.semantics = semantics;
	}

	@OslcDescription("Describes the (sub) semantics of the relationship.")
	@OslcPropertyDefinition(SRL.NAMESPACE + "subType")
	@OslcReadOnly
	@OslcRepresentation(Representation.Inline)
	@OslcTitle("Sub-Semantics of the relationship")
	@OslcName("subType")
	@OslcValueShape(OslcConstants.PATH_RESOURCE_SHAPES + "/" + OslcConstants.PATH_PUBLISHER)
	@OslcValueType(ValueType.LocalResource)
	public RSHPType getSubType() {
		return subType;
	}
	public void setSubType(RSHPType subType) {
		this.subType = subType;
	}
	
	
	@Override
	public String toString() {
		return "RSHP [from=" + from.getAbout() + ", to=" + to.getAbout()+" with type "+this.semantics+", yvalue="+ yvalue+"]";
	}
	
	@OslcDescription("A cardinalty value for the left-hand side of the relationship.")
	@OslcOccurs(Occurs.ZeroOrOne)
	@OslcPropertyDefinition(SRL.NAMESPACE + "xvalue")
	@OslcReadOnly
	@OslcTitle("Xvalue")
	@OslcValueType(ValueType.Integer)
	public int getXValue() {
		return xvalue;
	}

	public void setXValue(int xvalue) {
		this.xvalue = xvalue;
	}
	@OslcDescription("A cardinalty value for the right-hand side of the relationship.")
	@OslcOccurs(Occurs.ZeroOrOne)
	@OslcPropertyDefinition(SRL.NAMESPACE + "yvalue")
	@OslcReadOnly
	@OslcTitle("Yvalue")
	@OslcValueType(ValueType.Integer)
	public int getYValue() {
		return yvalue;
	}

	public void setYValue(int yvalue) {
		this.yvalue = yvalue;
	}
	
}


