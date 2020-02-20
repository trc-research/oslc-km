package com.reusecompany.srl.model;

public class TermSyntaxType extends Type<TermSyntaxType> {

	// Properties for TermSyntaxType
	
	
	// Constructors
	
	public TermSyntaxType() {
		
    }
	
	public TermSyntaxType(String name) {
		this.name = name;
    }

    public TermSyntaxType(String name, TermSyntaxType parent) {
        this.name = name;
        // forzamos a meter un objeto de tipo ArtifactType para:
        //  1- Que no se dupliquen los types y lo gestione el usuario
        //  2- Para mantener el strongly type
        this.parent = parent;
    }
    
    
	// Getters & Setters
    
    public TermSyntaxType getParentTermSyntaxType() {
    	if (this.parent != null && this.parent instanceof TermSyntaxType) {
            return (TermSyntaxType)this.parent;
        } else {
            return null;
        }
    }
    public void setParentTermSyntaxType(TermSyntaxType termSyntaxTypeValue) {
    	this.parent = termSyntaxTypeValue;
    }
    
    
    // Functions
   
    @Override
    public String toString() {
        String result;
        result = super.toString();
        final String EmptyData = "< None >";
        final String ToStringFormat = "({%s})\r\nId:{%s}\r\nName:{%s}\r\nParent Syntactic Type:{%s}";
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
