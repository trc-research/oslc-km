package com.reusecompany.srl.web.api;

import java.util.Collections;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.reusecompany.srl.appserv.ContentAppServ;
import com.reusecompany.srl.model.Artifact;
import com.reusecompany.srl.model.Type;
import com.reusecompany.srl.services.OperationServices;


@Scope("request")
@CrossOrigin
@RestController
@RequestMapping("/services/content")
public class ContentServicesAPI {

	protected static Logger logger = Logger.getLogger(ContentServicesAPI.class);

	@Autowired
	ContentAppServ contentAppServ;

	public ContentServicesAPI() {

	}

	@RequestMapping(value = "/greeting", method = RequestMethod.GET)
	public String greeting() {
		return "Hello!";
	}


	@RequestMapping(value = "/artifacts", method = RequestMethod.GET, produces = "application/json")
	public List<Artifact> list() {
		try{	
			logger.debug("Getting all artifacts");
			List<Artifact> clients =  contentAppServ.list();
			logger.debug("Sucessfull get all artifacts");
			return clients;
		}catch(Exception e){
			throw new ResponseStatusException(
					HttpStatus.INTERNAL_SERVER_ERROR, 
					"Problem listing all artifacts", e);

		}
	}

	@RequestMapping(value = "/artifacts/{id}", method = RequestMethod.GET, produces = "application/json")
	public Artifact getById(@PathVariable(value = "id") String id){
		try{
			logger.debug("Getting artifacts with id: "+id);
			return this.contentAppServ.getArtifactById(id);	
		}catch(Exception e){
			throw new ResponseStatusException(
					HttpStatus.INTERNAL_SERVER_ERROR, 
					"Problem getting artifact with id: "+id, e);

		}

	}

	@RequestMapping(value = "/artifacts/root", method = RequestMethod.GET, produces = "application/json")
	public List<Artifact> getRootArtifacts() {
		try{
			logger.debug("Getting root artifacts");
			return this.contentAppServ.getRootArtifacts();
		}catch(Exception e){
			throw new ResponseStatusException(
					HttpStatus.INTERNAL_SERVER_ERROR, 
					"Problem getting root artifacts", e);

		}
	}

	@RequestMapping(value = "/types", method = RequestMethod.GET, produces = "application/json")
	public List<Type> types() {
		try{
			logger.debug("Getting types");
			List<Type> types =  this.contentAppServ.types();
			if (types==null){
				types = Collections.EMPTY_LIST;
			}
			return types;
		}catch(Exception e){
			throw new ResponseStatusException(
					HttpStatus.INTERNAL_SERVER_ERROR, 
					"Problem getting types", e);

		}
	}


	@RequestMapping(value = "/artifacts/typeName", method = RequestMethod.GET, produces = "application/json")
	public List<Artifact> getArtifactsByTypeName(@RequestParam String name) {
		try{
			logger.debug("Artifacts by type with name: "+name);
			return this.contentAppServ.getArtifactsByTypeName(name);
		}catch(Exception e){
			throw new ResponseStatusException(
					HttpStatus.INTERNAL_SERVER_ERROR, 
					"Problem getting artifacts by type with name: "+name, e);

		}
	}

	@RequestMapping(value = "/artifacts/typeId", method = RequestMethod.GET, produces = "application/json")
	public List<Artifact> getArtifactsByTypeId(@RequestParam String id) {
		try{
			logger.debug("Artifacts by type with id: "+id);
			return this.contentAppServ.getArtifactsByTypeId(id);
		}catch(Exception e){
			throw new ResponseStatusException(
					HttpStatus.INTERNAL_SERVER_ERROR, 
					"Problem getting artifacts by type with id: "+id, e);

		}
	}


	@RequestMapping(value = "/artifacts/parent", method = RequestMethod.GET, produces = "application/json")
	public List<Artifact> filterArtifacts(
			@RequestParam String id, 
			@RequestBody Artifact filterConditions) {
		try{
			logger.debug("Artifacts with parent id: "+id+ "and filter conditions: "+filterConditions);
			Artifact parent = this.contentAppServ.getArtifactById(id);
			logger.debug("Parent id has been found");
			return this.contentAppServ.filterArtifacts(parent, filterConditions);
		}catch(Exception e){
			throw new ResponseStatusException(
					HttpStatus.INTERNAL_SERVER_ERROR, 
					"Problem getting types with parent: "+id, e);

		}
	}

	@RequestMapping(value = "/artifacts/{id}", params = "id", method = RequestMethod.GET, produces = "application/json")
	public Artifact getProductContent(@PathVariable(value = "id") String id, 
			@RequestBody Artifact filterConditions) {
		try{
			logger.debug("Getting artifact with id: "+id);
			Artifact artifact = this.contentAppServ.getArtifactById(id);
			return this.contentAppServ.getProductContent(artifact, filterConditions);
		}catch(Exception e){
			throw new ResponseStatusException(
					HttpStatus.INTERNAL_SERVER_ERROR, 
					"Problem getting types with cid: "+id, e);

		}
	}
	//
	//
	//	public byte add(Artifact artifact, Artifact parent) {
	//		return this.contentAppServ.add(artifact, parent);
	//	}
	//
	//
	//	public byte delete(Artifact artifact) {
	//		return this.contentAppServ.delete(artifact);
	//	}
	//
	//
	//	public byte delete(String id) {
	//		return this.contentAppServ.delete(id);
	//	}
	//
	//	public byte deleteAll(List<Artifact> artifacts) {
	//		return this.contentAppServ.deleteAll(artifacts);
	//	}
	//
	//	public byte delete(List<String> artifactIds) {
	//		return this.contentAppServ.delete(artifactIds);
	//	}
	//
	//
	//	public byte update(Artifact artifact) {
	//		return this.contentAppServ.update(artifact);
	//	}
	//
	//
	//	public byte updateAll(List<Artifact> artifacts) {
	//		return this.contentAppServ.updateAll(artifacts);
	//	}
	//
	//	public byte updateAllOnlyChanges(List<Artifact> artifacts) {
	//		return this.contentAppServ.updateAllOnlyChanges(artifacts);
	//	}
	//
	//	public byte[] plot(Artifact artifact) {
	//		return this.contentAppServ.plot(artifact);	
	//	}


	@RequestMapping(value = "/artifacts/{id}/operations", method = RequestMethod.GET, produces = "application/json")
	public List<OperationServices> getSupportedOperations(@PathVariable(value = "id") String id) {
		try{
			logger.debug("Getting operations of artifact with id: "+id);
			return this.contentAppServ.getSupportedOperations();	
		}catch(Exception e){
			throw new ResponseStatusException(
					HttpStatus.INTERNAL_SERVER_ERROR, 
					"Problem getting types with cid: "+id, e);

		}
	}


}
