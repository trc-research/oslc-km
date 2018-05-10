using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using Newtonsoft.Json;
using OSLC4Net.Core.Attribute;
using OSLC4Net.Core.Model;

namespace OSLC_KM_SRL.srl {
    [OslcNamespace(SRLShapeConstants.OSLC_KM_SRL_DOMAIN)]
    [OslcName(SRLShapeConstants.SRL_ARTIFACT)]
    [OslcResourceShape(title = "SRL Artifact Resource Shape", describes = new string[] {
        SRLShapeConstants.SRL_ARTIFACT_URI})]
    public class Artifact : AbstractResource {

        #region Properties for KE
        [JsonProperty(PropertyName = "term")]
        private Term term;
        [JsonProperty(PropertyName = "ke")]
        private bool ke = false;

        #endregion

        #region Properties for Artifact

        [JsonProperty(PropertyName = "artifactType")]
        private Type type;
        [JsonProperty(PropertyName = "rshps")]
        private List<Relationship> relationships = null;
        [JsonProperty(PropertyName = "metadatas")]
        private List<MetaData> metadata = null;
        [JsonProperty(PropertyName = "data")]
        private List<Data> data = null;
        [JsonProperty(PropertyName = "ownedArtifacts")]
        private List<Artifact> ownedArtifacts = null;


        #endregion

        #region OSLC Core properties
        [JsonProperty(PropertyName = "identifier")]
        private string identifier = string.Empty;
        [JsonProperty(PropertyName = "serviceProvider")]
        private Uri serviceProvider;
        #endregion

        #region Image, Binary and Physical Path
        [JsonProperty(PropertyName = "Content")]
        private string Content = null;
        [JsonProperty(PropertyName = "Snapshot")]
        private string SnapShot = null;
        [JsonProperty(PropertyName = "PhysicalPath")]
        private string PhysicalPath = string.Empty;
        #endregion

