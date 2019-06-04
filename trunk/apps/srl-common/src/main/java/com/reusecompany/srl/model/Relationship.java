package com.reusecompany.srl.model;

public class Relationship {

	protected String id = SRLModelUtils.EMPTY_STRING;
	protected String name = SRLModelUtils.EMPTY_STRING;
	protected String description = SRLModelUtils.EMPTY_STRING;

	private Artifact from = SRLModelUtils.EMPTY_ARTIFACT;
	private Artifact to = SRLModelUtils.EMPTY_ARTIFACT;
	private Type type = SRLModelUtils.DEFAULT_TYPE;
	private Type subType = SRLModelUtils.DEFAULT_TYPE;
	
	private String interfaceFrom = SRLModelUtils.EMPTY_STRING; //FIXME:
	private String interfaceTo = SRLModelUtils.EMPTY_STRING; //FIXME:
	
	private int multiplicityFromX  = 1;
	private int multiplicityFromY = 1;
	private int multiplicityToX = 1;
	private int multiplicityToY = 1;
	private boolean isSymmetric = Boolean.FALSE;
	
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
	public Type getType() {
		return type;
	}
	public void setType(Type type) {
		this.type = type;
	}
	public Type getSubType() {
		return subType;
	}
	public void setSubType(Type subType) {
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
	public int getMultiplicityFromX() {
		return multiplicityFromX;
	}
	public void setMultiplicityFromX(int multiplicityFromX) {
		this.multiplicityFromX = multiplicityFromX;
	}
	public int getMultiplicityFromY() {
		return multiplicityFromY;
	}
	public void setMultiplicityFromY(int multiplicityFromY) {
		this.multiplicityFromY = multiplicityFromY;
	}
	public int getMultiplicityToX() {
		return multiplicityToX;
	}
	public void setMultiplicityToX(int multiplicityToX) {
		this.multiplicityToX = multiplicityToX;
	}
	public int getMultiplicityToY() {
		return multiplicityToY;
	}
	public void setMultiplicityToY(int multiplicityToY) {
		this.multiplicityToY = multiplicityToY;
	}
	public boolean isSymmetric() {
		return isSymmetric;
	}
	public void setSymmetric(boolean isSymmetric) {
		this.isSymmetric = isSymmetric;
	}
	
	
	
	
}
