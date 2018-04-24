using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace OSLC_KM_SRL.utils {
    public static class SKOS {

        /** SKOS Namespace and Properties **/

        public const string CORE_NAMESPACE = "http://www.w3.org/2004/02/skos/core#";
        public const string PREFIX_CORE_NAMESPACE = "skos";
        public const string PREF_LABEL = CORE_NAMESPACE + "prefLabel";
        public const string ALT_LABEL = CORE_NAMESPACE + "altLabel";
        public const string HIDDEN_LABEL = CORE_NAMESPACE + "hiddenLabel";

        public const string CHANGE_NOTE = CORE_NAMESPACE + "changeNote";
        public const string EDITORIAL_NOTE = CORE_NAMESPACE + "editorialNote";
        public const string HISTORY_NOTE = CORE_NAMESPACE + "historyNote";
        public const string SCOPE_NOTE = CORE_NAMESPACE + "scopeNote";
        public const string EXAMPLE_NOTE = CORE_NAMESPACE + "example";
        public const string DEFINITION_NOTE = CORE_NAMESPACE + "definition";

        public const string NOTATION = CORE_NAMESPACE + "notation";
        public const string NARROWER = CORE_NAMESPACE + "narrower";
        public const string BROADER = CORE_NAMESPACE + "broader";
        public const string BROADER_TRANSITIVE = CORE_NAMESPACE + "broaderTransitive";
        public const string NARROWER_TRANSITIVE = CORE_NAMESPACE + "narrowerTransitive";


        public const string MATCH = CORE_NAMESPACE + "match";
        public const string CLOSE_MATCH = CORE_NAMESPACE + "closeMatch";
        public const string EXACT_MATCH = CORE_NAMESPACE + "exactMatch";
        public const string BROAD_MATCH = CORE_NAMESPACE + "broadMatch";
        public const string RELATED_MATCH = CORE_NAMESPACE + "relatedMatch";

        public const string IN_SCHEME = CORE_NAMESPACE + "inScheme";

        public const string CONCEPT = CORE_NAMESPACE + "Concept";

        public const string CONCEPT_SCHEME = CORE_NAMESPACE + "ConceptScheme";

    }
}