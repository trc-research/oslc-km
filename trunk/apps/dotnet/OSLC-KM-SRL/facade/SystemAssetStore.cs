using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using OSLC_KM_SRL.srl;

namespace OSLC_KM_SRL.facade {
    public interface SystemAssetStore {

           string create(Artifact artifact);
           Artifact retrieve(string uri);
           bool update(Artifact artifact);
           bool delete(string uri);
           List<Artifact> list();
           List<Artifact> search(SearchContext context);



    }
}
