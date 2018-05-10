using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using OSLC4Net.Core.Attribute;
using OSLC4Net.Core.Model;
using OSLC_KM_SRL.utils;
using Newtonsoft.Json;

namespace OSLC_KM_SRL.srl {
    [OslcNamespace(SRLShapeConstants.OSLC_KM_SRL_DOMAIN)]
    [OslcName(SRLShapeConstants.SRL_TERM)]
    [OslcResourceShape(title = "Vocabulary (Minimal) Concept Resource Shape", describes = new string[] { SKOS.CONCEPT })]
    public class Type : AbstractResource {

        #region Properties for Artifact
        [JsonProperty(PropertyName = "name")]
        private string name = string.Empty;
        #endregion

        #region OSLC Core properties
        [JsonProperty(PropertyName = "identifier")]
        private string identifier = string.Empty;
        [JsonProperty(PropertyName = "serviceProvider")]
        private Uri serviceProvider;
        #endregion

        #region Constructors
        public Type() {
        }

        public Type(string name) {
            this.name = name;
        }
        #endregion

        #region Common OSLC methods
        [OslcDescription("The scope of a resource is a URI for the resource's OSLC Service Provider.")]
        [OslcOccurs(Occurs.ExactlyOne)]
        [OslcPropertyDefinition(OslcConstants.OSLC_CORE_NAMESPACE + "serviceProvider")]
        [OslcRange(OslcConstants.TYPE_SERVICE_PROVIDER)]
        [OslcReadOnly]
        [OslcTitle("Service Provider")]
        public Uri GetServiceProvider() {
            return serviceProvider;
        }
        public void SetServiceProvider(Uri value) {
            this.serviceProvider = value;
        }


        [OslcDescription("A unique identifier for a resource. Assigned by the service provider when a resource is created. Not intended for end-user display.")]
        [OslcOccurs(Occurs.ExactlyOne)]
        [OslcPropertyDefinition(OslcConstants.DCTERMS_NAMESPACE + "identifier")]
        [OslcReadOnly]
        [OslcTitle("Identifier")]
        public string GetIdentifier() {
            return identifier;
        }

        public void SetIdentifier(string identifier) {
            this.identifier = identifier;
        }



        [OslcDescription("The resource type URIs.")]
        [OslcName("type")]
        [OslcPropertyDefinition(OslcConstants.RDF_NAMESPACE + "type")]
        [OslcTitle("Types")]
        public Uri[] GetRdfTypes() {
            return base.GetTypes().ToArray();
        }

        public void SetRdfTypes(Uri[] rdfTypes) {
            base.GetTypes().AddAll(rdfTypes);
        }

        #endregion

        #region Getters & Setters
        [OslcDescription("Name of the artifact type.")]
        [OslcOccurs(Occurs.ExactlyOne)]
        [OslcPropertyDefinition(SRLShapeConstants.OSLC_KM_SRL_VOCAB + "name")]
        [OslcReadOnly]
        [OslcTitle("Name of the artifact type.")]
        [OslcName("name")]
        public string GetName() {
            return this.name;
        }
        public void SetName(string name) {
            this.name = name;
        }
        #endregion

    }
}