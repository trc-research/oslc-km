package com.reusecompany.srl.appserv;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.reusecompany.srl.model.Artifact;
import com.reusecompany.srl.model.Type;
import com.reusecompany.srl.services.Content2SRL;
import com.reusecompany.srl.services.OperationServices;

@Component
public class ContentAppServ {

	@Autowired
	Content2SRL contentServices;
	
	public ContentAppServ(){
		
	}

	public ContentAppServ(Content2SRL ds){
		if(ds != null){
			this.contentServices = ds;
		}
	}

	public List<Artifact> list(){
		return this.contentServices.list();
	}
	public Artifact getById(String id){
		return this.contentServices.getArtifactById(id);
	}
	
	public List<Artifact> getRootArtifacts() {
		return this.contentServices.getRootArtifacts();
	}


	public List<Type> types() {
		return this.contentServices.types();
	}



	public List<Artifact> getArtifactsByType(Type t) {
		return this.contentServices.getArtifactsByType(t);
	}


	public List<Artifact> filterArtifacts(Artifact parent, Artifact filterConditions) {
		return this.contentServices.filterArtifacts(parent, filterConditions);
	}

	
	public Artifact getProductContent(Artifact artifact, Artifact filterConditions) {
		return this.contentServices.getProductContent(artifact, filterConditions);
	}


	public byte add(Artifact artifact, Artifact parent) {
		return this.contentServices.add(artifact, parent);
	}

	
	public byte delete(Artifact artifact) {
		return this.contentServices.delete(artifact);
	}


	public byte delete(String id) {
		return this.contentServices.delete(id);
	}

	public byte deleteAll(List<Artifact> artifacts) {
		return this.contentServices.deleteAll(artifacts);
	}

	public byte delete(List<String> artifactIds) {
		return this.contentServices.delete(artifactIds);
	}


	public byte update(Artifact artifact) {
		return this.contentServices.update(artifact);
	}


	public byte updateAll(List<Artifact> artifacts) {
		return this.contentServices.updateAll(artifacts);
	}

	public byte updateAllOnlyChanges(List<Artifact> artifacts) {
		return this.contentServices.updateAllOnlyChanges(artifacts);
	}

	public byte[] plot(Artifact artifact) {
		return this.contentServices.plot(artifact);	
		}

	
	public List<OperationServices> getSupportedOperations() {
		return this.contentServices.getSupportedOperations();	
	}


	public Artifact getArtifactById(String id) {
		return this.contentServices.getArtifactById(id);
	}
	
}
