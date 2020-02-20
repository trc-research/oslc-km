package com.reusecompany.srl.model;


import java.util.ArrayList;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Term extends GeneralElement {
	
	// CONSTANTS
	
	final String DEFAULT_TERM_TYPE_NAME = "NOUN";
	
	
	// Properties for Term
	
	@Expose
	@SerializedName("name")
	protected String name;
	@Expose
	@SerializedName("language")
    protected String language;
	@Expose
	@SerializedName("scopeNote")
    protected String scopeNote;
	@Expose
	@SerializedName("historyNote")
    protected String historyNote;
	@Expose
	@SerializedName("sourceNote")
    protected String sourceNote;
	@Expose
	@SerializedName("classificationCode")
    protected String classificationCode;
	
	@Expose
	@SerializedName("type")
    protected TermSyntaxType syntaxType = new TermSyntaxType(DEFAULT_TERM_TYPE_NAME);
	@Expose
	@SerializedName("semanticClusters")
    protected ArrayList<TermSemanticsType> semanticClusters = new ArrayList<TermSemanticsType>();
	@Expose
	@SerializedName("relationshipSemanticsInCaseOfVerb")
    protected RelationshipSemanticsType relationshipSemanticsInCaseOfVerb;
  
    
    // Constructors
    
	public Term() {
		
	}
	
	public Term(String termName) {
		this.name = termName;
	}

	public Term(String termName, TermSyntaxType termSyntaxType) {
		this.name = termName;
		this.syntaxType = termSyntaxType;
	}

	public Term(String termName, String termLanguage, TermSyntaxType termSyntaxType) {
		this.name = termName;
		this.language = termLanguage;
		this.syntaxType = termSyntaxType;
	}

	
	// Getters and Setters
	
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
	
	public TermSyntaxType getSyntaxType() {
		return syntaxType;
	}
	public void setSyntaxType(TermSyntaxType syntaxType) {
		this.syntaxType = syntaxType;
	}
	
	public ArrayList<TermSemanticsType> getSemanticClusters() {
		return semanticClusters;
	}
	public void setSemanticClusters(ArrayList<TermSemanticsType> semanticClusters) {
		this.semanticClusters = semanticClusters;
	}
	
	public RelationshipSemanticsType getRelationshipSemanticsInCaseOfVerb() {
		return relationshipSemanticsInCaseOfVerb;
	}
	public void setRelationshipSemanticsInCaseOfVerb(RelationshipSemanticsType relationshipSemanticsInCaseOfVerb) {
		this.relationshipSemanticsInCaseOfVerb = relationshipSemanticsInCaseOfVerb;
	}

	public String getDEFAULT_TERM_TYPE_NAME() {
		return DEFAULT_TERM_TYPE_NAME;
	}

	
	// Functions

	@Override
	public String toString() {
		String result;
        result = super.toString();
        final String EmptyData = "< None >";
        final String ToStringFormat = "({%s}\r\nId:{%s}\r\nName:{%s}\r\nSyntactic Type:{%s}";
        final String EmptyArtifactFormat = "({%s}):{%s}";
        String nullableIdentifier;
        nullableIdentifier = EmptyData;
        if (this.identifier != null 
        		&& !this.identifier.isEmpty()) {
            nullableIdentifier = this.identifier;
        }
        String nullableName;
        nullableName = EmptyData;
        if (this.name != null 
        		&& !this.name.isEmpty()) {
            nullableName = this.name;
        }
        String nullableTypeName;
        nullableTypeName = EmptyData;
        if (this.syntaxType != null 
        		&& this.syntaxType.name != null 
        		&& !this.syntaxType.name.isEmpty()) {
            nullableTypeName = this.syntaxType.name;
        }
        if ((nullableIdentifier == null || nullableIdentifier.isEmpty()) 
        		&& (nullableName == null  ||  nullableName.isEmpty()) 
        		&& (nullableTypeName == null || nullableTypeName.isEmpty())) {
            result = String.format(EmptyArtifactFormat, result, hashCode());
        } else {
            result = String.format(ToStringFormat, result, nullableIdentifier, nullableName, nullableTypeName);
        }
        return result;
	}
}
