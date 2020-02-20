package com.reusecompany.srl.model;

import com.google.gson.annotations.*;

public abstract class Type<T extends Type<T>> extends GeneralElement {
	
	// Properties for Type
	
	@Expose
	@SerializedName("name")
	protected String name = SRLModelUtils.EMPTY_STRING;
	@Expose
	@SerializedName("parent")
	protected T parent;
	
	
	// Constructors
	
	public Type() {
		
	}
	
	public Type(String name) {
		this.name = name;
	}
	
	public Type(String name, T parent) {
		this.name = name;
		this.parent = parent;
	}

	
	// Getters & Setters	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public T getParentType() {
		return parent;
	}

	public void setParentType(T parent) {
		this.parent = parent;
	}
	
	
	// Functions

	@Override
	public String toString() {
        String result;
        result = super.toString();
        final String EmptyData = "< None >";
        final String ToStringFormat = "({%s})\r\nId:{%s}\r\nName:{%s}\r\nParent Type:{%s}";
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
