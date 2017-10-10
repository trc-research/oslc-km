package com.reusecompany.oslc.km.services;


import java.net.URI;
import java.net.URISyntaxException;
import java.util.Date;
import java.util.HashMap;
import java.util.SortedMap;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.TreeSet;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response.Status;

import org.eclipse.lyo.oslc4j.client.ServiceProviderRegistryURIs;
import org.eclipse.lyo.oslc4j.core.exception.OslcCoreApplicationException;
import org.eclipse.lyo.oslc4j.core.model.Publisher;
import org.eclipse.lyo.oslc4j.core.model.Service;
import org.eclipse.lyo.oslc4j.core.model.ServiceProvider;
import org.eclipse.lyo.oslc4j.core.model.ServiceProviderCatalog;



/**
 * This is the OSLC service provider catalog for the KM adapter.  Service providers are
 * not registered with the catalog until a request comes in to access either the catalog or a 
 * specific service provider.   This request could be from an external consumer or an internal
 * request triggered by a consumer accessing a change request.
 * 
 * The service providers are created and registered in the initServiceProvidersFromProducts()
 * method. 
 * 
 * The registered service providers are refreshed on each catalog or service provider collection
 * request.
 */
public class ServiceProviderCatalogSingleton{
    public static final String KM_TRC_ID = "KM_TRC";
	private static final ServiceProviderCatalog             serviceProviderCatalog;
    private static final SortedMap<String, ServiceProvider> serviceProviders = new TreeMap<String, ServiceProvider>();

    static{
        try{
            serviceProviderCatalog = new ServiceProviderCatalog();
            serviceProviderCatalog.setAbout(new URI(ServiceProviderRegistryURIs.getServiceProviderRegistryURI()));
            serviceProviderCatalog.setTitle("OSLC Service Provider Catalog");
            serviceProviderCatalog.setDescription("OSLC Service Provider Catalog");
            serviceProviderCatalog.setPublisher(new Publisher("KR UC3M", "kf.inf.uc3m.es"));
            serviceProviderCatalog.getPublisher().setIcon(new URI("http://ocw.uc3m.es/ingenieria-informatica/ingenieria-de-la-informacion/imagenes/kr-logo-lateral-200.png"));
        }
        catch (final URISyntaxException exception){
            // We should never get here.
            throw new ExceptionInInitializerError(exception);
        }
    }


    private ServiceProviderCatalogSingleton(){   	
        super();
    }
    

    public static URI getUri(){
    	return serviceProviderCatalog.getAbout();
    }

    public static ServiceProviderCatalog getServiceProviderCatalog(HttpServletRequest httpServletRequest){
    	initServiceProvidersFromProducts(httpServletRequest);
        return serviceProviderCatalog;
    }

    public static ServiceProvider[] getServiceProviders(HttpServletRequest httpServletRequest){
        synchronized(serviceProviders) {
        	initServiceProvidersFromProducts(httpServletRequest);
            return serviceProviders.values().toArray(new ServiceProvider[serviceProviders.size()]);
        }
    }

    public static ServiceProvider getServiceProvider(HttpServletRequest httpServletRequest, 
    		final String serviceProviderId){
        ServiceProvider serviceProvider;

        synchronized(serviceProviders){     	
            serviceProvider = serviceProviders.get(serviceProviderId);
            
            //One retry refreshing the service providers
            if (serviceProvider == null){
            	getServiceProviders(httpServletRequest);
            	serviceProvider = serviceProviders.get(serviceProviderId);
            }
        }

        if (serviceProvider != null){
            return serviceProvider;
        }

        throw new WebApplicationException(Status.NOT_FOUND);
    }

