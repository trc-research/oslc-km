using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using OSLC_KM_SRL.srl;

namespace OSLC_KM_SRL.facade {
    public class RepositoryContext {
        public string Text {
            get;
            set;
        }
        public string Content {
            get;
            set;
        }

        public Data data {
            get;
            set;
        }

        public MetaData metadata {
            get;
            set;
        }

        public string TraceType {
            get;
            set;
        }

    }
}
