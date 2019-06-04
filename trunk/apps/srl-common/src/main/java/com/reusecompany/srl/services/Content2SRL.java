package com.reusecompany.srl.services;

import java.util.List;

import com.reusecompany.srl.model.*;

public interface Content2SRL {
	

	public List<Artifact> getRootArtifacts();
	public List<Artifact> list();
	public List<Type> types();
	
	public Artifact getArtifactById(String id);
	public List<Artifact> getArtifactsByType(Type t);
	public List<Artifact> filterArtifacts(Artifact parent, Artifact filterConditions);
	public Artifact getProductContent(Artifact artifact, Artifact filterConditions);
	
	public byte add(Artifact artifact, Artifact parent);
	public byte delete(Artifact artifact);
	public byte delete(String id);

	public byte deleteAll(List<Artifact> artifacts);
	public byte delete(List<String> artifactIds);
	
	public byte update (Artifact artifact);
	public byte updateAll(List<Artifact> artifacts);
	public byte updateAllOnlyChanges(List<Artifact> artifacts);
	
	public byte[] plot(Artifact artifact);
	
	public List<OperationServices> getSupportedOperations();
}
