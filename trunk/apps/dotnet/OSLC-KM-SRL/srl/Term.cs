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
    [OslcResourceShape(title = "Vocabulary (Minimal) Concept Resource Shape", describes = new string[] {
        SKOS.CONCEPT })]
    public class Term : AbstractResource {
        const string DEFAULT_TERM_TYPE = "NOUN";

        #region Properties for Term
        [JsonProperty(PropertyName = "prefLabel")]
        private string prefLabel;
        [JsonProperty(PropertyName = "lang")]
        private string lang;
        [JsonProperty(PropertyName = "type")]
        private Type type = new Type(DEFAULT_TERM_TYPE);
        [JsonProperty(PropertyName = "semanticClusters")]
        private List<Type> semanticClusters = null;
        #endregion

        #region OSLC Core properties

        private string identifier = string.Empty;
        private Uri serviceProvider;
        private Uri baseUri;

        #endregion

        #region Constructors
        public Term() {
            // TODO Auto-generated constructor stub
            init();
        }

        public Term(string label, string language, Type type) {
            this.prefLabel = label;
            this.lang = language;
            this.type = type;
            init();
        }

        public Term(string label) {
            this.prefLabel = label;
            init();
        }
        public Term(string label, string language) {
            this.prefLabel = label;
            this.lang = language;
            init();
        }
        private void init() {
            semanticClusters = new List<Type>();
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
        [OslcDescription("A preferred label for each element of the vocabulary. The tuple (literal, lang) should be unique.")]
        [OslcOccurs(Occurs.ZeroOrOne)]
        [OslcPropertyDefinition(SKOS.PREF_LABEL)]
        [OslcReadOnly]
        [OslcTitle("A preferred label")]
        [OslcName("prefLabel")]
        public string GetPrefLabel() {
            return this.prefLabel;
        }
        public void SetPrefLabel(string label) {
            this.prefLabel = label;
        }


        [OslcDescription("The language of the term.")]
        [OslcOccurs(Occurs.ZeroOrOne)]
        [OslcPropertyDefinition(XML.NAMESPACE + "lang")]
        [OslcReadOnly]
        [OslcTitle("A language")]
        [OslcName("lang")]
        public string GetLang() {
            return this.lang;
        }
        public void SetLang(string language) {
            this.lang = language;
        }

        [OslcDescription("Term Type (syntax)")]
        [OslcPropertyDefinition(SRLShapeConstants.OSLC_KM_SRL_VOCAB + "type")]
        [OslcReadOnly]
        [OslcRepresentation(Representation.Inline)]
        [OslcTitle("Term Type (syntax)")]
        [OslcName("type")]
        [OslcValueType(OSLC4Net.Core.Model.ValueType.LocalResource)]
        public Type GetTermType() {
            return this.type;
        }

        public void GetTermType(Type type) {
            this.type = type;
        }
        #endregion
    }
}