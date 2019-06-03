package com.reusecompany.srl.services.impl;

import java.util.LinkedList;
import java.util.List;

import com.reusecompany.srl.model.Artifact;
import com.reusecompany.srl.services.DataServices;

public class NaiveDataServicesImpl implements DataServices {
	
	private List<Artifact> staticList = createArtifactList();

	
	private static List<Artifact> createArtifactList(){
		List<Artifact> artifacts = new LinkedList();
		Artifact artifact;
		for(int i = 0; i<5; i++){
			artifact = new Artifact();
			artifact.setId(""+i);
			artifact.setTitle("Title of "+i);
			artifact.setDescription("Description of "+i);
			artifacts.add(artifact);
		}
		return artifacts;
	}
	
	public List<Artifact> list() {
		return staticList;
	}

	public Artifact getById(String id) {
		Artifact searched = Artifact.EMPTY_ARTIFACT;
		if(id != null){
			searched = staticList.stream().filter(a -> a.getId().equals(id)).findAny().get();
		}
		return searched;
	}

}
