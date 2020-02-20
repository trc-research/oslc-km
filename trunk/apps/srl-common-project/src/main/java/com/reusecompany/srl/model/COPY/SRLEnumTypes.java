package com.reusecompany.srl.model.COPY;

public class SRLEnumTypes {

	 enum SRLValueType{
	        NoValue,
	        Decimal,
	        String,
	        Boolean,
	        Date,
	        Integer,
	        Double,
	        Range,
	        ArtifactValue,
	        TermValue,
	        Position,
	        Column,
	        TransformationLinkedPattern,
	        RBS
	    }

	    enum SRLOperator{
	        Equal,
	        Greater,
	        GreaterEqual,
	        LessEqual ,
	        Less,
	        Contains,
	        NotIn,
	        Distinct,
	        InRange,
	    }

	    enum SRLElementState{
	        NewElement ,
	        Loaded,
	        Updated,
	        Deleted        
	    }
}
