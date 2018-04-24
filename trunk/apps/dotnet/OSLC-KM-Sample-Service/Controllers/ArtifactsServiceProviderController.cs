using System;
using System.Collections.Generic;
using OSLC4Net.Core.DotNetRdfProvider;
using OSLC4Net.Core.Model;
using System.Linq;
using System.Net;
using System.Net.Http;
using System.Web.Http;
using OSLC_KM_SRL.srl;




namespace OSLC.Controllers{

    public class ArtifactsServiceProviderController : ApiController {
        public const string SERVICE_PROVIDER_PATH = "km";

        public static ServiceProvider serviceProvider;
        public static string BaseUri { get; set; }            //URI (as string) of the webapps root context
        public static Uri About { get; set; }                //URI for the StockQuote service
        public static Uri ServiceProviderUri { get; set; }   //URI for the ServiceProvider service
     


        public static void init(string baseUri) {
            BaseUri = baseUri;
            serviceProvider = ServiceProviderFactory.CreateServiceProvider(BaseUri,
                                                                     "Artifacts Service Provider",
                                                                     "Sample OSLC Service Provider for artifacts, RSHPs, etc.",
                                                                     new Publisher("The Reuse Company with OSLC4Net", "urn:trc:oslcvm"),
                                                                     new System.Type[] { typeof(ArtifactsController) });



            serviceProvider.SetPrefixDefinitions(Constants.PREFIX_DEFINITIONS);

            About = new Uri(BaseUri + "/" + SRLShapeConstants.SRL_ARTIFACT);
            serviceProvider.SetAbout(About);
            ServiceProviderUri = new Uri(BaseUri + "/" + ArtifactsServiceProviderController.SERVICE_PROVIDER_PATH);

        }

        [HttpGet]
        [ActionName("info")]
        public ServiceProvider GetServiceProvider() {
            return ArtifactsServiceProviderController.serviceProvider;
        }
    }
    
}
