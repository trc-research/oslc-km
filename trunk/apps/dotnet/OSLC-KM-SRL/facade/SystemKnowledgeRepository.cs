using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using OSLC_KM_SRL.srl;

namespace OSLC_KM_SRL.facade {
    public abstract class SystemKnowledgeRepository {

        public abstract List<Artifact> index(RepositoryContext context);
        public abstract List<Artifact> trace(string uri, RepositoryContext context);
        public abstract string visualize(string uri);
        public abstract string visualize(Artifact artifact);
        public abstract List<Artifact> normalize(RepositoryContext context);
        

    }
}
