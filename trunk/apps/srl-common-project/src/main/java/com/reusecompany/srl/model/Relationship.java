package com.reusecompany.srl.model;

import java.util.HashMap;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Relationship extends GeneralElement {

	// CONSTANTS
	
	final String DEFAULT_RSHP_TYPE = "ASSOCIATION";
	
	
	// Properties for Relationship
	
	@Expose
	@SerializedName("name")
	private String name = null;
	@Expose
	@SerializedName("description")
	private String description = null;
	@Expose
	@SerializedName("symmetric")
	private boolean isSymmetric = false;
	@Expose
	@SerializedName("from")
	private Artifact from = SRLModelUtils.EMPTY_ARTIFACT;
	@Expose
	@SerializedName("to")
	private Artifact to = SRLModelUtils.EMPTY_ARTIFACT;
	@Expose
	@SerializedName("type")
	private RelationshipSemanticsType type;
	@Expose
	@SerializedName("subType")
	private RelationshipSemanticsType subType;
	@Expose
	@SerializedName("interfaceFrom")
	private String interfaceFrom = SRLModelUtils.EMPTY_STRING; //FIXME: list
	@Expose
	@SerializedName("interfaceTo")
	private String interfaceTo = SRLModelUtils.EMPTY_STRING; //FIXME: list
	@Expose
	@SerializedName("multiplicityFromX")
	private String multiplicityFromX;
	@Expose
	@SerializedName("multiplicityFromY")
	private String multiplicityFromY;
	@Expose
	@SerializedName("multiplicityToX")
	private String multiplicityToX = SRLModelUtils.EMPTY_STRING;
	@Expose
	@SerializedName("multiplicityToY")
	private String multiplicityToY = SRLModelUtils.EMPTY_STRING;
	@Expose
	@SerializedName("participants")
	private HashMap<Integer, ArtifactOccurrence> participants = new HashMap<Integer, ArtifactOccurrence>();
	
	
	// Getters and Setters
	
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
	
	public Artifact getFrom() {
		return from;
	}
	public void setFrom(Artifact from) {
		this.from = from;
	}
	
	public Artifact getTo() {
		return to;
	}
	public void setTo(Artifact to) {
		this.to = to;
	}
	
	public RelationshipSemanticsType getType() {
		return type;
	}
	public void setType(RelationshipSemanticsType type) {
		this.type = type;
	}
	
	public RelationshipSemanticsType getSubType() {
		return subType;
	}
	public void setSubType(RelationshipSemanticsType subType) {
		this.subType = subType;
	}
	
	public String getInterfaceFrom() {
		return interfaceFrom;
	}
	public void setInterfaceFrom(String interfaceFrom) {
		this.interfaceFrom = interfaceFrom;
	}
	
	public String getInterfaceTo() {
		return interfaceTo;
	}
	public void setInterfaceTo(String interfaceTo) {
		this.interfaceTo = interfaceTo;
	}
	
	public String getMultiplicityFromX() {
		return multiplicityFromX;
	}
	public void setMultiplicityFromX(String multiplicityFromX) {
		this.multiplicityFromX = multiplicityFromX;
	}
	
	public String getMultiplicityFromY() {
		return multiplicityFromY;
	}
	public void setMultiplicityFromY(String multiplicityFromY) {
		this.multiplicityFromY = multiplicityFromY;
	}
	
	public String getMultiplicityToX() {
		return multiplicityToX;
	}
	public void setMultiplicityToX(String multiplicityToX) {
		this.multiplicityToX = multiplicityToX;
	}
	
	public String getMultiplicityToY() {
		return multiplicityToY;
	}
	public void setMultiplicityToY(String multiplicityToY) {
		this.multiplicityToY = multiplicityToY;
	}
	
	public boolean isSymmetric() {
		return isSymmetric;
	}
	public void setSymmetric(boolean isSymmetric) {
		this.isSymmetric = isSymmetric;
	}

	public HashMap<Integer, ArtifactOccurrence> getParticipants() {
		return participants;
	}
	public void setParticipants(HashMap<Integer, ArtifactOccurrence> participants) {
		this.participants = participants;
	}
	
	
	// Constructors & Initializers
	
	public Relationship() {
		this.type = new RelationshipSemanticsType(DEFAULT_RSHP_TYPE);
    }
	
    public Relationship(String name, RelationshipSemanticsType type) {
        this.name = name;
        this.type = type;
    }

    public Relationship(String name, RelationshipSemanticsType type, Artifact from, Artifact to) {
    	this.name = name;
        this.type = type;
        this.from = from;
        this.to = to;
        this.participants = null;
    }
    
    public Relationship(RelationshipSemanticsType type, Artifact from, Artifact to) {
    	this.type = type;
        this.from = from;
        this.to = to;
        this.participants = null;
    }
    
    public Relationship(String name, String typeName) {
    	this.name = name;
    	this.type = new RelationshipSemanticsType(typeName);
    }

    public Relationship(String name, String typeName, Artifact from, Artifact to) {
    	this.name = name;
    	this.type = new RelationshipSemanticsType(typeName);
    	this.from = from;
        this.to = to;
    }
    
    public Relationship(String typeName, Artifact from, Artifact to) {
    	this.type = new RelationshipSemanticsType(typeName);
    	this.from = from;
        this.to = to;
    }
	
    
	// Functions
	
    @Override
    public String toString() {
        String result;
        result = super.toString();
        final String EmptyData = "< None >";
        final String ToStringFormat = "({%s}) Id:{%s}; Name:{%s}; Type:{%s};\r\nFrom:[{%s}]\r\nTo:[{%s}]";
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
        if (this.type != null 
        		&& this.type.name != null 
        		&& !this.type.name.isEmpty()) {
            nullableTypeName = this.type.name;
        }
        String nullableFrom;
        nullableFrom = EmptyData;
        if (this.from != null
        		&& this.from.toString() != null
        		&& !this.from.toString().isEmpty()) {
            nullableFrom = this.from.toString();
        }
        String nullableTo;
        nullableTo = EmptyData;
        if (this.to != null
        		&& this.to.toString() != null
        		&& !this.to.toString().isEmpty()) {
            nullableTo = this.to.toString();
        }
        if ((nullableIdentifier == null || nullableIdentifier.isEmpty()) 
        		&& (nullableName == null  ||  nullableName.isEmpty()) 
        		&& (nullableTypeName == null || nullableTypeName.isEmpty())
        		&& (nullableFrom == null || nullableFrom.isEmpty())
        		&& (nullableTo == null || nullableTo.isEmpty())) {
            result = String.format(EmptyArtifactFormat, result, hashCode());
        } else {
            result = String.format(ToStringFormat, result, nullableIdentifier, nullableName, nullableTypeName, nullableFrom, nullableTo);
        }
        return result;
    }
}
