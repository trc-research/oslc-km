package com.reusecompany.oslc.km.services;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriInfo;

import org.apache.log4j.Logger;
import org.eclipse.lyo.oslc4j.core.annotation.OslcDialog;
import org.eclipse.lyo.oslc4j.core.annotation.OslcDialogs;
import org.eclipse.lyo.oslc4j.core.annotation.OslcQueryCapability;
import org.eclipse.lyo.oslc4j.core.annotation.OslcService;
import org.eclipse.lyo.oslc4j.core.model.OslcConstants;
import org.eclipse.lyo.oslc4j.core.model.OslcMediaType;

import com.reusecompany.oslc.km.services.facade.SystemAssetManagementFacade;
import com.reusecompany.oslc.km.services.facade.SystemAssetManagementFacadeOWLImpl;
import com.reusecompany.oslc.km.services.facade.SystemAssetManagementFacadeNaiveImpl;
import com.reusecompany.oslc.km.services.utils.OSLC_KM;
import com.reusecompany.oslc.km.srl.Artifact;
import com.reusecompany.oslc.km.srl.MetaProperty;
import com.reusecompany.oslc.km.srl.Term;
import com.reusecompany.oslc.km.vocabs.SRL;


@OslcService(SRL.DOMAIN)
@Path("sas")
public class KnowledgeManagementService {
	
	protected static Logger logger = Logger.getLogger(KnowledgeManagementService.class);

	@Context private HttpServletRequest httpServletRequest;
	@Context private HttpServletResponse httpServletResponse;
	@Context private UriInfo uriInfo;
	
	private SystemAssetManagementFacade facade;

	public KnowledgeManagementService(){
		super();
		this.facade = new SystemAssetManagementFacadeOWLImpl();
	}

	@OslcDialogs(
			{
				@OslcDialog
				(
						title = "Knowledge Management Selection Dialog",
						label = "Knowledge Management Selection Dialog",
						uri = "/vm/selector",
						hintWidth = "525px",
						hintHeight = "325px",
						resourceTypes = {SRL.NAMESPACE + SRL.ARTIFACT},
						usages = {OslcConstants.OSLC_USAGE_DEFAULT}
						)

			})
	@OslcQueryCapability
	(
			title = "Knowledge Management Query Capability",
			label = "Knowledge Management Catalog Query",
			resourceShape = 
					OslcConstants.PATH_RESOURCE_SHAPES + "/" + 
					OSLC_KM.PATH_KNOWLEDGE_MANAGEMENT_REQUEST,
			resourceTypes = {SRL.NAMESPACE + SRL.ARTIFACT},
			usages = {OslcConstants.OSLC_USAGE_DEFAULT}
			)

	@GET
	@Path("artifact")
	@Produces({OslcMediaType.APPLICATION_RDF_XML, OslcMediaType.APPLICATION_XML, OslcMediaType.APPLICATION_JSON})
	public List<Artifact> getArtifacts() throws IOException, ServletException {
		logger.info("Requesting all artifacts");
		try{
			return this.facade.getArtifacts();
		}catch(Exception e){
			logger.error(e);
			throw new WebApplicationException(Status.INTERNAL_SERVER_ERROR);
		}
		


	}

	@GET
	@Path("artifact/{id}")
	@Produces({OslcMediaType.APPLICATION_RDF_XML, OslcMediaType.APPLICATION_XML, OslcMediaType.APPLICATION_JSON})
	public Artifact getArtifactById(@PathParam("id") String id) throws IOException, ServletException {
		logger.info("Requesting artifact with id: "+id);
		try{
			return this.facade.getArtifactById(Integer.parseInt(id));
		}catch(Exception e){
			logger.error(e);
			throw new WebApplicationException(Status.INTERNAL_SERVER_ERROR);
		}
		
	}

	@GET
	@Path("artifact/{id}/concept")
	@Produces({OslcMediaType.APPLICATION_RDF_XML, OslcMediaType.APPLICATION_XML, OslcMediaType.APPLICATION_JSON})
	public List<Term> getArtifactTermsById(@PathParam("id") String id) throws IOException, ServletException {
		logger.info("Requesting artifact terms with id: "+id);
		try{
			return this.facade.getArtifactTermsById(tryToInteger(id));
		}catch(Exception e){
			logger.error(e);
			throw new WebApplicationException(Status.INTERNAL_SERVER_ERROR);
		}
		
	}
	
	@GET
	@Path("artifact/{id}/meta")
	@Produces({OslcMediaType.APPLICATION_RDF_XML, OslcMediaType.APPLICATION_XML, OslcMediaType.APPLICATION_JSON})
	public List<MetaProperty> getArtifactMetaPropertiesById(@PathParam("id") String id) throws IOException, ServletException {
		logger.info("Requesting artifact metaproperties with id: "+id);
		try{
			return this.facade.getArtifactMetaPropertiesById(tryToInteger(id));
		}catch(Exception e){
			logger.error(e);
			throw new WebApplicationException(Status.INTERNAL_SERVER_ERROR);
		}
		
	}
	
	
	@GET
	@Path("artifact/{id}/subartifact")
	@Produces({OslcMediaType.APPLICATION_RDF_XML, OslcMediaType.APPLICATION_XML, OslcMediaType.APPLICATION_JSON})
	public List<Artifact> getArtifactSubArtifactsById(@PathParam("id") String id) throws IOException, ServletException {
		logger.info("Requesting artifact sub-artifacts with id: "+id);
		try{
			return this.facade.getArtifactSubArtifactsById(tryToInteger(id));
		}catch(Exception e){
			logger.error(e);
			throw new WebApplicationException(Status.INTERNAL_SERVER_ERROR);
		}
		
	}
	
	@GET
	@Path("artifact/{id}/subartifact/{idSubartifact}")
	@Produces({OslcMediaType.APPLICATION_RDF_XML, OslcMediaType.APPLICATION_XML, OslcMediaType.APPLICATION_JSON})
	public Artifact getArtifactSubArtifactsById(
			@PathParam("id") String id,
			@PathParam("idSubartifact") String idSubartifact) throws IOException, ServletException {
		logger.info("Requesting artifact with id: "+id+" and subartifact with id: "+idSubartifact);
		try{
			return this.facade.getArtifactSubArtifactsById(tryToInteger(id), tryToInteger(idSubartifact));
		}catch(Exception e){
			logger.error(e);
			throw new WebApplicationException(Status.INTERNAL_SERVER_ERROR);
		}
		
	}
	
	protected static int tryToInteger(String value){
		try{
			return Integer.valueOf(value);
		}catch(NumberFormatException e){
			logger.error(e);
		}
		return -1;
	}

}
