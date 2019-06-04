package com.reusecompany.srl.appserv;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.reusecompany.srl.model.Artifact;
import com.reusecompany.srl.services.DataServices;
import com.reusecompany.srl.services.impl.NaiveDataServicesImpl;

@Component
public class DataAppServ {

	@Autowired
	DataServices dataServices;
	
	public DataAppServ(){
		
	}

	public DataAppServ(DataServices ds){
		if(ds != null){
			this.dataServices = ds;
		}
	}

	public List<Artifact> list(){
		return this.dataServices.list();
	}
	public Artifact getById(String id){
		return this.dataServices.getById(id);
	}
	
}
