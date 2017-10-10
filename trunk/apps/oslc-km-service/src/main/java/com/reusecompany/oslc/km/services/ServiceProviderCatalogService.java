package com.reusecompany.oslc.km.services;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.core.Response.Status;

import org.eclipse.lyo.oslc4j.core.annotation.OslcDialog;
import org.eclipse.lyo.oslc4j.core.annotation.OslcQueryCapability;
import org.eclipse.lyo.oslc4j.core.annotation.OslcService;
import org.eclipse.lyo.oslc4j.core.model.OslcConstants;
import org.eclipse.lyo.oslc4j.core.model.OslcMediaType;
import org.eclipse.lyo.oslc4j.core.model.ServiceProviderCatalog;

import com.reusecompany.oslc.km.services.utils.OSLC_KM;

@OslcService(OslcConstants.OSLC_CORE_DOMAIN)
@Path("catalog")
public class ServiceProviderCatalogService {
	@Context private HttpServletRequest httpServletRequest;
	@Context private HttpServletResponse httpServletResponse;
	@Context private UriInfo uriInfo;
	@OslcDialog
	(
			title = "Service Provider Catalog Selection Dialog",
			label = "Service Provider Catalog Selection Dialog",
			uri = "/catalog",
			hintWidth = "1000px",
			hintHeight = "600px",
			resourceTypes = {OslcConstants.TYPE_SERVICE_PROVIDER_CATALOG},
			usages = {OslcConstants.OSLC_USAGE_DEFAULT}
			)
	@OslcQueryCapability
	(
			title = "Service Provider Catalog Query Capability",
			label = "Service Provider Catalog Query",
			resourceShape = OslcConstants.PATH_RESOURCE_SHAPES + "/" + OslcConstants.PATH_SERVICE_PROVIDER_CATALOG,
			resourceTypes = {OslcConstants.TYPE_SERVICE_PROVIDER_CATALOG},
			usages = {OslcConstants.OSLC_USAGE_DEFAULT}
			)

	/**
	 * Redirect requests to /catalog to /catalog/singleton
	 * 
	 * By default, OSLC4J returns an OSLC query response for /catalog.  We really just
	 * want the catalog itself which lives at /catalog/{serviceProviderCatalogId}
	 * 
	 * @return
	 * @throws IOException
	 * @throws URISyntaxException
	 */
	@GET
	public Response getServiceProviderCatalogs() throws IOException, URISyntaxException{
		String forwardUri = uriInfo.getAbsolutePath() + "/singleton";
		httpServletResponse.sendRedirect(forwardUri);
		return Response.seeOther(new URI(forwardUri)).build();
	}

	/**
	 * Return the OSLC service provider catalog as RDF/XML, XML or JSON
	 * 
	 * @return
	 */
	@GET
	@Path("{serviceProviderCatalogId}") // Required to distinguish from array result.  But, ignored.
	@Produces({OslcMediaType.APPLICATION_RDF_XML, OslcMediaType.APPLICATION_XML, OslcMediaType.APPLICATION_JSON})
	public ServiceProviderCatalog getServiceProviderCatalog(){
		ServiceProviderCatalog catalog =  ServiceProviderCatalogSingleton.getServiceProviderCatalog(httpServletRequest);
		if (catalog != null) {
			httpServletResponse.addHeader(OSLC_KM.HDR_OSLC_VERSION,"2.0");
			return catalog;
		}
		throw new WebApplicationException(Status.NOT_FOUND);
	}


}
