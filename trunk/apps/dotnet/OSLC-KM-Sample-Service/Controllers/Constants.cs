using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using OSLC4Net.Core.Model;

namespace OSLC.Controllers {
    public static class Constants {

        public static PrefixDefinition[] PREFIX_DEFINITIONS = {
                 new PrefixDefinition(OslcConstants.DCTERMS_NAMESPACE_PREFIX,   new Uri(OslcConstants.DCTERMS_NAMESPACE)),
                 new PrefixDefinition(OslcConstants.OSLC_CORE_NAMESPACE_PREFIX, new Uri(OslcConstants.OSLC_CORE_NAMESPACE)),
                 new PrefixDefinition(OslcConstants.RDF_NAMESPACE_PREFIX,       new Uri(OslcConstants.RDF_NAMESPACE)),
                 new PrefixDefinition(OslcConstants.RDFS_NAMESPACE_PREFIX,      new Uri(OslcConstants.RDFS_NAMESPACE)),
                 new PrefixDefinition("skos",      new Uri("http://www.w3.org/2004/02/skos/core# ")),
                 new PrefixDefinition("foaf",      new Uri("http://xmlns.com/foaf/0.1/")),
                 new PrefixDefinition("rshp",      new Uri("http://www.reusecompany.com/oslc/km/rshp/")),
                 new PrefixDefinition("xml",      new Uri("http://www.w3.org/XML/1998/namespace/")),

            };
    }
}