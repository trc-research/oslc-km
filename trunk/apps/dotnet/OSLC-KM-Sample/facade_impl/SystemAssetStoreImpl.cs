using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using OSLC_KM_SRL.srl;
using OSLC_KM_SRL.facade;


namespace OSLC_KM_SRL.Sample.facade_impl {
    public class SystemAssetStoreImpl : SystemAssetStore {
        
        public string create(Artifact artifact) {
            return string.Empty;
        }
        public Artifact retrieve(string uri) {
            return new Artifact();
        }
        public bool update(Artifact artifact) {
            return false;
        }
        public bool delete(string uri) {
            return false;
        }
        public List<Artifact> list() {
            return new List<Artifact>();
        }
        public List<Artifact> search(SearchContext context) {
            return new List<Artifact>();
        }


    }
}
