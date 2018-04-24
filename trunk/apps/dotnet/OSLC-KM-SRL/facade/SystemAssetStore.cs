using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using OSLC_KM_SRL.srl;

namespace OSLC_KM_SRL.facade {
    public abstract class SystemAssetStore {

         public abstract string create(Artifact artifact);
         public abstract Artifact retrieve(string uri);
         public abstract bool update(Artifact artifact);
         public abstract bool delete(string uri);
         public abstract List<Artifact> list();
         public abstract List<Artifact> search(SearchContext context);



    }
}
