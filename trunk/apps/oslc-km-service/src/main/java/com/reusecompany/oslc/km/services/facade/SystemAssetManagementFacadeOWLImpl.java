package com.reusecompany.oslc.km.services.facade;

import java.io.InputStream;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.hp.hpl.jena.ontology.OntDocumentManager;
import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.ontology.OntModelSpec;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.reusecompany.oslc.km.exceptions.ConceptNotFoundException;
import com.reusecompany.oslc.km.services.facade.impl.OWL2SRLVisitor;
import com.reusecompany.oslc.km.srl.Artifact;
import com.reusecompany.oslc.km.srl.MetaProperty;
import com.reusecompany.oslc.km.srl.RSHP;
import com.reusecompany.oslc.km.srl.Term;

public class SystemAssetManagementFacadeOWLImpl implements SystemAssetManagementFacade {

	protected static Logger logger = Logger.getLogger(SystemAssetManagementFacadeOWLImpl.class);

	Map<String,Artifact> artifacts;
	
	public SystemAssetManagementFacadeOWLImpl(){
		artifacts = new HashMap<String,Artifact>();
		init();
	}

	/**
	 * 
	 */
	public void init() {
		InputStream is = Thread.currentThread().getContextClassLoader().
				getResourceAsStream("ontology/CAR.owl");
		OntDocumentManager dm = OntDocumentManager.getInstance();
		dm.setProcessImports(false);
		OntModelSpec spec = new OntModelSpec(OntModelSpec.OWL_DL_MEM);
		spec.setDocumentManager(dm);        
		OntModel ontModel = ModelFactory.createOntologyModel( spec, null );
		ontModel.read(is,"","TURTLE");
		try {
			OWL2SRLVisitor visitor = new OWL2SRLVisitor();
			Artifact art = (Artifact) visitor.visit(ontModel);
			int i = 0;
			this.artifacts.put(""+(i++), art);
			for(Artifact a:art.getOwnedArtifacts()){
				this.artifacts.put(""+(i++), a);	
			}
			
		} catch (ConceptNotFoundException e) {
			logger.error(e);
		} catch (Exception e) {
			logger.error(e);
		}
	}
	

	public List<Artifact> getArtifacts() {
		logger.info("Getting all artifacts");
		return new ArrayList(this.artifacts.values());
	}

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


	public List<MetaProperty> getArtifactMetaPropertiesById(int id) {
		logger.info("Getting artifact metaproperties with id: "+id);
		Artifact artifact = this.getArtifactById(id);
		return artifact.getMetaPropertiesAsList();
	}


	public List<Artifact> getArtifactSubArtifactsById(int id) {
		logger.info("Getting artifact subartifacts with id: "+id);
		Artifact artifact = this.getArtifactById(id);
		return artifact.getOwnedArtifactsAsList();
	}


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
