package com.reusecompany.srl.model;

public class Type {
	protected String id = SRLModelUtils.EMPTY_STRING;
	protected String name = SRLModelUtils.EMPTY_STRING;
	protected Type parent;
	
	
	public Type() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	public Type(String id, String name, Type parent) {
		super();
		this.id = id;
		this.name = name;
		this.parent = parent;
	}
	

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
	public Type getParent() {
		return parent;
	}
	public void setParent(Type parent) {
		this.parent = parent;
	}
	
	
	
}