    public static ServiceProvider registerServiceProvider(final HttpServletRequest httpServletRequest,
                                                          final ServiceProvider    serviceProvider
                                                          ) throws URISyntaxException {
        synchronized(serviceProviders){
            final URI serviceProviderURI = new URI(httpServletRequest.getScheme(),
                                                   null,
                                                   httpServletRequest.getServerName(),
                                                   httpServletRequest.getServerPort(),
                                                   httpServletRequest.getContextPath() + "/serviceProviders/" + KM_TRC_ID,
                                                   null,
                                                   null);

            return registerServiceProviderNoSync(serviceProviderURI,
                                                 serviceProvider,
                                                 KM_TRC_ID);
        }
    }


/**
 * Register a service provider with the OSLC catalog
 * 
 * @param serviceProviderURI
 * @param serviceProvider
 * @param idService
 * @return
 */
    private static ServiceProvider registerServiceProviderNoSync(final URI             serviceProviderURI,
                                                                 final ServiceProvider serviceProvider,
                                                                 final String idService){
        final SortedSet<URI> serviceProviderDomains = getServiceProviderDomains(serviceProvider);

        serviceProvider.setAbout(serviceProviderURI);
        serviceProvider.setIdentifier(idService);
        serviceProvider.setCreated(new Date());

        serviceProviderCatalog.addServiceProvider(serviceProvider);
        serviceProviderCatalog.addDomains(serviceProviderDomains);

        serviceProviders.put(idService,serviceProvider);


        return serviceProvider;
    }
    
    // This version is for self-registration and thus package-protected
    static ServiceProvider registerServiceProvider(final String          baseURI,
                                                   final ServiceProvider serviceProvider,
                                                   final String idService)
           throws URISyntaxException {
        synchronized(serviceProviders){
            final URI serviceProviderURI = new URI(baseURI + "/serviceProviders/" + idService);

            return registerServiceProviderNoSync(serviceProviderURI,
                                                 serviceProvider,
                                                 idService);
        }
    }

    public static void deregisterServiceProvider(final String serviceProviderId){
        synchronized(serviceProviders){
            final ServiceProvider deregisteredServiceProvider = serviceProviders.remove(serviceProviderId);

            if (deregisteredServiceProvider != null){
                final SortedSet<URI> remainingDomains = new TreeSet<URI>();

                for (final ServiceProvider remainingServiceProvider : serviceProviders.values()){
                    remainingDomains.addAll(getServiceProviderDomains(remainingServiceProvider));
                }

                final SortedSet<URI> removedServiceProviderDomains = getServiceProviderDomains(deregisteredServiceProvider);

                removedServiceProviderDomains.removeAll(remainingDomains);
                serviceProviderCatalog.removeDomains(removedServiceProviderDomains);
                serviceProviderCatalog.removeServiceProvider(deregisteredServiceProvider);
            }
            else{
                throw new WebApplicationException(Status.NOT_FOUND);
            }
        }
    }

    private static SortedSet<URI> getServiceProviderDomains(final ServiceProvider serviceProvider){
        final SortedSet<URI> domains = new TreeSet<URI>();

        if (serviceProvider!=null) {
    		final Service[] services = serviceProvider.getServices();
	        for (final Service service : services)
	        {
	            final URI domain = service.getDomain();

	            domains.add(domain);
	        }
        }
        return domains;
    }
    
    /**
     * Retrieve a list of products from Bugzilla and construct a service provider for each.
     * 
     * Each product ID is added to the parameter map which will be used during service provider
     * creation to create unique URI paths for each Bugzilla product.  See @Path definition at
     * the top of BugzillaChangeRequestService.
     * 
     * @param httpServletRequest
     */
    protected static void initServiceProvidersFromProducts (HttpServletRequest httpServletRequest) {
    	try {
			registerServiceProvider(
					httpServletRequest,
					OSLCKMServiceProviderFactory.createServiceProvider("http://kr.inf.uc3m.es",KM_TRC_ID,new HashMap<String, Object>()));
		} catch (URISyntaxException e) {
			e.printStackTrace();
			throw new WebApplicationException(e,Status.INTERNAL_SERVER_ERROR);
		} catch (OslcCoreApplicationException e) {
			e.printStackTrace();
			throw new WebApplicationException(e,Status.INTERNAL_SERVER_ERROR);
		}
    }
   
}
