using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using OSLC_KM_SRL.srl;
using OSLC_KM_SRL.facade;

namespace OSLC_KM_SRL.Sample.facade_impl {
    public class SystemKnowledgeRepositoryImpl:SystemKnowledgeRepository {

        public List<Artifact> index(RepositoryContext context) {
            return new List<Artifact>();
        }
        public List<Artifact> trace(string uri, RepositoryContext context) {
            return new List<Artifact>();
        }
        public string visualize(string uri) {
            return string.Empty;
        }
        public string visualize(Artifact artifact) {
            return string.Empty;
        }
        public List<Artifact> normalize(RepositoryContext context) {
            return new List<Artifact>();
        }
        


    }
}
