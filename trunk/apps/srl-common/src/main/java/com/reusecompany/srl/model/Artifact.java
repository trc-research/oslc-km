package com.reusecompany.srl.model;

public class Artifact {
	
	public static Artifact EMPTY_ARTIFACT = new Artifact();
	
	private String id = null;
	private String title = null;
	private String description = null;
	
	
	
	public Artifact() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}

	
	

}
