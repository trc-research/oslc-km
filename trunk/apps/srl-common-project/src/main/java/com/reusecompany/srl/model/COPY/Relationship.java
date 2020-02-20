package com.reusecompany.srl.model.COPY;

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
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((from == null) ? 0 : from.hashCode());
		result = prime * result + ((interfaceFrom == null) ? 0 : interfaceFrom.hashCode());
		result = prime * result + ((interfaceTo == null) ? 0 : interfaceTo.hashCode());
		result = prime * result + (isSymmetric ? 1231 : 1237);
		result = prime * result + multiplicityFromX;
		result = prime * result + multiplicityFromY;
		result = prime * result + multiplicityToX;
		result = prime * result + multiplicityToY;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((subType == null) ? 0 : subType.hashCode());
		result = prime * result + ((to == null) ? 0 : to.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Relationship other = (Relationship) obj;
		if (from == null) {
			if (other.from != null)
				return false;
		} else if (!from.equals(other.from))
			return false;
		if (interfaceFrom == null) {
			if (other.interfaceFrom != null)
				return false;
		} else if (!interfaceFrom.equals(other.interfaceFrom))
			return false;
		if (interfaceTo == null) {
			if (other.interfaceTo != null)
				return false;
		} else if (!interfaceTo.equals(other.interfaceTo))
			return false;
		if (isSymmetric != other.isSymmetric)
			return false;
		if (multiplicityFromX != other.multiplicityFromX)
			return false;
		if (multiplicityFromY != other.multiplicityFromY)
			return false;
		if (multiplicityToX != other.multiplicityToX)
			return false;
		if (multiplicityToY != other.multiplicityToY)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (subType == null) {
			if (other.subType != null)
				return false;
		} else if (!subType.equals(other.subType))
			return false;
		if (to == null) {
			if (other.to != null)
				return false;
		} else if (!to.equals(other.to))
			return false;
		if (type == null) {
			if (other.type != null)
				return false;
		} else if (!type.equals(other.type))
			return false;
		return true;
	}
	
	
	
	
}
