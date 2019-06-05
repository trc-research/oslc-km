package com.reusecompany.srl.model;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class Term {
	protected String id = SRLModelUtils.EMPTY_STRING;
	protected String name = SRLModelUtils.EMPTY_STRING;
    protected String language = SRLModelUtils.EMPTY_STRING;
    protected String scopeNote = SRLModelUtils.EMPTY_STRING;
    protected String historyNote = SRLModelUtils.EMPTY_STRING;
    protected String sourceNote = SRLModelUtils.EMPTY_STRING;
    protected String classificationCode = SRLModelUtils.EMPTY_STRING;
    
    protected Type syntaxType = new Type("NOUN");
    protected List<Type> semanticClusters = null;
    protected List<Type> verbRelationships = null;
   
    
	public Term() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	public Term(String name) {
		super();
		this.name = name;
	}


	public Term(String name, Type syntaxType) {
		super();
		this.name = name;
		this.syntaxType = syntaxType;
	}


	public Term(String name, String language, Type syntaxType) {
		super();
		this.name = name;
		this.language = language;
		this.syntaxType = syntaxType;
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
	public String getLanguage() {
		return language;
	}
	public void setLanguage(String language) {
		this.language = language;
	}
	public String getScopeNote() {
		return scopeNote;
	}
	public void setScopeNote(String scopeNote) {
		this.scopeNote = scopeNote;
	}
	public String getHistoryNote() {
		return historyNote;
	}
	public void setHistoryNote(String historyNote) {
		this.historyNote = historyNote;
	}
	public String getSourceNote() {
		return sourceNote;
	}
	public void setSourceNote(String sourceNote) {
		this.sourceNote = sourceNote;
	}
	public String getClassificationCode() {
		return classificationCode;
	}
	public void setClassificationCode(String classificationCode) {
		this.classificationCode = classificationCode;
	}
	public Type getSyntaxType() {
		return syntaxType;
	}
	public void setSyntaxType(Type syntaxType) {
		this.syntaxType = syntaxType;
	}
	public List<Type> getSemanticClusters() {
		if(this.semanticClusters == null){
			this.semanticClusters = new LinkedList<Type>();
		}
		return semanticClusters;
	}
	public void setSemanticClusters(List<Type> semanticClusters) {
		this.semanticClusters = semanticClusters;
	}
	public List<Type> getVerbRelationships() {
		if(this.verbRelationships == null){
			this.verbRelationships = new LinkedList<Type>();
		}
		return verbRelationships;
	}
	public void setVerbRelationships(List<Type> verbRelationships) {
		this.verbRelationships = verbRelationships;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
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
		Term other = (Term) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
    
    
    

}
