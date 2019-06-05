package com.reusecompany.srl.model;

import java.util.LinkedList;
import java.util.List;

public class Artifact {
		
	protected String id = SRLModelUtils.EMPTY_STRING;
	protected String name = SRLModelUtils.EMPTY_STRING;
    protected String description = SRLModelUtils.EMPTY_STRING;
    protected String content = SRLModelUtils.EMPTY_STRING;
    protected String snapShot = SRLModelUtils.EMPTY_STRING;
	protected String physicalPath = SRLModelUtils.EMPTY_STRING;
	protected String physicalName = SRLModelUtils.EMPTY_STRING;
  
    protected Term term;
    protected Type type;
    protected List<Relationship> relationships = null;
    protected List<MetaData> metadata = null;
    protected List<Data> data = null;
    protected List<Artifact> ownedArtifacts = null;
    protected List<Term> ownedTerms = null;
	
	public Artifact() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getSnapShot() {
		return snapShot;
	}

	public void setSnapShot(String snapShot) {
		this.snapShot = snapShot;
	}

	public String getPhysicalPath() {
		return physicalPath;
	}

	public void setPhysicalPath(String physicalPath) {
		this.physicalPath = physicalPath;
	}

	public String getPhysicalName() {
		return physicalName;
	}

	public void setPhysicalName(String physicalName) {
		this.physicalName = physicalName;
	}

	public Term getTerm() {
		return term;
	}

	public void setTerm(Term term) {
		this.term = term;
	}

	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}

	public List<Relationship> getRelationships() {
		if (this.relationships == null){
			this.relationships = new LinkedList<Relationship>();
		}
		return relationships;
	}

	public void setRelationships(List<Relationship> relationships) {
		this.relationships = relationships;
	}

	public List<MetaData> getMetadata() {
		if (this.metadata == null){
			this.metadata = new LinkedList<MetaData>();
		}
		return metadata;
	}

	public void setMetadata(List<MetaData> metadata) {
		this.metadata = metadata;
	}

	public List<Data> getData() {
		if (this.data == null){
			this.data = new LinkedList<Data>();
		}
		return data;
	}

	public void setData(List<Data> data) {
		this.data = data;
	}

	public List<Artifact> getOwnedArtifacts() {
		if(this.ownedArtifacts == null){
			this.ownedArtifacts = new LinkedList<Artifact>();
		}
		return ownedArtifacts;
	}

	public void setOwnedArtifacts(List<Artifact> ownedArtifacts) {
		this.ownedArtifacts = ownedArtifacts;
	}

	public List<Term> getOwnedTerms() {
		if (this.ownedTerms == null){
			this.ownedTerms = new LinkedList<Term>();
		}
		return ownedTerms;
	}

	public void setOwnedTerms(List<Term> ownedTerms) {
		this.ownedTerms = ownedTerms;
	}
	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	

}
