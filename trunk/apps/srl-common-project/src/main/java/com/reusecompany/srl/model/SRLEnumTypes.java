package com.reusecompany.srl.model;

import com.google.gson.annotations.SerializedName;

public class SRLEnumTypes {

	public enum SrlValueType {
			@SerializedName("0")
			NoValue(0),
			@SerializedName("1")
	        Decimal(1),
	        @SerializedName("2")
	        String(2),
	        @SerializedName("3")
	        Boolean(3),
	        @SerializedName("4")
	        Date(4),
	        @SerializedName("5")
	        Integer(5),
	        @SerializedName("6")
	        Double(6),
	        @SerializedName("10")
	        Range(10),
	        @SerializedName("100")
	        ArtifactValue(100),
	        @SerializedName("101")
	        TermValue(101),
	        @SerializedName("200")
	        Position(200),
	        @SerializedName("210")
	        Column(210),
	        @SerializedName("220")
	        TransformationLinkedPattern(220),
	        @SerializedName("230")
	        RBS(230);

			SrlValueType(int value) {
				
			}
	}

	public enum SrlOperator {
			@SerializedName("0")
	        Equal(0),
	        @SerializedName("1")
	        Greater(1),
	        @SerializedName("2")
	        GreaterEqual(2),
	        @SerializedName("3")
	        LessEqual(3),
	        @SerializedName("4")
	        Less(4),
	        @SerializedName("5")
	        Contains(5),
	        @SerializedName("6")
	        NotIn(6),
	        @SerializedName("7")
	        Distinct(7),
	        @SerializedName("8")
	        InRange(8);

			SrlOperator(int value) {
				
			}
	}
	
	public enum SrlElementState {
			@SerializedName("0")
	        NewElement(0),
	        @SerializedName("1")
	        Loaded(1),
	        @SerializedName("2")
	        Updated(2),
	        @SerializedName("3")
	        Deleted(3);

			SrlElementState(int value) {
				
			}        
	}
	
}
