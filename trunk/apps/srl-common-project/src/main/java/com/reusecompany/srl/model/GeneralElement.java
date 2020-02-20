package com.reusecompany.srl.model;

import com.google.gson.annotations.*;
import com.reusecompany.srl.model.SRLEnumTypes.SrlElementState;

public abstract class GeneralElement {
	
	// CONSTANTS
	
	protected final String VersionName = "2";
	
	
	// Properties for GeneralElement
	
	@Expose
	@SerializedName("version")
	protected String version = VersionName;
	@Expose
	@SerializedName("state")
	protected SRLEnumTypes.SrlElementState state;
	// Serialization Code for the element
	@Expose
	@SerializedName("identifier")
	protected String identifier = SRLModelUtils.EMPTY_STRING;		// UUID.randomUUID().toString();
	// Cajon desastre para intercambiar informacion especifica que haya en el lado nuestro
	//@Expose										// En caso de declararse que aparezca reflejado
	@SerializedName("additionalInformation")
	private String additionalInformation;			// Serialized information. Dudo de si hacer esto. Es para meter lo que quiera
	
	
	// Constructors
	
	public GeneralElement() {
		state = SrlElementState.NewElement;
	}
	
	
	// Getters & Setters
	
	public String getVersion() {
		return version;
	}

	public SRLEnumTypes.SrlElementState getState() {
		return state;
	}
	public void setState(SRLEnumTypes.SrlElementState state) {
		this.state = state;
	}

	public String getIdentifier() {
		return identifier;
	}
	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}

	public String getAdditionalInformation() {
		return additionalInformation;
	}
	public void setAdditionalInformation(String additionalInformation) {
		this.additionalInformation = additionalInformation;
	}
}
