using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using Newtonsoft.Json;
using OSLC4Net.Core.Attribute;
using OSLC4Net.Core.Model;

namespace OSLC_KM_SRL.srl {
    [OslcNamespace(SRLShapeConstants.OSLC_KM_SRL_DOMAIN)]
    [OslcName(SRLShapeConstants.SRL_RELATIONSHIP)]
    [OslcResourceShape(title = "Relationship Resource Shape", describes = new string[] {
        SRLShapeConstants.SRL_RELATIONSHIP_URI })]
    public class Relationship : AbstractResource {

        const string DEFAULT_RSHP_TYPE = "UNKNOW RELATIONSHIP";

        #region Properties for Relationship
        [JsonProperty(PropertyName = "from")]
        private Artifact from; //FIXME: list
        [JsonProperty(PropertyName = "to")]
        private Artifact to; //FIXME: list
        [JsonProperty(PropertyName = "RshpType")]
        private Type type;
        [JsonProperty(PropertyName = "RshpSubType")]
        private Type subType;
        [JsonProperty(PropertyName = "name")]
        private string name = null;
        [JsonProperty(PropertyName = "InterfaceFrom")]
        private string InterfaceFrom = "";
        [JsonProperty(PropertyName = "InterfaceTo")]
        private string InterfaceTo = "";
        #endregion

        #region OSLC Core properties
        [JsonProperty(PropertyName = "serviceProvider")]
        private Uri serviceProvider;
        [JsonProperty(PropertyName = "identifier")]
        private string identifier = string.Empty;

        #endregion

        #region Constructors
        public Relationship() {
            this.type = new Type(DEFAULT_RSHP_TYPE);
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
        [OslcDescription("Relationship Type")]
        [OslcPropertyDefinition(SRLShapeConstants.OSLC_KM_SRL_VOCAB + "type")]
        [OslcReadOnly]
        [OslcRepresentation(Representation.Inline)]
        [OslcTitle("Relationship Type")]
        [OslcName("type")]
        [OslcValueType(OSLC4Net.Core.Model.ValueType.LocalResource)]
        public Type GetRelationshipType() {
            return this.type;
        }

        public void SetRelationshipType(Type type) {
            this.type = type;
        }
        [OslcDescription("Relationship Type")]
        [OslcPropertyDefinition(SRLShapeConstants.OSLC_KM_SRL_VOCAB + "subtype")]
        [OslcReadOnly]
        [OslcRepresentation(Representation.Inline)]
        [OslcTitle("Relationship Type")]
        [OslcName("type")]
        [OslcValueType(OSLC4Net.Core.Model.ValueType.LocalResource)]
        public Type GetSubType() {
            return this.subType;
        }

        public void SetSubType(Type subType) {
            this.subType = subType;
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
        [OslcDescription("Describes the semantics of the relationship .")]
        [OslcPropertyDefinition(SRLShapeConstants.OSLC_KM_SRL_VOCAB + "name")]
        [OslcReadOnly]
        [OslcRepresentation(Representation.Inline)]
        [OslcTitle("Element with description of the meaning of the relationship")]
        [OslcName("name")]
        [OslcValueShape(OslcConstants.PATH_RESOURCE_SHAPES + "/" + OslcConstants.PATH_PUBLISHER)]
        [OslcValueType(OSLC4Net.Core.Model.ValueType.LocalResource)]
        public string GetName() {
            return name;
        }
        public void SetName(string name) {
            this.name = name;
        }
        public void SetInterfaceFrom(string InterfaceFrom) {
            this.InterfaceFrom = InterfaceFrom;
        }
        public string GetInterfaceFrom() {
            return this.InterfaceFrom;
        }
        public void SetInterfaceTo(string InterfaceTo) {
            this.InterfaceTo = InterfaceTo;
        }
        public string GetInterfaceTo() {
            return this.InterfaceTo;
        }
        #endregion

    }
}