package com.reusecompany.srl.utils;

import java.io.FileWriter;
import java.util.ArrayList;
import java.io.IOException;

import java.nio.file.Files;
import java.nio.file.Paths;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.reusecompany.srl.model.Artifact;
import com.reusecompany.srl.model.ArtifactType;
import com.reusecompany.srl.model.Relationship;

public class SRLGsonSerializerTest {
	
	private static Artifact createArtifact(){

		Artifact rootArtifact = new Artifact();
		rootArtifact.setIdentifier("1");
		rootArtifact.setName("Project");
		rootArtifact.setType(new ArtifactType("project"));
		rootArtifact.setDescription("This is the first project");
		
		Artifact logicalModel = new Artifact();
		logicalModel.setIdentifier("11");
		logicalModel.setName("Logical model");
		logicalModel.setType(new ArtifactType("Logical model"));
		
		Artifact classDiagram = new Artifact();
		classDiagram.setIdentifier("111");
		classDiagram.setName("Class diagram");
		classDiagram.setType(new ArtifactType("Class diagram"));
		
		ArtifactType classBlock = new ArtifactType("Class Block");
		classBlock.setIdentifier("111");
		classBlock.setAdditionalInformation("cosas random");
		
		Artifact classCar = new Artifact();
		classCar.setIdentifier("1111");
		classCar.setName("Car");
		classCar.setType(classBlock);
		
		Artifact classWheel = new Artifact();
		classWheel.setIdentifier("1112");
		classWheel.setName("Wheel");
		classWheel.setType(classBlock);
		
		Relationship rshp = new Relationship();
		rshp.setName("RELACION EN MAYUSCULAS!");
		rshp.setFrom(classCar);
		rshp.setTo(classWheel);
		rshp.setMultiplicityToY("4");
		
		classDiagram.getOwnedArtifacts().add(classCar);
		classDiagram.getOwnedArtifacts().add(classWheel);
		classDiagram.getRelationships().add(rshp);

		logicalModel.getOwnedArtifacts().add(classDiagram);
		rootArtifact.getOwnedArtifacts().add(logicalModel);
		
		ArrayList<Artifact> artifacts = new ArrayList<Artifact>();
		artifacts.add(classCar);
		artifacts.add(classWheel);
		artifacts.add(classDiagram);
		artifacts.add(logicalModel);
		return rootArtifact;
	}
	
	public static void main(String[] args) {
	
		Artifact artifact1 = createArtifact();
		
		// Serialization
		
		Gson gsonBuilder = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().serializeNulls().create();
		
		String jsonString = gsonBuilder.toJson(artifact1);
		
		System.out.println(jsonString.replaceAll("\"", "\\\\\""));
		
		FileWriter jsonFile = null;
		
		try {
			jsonFile = new FileWriter("D:/GsonToJson.json");
			jsonFile.write(jsonString);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (jsonFile != null) {
					jsonFile.flush();
					jsonFile.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		System.out.println("");
		
		// Deserialization JAVA
		
		String jsonLectura = null;
		
		try {
			jsonLectura = new String(Files.readAllBytes(Paths.get("D:/GsonToJson.json")));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		Artifact artifactRecuperadoJava = null;
		
		artifactRecuperadoJava = gsonBuilder.fromJson(jsonLectura, Artifact.class);
		
		// Volver a mostrar el Artifact recuperado 
		
		String resultD = gsonBuilder.toJson(artifactRecuperadoJava);
		
		System.out.println(resultD.replaceAll("\"", "\\\\\""));
		
		System.out.println("");
	}
}
