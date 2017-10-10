package com.reusecompany.oslc.km.services.facade;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.reusecompany.oslc.km.srl.Artifact;
import com.reusecompany.oslc.km.srl.MetaProperty;
import com.reusecompany.oslc.km.srl.RSHP;
import com.reusecompany.oslc.km.srl.Term;

public class SystemAssetManagementFacadeNaiveImpl implements SystemAssetManagementFacade {

	protected static Logger logger = Logger.getLogger(SystemAssetManagementFacadeNaiveImpl.class);

	Map<String,Artifact> artifacts;
	
	public SystemAssetManagementFacadeNaiveImpl(){
		this.artifacts = createArtifacts();
	}
	
	@Override
	public List<Artifact> getArtifacts() {
		logger.info("Getting all artifacts");
		return new ArrayList(this.artifacts.values());
	}

	@Override
	public Artifact getArtifactById(int id) {
		logger.info("Getting artifact with id: "+id);
		Artifact artifact = null;
		if(this.artifacts.containsKey(""+id)){
			artifact = this.artifacts.get(""+id);
		}else{
			artifact = Artifact.EMPTY_ARTIFACT;
		}
		return artifact;
	}

	@Override
	public List<Term> getArtifactTermsById(int id) {
		logger.info("Getting artifact terms with id: "+id);
		Artifact artifact = this.getArtifactById(id);
		List<Term> terms = new LinkedList<Term>();
		terms.add(artifact.getTerm());
		for(RSHP rshp:artifact.getRshps()){
			terms.add(rshp.getTo().getTerm());
			terms.add(rshp.getFrom().getTerm());
		}
		for(Artifact subartifact:artifact.getOwnedArtifactsAsList()){
			terms.add(subartifact.getTerm());
		}
		logger.info("Returning "+terms.size()+" terms for artifact "+id);
		return terms;
	}

	@Override
	public List<MetaProperty> getArtifactMetaPropertiesById(int id) {
		logger.info("Getting artifact metaproperties with id: "+id);
		Artifact artifact = this.getArtifactById(id);
		return artifact.getMetaPropertiesAsList();
	}

	@Override
	public List<Artifact> getArtifactSubArtifactsById(int id) {
		logger.info("Getting artifact subartifacts with id: "+id);
		Artifact artifact = this.getArtifactById(id);
		return artifact.getOwnedArtifactsAsList();
	}

	@Override
	public Artifact getArtifactSubArtifactsById(int id, int idSubartifact) {
		logger.info("Getting artifact with id: "+id+" and sub-artifact with id: "+idSubartifact);
		try{
			Artifact subartifact = null;
			List<Artifact> subArtifacts = getArtifactSubArtifactsById(id);
			System.out.println(subArtifacts);
			subartifact = subArtifacts.stream().filter(a -> a.getIdentifier().equals(""+idSubartifact)).findFirst().get();
			return (subartifact);
		}catch(Exception e){
			logger.error(e);
		}
		return Artifact.EMPTY_ARTIFACT;
		
	}
	
	protected static Map<String,Artifact> createArtifacts(){
		Map<String,Artifact> artifacts = new HashMap<String,Artifact>();
		Artifact artifact;
		Artifact subArtifact;
		for(int i = 0; i<5;i++){
			artifact = new Artifact("Artifact "+i);
			artifact.setIdentifier(""+i);
			for(int j = 0; j<3;j++){
				subArtifact = new Artifact("Subartifact "+j);
				subArtifact.setIdentifier(""+j);
				artifact.getOwnedArtifactsAsList().add(subArtifact);
			}
			
			artifacts.put(artifact.getIdentifier(), artifact);
		}
		return artifacts;
	}

}
