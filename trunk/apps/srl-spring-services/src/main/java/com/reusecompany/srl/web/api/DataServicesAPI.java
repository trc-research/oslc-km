package com.reusecompany.srl.web.api;

import java.util.Collections;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.reusecompany.srl.appserv.DataAppServ;
import com.reusecompany.srl.model.Artifact;


@Scope("request")
@CrossOrigin
@RestController
@RequestMapping("/services/sas")
public class DataServicesAPI {

		protected static Logger logger = Logger.getLogger(DataServicesAPI.class);

		@Autowired
		DataAppServ dataAppServ;

		public DataServicesAPI() {

		}

		@RequestMapping(value = "/greeting", method = RequestMethod.GET)
		public String greeting() {
			return "Hello!";
		}


		@RequestMapping(value = "/artifacts", method = RequestMethod.GET, produces = "application/json")
		public List<Artifact> list() {
			try{	
				logger.debug("Getting all artifacts");
				List<Artifact> clients =  dataAppServ.list();
				logger.debug("Sucessfull get all artifacts");
				return clients;
			}catch(Exception e){
				logger.error("Getting all artifacts", e);
			}
			return Collections.emptyList();
		}

		

}
