package com.reusecompany.srl.services;

import java.util.List;

import com.reusecompany.srl.model.Artifact;

public interface OperationServices {
	
	public Artifact describeOperation();

	public List<Artifact> execute(Artifact configutaion);
}
