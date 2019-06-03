package com.reusecompany.srl.appserv;

import java.util.List;

import org.springframework.stereotype.Component;

import com.reusecompany.srl.model.Artifact;
import com.reusecompany.srl.services.DataServices;
import com.reusecompany.srl.services.impl.NaiveDataServicesImpl;

@Component
public class DataAppServ {

	DataServices ds;
	
	public DataAppServ(){
		this.ds = new NaiveDataServicesImpl();
	}

	public DataAppServ(DataServices ds){
		if(ds != null){
			this.ds = ds;
		}
	}

	public List<Artifact> list(){
		return this.ds.list();
	}
	public Artifact getById(String id){
		return this.ds.getById(id);
	}
	
}
