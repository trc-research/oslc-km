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
	
}
