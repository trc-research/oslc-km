using System;
using System.Collections.Generic;
using System.Linq;
using System.Net;
using System.Net.Http;
using System.Web.Http;
using OSLC4Net.Core.Model;
using OSLC4Net.Core.Attribute;
using System.Web;
using OSLC_KM_SRL.srl;
using OSLC_KM_SRL.facade;
using OSLC_KM_SRL.Sample.facade_impl;

namespace OSLC.Controllers
{
    [OslcService(SRLShapeConstants.SRL_ARTIFACT_URI)]
    public class ArtifactsController : ApiController{

        SystemAssetStore sas;
        public ArtifactsController() {
           // conceptContext.ServerUri = ServiceProviderController.About.ToString();
            sas = new SystemAssetStoreImpl();
        }
        #region OSLC methods

        [HttpGet]
        [ActionName("resourceShape")]
        public ResourceShape GetResourceShape() {
            try {
                ServiceProvider serviceProvider = ArtifactsServiceProviderController.serviceProvider;
                ResourceShape shape =
                    ResourceShapeFactory.CreateResourceShape(ArtifactsServiceProviderController.BaseUri,
                                                             SRLShapeConstants.SRL_TERM,
                                                             "getShape=true",
                                                             typeof(Term));
                return shape;
            } catch (Exception e) {
                throw new HttpResponseException(HttpStatusCode.BadRequest);
            }
        }

  
        [HttpGet]
        [ActionName("artifact")]
        public Artifact GetArtifact([FromUri]string id = null) {
            try {
                if (id != null) {
                    return this.sas.retrieve(id);
                }
                throw new HttpResponseException(HttpStatusCode.BadRequest);
            } catch (Exception e) {
                throw new HttpResponseException(HttpStatusCode.BadRequest);
            }
        }


      
        #endregion 

        private static ResponseInfoCollection<Artifact> createResponseArtifactGroup(List<Artifact> artifacts) {
            ResponseInfoCollection<Artifact> responseInfo =
                new ResponseInfoCollection<Artifact>(artifacts,
                                                       null,
                                                       artifacts.Count,
                                                       (string)null);

            return responseInfo;
        }

    }
}
