using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using OSLC4Net.Core.Attribute;
using OSLC4Net.Core.Model;


namespace OSLC_KM_SRL.srl {
    [OslcNamespace(SRLShapeConstants.OSLC_KM_SRL_DOMAIN)]
    [OslcName(SRLShapeConstants.SRL_METADATA)]
    [OslcResourceShape(title = "Vocabulary (Minimal) Concept Resource Shape", describes = new string[] { 
        SRLShapeConstants.SRL_METADATA_URI })]
    public class MetaData:AbstractResource {

        #region Properties for Metadata

        Artifact key;
        Artifact value;
        
        #endregion


        #region OSLC Core properties

        private string identifier = string.Empty;
        private Uri serviceProvider;
        private Uri baseUri;

        #endregion

        public MetaData() {
        
        }
        public MetaData(Artifact key, Artifact value) {
            this.key = key;
            this.value = value;
        }

        public MetaData(string key, string value) {
            this.key = new Artifact(key);
            this.value = new Artifact(value);
        }


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


        [OslcDescription("Describes the value of the tag.")]
        [OslcPropertyDefinition(SRLShapeConstants.OSLC_KM_SRL_VOCAB + "value")]
        [OslcReadOnly]
        [OslcRepresentation(Representation.Inline)]
        [OslcTitle("Value of the tag")]
        [OslcValueShape(OslcConstants.PATH_RESOURCE_SHAPES + "/" + OslcConstants.PATH_PUBLISHER)]
        [OslcValueType(OSLC4Net.Core.Model.ValueType.LocalResource)]
        public Artifact GetValue() {
            return this.value;
        }

        public void SetValue(Artifact value) {
            this.value = value;
        }

         public void SetTag(Artifact tag) {
            this.key = tag;
        }

        [OslcDescription("Describes the key.")]
        [OslcPropertyDefinition(SRLShapeConstants.OSLC_KM_SRL_VOCAB + "key")]
        [OslcReadOnly]
        [OslcRepresentation(Representation.Inline)]
        [OslcTitle("The key used as metadata.")]
        [OslcValueShape(OslcConstants.PATH_RESOURCE_SHAPES + "/" + OslcConstants.PATH_PUBLISHER)]
        [OslcValueType(OSLC4Net.Core.Model.ValueType.LocalResource)]
        public Artifact GetTag() {
            return this.key;
        }


    }
}