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
import org.eclipse.lyo.oslc4j.core.annotation.OslcResourceShape;
import org.eclipse.lyo.oslc4j.core.annotation.OslcTitle;
import org.eclipse.lyo.oslc4j.core.model.AbstractResource;
import org.eclipse.lyo.oslc4j.core.model.Occurs;
import org.eclipse.lyo.oslc4j.core.model.OslcConstants;

import com.reusecompany.oslc.km.vocabs.SKOS;
import com.reusecompany.oslc.km.vocabs.SRL;

@OslcNamespace(SRL.NAMESPACE)
@OslcName(SRL.ARTIFACT_TYPE)
@OslcResourceShape(title = "RHSP Artifact Type Resource Shape", describes = SKOS.CONCEPT)
public class ArtifactType extends AbstractResource{

	String name = "";
	String identifier = "";
	URI serviceProvider;


	public ArtifactType() {
	}

	public ArtifactType(String name) {
		this.name = name;
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



	@OslcDescription("Name of the artifact type.")
	@OslcOccurs(Occurs.ExactlyOne)
	@OslcPropertyDefinition(SRL.NAMESPACE + "name")
	@OslcReadOnly
	@OslcTitle("Name of the artifact type.")
	@OslcName("name")
	public String getName() {
		return this.name;
	}
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "ArtifactType [name=" + name + ", identifier=" + identifier + "]";
	}


}
