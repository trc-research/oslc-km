using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace OSLC_KM_SRL.srl {
    public class SRLShapeConstants {

        #region OSLC KM Base URIs


        public const string OSLC_KM_BASE_URI = "http://www.reusecompany.com/oslc/km";
        public const string OSLC_KM_SRL_BASE_URI = OSLC_KM_BASE_URI+"/srl";
        public const string OSLC_KM_SRL_DOMAIN = OSLC_KM_SRL_BASE_URI + "/";
        public const string OSLC_KM_SRL_VOCAB = OSLC_KM_SRL_BASE_URI + "/vocab/";

        #endregion

        #region OSLC KM Types URI

        public const string SRL_ARTIFACT = "Artifact";
        public const string SRL_ARTIFACT_URI = OSLC_KM_SRL_BASE_URI + "/"+SRL_ARTIFACT;

        public const string SRL_RELATIONSHIP = "Relationship";
        public const string SRL_RELATIONSHIP_URI = OSLC_KM_SRL_BASE_URI + "/" + SRL_RELATIONSHIP;

        public const string SRL_TERM = "Term";
        public const string SRL_TERM_URI = OSLC_KM_SRL_BASE_URI + "/" + SRL_TERM;

        public const string SRL_DATA = "Data";
        public const string SRL_DATA_URI = OSLC_KM_SRL_BASE_URI + "/" + SRL_DATA;

        public const string SRL_METADATA = "MetaData";
        public const string SRL_METADATA_URI = OSLC_KM_SRL_BASE_URI + "/" + SRL_METADATA;

        public const string SRL_TYPE = "Type";
        public const string SRL_TYPE_URI = OSLC_KM_SRL_BASE_URI + "/" + SRL_TYPE;

        #endregion
    }
}