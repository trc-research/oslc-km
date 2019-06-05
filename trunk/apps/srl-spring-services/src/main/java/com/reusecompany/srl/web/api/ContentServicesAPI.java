package com.reusecompany.srl.web.api;

import java.math.BigInteger;
import java.util.Collections;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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


	@PostMapping(value = "/artifacts", produces = "application/json")
	public ResponseEntity <String> add(@RequestBody Artifact artifact,
			@RequestParam String parentId) {
		try{
			Artifact parent = this.contentAppServ.getArtifactById(parentId);
			if (parent != null){
				this.contentAppServ.add(artifact, parent);
				return ResponseEntity.status(HttpStatus.CREATED).build();	
			}
		}catch(Exception e){
			logger.debug("Saving artifact: "+artifact);
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
	}


	@DeleteMapping(value = "/artifacts/{id}")
	public ResponseEntity <String> delete(@PathVariable String id) {
		try{
			Artifact artifact = this.contentAppServ.getArtifactById(id);
			if (artifact != null){
				if(this.contentAppServ.delete(artifact)==0){
					return ResponseEntity.status(HttpStatus.OK).build();	
				}
			}
		}catch(Exception e){
			logger.debug("Removing artifact with id: "+id);
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
	}

	//	@PostMapping(value = "/artifacts", produces = "application/json")
	//	public ResponseEntity <String> deleteAll(@RequestBody List<Artifact> artifacts) {
	//		try{
	//			if(artifacts != null){
	//				if(this.contentAppServ.deleteAll(artifacts)==0){
	//					return ResponseEntity.status(HttpStatus.OK).build();	
	//				}
	//			}
	//		}catch(Exception e){
	//			logger.debug("Removing artifacts: "+artifacts);
	//		}
	//		return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
	//	}

	//	@PostMapping(value = "/artifacts", produces = "application/json")
	//	public ResponseEntity <String> delete(@RequestBody List<String> artifactIds) {
	//		try{
	//			if(artifactIds != null){
	//				if(this.contentAppServ.delete(artifactIds) == 0){
	//					return ResponseEntity.status(HttpStatus.OK).build();	
	//				}
	//			}
	//		}catch(Exception e){
	//			logger.debug("Removing artifacts: "+ artifactIds);
	//		}
	//		return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
	//	}


	@PutMapping(value = "/artifacts/{id}", params = "id", produces = "application/json")
	public ResponseEntity <String> update(@PathVariable String id, @RequestBody Artifact artifact) {
		try{
			Artifact exists = this.contentAppServ.getArtifactById(id);
			if (exists!= null && artifact != null){
				if(this.contentAppServ.update(artifact)==0){
					return ResponseEntity.status(HttpStatus.OK).build();	
				}
			}
		}catch(Exception e){
			logger.debug("Removing artifact with id: "+id);
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
	}

	//
	//	public byte updateAll(List<Artifact> artifacts) {
	//		return this.contentAppServ.updateAll(artifacts);
	//	}
	//
	//	public byte updateAllOnlyChanges(List<Artifact> artifacts) {
	//		return this.contentAppServ.updateAllOnlyChanges(artifacts);
	//	}
	//

	@RequestMapping(value = "/artifacts/{id}/plot", method = RequestMethod.GET, produces = "application/json")
	public byte[] plot(@PathVariable(value = "id") String id) {
		try{
			Artifact artifact = this.contentAppServ.getArtifactById(id);
			if(artifact != null){
				return this.contentAppServ.plot(artifact);
			}
		}catch(Exception e){
			logger.debug("Plotting artifact with id: "+id);
		}
		return new byte [0];
	}


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
