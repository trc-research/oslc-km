package com.reusecompany.srl.model;

public class TermSemanticsType extends Type<TermSemanticsType> {

	// Properties for TermSyntaxType
	
	
	// Constructors
	
	public TermSemanticsType() {
		
    }
	
	public TermSemanticsType(String name) {
		this.name = name;
    }

    public TermSemanticsType(String name, TermSemanticsType parent) {
        this.name = name;
        // forzamos a meter un objeto de tipo ArtifactType para:
        //  1- Que no se dupliquen los types y lo gestione el usuario
        //  2- Para mantener el strongly type
        this.parent = parent;
    }
    
	// Getters & Setters
    
    public TermSemanticsType getParentTermSemanticsType() {
    	if (this.parent != null && this.parent instanceof TermSemanticsType) {
            return (TermSemanticsType)this.parent;
        } else {
            return null;
        }
    }
    public void setParentTermSyntaxType(TermSemanticsType termSemanticsTypeValue) {
    	this.parent = termSemanticsTypeValue;
    }
    
    
    // Functions
    
    @Override
    public String toString() {
        String result;
        result = super.toString();
        final String EmptyData = "< None >";
        final String ToStringFormat = "({%s})\r\nId:{%s}\r\nName:{%s}\r\nParent Semantic Type:{%s}";
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
        if (this.parent != null 
        		&& this.parent.name != null 
        		&& !this.parent.name.isEmpty()) {
            nullableTypeName = this.parent.name;
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
