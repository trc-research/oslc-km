package com.reusecompany.srl.model;

public class MetaData extends GeneralData{

	// Properties for Metadata
	
	// TODO en C# esta clase hereda y ademas tiene la propiedad "MetadataAsDataTable" (la cual no tiene equivalente directo en java)
	// TODO: falta implementar "Default Query Indexes", la cual esta en C#
	
	
	// Constructors
	
	public MetaData() {
		
	}

	public MetaData(String key, String value) {
		this.key = key;
		this.value = value;
	}

	public MetaData(String key, String value, SRLEnumTypes.SrlValueType valueType) {
		this.key = key;
		this.value = value;
		this.valueType = valueType;
	}

	public MetaData(String key, String value, SRLEnumTypes.SrlOperator operator, SRLEnumTypes.SrlValueType valueType) {
		this.key = key;
		this.value = value;
		this.propertyOperation = operator;
		this.valueType = valueType;
	}
	
	// Getters and Setters
	
	// TODO: get y set para propiedad DataTable
	

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
        if (!this.identifier.isEmpty() && this.identifier != null) {
            nullableId = this.identifier;
        }
        String nullableDataKey;
        nullableDataKey = EmptyData;
        if (!this.key.isEmpty() && this.key != null) {
            nullableDataKey = this.key;
        }
        String nullableDataValue;
        nullableDataValue = EmptyData;
        if (!this.value.isEmpty() && this.value != null) {
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
