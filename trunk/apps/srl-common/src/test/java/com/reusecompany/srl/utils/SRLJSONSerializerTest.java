package com.reusecompany.srl.utils;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.IOException;

import org.junit.Test;

import com.reusecompany.srl.model.Artifact;
import com.reusecompany.srl.model.Relationship;
import com.reusecompany.srl.model.Type;

public class SRLJSONSerializerTest {

	@Test
	public void testToJson() {
		Artifact artifact = createArtifact();
		try {
			String jsonArtifact = SRLJSONSerializer.toJson(artifact);
			assertNotNull(jsonArtifact);
			Artifact readed = SRLJSONSerializer.toArtifact(jsonArtifact);
			assertEquals(artifact.getId(), readed.getId());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

private static Artifact createArtifact(){
		
		Artifact rootArtifact = new Artifact();
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
		
		Type classBlock = new Type("Class Block");
		classBlock.setId("111");
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
		
//		List<Artifact> artifacts = new LinkedList<Artifact>();
//		artifacts.add(classCar);
//		artifacts.add(classWheel);
//		artifacts.add(classDiagram);
//		artifacts.add(logicalModel);
		return rootArtifact;
	}
}
