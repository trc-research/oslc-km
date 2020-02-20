package com.reusecompany.srl.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ArtifactOccurrence {

	// Attribute
	@Expose
	@SerializedName("participant")
	private Artifact participant;
	@Expose
	@SerializedName("position")
	private int position;
	@Expose
	@SerializedName("x")
	private double x;
	@Expose
	@SerializedName("y")
	private double y;
	@Expose
	@SerializedName("asReference")
	private Artifact asReference;
	@Expose
	@SerializedName("interfaceOccurrence")
	private String interfaceOccurrence;
	
	
	// Getters & Setters
	
	public Artifact getParticipant() {
		return participant;
	}
	public void setParticipant(Artifact participant) {
		this.participant = participant;
	}
	public int getPosition() {
		return position;
	}
	public void setPosition(int position) {
		this.position = position;
	}
	public double getX() {
		return x;
	}
	public void setX(double x) {
		this.x = x;
	}
	public double getY() {
		return y;
	}
	public void setY(double y) {
		this.y = y;
	}
	public Artifact getAsReference() {
		return asReference;
	}
	public void setAsReference(Artifact asReference) {
		this.asReference = asReference;
	}
	public String getInterfaceOccurrence() {
		return interfaceOccurrence;
	}
	public void setInterfaceOccurrence(String interfaceOccurrence) {
		this.interfaceOccurrence = interfaceOccurrence;
	}	
}
