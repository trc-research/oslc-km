package com.reusecompany.oslc.km.srl;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.Collections;
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

import com.hp.hpl.jena.rdf.model.SimpleSelector;
import com.hp.hpl.jena.rdf.model.StmtIterator;
import com.reusecompany.oslc.km.vocabs.SRL;

@OslcNamespace(SRL.NAMESPACE)
@OslcName(SRL.ARTIFACT)
@OslcResourceShape(title = "SRL Artifact Resource Shape", 
describes = SRL.NAMESPACE + SRL.ARTIFACT )
public class Artifact extends AbstractResource {
	public static final Artifact EMPTY_ARTIFACT = new Artifact("empty");
	/**Properties as KE**/
	Term term;
	boolean ke = false;
	
	/**Properties as Artifact**/
	ArtifactType artifactType = DEFAULT_ARTIFACT_TYPE;
	List<RSHP> rshps = new LinkedList<RSHP>();
	List<MetaProperty> metaproperties = new LinkedList<MetaProperty>();
	List<Artifact> ownedArtifacts = new LinkedList<Artifact>();
	
	/**Properties to access an Artifact**/
	URI fromURI = null;
	URI sparqlEndpoint = null;
	String rdfContent = null;
	
	/**OSLC Core properties**/
	private URI serviceProvider;
	private String identifier = "";
	private String title = "";
	private String description = "";
	private URI preferredVisualization;
	
	public static ArtifactType DEFAULT_ARTIFACT_TYPE = null;
	
	static{
		DEFAULT_ARTIFACT_TYPE = new ArtifactType();
		try {
			DEFAULT_ARTIFACT_TYPE.setAbout(new URI(SRL.NAMESPACE));
		} catch (URISyntaxException e) {
		
		}
		DEFAULT_ARTIFACT_TYPE.setIdentifier("19");
		DEFAULT_ARTIFACT_TYPE.setName("DEFAULT");
	}
	
	public Artifact() {
	}
	
	public Artifact(Term term) {
		this.term = term;
	}

	public Artifact(String term) {
		this.term = new Term();
		this.term.setPrefLabel(term);
	}


