package com.reusecompany.oslc.km.services.facade;

import java.util.List;

import com.reusecompany.oslc.km.srl.Artifact;
import com.reusecompany.oslc.km.srl.MetaProperty;
import com.reusecompany.oslc.km.srl.Term;

public interface SystemAssetManagementFacade {

	public List<Artifact> getArtifacts();
	public Artifact getArtifactById(int id);
	public List<Term> getArtifactTermsById(int id);
	public List<MetaProperty> getArtifactMetaPropertiesById(int id);
	public List<Artifact> getArtifactSubArtifactsById(int id);
	public Artifact getArtifactSubArtifactsById(int id,int idSubartifact);
	
}
