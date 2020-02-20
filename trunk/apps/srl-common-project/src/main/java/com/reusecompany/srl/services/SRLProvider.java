package com.reusecompany.srl.services;

import java.util.List;

import com.reusecompany.srl.model.Artifact;
import com.reusecompany.srl.model.Type;

public interface SRLProvider {

	public byte open(Artifact configuration);
	public byte persist(Artifact configuration);
	public byte close();

	public String getSRLName();
	public String getSRLDescription();
	public String getSRLIcon();
	public String getVersion();
	public List<Type> getSourceInformationTypes();


}
