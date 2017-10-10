package com.reusecompany.oslc.km.services;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Map;

import org.eclipse.lyo.oslc4j.core.exception.OslcCoreApplicationException;
import org.eclipse.lyo.oslc4j.core.model.OslcConstants;
import org.eclipse.lyo.oslc4j.core.model.PrefixDefinition;
import org.eclipse.lyo.oslc4j.core.model.Publisher;
import org.eclipse.lyo.oslc4j.core.model.ServiceProvider;
import org.eclipse.lyo.oslc4j.core.model.ServiceProviderFactory;

import com.hp.hpl.jena.sparql.vocabulary.FOAF;
import com.reusecompany.oslc.km.services.utils.OSLC_KM;
import com.reusecompany.oslc.km.vocabs.SKOS;
import com.reusecompany.oslc.km.vocabs.SRL;


public class OSLCKMServiceProviderFactory{
    private static Class<?>[] RESOURCE_CLASSES =  {
        //ServiceProviderService.class,
        //DummyServiceProviderService.class,
        ServiceProviderCatalogService.class,
        KnowledgeManagementService.class,
    };

    private OSLCKMServiceProviderFactory()   {
        super();
    }

    /**
     * Create a new KR OSLC requirements management service provider.
     * @param baseURI
     * @param product - This is if the service provides more than a product
     * @param parameterValueMap - a map containing the path replacement value for {productId}.  See ServiceProviderCatalogSingleton.initServiceProvidersFromProducts()
     * @return
     * @throws OslcCoreApplicationException
     * @throws URISyntaxException
     */
    public static ServiceProvider createServiceProvider(final String baseURI, final String product, final Map<String,Object> parameterValueMap)
           throws OslcCoreApplicationException, URISyntaxException   {
        final ServiceProvider serviceProvider = 
        		ServiceProviderFactory.createServiceProvider(baseURI,
        				product,
        				"Service provider for OSLC Knowledge Management by The Reuse Company Inc.: "+product,
        				product, 
        				new Publisher("OSLC Knowledge Management TRC via Eclipse Lyo", "urn:oslc:ServiceProvider"),
        				RESOURCE_CLASSES,
        				parameterValueMap);
        
        URI detailsURIs[] = {new URI(baseURI + "/details")};
        serviceProvider.setDetails(detailsURIs);

        final PrefixDefinition[] prefixDefinitions = {
            new PrefixDefinition(OslcConstants.DCTERMS_NAMESPACE_PREFIX,             new URI(OslcConstants.DCTERMS_NAMESPACE)),
            new PrefixDefinition(OslcConstants.OSLC_CORE_NAMESPACE_PREFIX,           new URI(OslcConstants.OSLC_CORE_NAMESPACE)),
            new PrefixDefinition(OslcConstants.OSLC_DATA_NAMESPACE_PREFIX,           new URI(OslcConstants.OSLC_DATA_NAMESPACE)),
            new PrefixDefinition(OslcConstants.RDF_NAMESPACE_PREFIX,                 new URI(OslcConstants.RDF_NAMESPACE)),
            new PrefixDefinition(OslcConstants.RDFS_NAMESPACE_PREFIX,                new URI(OslcConstants.RDFS_NAMESPACE)),
            new PrefixDefinition(SRL.NAMESPACE_PREFIX, new URI(SRL.NAMESPACE)),
            //Adding new ones FIXME: read from properties
            new PrefixDefinition("skos", new URI(SKOS.CORE_NAMESPACE)),
            new PrefixDefinition("foaf", new URI(FOAF.NS))
        };

        serviceProvider.setPrefixDefinitions(prefixDefinitions);

        return serviceProvider;
    }
}
