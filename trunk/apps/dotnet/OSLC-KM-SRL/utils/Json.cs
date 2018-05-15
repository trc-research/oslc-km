using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using Newtonsoft.Json;
using OSLC_KM_SRL.srl;

namespace OSLC_KM_SRL.utils {
    public static class Json {
        #region Json Management        
        public static void Serialize(string path, srl.Artifact artifact) {
            using (System.IO.StreamWriter file = System.IO.File.CreateText(path)) {
                JsonSerializer serializer = new JsonSerializer();
                serializer.Serialize(file, artifact);
            }
        }
        public static string Serialize(srl.Artifact artifact) {            
            string json = string.Empty;
            json = JsonConvert.SerializeObject(artifact, Formatting.Indented);
            return json;
        }
        public static srl.Artifact Deserialize(string filePath) {
            Artifact result;
            using (System.IO.StreamReader file = System.IO.File.OpenText(filePath)) {
                JsonSerializer serializer = new JsonSerializer();
                result = (Artifact)serializer.Deserialize(file, typeof(Artifact));
            }
            return result;
        }
        public static Artifact DeserializeFromJsonString(string json) {
            Artifact result;
            result = JsonConvert.DeserializeObject<Artifact>(json);
            return result;
        }
        #endregion
    }
}