        #region Constructors
        public Artifact() {
            init();
        }
        public Artifact(bool isKE, Uri uri) {
            this.ke = isKE;
            this.SetAbout(uri);
            init();
        }
        public Artifact(string term) {
            this.term = new Term();
            this.term.SetPrefLabel(term);
            init();
        }
        private void init() {
            this.type = DEFAULT_ARTIFACT_TYPE;
            this.relationships = new List<Relationship>();
            this.metadata = new List<MetaData>();
            this.data = new List<Data>();
            this.ownedArtifacts = new List<Artifact>();
        }
        public static readonly Type DEFAULT_ARTIFACT_TYPE;
        static Artifact() {
            DEFAULT_ARTIFACT_TYPE = new Type();
            DEFAULT_ARTIFACT_TYPE.SetIdentifier("19");
            DEFAULT_ARTIFACT_TYPE.SetName("TERM");
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
        [OslcDescription("Artifact Type")]
        [OslcPropertyDefinition(SRLShapeConstants.OSLC_KM_SRL_VOCAB + "type")]
        [OslcReadOnly]
        [OslcRepresentation(Representation.Inline)]
        [OslcTitle("Artifact Type")]
        [OslcName("type")]
        [OslcValueType(OSLC4Net.Core.Model.ValueType.LocalResource)]
        public Type GetArtifactType() {
            return this.type;
        }

        public void SetArtifactType(Type type) {
            this.type = type;
        }


        [OslcDescription("Content")]
        [OslcPropertyDefinition(SRLShapeConstants.OSLC_KM_SRL_VOCAB + "content")]
        [OslcReadOnly]
        [OslcRepresentation(Representation.Inline)]
        [OslcTitle("Content")]
        [OslcName("content")]
        [OslcValueType(OSLC4Net.Core.Model.ValueType.LocalResource)]
        public Relationship[] GetRelationships() {
            return this.relationships.ToArray<Relationship>();
        }
        public void SetRelationships(Relationship[] relationships) {
            this.relationships.Clear();
            this.relationships.AddAll(relationships);
        }

        public void SetRelationshipsAsList(List<Relationship> relationships) {
            this.relationships.Clear();
            this.relationships = null;
            this.relationships = relationships;
        }
        public List<Relationship> GetRelationshipsAsList() {
            return this.relationships;
        }
        [OslcDescription("Artifact concept")]
        [OslcPropertyDefinition(SRLShapeConstants.OSLC_KM_SRL_VOCAB + "concept")]
        [OslcReadOnly]
        [OslcRepresentation(Representation.Inline)]
        [OslcTitle("Artifact concept")]
        [OslcValueType(OSLC4Net.Core.Model.ValueType.LocalResource)]
        public Term GetTerm() {
            return term;
        }
        public void SetTerm(Term term) {
            this.term = term;
        }
        [OslcDescription("The metadata available forthis knowledge element.")]
        [OslcPropertyDefinition(SRLShapeConstants.OSLC_KM_SRL_VOCAB + "metadata")]
        [OslcReadOnly]
        [OslcRepresentation(Representation.Inline)]
        [OslcTitle("The metadata")]
        [OslcValueShape(OslcConstants.PATH_RESOURCE_SHAPES + "/" + OslcConstants.PATH_PUBLISHER)]
        [OslcValueType(OSLC4Net.Core.Model.ValueType.LocalResource)]
        public MetaData[] GetMetaData() {
            return this.metadata.ToArray<MetaData>();
        }
        public void SetMetaData(MetaData[] metadata) {
            this.metadata.Clear();
            this.metadata.AddAll(metadata);
        }
        [OslcDescription("The data available forthis knowledge element.")]
        [OslcPropertyDefinition(SRLShapeConstants.OSLC_KM_SRL_VOCAB + "data")]
        [OslcReadOnly]
        [OslcRepresentation(Representation.Inline)]
        [OslcTitle("The data")]
        [OslcValueShape(OslcConstants.PATH_RESOURCE_SHAPES + "/" + OslcConstants.PATH_PUBLISHER)]
        [OslcValueType(OSLC4Net.Core.Model.ValueType.LocalResource)]
        public Data[] GetData() {
            return this.data.ToArray<Data>();
        }
        public void SetData(Data[] data) {
            this.data.Clear();
            this.data.AddAll(data);
        }
        [OslcDescription("The metadata available for this knowledge element in a list format.")]
        [OslcPropertyDefinition(SRLShapeConstants.OSLC_KM_SRL_VOCAB + "data")]
        [OslcReadOnly]
        [OslcRepresentation(Representation.Inline)]
        [OslcTitle("The metadata")]
        [OslcValueShape(OslcConstants.PATH_RESOURCE_SHAPES + "/" + OslcConstants.PATH_PUBLISHER)]
        [OslcValueType(OSLC4Net.Core.Model.ValueType.LocalResource)]
        public List<MetaData> GetMetaDataAsList() {
            return this.metadata;
        }
        public void SetMetaDataAsList(List<MetaData> metadata) {
            this.metadata = metadata;
        }
        [OslcDescription("The data available for this knowledge element in a list format.")]
        [OslcPropertyDefinition(SRLShapeConstants.OSLC_KM_SRL_VOCAB + "data")]
        [OslcReadOnly]
        [OslcRepresentation(Representation.Inline)]
        [OslcTitle("The data")]
        [OslcValueShape(OslcConstants.PATH_RESOURCE_SHAPES + "/" + OslcConstants.PATH_PUBLISHER)]
        [OslcValueType(OSLC4Net.Core.Model.ValueType.LocalResource)]
        public List<Data> GetDataAsList() {
            return this.data;
        }
        public void SetDataAsList(List<Data> data) {
            this.data = data;
        }
        [OslcDescription("The content in binary format for this knowledge element.")]
        [OslcPropertyDefinition(SRLShapeConstants.OSLC_KM_SRL_VOCAB + "data")]
        [OslcReadOnly]
        [OslcRepresentation(Representation.Inline)]
        [OslcTitle("The data")]
        [OslcValueShape(OslcConstants.PATH_RESOURCE_SHAPES + "/" + OslcConstants.PATH_PUBLISHER)]
        [OslcValueType(OSLC4Net.Core.Model.ValueType.LocalResource)]
        public byte[] GetContent() {
            if (this.Content != null) {
                return Convert.FromBase64String(this.Content);
            } else {
                return null;
            }
        }
        public void SetContent(byte[] artifactContent) {
            if (artifactContent != null) {
                this.Content = Convert.ToBase64String(artifactContent);
            }
        }
        [OslcDescription("The content in image binary format for this knowledge element.")]
        [OslcPropertyDefinition(SRLShapeConstants.OSLC_KM_SRL_VOCAB + "data")]
        [OslcReadOnly]
        [OslcRepresentation(Representation.Inline)]
        [OslcTitle("The data")]
        [OslcValueShape(OslcConstants.PATH_RESOURCE_SHAPES + "/" + OslcConstants.PATH_PUBLISHER)]
        [OslcValueType(OSLC4Net.Core.Model.ValueType.LocalResource)]
        public byte[] GetSnapshot() {
            if (this.SnapShot != null) {
                return Convert.FromBase64String(this.SnapShot);
            } else {
                return null;
            }
        }
        public void SetSnapshot(byte[] artifactshapshot) {
            if (artifactshapshot != null) {
                this.SnapShot = Convert.ToBase64String(artifactshapshot);
            }

        }
        [OslcDescription("Physical or logical path for this knowledge element.")]
        [OslcPropertyDefinition(SRLShapeConstants.OSLC_KM_SRL_VOCAB + "data")]
        [OslcReadOnly]
        [OslcRepresentation(Representation.Inline)]
        [OslcTitle("The data")]
        [OslcValueShape(OslcConstants.PATH_RESOURCE_SHAPES + "/" + OslcConstants.PATH_PUBLISHER)]
        [OslcValueType(OSLC4Net.Core.Model.ValueType.LocalResource)]
        public string GetPhysicalPath() {
            return this.PhysicalPath;
        }
        public void SetPhysicalPath(string PhysicalPath) {
            this.PhysicalPath = PhysicalPath;
        }
        public bool isKE() {
            return this.ke;
        }
        #endregion

        #region Json Management
        public void Serialize(string path) {
            using (System.IO.StreamWriter file = System.IO.File.CreateText(path)) {
                JsonSerializer serializer = new JsonSerializer();
                serializer.Serialize(file, this);
            }
        }
        public void SerializeToJson(string path) {
            using (System.IO.StreamWriter file = System.IO.File.CreateText(path)) {
                JsonSerializer serializer = new JsonSerializer();
                serializer.Serialize(file, this);
            }
        }
        public string SerializeToJson() {
            string json = string.Empty;
            json = JsonConvert.SerializeObject(this, Formatting.Indented);
            return json;
        }
        public Artifact DeserializeFromJson(string filePath) {
            Artifact result;
            using (System.IO.StreamReader file = System.IO.File.OpenText(filePath)) {
                JsonSerializer serializer = new JsonSerializer();
                result = (Artifact)serializer.Deserialize(file, typeof(Artifact));
            }
            return result;
        }
        public Artifact DeserializeFromJsonString(string json) {
            Artifact result;
            result = JsonConvert.DeserializeObject<Artifact>(json);
            return result;
        }
        #endregion


    }
}