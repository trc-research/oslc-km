package com.reusecompany.srl.model;

import java.util.ArrayList;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.reusecompany.srl.model.SRLEnumTypes.SrlOperator;
import com.reusecompany.srl.model.SRLEnumTypes.SrlValueType;

//import com.reusecompany.srl.model.SRLEnumTypes.SRLValueType;

public abstract class GeneralData extends GeneralElement {
	
	// Properties for GeneralData
	
	@Expose
	@SerializedName("key")
	protected String key = SRLModelUtils.EMPTY_STRING;
	@Expose
	@SerializedName("value")
	protected String value;
	@Expose
	@SerializedName("complex key")
	protected ArrayList<String> complexKey;
	@Expose
	@SerializedName("complex value")
	protected ArrayList<String> complexValue;
	@Expose
	@SerializedName("value Type")
	protected SrlValueType valueType = SrlValueType.String;
	@Expose
	@SerializedName("property Operation")
	protected SrlOperator propertyOperation = SrlOperator.Equal;
	@Expose
	@SerializedName("complex ID")
	protected Integer complexId = null;

	
	// Constructors
	
	public GeneralData() {
		
	}
	
	public GeneralData(String dataKey, String dataValue) {
		this.key = dataKey;
		this.value = dataValue;
	}
	
	public GeneralData(String dataKey, String dataValue, SrlValueType dataValueType) {
		this.key = dataKey;
		this.value = dataValue;
		this.valueType = dataValueType;
	}
	
	public GeneralData(String dataKey, String dataValue, SrlValueType dataValueType, SrlOperator dataOperator) {
		this.key = dataKey;
		this.value = dataValue;
		this.valueType = dataValueType;
		this.propertyOperation = dataOperator;
	}

	
	// Getters and Setters
	
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

	public ArrayList<String> getComplexKey() {
		return complexKey;
	}

	public void setComplexKey(ArrayList<String> complexKey) {
		this.complexKey = complexKey;
	}

	public ArrayList<String> getComplexValue() {
		return complexValue;
	}

	public void setComplexValue(ArrayList<String> complexValue) {
		this.complexValue = complexValue;
	}

	public SrlValueType getValueType() {
		return valueType;
	}

	public void setValueType(SrlValueType valueType) {
		this.valueType = valueType;
	}

	public SRLEnumTypes.SrlOperator getPropertyOperation() {
		return propertyOperation;
	}

	public void setPropertyOperation(SRLEnumTypes.SrlOperator propertyOperation) {
		this.propertyOperation = propertyOperation;
	}

	public Integer getComplexId() {
		return complexId;
	}

	public void setComplexId(Integer complexId) {
		this.complexId = complexId;
	}
	
	
	// Functions
	
	@Override
	public String toString() {
		String result;
        result = super.toString();
        final String EmptyData = "< None >";
        final String ToStringFormat = "({%s})\r\nId:{%s}\r\nKey:{%s}\r\nValue:{%s}\r\nValue Type:{%s};\r\nOperator:{%s}";
        final String EmptyArtifactFormat = "({%s}):{%s}";
        String nullableId;
        nullableId = EmptyData;
        if (this.identifier != null 
        		&& !this.identifier.isEmpty()) {
            nullableId = this.identifier;
        }
        String nullableDataKey;
        nullableDataKey = EmptyData;
        if (this.key != null 
        		&& !this.key.isEmpty()) {
            nullableDataKey = this.key;
        }
        String nullableDataValue;
        nullableDataValue = EmptyData;
        if (this.value != null 
        		&& !this.value.isEmpty()) {
            nullableDataValue = this.value;
        }
        String nullableDataValueType;
        nullableDataValueType = this.valueType.toString();
        String nullableDataOperator;
        nullableDataOperator = this.propertyOperation.toString();
        if ((nullableId == null || nullableId.isEmpty()) 
        		&& (nullableDataKey == null || nullableDataKey.isEmpty())
        		&& (nullableDataValue == null || nullableDataValue.isEmpty())
        		&& (nullableDataValueType == null || nullableDataValueType.isEmpty())
        		&& (nullableDataOperator == null || nullableDataOperator.isEmpty())) {
            result = String.format(EmptyArtifactFormat, result, hashCode());
        } else {
            result = String.format(ToStringFormat, result, nullableId, nullableDataKey, nullableDataValue, nullableDataValueType, nullableDataOperator);
        }
        return result;
    }
}
