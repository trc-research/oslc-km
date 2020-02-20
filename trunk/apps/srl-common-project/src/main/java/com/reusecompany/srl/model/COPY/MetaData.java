package com.reusecompany.srl.model.COPY;

public class MetaData {

	protected String key = SRLModelUtils.EMPTY_STRING;
	protected String value = SRLModelUtils.EMPTY_STRING;
	protected SRLEnumTypes.SRLOperator operator = SRLEnumTypes.SRLOperator.Equal;
	protected SRLEnumTypes.SRLValueType valueType = SRLEnumTypes.SRLValueType.String;

	public MetaData() {
		super();
		// TODO Auto-generated constructor stub
	}

	public MetaData(String key, String value) {
		super();
		this.key = key;
		this.value = value;
	}

	public MetaData(String key, String value, SRLEnumTypes.SRLValueType valueType) {
		super();
		this.key = key;
		this.value = value;
		this.valueType = valueType;
	}

	public MetaData(String key, String value, SRLEnumTypes.SRLOperator operator, SRLEnumTypes.SRLValueType valueType) {
		super();
		this.key = key;
		this.value = value;
		this.operator = operator;
		this.valueType = valueType;
	}

	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}

	public SRLEnumTypes.SRLOperator getOperator() {
		return operator;
	}

	public void setOperator(SRLEnumTypes.SRLOperator operator) {
		this.operator = operator;
	}

	public SRLEnumTypes.SRLValueType getValueType() {
		return valueType;
	}

	public void setValueType(SRLEnumTypes.SRLValueType valueType) {
		this.valueType = valueType;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((key == null) ? 0 : key.hashCode());
		result = prime * result + ((operator == null) ? 0 : operator.hashCode());
		result = prime * result + ((value == null) ? 0 : value.hashCode());
		result = prime * result + ((valueType == null) ? 0 : valueType.hashCode());
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
		MetaData other = (MetaData) obj;
		if (key == null) {
			if (other.key != null)
				return false;
		} else if (!key.equals(other.key))
			return false;
		if (operator != other.operator)
			return false;
		if (value == null) {
			if (other.value != null)
				return false;
		} else if (!value.equals(other.value))
			return false;
		if (valueType != other.valueType)
			return false;
		return true;
	}

}
