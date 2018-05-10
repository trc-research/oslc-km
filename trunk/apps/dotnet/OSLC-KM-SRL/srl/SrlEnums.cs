using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace OSLC_KM_SRL.srl {
    public enum SrlValueType {
        NoValue = 0,
        Decimal = 1,
        String = 2,
        Boolean = 3,
        Date = 4,
        Integer = 5,
        Double = 6,
        Range = 10,
        ArtifactValue = 100,
        TermValue = 101,
        Position = 200,
        Column = 210,
        TransformationLinkedPattern = 220,
        RBS = 230
    }
    public enum SrlOperator {
        Equal = 0,
        Greater = 1,
        GreaterEqual = 2,
        LessEqual = 3,
        Less = 4,
        Contains = 5,
        NotIn = 6,
        Distinct = 7,
        InRange = 8,
    }
}
