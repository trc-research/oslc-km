package com.reusecompany.srl.model;

public class RelationshipSemanticsType extends Type<RelationshipSemanticsType> {

	// Properties for TermSyntaxType

	
	// Constructors
	
	public RelationshipSemanticsType() {
		
    }
	
	public RelationshipSemanticsType(String name) {
		this.name = name;
    }

    public RelationshipSemanticsType(String name, RelationshipSemanticsType parentType) {
        this.name = name;
        // forzamos a meter un objeto de tipo ArtifactType para:
        //  1- Que no se dupliquen los types y lo gestione el usuario
        //  2- Para mantener el strongly type
        this.parent = parentType;
    }
    
    
	// Getters & Setters
    
    public RelationshipSemanticsType getParentRelationshipSemanticsType(){
    	if (this.parent != null && this.parent instanceof RelationshipSemanticsType) {
            return (RelationshipSemanticsType)this.parent;
        } else {
            return null;
        }
    }
    
    public void setParentRelationshipSemanticsType(RelationshipSemanticsType parentType){
    	this.parent = parentType;
    }
    
    
    // Functions
    
    public String toString() {
        String result;
        result = super.toString();
        final String EmptyData = "< None >";
        final String ToStringFormat = "({%s})\r\nId:{%s}\r\nName:{%s}\r\nParent Relationship Semantics Type:{%s}";
        final String EmptyArtifactFormat = "({%s}):{%s}";
        String nullableldIdentifier;
        nullableldIdentifier = EmptyData;
        if (this.identifier != null 
        		 && !this.identifier.isEmpty()) {
            nullableldIdentifier = this.identifier;
        }
        String nullableName;
        nullableName = EmptyData;
        if (this.name != null 
        		&& !this.name.isEmpty()) {
            nullableName = this.name;
        }
        String nullableTypeName;
        nullableTypeName = EmptyData;
        if (this.parent.name != null 
        		&& !this.parent.name.isEmpty()) {
            nullableTypeName = this.parent.name;
        }
        if ((nullableldIdentifier == null || nullableldIdentifier.isEmpty()) 
        		&& (nullableName == null  ||  nullableName.isEmpty()) 
        		&& (nullableTypeName == null || nullableTypeName.isEmpty())) {
            result = String.format(EmptyArtifactFormat, result, hashCode());
        } else {
            result = String.format(ToStringFormat, result, nullableldIdentifier, nullableName, nullableTypeName);
        }
        return result;
    }
}
