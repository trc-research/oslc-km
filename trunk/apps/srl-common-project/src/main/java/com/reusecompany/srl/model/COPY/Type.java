package com.reusecompany.srl.model.COPY;

public class Type {
	protected String id = SRLModelUtils.EMPTY_STRING;
	protected String name = SRLModelUtils.EMPTY_STRING;
	protected Type parent;
	
	
	public Type() {
		super();
		this.id = System.nanoTime()+"";
	}
	
	
	public Type(String id, String name, Type parent) {
		super();
		this.id = id;
		this.name = name;
		this.parent = parent;
	}
	

	

	public Type(String name) {
		this.name = name;
		this.id = System.nanoTime()+"";
	}


	public Type(String id, String name) {
		this.id = id;
		this.name = name;
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
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
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
		Type other = (Type) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	
}
