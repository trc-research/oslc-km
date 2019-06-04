package com.reusecompany.srl.model;

public class Data {

	protected String key = SRLModelUtils.EMPTY_STRING;
	protected String value = SRLModelUtils.EMPTY_STRING;
	protected SRLEnumTypes.SRLOperator operator = SRLEnumTypes.SRLOperator.Equal;
	protected SRLEnumTypes.SRLValueType valueType = SRLEnumTypes.SRLValueType.String;

	public Data() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Data(String key, String value) {
		super();
		this.key = key;
		this.value = value;
	}

	public Data(String key, String value, SRLEnumTypes.SRLValueType valueType) {
		super();
		this.key = key;
		this.value = value;
		this.valueType = valueType;
	}

	public Data(String key, String value, SRLEnumTypes.SRLOperator operator, SRLEnumTypes.SRLValueType valueType) {
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

}
