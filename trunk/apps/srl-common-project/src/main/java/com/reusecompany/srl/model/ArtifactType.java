package com.reusecompany.srl.model;

public class ArtifactType extends Type<ArtifactType> {
	
	// Properties for ArtifactType
	
	
	// Constructors
	
	public ArtifactType() {
		
	}

	public ArtifactType(String name) {
		this.name = name;
	}

	public ArtifactType(String name, ArtifactType parent) {
		this.name = name;
		// forzamos a meter un objeto de tipo ArtifactType para:
        //  1- Que no se dupliquen los types y lo gestione el usuario
        //  2- Para mantener el strongly type
		this.parent = parent;
	}
	
	
	// Getters & Setters
	
	public ArtifactType getArtifactType() {
		 if (this.parent != null && this.parent instanceof ArtifactType) {
             return (ArtifactType) this.parent;
         } else {
             return null;
         }
	}
	public void setArtifactType(ArtifactType parent) {
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
        if (this.identifier != null && !this.identifier.isEmpty()) {
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