	public Artifact(boolean isKE, URI uri) {
		this.ke = isKE;
		this.setAbout(uri);
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


	@OslcDescription("The resource type URIs.")
	@OslcOccurs(Occurs.ZeroOrMany)
	@OslcName("owned-artifacts")
	@OslcPropertyDefinition(SRL.NAMESPACE + "owned-artifacts")
	@OslcTitle("List of subartifacts")
	public Artifact[] getOwnedArtifacts() {
		return this.ownedArtifacts.toArray(new Artifact[this.ownedArtifacts.size()]);
	}

	public List<Artifact> getOwnedArtifactsAsList() {
		return this.ownedArtifacts;
	}
	
	public void setOwnedArtifactsAsList( List<Artifact> ownedArtifacts) {
		if(ownedArtifacts != null){
			this.ownedArtifacts = ownedArtifacts;
		}
	}
	
	public void setOwnedArtifacts(Artifact[] ownedArtifacts) {
		this.ownedArtifacts.addAll(Arrays.asList(ownedArtifacts));
	}
	
	@OslcDescription("The visualization of this artifact.")
	@OslcOccurs(Occurs.ExactlyOne)
	@OslcPropertyDefinition(SRL.NAMESPACE + "preferred-visualization")
	@OslcReadOnly
	@OslcName("preferred-visualization")
	@OslcTitle("Graphical view of this artifact.")
	public URI getPreferredVisualization() {
		//FIXME:
//		URI outURI;
//		if (ImageLoader.pictures.TrygetValue(this.identifier, out outURI)) {
//			return outURI;
//		}
		return this.preferredVisualization;
	}

	public void setPreferredVisualization(URI URI) {
		this.preferredVisualization = URI;
	}


	@OslcDescription("The URI from which external content is read.")
	@OslcOccurs(Occurs.ExactlyOne)
	@OslcPropertyDefinition(SRL.NAMESPACE + "from-URI")
	@OslcReadOnly
	@OslcName("from-URI")
	@OslcTitle("URI to read external content.")
	public URI getFromURI() {
		return this.fromURI;
	}

	public void setFromURI(URI URI){
		this.fromURI = URI;
	}



	@OslcDescription("The SPARQL endpoint from which external content is read.")
	@OslcOccurs(Occurs.ExactlyOne)
	@OslcPropertyDefinition(SRL.NAMESPACE + "sparql-endpoint")
	@OslcReadOnly
	@OslcTitle("URI to read external content.")
	public URI getSparqlEndpoint() {
		return this.sparqlEndpoint;
	}

	public void setSparqlEndpoint(URI URI) {
		this.sparqlEndpoint = URI;
	}


	@OslcDescription("The RDF content of a resource.")
	@OslcOccurs(Occurs.ExactlyOne)
	@OslcPropertyDefinition(SRL.NAMESPACE + "value")
	@OslcReadOnly
	@OslcTitle("The RDF content of a resource.")
	public String getRdfContent() {
		return this.rdfContent;
	}

	public void setRdfContent(String rdfContent) {
		this.rdfContent = rdfContent;
	}

	@OslcDescription("The title of an artifact.")
	@OslcOccurs(Occurs.ExactlyOne)
	@OslcPropertyDefinition(SRL.NAMESPACE + "title")
	@OslcReadOnly
	@OslcTitle("The title of an artifact.")
	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@OslcDescription("The description of an artifact.")
	@OslcOccurs(Occurs.ExactlyOne)
	@OslcPropertyDefinition(SRL.NAMESPACE + "description")
	@OslcReadOnly
	@OslcTitle("The description of an artifact.")
	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@OslcDescription("Relationships")
	@OslcPropertyDefinition(SRL.NAMESPACE + "rshps")
	@OslcReadOnly
	@OslcRepresentation(Representation.Inline)
	@OslcTitle("Relationships")
	@OslcName("rshps")
	@OslcValueType(ValueType.LocalResource)
	public RSHP[] getRshps() {
		return this.rshps.toArray(new RSHP[this.rshps.size()]);
	}
	public void setRshps(RSHP[] rshps) {
		this.rshps.clear();
		this.rshps.addAll(Arrays.asList(rshps));
	}

	public void setRshpsAsList(List<RSHP> rshps) {
		this.rshps.clear();
		this.rshps = null;
		this.rshps = rshps;
	}


	public List<RSHP> getRshpsAsList() {
		return this.rshps;
	}

	@OslcDescription("Artifact Type")
	@OslcPropertyDefinition(SRL.NAMESPACE + "artifact-type")
	@OslcReadOnly
	@OslcRepresentation(Representation.Inline)
	@OslcTitle("Artifact Type")
	@OslcName("artifact-type")
	@OslcValueType(ValueType.LocalResource)
	public ArtifactType getArtifactType() {
		return this.artifactType;
	}

	public void setArtifactType(ArtifactType artifactType) {
		this.artifactType = artifactType;
	}


	@OslcDescription("Artifact term")
	@OslcPropertyDefinition(SRL.NAMESPACE + "term")
	@OslcReadOnly
	@OslcRepresentation(Representation.Inline)
	@OslcTitle("Artifact term")
	@OslcValueType(ValueType.LocalResource)
	public Term getTerm() {
		return term;
	}

	public void setTerm(Term term) {
		this.term = term;
	}

	@OslcDescription("The list of metaproperties in this knowledge element.")
	@OslcPropertyDefinition(SRL.NAMESPACE + "metaproperties")
	@OslcReadOnly
	@OslcRepresentation(Representation.Inline)
	@OslcTitle("The Metaproperties")
	@OslcValueShape(OslcConstants.PATH_RESOURCE_SHAPES + "/" + OslcConstants.PATH_PUBLISHER)
	@OslcValueType(ValueType.LocalResource)
	public MetaProperty[] getMetaproperties() {
		return this.metaproperties.toArray(new MetaProperty[this.metaproperties.size()]);
	}
	public void setMetaproperties(MetaProperty[] metaproperties) {
		this.metaproperties.clear();
		this.metaproperties.addAll(Arrays.asList(metaproperties));
	}

	public List<MetaProperty> getMetaPropertiesAsList() {
		return this.metaproperties;
	}
	public void setMetapropertiesAsList(List<MetaProperty> metaproperties) {
		this.metaproperties = metaproperties;
	}

	public boolean isKE() {
		return this.ke;
	}

	@Override
	public String toString() {
		return "Artifact, uri: "+(this.getAbout()!=null?this.getAbout().toString():"")+", [term=" + term + "], "
				+ "artifactType=" + artifactType + ", rshps=" + rshps + ", metaproperties="
				+ metaproperties+ "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((term == null) ? 0 : term.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Artifact other = (Artifact) obj;
		if (term == null) {
			if (other.term != null)
				return false;
		} else if (!term.equals(other.term))
			return false;
		return true;
	}


	


	
}
