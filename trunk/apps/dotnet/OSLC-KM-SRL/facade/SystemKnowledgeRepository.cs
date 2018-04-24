using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using OSLC_KM_SRL.srl;

namespace OSLC_KM_SRL.facade {
    public interface SystemKnowledgeRepository {

          List<Artifact> index(RepositoryContext context);
          List<Artifact> trace(string uri, RepositoryContext context);
          string visualize(string uri);
          string visualize(Artifact artifact);
          List<Artifact> normalize(RepositoryContext context);
        

    }
}
