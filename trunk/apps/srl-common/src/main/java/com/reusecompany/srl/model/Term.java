package com.reusecompany.srl.model;

import java.util.Collections;
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
    protected List<Type> semanticClusters = Collections.EMPTY_LIST;
    protected List<Type> verbRelationships = Collections.EMPTY_LIST;
   
    
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
		return semanticClusters;
	}
	public void setSemanticClusters(List<Type> semanticClusters) {
		this.semanticClusters = semanticClusters;
	}
	public List<Type> getVerbRelationships() {
		return verbRelationships;
	}
	public void setVerbRelationships(List<Type> verbRelationships) {
		this.verbRelationships = verbRelationships;
	}
    
    
    

}
