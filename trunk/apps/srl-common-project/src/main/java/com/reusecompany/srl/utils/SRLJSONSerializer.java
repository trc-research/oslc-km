package com.reusecompany.srl.utils;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.reusecompany.srl.model.Artifact;

public class SRLJSONSerializer {
	
	public static String toJson(Artifact artifact) throws JsonProcessingException{
		String serialized = new ObjectMapper().writeValueAsString(artifact);	
		return serialized;
	}
	
	public static Artifact toArtifact(String jsonArtifact) throws IOException{
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		Artifact artifact = mapper.readValue(jsonArtifact, Artifact.class);
		return artifact;
	}

}
