using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using Newtonsoft.Json;
using OSLC4Net.Core.Attribute;
using OSLC4Net.Core.Model;


namespace OSLC_KM_SRL.srl {
    [OslcNamespace(SRLShapeConstants.OSLC_KM_SRL_DOMAIN)]
    [OslcName(SRLShapeConstants.SRL_METADATA)]
    [OslcResourceShape(title = "Vocabulary (Minimal) Concept Resource Shape", describes = new string[] {
        SRLShapeConstants.SRL_METADATA_URI })]
    public class MetaData : AbstractResource {

        #region Properties for Metadata
        [JsonProperty(PropertyName = "key")]
        private Artifact key;
        [JsonProperty(PropertyName = "value")]
        private Artifact value;
        [JsonProperty(PropertyName = "Complex key")]
        private List<Artifact> complexKey;
        [JsonProperty(PropertyName = "Complex value")]
        private List<Artifact> complexValue;
        [JsonProperty(PropertyName = "Table Metaproperties")]
        private System.Data.DataTable tablesmMetaProperty;
        [JsonProperty(PropertyName = "Value Type")]
        private SrlValueType valueType = SrlValueType.String;
        [JsonProperty(PropertyName = "Property Operation")]
        private SrlOperator PropertyOperation = SrlOperator.Equal;
        #endregion

        #region OSLC Core properties

        [JsonProperty(PropertyName = "identifier")]
        private string identifier = string.Empty;
        [JsonProperty(PropertyName = "serviceProvider")]
        private Uri serviceProvider;

        #endregion

        #region Constructor
        public MetaData() {
            init();
        }
        public MetaData(Artifact key, Artifact value) {
            this.key = key;
            this.value = value;
            init();
        }

        public MetaData(string key, string value) {
            this.key = new Artifact(key);
            this.value = new Artifact(value);
            init();
        }
        private void init() {
            complexKey = new List<Artifact>();
            complexValue = new List<Artifact>();

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

        [OslcDescription("Describes the value of the metadata.")]
        [OslcPropertyDefinition(SRLShapeConstants.OSLC_KM_SRL_VOCAB + "value")]
        [OslcReadOnly]
        [OslcRepresentation(Representation.Inline)]
        [OslcTitle("Value of the metadata")]
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

        [OslcDescription("Describes the key of the metadata.")]
        [OslcPropertyDefinition(SRLShapeConstants.OSLC_KM_SRL_VOCAB + "key")]
        [OslcReadOnly]
        [OslcRepresentation(Representation.Inline)]
        [OslcTitle("The key used as metadata.")]
        [OslcValueShape(OslcConstants.PATH_RESOURCE_SHAPES + "/" + OslcConstants.PATH_PUBLISHER)]
        [OslcValueType(OSLC4Net.Core.Model.ValueType.LocalResource)]
        public Artifact GetTag() {
            return this.key;
        }

        [OslcDescription("Describes the complex key.")]
        [OslcPropertyDefinition(SRLShapeConstants.OSLC_KM_SRL_VOCAB + "key")]
        [OslcReadOnly]
        [OslcRepresentation(Representation.Inline)]
        [OslcTitle("The key used as complex metadata.")]
        [OslcValueShape(OslcConstants.PATH_RESOURCE_SHAPES + "/" + OslcConstants.PATH_PUBLISHER)]
        [OslcValueType(OSLC4Net.Core.Model.ValueType.LocalResource)]
        public List<Artifact> GetComplexTag() {
            return this.complexKey;
        }
        public void SetComplexTag(List<Artifact> complexTag) {
            this.complexKey = complexTag;
        }
        [OslcDescription("Describes the complex value.")]
        [OslcPropertyDefinition(SRLShapeConstants.OSLC_KM_SRL_VOCAB + "key")]
        [OslcReadOnly]
        [OslcRepresentation(Representation.Inline)]
        [OslcTitle("The value used as complex metadata.")]
        [OslcValueShape(OslcConstants.PATH_RESOURCE_SHAPES + "/" + OslcConstants.PATH_PUBLISHER)]
        [OslcValueType(OSLC4Net.Core.Model.ValueType.LocalResource)]
        public List<Artifact> GetComplexValue() {
            return this.complexValue;
        }
        public void SetComplexValue(List<Artifact> complexValue) {
            this.complexKey = complexValue;
        }
        [OslcDescription("Describes the table metadata.")]
        [OslcPropertyDefinition(SRLShapeConstants.OSLC_KM_SRL_VOCAB + "key")]
        [OslcReadOnly]
        [OslcRepresentation(Representation.Inline)]
        [OslcTitle("The value used as table metadata.")]
        [OslcValueShape(OslcConstants.PATH_RESOURCE_SHAPES + "/" + OslcConstants.PATH_PUBLISHER)]
        [OslcValueType(OSLC4Net.Core.Model.ValueType.LocalResource)]
        public System.Data.DataTable GetTableData() {
            return this.tablesmMetaProperty;
        }
        public void SetTableData(System.Data.DataTable dataTableData) {
            this.tablesmMetaProperty = dataTableData;
        }
        [OslcDescription("Describes operation for the metadata element.")]
        [OslcPropertyDefinition(SRLShapeConstants.OSLC_KM_SRL_VOCAB + "key")]
        [OslcReadOnly]
        [OslcRepresentation(Representation.Inline)]
        [OslcTitle("The operation of the metadata.")]
        [OslcValueShape(OslcConstants.PATH_RESOURCE_SHAPES + "/" + OslcConstants.PATH_PUBLISHER)]
        [OslcValueType(OSLC4Net.Core.Model.ValueType.LocalResource)]
        public SrlOperator GetOperator() {
            return this.PropertyOperation;
        }
        public void SetOperator(SrlOperator propertyOperator) {
            this.PropertyOperation = propertyOperator;
        }
        [OslcDescription("Describes value type for the metadata element.")]
        [OslcPropertyDefinition(SRLShapeConstants.OSLC_KM_SRL_VOCAB + "key")]
        [OslcReadOnly]
        [OslcRepresentation(Representation.Inline)]
        [OslcTitle("The value type of the metadata element.")]
        [OslcValueShape(OslcConstants.PATH_RESOURCE_SHAPES + "/" + OslcConstants.PATH_PUBLISHER)]
        [OslcValueType(OSLC4Net.Core.Model.ValueType.LocalResource)]
        public SrlValueType GetValueType() {
            return this.valueType;
        }
        public void SetValueType(SrlValueType valueType) {
            this.valueType = valueType;
        }
        #endregion
    }
}