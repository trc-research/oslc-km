package com.reusecompany.srl.web.api;

import java.util.Collections;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.reusecompany.srl.appserv.ContentAppServ;
import com.reusecompany.srl.model.Artifact;


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
			//return Collections.emptyList();
		}

		

}
