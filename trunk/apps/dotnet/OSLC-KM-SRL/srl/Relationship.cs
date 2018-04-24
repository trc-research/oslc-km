using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using OSLC4Net.Core.Attribute;
using OSLC4Net.Core.Model;

namespace OSLC_KM_SRL.srl {
    [OslcNamespace(SRLShapeConstants.OSLC_KM_SRL_DOMAIN)]
    [OslcName(SRLShapeConstants.SRL_RELATIONSHIP)]
    [OslcResourceShape(title = "Relationship Resource Shape", describes = new string[] { 
        SRLShapeConstants.SRL_RELATIONSHIP_URI })]
    public class Relationship:AbstractResource {
     

        #region Properties for Relationship

        Artifact from; //FIXME: list
        Artifact to; //FIXME: list
        Type type;

        #endregion


        #region OSLC Core properties
       
        private Uri serviceProvider;
        private Uri baseUri;
        private string identifier = string.Empty;

        #endregion

        public Relationship() {
        
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


        [OslcDescription("Relationship Type")]
        [OslcPropertyDefinition(SRLShapeConstants.OSLC_KM_SRL_VOCAB + "type")]
        [OslcReadOnly]
        [OslcRepresentation(Representation.Inline)]
        [OslcTitle("Relationship Type")]
        [OslcName("type")]
        [OslcValueType(OSLC4Net.Core.Model.ValueType.LocalResource)]
        public Type GetArtifactType() {
            return this.type;
        }

        public void SetArtifactType(Type artifactType) {
            this.type = artifactType;
        }

        [OslcDescription("Describes the set of sources that are on the one hand of the relationship.")]
        [OslcPropertyDefinition(SRLShapeConstants.OSLC_KM_SRL_VOCAB + "from")]
        [OslcReadOnly]
        [OslcRepresentation(Representation.Inline)]
        [OslcTitle("Element in concept order 1")]
        [OslcName("from")]
        [OslcValueShape(OslcConstants.PATH_RESOURCE_SHAPES + "/" + OslcConstants.PATH_PUBLISHER)]
        [OslcValueType(OSLC4Net.Core.Model.ValueType.LocalResource)]
        public Artifact GetFrom() {
            return from;
        }
        public void SetFrom(Artifact from) {
            this.from = from;
        }
        [OslcDescription("Describes the set of sources that are on the other hand of the relationship.")]
        [OslcPropertyDefinition(SRLShapeConstants.OSLC_KM_SRL_VOCAB + "to")]
        [OslcReadOnly]
        [OslcRepresentation(Representation.Inline)]
        [OslcTitle("Element in concept order 2")]
        [OslcName("to")]
        [OslcValueShape(OslcConstants.PATH_RESOURCE_SHAPES + "/" + OslcConstants.PATH_PUBLISHER)]
        [OslcValueType(OSLC4Net.Core.Model.ValueType.LocalResource)]
        public Artifact GetTo() {
            return to;
        }
        public void SetTo(Artifact to) {
            this.to = to;
        }

        
    }
}