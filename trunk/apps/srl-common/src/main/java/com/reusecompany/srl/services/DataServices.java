package com.reusecompany.srl.services;

import java.util.List;

import com.reusecompany.srl.model.*;

public interface DataServices {
	
	public List<Artifact> list();
	public Artifact getById(String id);

}
