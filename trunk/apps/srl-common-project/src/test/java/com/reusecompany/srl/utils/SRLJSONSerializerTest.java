package com.reusecompany.srl.utils;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import org.junit.Test;

import com.reusecompany.srl.model.Artifact;
import com.reusecompany.srl.model.ArtifactType;
import com.reusecompany.srl.model.Relationship;
import com.reusecompany.srl.model.RelationshipSemanticsType;

public class SRLJSONSerializerTest {

	@Test
	public void testToJson() {
		Artifact artifact = createArtifact();
		try {
			String jsonArtifact = SRLJSONSerializer.toJson(artifact);
			assertNotNull(jsonArtifact);
			Artifact readed = SRLJSONSerializer.toArtifact(jsonArtifact);
			assertEquals(artifact.getIdentifier(), readed.getIdentifier());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static Artifact createArtifact(){
		
		Artifact rootArtifact = new Artifact();
		rootArtifact.setIdentifier("1");
		rootArtifact.setName("Project");
		rootArtifact.setType(new ArtifactType("project"));

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
		Artifact classCar = new Artifact();
		classCar.setIdentifier("1111");
		classCar.setName("Car");
		classCar.setType(classBlock);
		
		Artifact classWheel = new Artifact();
		classWheel.setIdentifier("1112");
		classWheel.setName("Wheel");
		classWheel.setType(classBlock);
		
		Relationship rshp = new Relationship();
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

	@Test
	public void testToFile() {
		
		Artifact interop = createInteropArtifact();
		try {
			String jsonArtifact = SRLJSONSerializer.toJson(interop);
			assertNotNull(jsonArtifact);
			//save to file
			try (PrintWriter out = new PrintWriter("d:\\srljsontest1.json")) {
			    out.println(jsonArtifact);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}		
	}
	
	private static Artifact createInteropArtifact() {
	
		//create container artifact
		Artifact rootArtifact = new Artifact();
		rootArtifact.setIdentifier("R1");
		rootArtifact.setName("Result 1");
		rootArtifact.setType(new ArtifactType("Result"));
		
		//un onwed artifact
		Artifact childArtifact = new Artifact();
		childArtifact.setIdentifier("Child1");
		childArtifact.setName("Passenger");
		childArtifact.setType(new ArtifactType("Actor"));	
		
		//rootArtifact.getOwnedArtifacts().add(childArtifact);
		
		Artifact childArtifact2 = new Artifact();
		childArtifact2.setIdentifier("Child2");
		childArtifact2.setName("Watch Movie");
		childArtifact2.setType(new ArtifactType("Operational Capability"));	
		
		//una rshp Art-Art
		Relationship rshp = new Relationship();
		rshp.setFrom(childArtifact);
		rshp.setTo(childArtifact2);
		rshp.setType(new RelationshipSemanticsType("Boundary"));
		
		rootArtifact.getRelationships().add(rshp);
		
		//Data simple
//		Property prop = new Property("Prop1", "MetaProp1");
//		childArtifact.getData().add(prop);
		
		//Meta simple
//		MetaData data = new MetaData("Meta1", "MetaData1");
//		childArtifact.getMetadata().add(data);
		
		return rootArtifact;
	}
	
	public static final String jsonFromJava = "{\"id\":\"R1\",\"name\":\"Result 1\",\"description\":\"\",\"content\":\"\",\"snapShot\":\"\",\"physicalPath\":\"\",\"physicalName\":\"\",\"term\":null,\"type\":{\"id\":\"6596842010700\",\"name\":\"Result\",\"parent\":null},\"relationships\":[{\"id\":\"\",\"name\":\"\",\"description\":\"\",\"from\":{\"id\":\"Child1\",\"name\":\"Passenger\",\"description\":\"\",\"content\":\"\",\"snapShot\":\"\",\"physicalPath\":\"\",\"physicalName\":\"\",\"term\":null,\"type\":{\"id\":\"6596842040600\",\"name\":\"Actor\",\"parent\":null},\"relationships\":[],\"metadata\":[{\"key\":\"Meta1\",\"value\":\"MetaData1\",\"operator\":\"Equal\",\"valueType\":\"String\"}],\"data\":[{\"key\":\"Prop1\",\"value\":\"MetaProp1\",\"operator\":\"Equal\",\"valueType\":\"String\"}],\"ownedArtifacts\":[],\"ownedTerms\":[]},\"to\":{\"id\":\"Child2\",\"name\":\"Watch Movie\",\"description\":\"\",\"content\":\"\",\"snapShot\":\"\",\"physicalPath\":\"\",\"physicalName\":\"\",\"term\":null,\"type\":{\"id\":\"6596842047300\",\"name\":\"Operational Capability\",\"parent\":null},\"relationships\":[],\"metadata\":[],\"data\":[],\"ownedArtifacts\":[],\"ownedTerms\":[]},\"type\":{\"id\":\"6596843036500\",\"name\":\"Boundary\",\"parent\":null},\"subType\":{\"id\":\"6596841880300\",\"name\":null,\"parent\":null},\"interfaceFrom\":\"\",\"interfaceTo\":\"\",\"multiplicityFromX\":1,\"multiplicityFromY\":1,\"multiplicityToX\":1,\"multiplicityToY\":1,\"symmetric\":false}],\"metadata\":[],\"data\":[],\"ownedArtifacts\":[],\"ownedTerms\":[]}";
	public static final String jsonFromNet = "{\"$id\":\"1\",\"relationships\":[{\"$id\":\"2\",\"from\":{\"$id\":\"3\",\"relationships\":[],\"metadata\":[{\"$id\":\"4\",\"Table MetaData\":null,\"key\":\"Meta1\",\"value\":\"MetaData1\",\"complex key\":null,\"complex value\":null,\"value Type\":2,\"property Operation\":0,\"complex ID\":null,\"version\":\"2\",\"state\":0,\"id\":\"\"}],\"data\":[{\"$id\":\"5\",\"Table Data\":null,\"key\":\"Prop1\",\"value\":\"MetaProp1\",\"complex key\":null,\"complex value\":null,\"value Type\":2,\"property Operation\":0,\"complex ID\":null,\"version\":\"2\",\"state\":0,\"id\":\"\"}],\"ownedArtifacts\":[],\"ownedTerms\":[],\"type\":{\"$id\":\"6\",\"parent\":null,\"name\":\"Actor\",\"version\":\"2\",\"state\":0,\"id\":\"6596842040600\"},\"name\":\"Passenger\",\"description\":\"\",\"content\":\"\",\"snapShot\":\"\",\"physicalPath\":\"\",\"physicalName\":\"\",\"sparqlEndpoint\":null,\"rdfContent\":null,\"term\":null,\"version\":\"2\",\"state\":0,\"id\":\"Child1\"},\"to\":{\"$id\":\"7\",\"relationships\":[],\"metadata\":[],\"data\":[],\"ownedArtifacts\":[],\"ownedTerms\":[],\"type\":{\"$id\":\"8\",\"parent\":null,\"name\":\"Operational Capability\",\"version\":\"2\",\"state\":0,\"id\":\"6596842047300\"},\"name\":\"Watch Movie\",\"description\":\"\",\"content\":\"\",\"snapShot\":\"\",\"physicalPath\":\"\",\"physicalName\":\"\",\"sparqlEndpoint\":null,\"rdfContent\":null,\"term\":null,\"version\":\"2\",\"state\":0,\"id\":\"Child2\"},\"type\":{\"$id\":\"9\",\"parent\":null,\"name\":\"Boundary\",\"version\":\"2\",\"state\":0,\"id\":\"6596843036500\"},\"subType\":{\"$id\":\"10\",\"parent\":null,\"name\":null,\"version\":\"2\",\"state\":0,\"id\":\"6596841880300\"},\"name\":\"\",\"description\":\"\",\"interfaceFrom\":\"\",\"interfaceTo\":\"\",\"multiplicityFromX\":\"1\",\"multiplicityFromY\":\"1\",\"multiplicityToX\":\"1\",\"multiplicityToY\":\"1\",\"symmetric\":false,\"version\":\"2\",\"state\":0,\"id\":\"\"}],\"metadata\":[],\"data\":[],\"ownedArtifacts\":[],\"ownedTerms\":[],\"type\":{\"$id\":\"11\",\"parent\":null,\"name\":\"Result\",\"version\":\"2\",\"state\":0,\"id\":\"6596842010700\"},\"name\":\"Result 1\",\"description\":\"\",\"content\":\"\",\"snapShot\":\"\",\"physicalPath\":\"\",\"physicalName\":\"\",\"sparqlEndpoint\":null,\"rdfContent\":null,\"term\":null,\"version\":\"2\",\"state\":0,\"id\":\"R1\"}";

	@Test
	public void testInterop() {
		
		try {
			Artifact art = SRLJSONSerializer.toArtifact(jsonFromNet);
			String json = SRLJSONSerializer.toJson(art);
			
			assertEquals(jsonFromJava, json);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}