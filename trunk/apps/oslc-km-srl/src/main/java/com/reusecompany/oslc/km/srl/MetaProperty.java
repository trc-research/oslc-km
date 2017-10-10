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
@OslcName(SRL.META_PROPERTY)
@OslcResourceShape(title = "RSHP Metaproperty Resource Shape", describes = SKOS.CONCEPT)
public class MetaProperty extends AbstractResource{
	 Artifact tag;
     Artifact value;

     String identifier = "";
     URI serviceProvider;
     URI baseURI;

     public MetaProperty() {
     
     }
     public MetaProperty(Artifact tag, Artifact value) {
         this.tag = tag;
         this.value = value;
     }

     public MetaProperty(String tag, Artifact value) {
         this.tag = new Artifact(tag);
         this.value = value;
     }
     
     public MetaProperty(String tag, String value) {
         this.tag = new Artifact(tag);
         this.value = new Artifact(value);
     }


   
     @OslcDescription("The scope of a resource is a URI for the resource's OSLC Service Provider.")
     @OslcOccurs(Occurs.ExactlyOne)
     @OslcPropertyDefinition(OslcConstants.OSLC_CORE_NAMESPACE + "serviceProvider")
     @OslcRange(OslcConstants.TYPE_SERVICE_PROVIDER)
     @OslcReadOnly
     @OslcTitle("Service Provider")
     public URI GetServiceProvider() {
         return serviceProvider;
     }
     public void SetServiceProvider(URI value) {
         this.serviceProvider = value;
     }

     @OslcDescription("A unique identifier for a resource. Assigned by the service provider when a resource is created. Not intended for end-user display.")
     @OslcOccurs(Occurs.ExactlyOne)
     @OslcPropertyDefinition(OslcConstants.DCTERMS_NAMESPACE + "identifier")
     @OslcReadOnly
     @OslcTitle("Identifier")
     public String GetIdentifier() {
         return identifier;
     }

     public void SetIdentifier(String identifier) {
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


     @OslcDescription("Describes the value of the tag.")
     @OslcPropertyDefinition(SRL.NAMESPACE + "value")
     @OslcReadOnly
     @OslcRepresentation(Representation.Inline)
     @OslcTitle("Value of the tag")
     @OslcValueShape(OslcConstants.PATH_RESOURCE_SHAPES + "/" + OslcConstants.PATH_PUBLISHER)
     @OslcValueType(ValueType.LocalResource)
     public Artifact GetValue() {
         return this.value;
     }

     public void SetValue(Artifact value) {
         this.value = value;
     }

      public void SetTag(Artifact tag) {
         this.tag = tag;
     }

     @OslcDescription("Describes the tag.")
     @OslcPropertyDefinition(SRL.NAMESPACE + "tag")
     @OslcReadOnly
     @OslcRepresentation(Representation.Inline)
     @OslcTitle("The tag used as metadata.")
     @OslcValueShape(OslcConstants.PATH_RESOURCE_SHAPES + "/" + OslcConstants.PATH_PUBLISHER)
     @OslcValueType(ValueType.LocalResource)
     public Artifact GetTag() {
         return this.tag;
     }
	@Override
	public String toString() {
		return "MetaProperty [tag=" + ("uri:"+tag.getAbout()+",term: "+tag.getTerm()+")") + ", value=" + ("uri:"+value.getAbout()+",term: "+value.getTerm()+")") + "]";
	}

}
