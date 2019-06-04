package com.reusecompany.srl.services.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.reusecompany.srl.model.Artifact;
import com.reusecompany.srl.model.Relationship;
import com.reusecompany.srl.model.SRLModelUtils;
import com.reusecompany.srl.model.Type;
import com.reusecompany.srl.services.Content2SRL;
import com.reusecompany.srl.services.OperationServices;

@Component
public class NaiveContentServicesImpl implements Content2SRL {
	
	private List<Artifact> staticList = createArtifactList();
	private static Artifact rootArtifact;
	
	private static List<Artifact> createArtifactList(){
		
		rootArtifact = new Artifact();
		rootArtifact.setId("1");
		rootArtifact.setName("Project");
		rootArtifact.setType(new Type("project"));

		Artifact logicalModel = new Artifact();
		logicalModel.setId("11");
		logicalModel.setName("Logical model");
		logicalModel.setType(new Type("Logical model"));
		
		Artifact classDiagram = new Artifact();
		classDiagram.setId("111");
		classDiagram.setName("Class diagram");
		classDiagram.setType(new Type("Class diagram"));
		
		Type classBlock = new Type("classBlock");
		Artifact classCar = new Artifact();
		classCar.setId("1111");
		classCar.setName("Car");
		classCar.setType(classBlock);
		
		Artifact classWheel = new Artifact();
		classWheel.setId("1112");
		classWheel.setName("Wheel");
		classWheel.setType(classBlock);
		
		Relationship rshp = new Relationship();
		rshp.setFrom(classCar);
		rshp.setTo(classWheel);
		rshp.setMultiplicityToY(4);
		
		classDiagram.getOwnedArtifacts().add(classCar);
		classDiagram.getOwnedArtifacts().add(classWheel);
		classDiagram.getRelationships().add(rshp);
		
		logicalModel.getOwnedArtifacts().add(classDiagram);
		rootArtifact.getOwnedArtifacts().add(logicalModel);
		
		List<Artifact> artifacts = new LinkedList<Artifact>();
		artifacts.add(classCar);
		artifacts.add(classWheel);
		artifacts.add(classDiagram);
		artifacts.add(logicalModel);
		return artifacts;
	}
	
	public List<Artifact> list() {
		return staticList;
	}

	

	@Override
	public List<Artifact> getRootArtifacts() {
		List<Artifact> rootArtifacts = new LinkedList<Artifact>();
		rootArtifacts.add(rootArtifact);
		return rootArtifacts;
	}

	@Override
	public List<Type> types() {
		Set<Type> types = staticList.stream().map(Artifact::getType).collect(Collectors.toSet());
		return new ArrayList<Type>(types);
	}


	@Override
	public List<Artifact> getArtifactsByType(Type t) {
		 List<Artifact> artifacts = 
				 staticList.stream().filter(x -> t.equals(x.getType())).collect(Collectors.toList());
		if(artifacts == null){
			artifacts = Collections.EMPTY_LIST;
		}
		return artifacts;
	}

	@Override
	public List<Artifact> filterArtifacts(Artifact parent, Artifact filterConditions) {
		return Collections.EMPTY_LIST;
	}

	@Override
	public Artifact getProductContent(Artifact artifact, Artifact filterConditions) {
		Artifact searched = SRLModelUtils.EMPTY_ARTIFACT;
		if(artifact != null){
			searched = staticList.stream().filter(a -> a.getId().equals(artifact.getId())).findAny().get();
		}
		return searched;
	}

	@Override
	public byte add(Artifact artifact, Artifact parent) {
		return 0;
	}

	@Override
	public byte delete(Artifact artifact) {
		return (byte) (staticList.remove(artifact)?1:0);
	}

	@Override
	public byte delete(String id) {
		Artifact artifact = this.getArtifactById(id);
		if(artifact != null){
			return this.delete(artifact);
		}
		return 0;
	}

	@Override
	public byte deleteAll(List<Artifact> artifacts) {
		if(artifacts != null){
			for(Artifact artifact: artifacts){
				this.delete(artifact);
			}
		}
		return 1;
	}

	@Override
	public byte delete(List<String> artifactIds) {
		if(artifactIds != null){
			for(String artifactId: artifactIds){
				this.delete(artifactId);
			}
		}
		return 1;
	}

	@Override
	public byte update(Artifact artifact) {
		return 0;
	}

	@Override
	public byte updateAll(List<Artifact> artifacts) {
		return 0;
	}

	@Override
	public byte updateAllOnlyChanges(List<Artifact> artifacts) {
		return 0;
	}

	@Override
	public byte[] plot(Artifact artifact) {
		return new byte[0];
	}

	@Override
	public List<OperationServices> getSupportedOperations() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Artifact getArtifactById(String id) {
		Artifact searched = SRLModelUtils.EMPTY_ARTIFACT;
		if(id != null){
			searched = staticList.stream().filter(a -> a.getId().equals(id)).findAny().get();
		}
		return searched;
	}

}
