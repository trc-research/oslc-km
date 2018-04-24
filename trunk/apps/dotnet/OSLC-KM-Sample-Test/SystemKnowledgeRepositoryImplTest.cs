using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using OSLC_KM_SRL.srl;
using OSLC_KM_SRL.facade;
using OSLC_KM_SRL.Sample.facade_impl;


namespace OSLC_Sample.test {
    class SystemKnowledgeRepositoryImplTest {

        //[Test]
        public void TestIndex() {
            RepositoryContext context = new RepositoryContext();
            context.Text = "text";
            SystemKnowledgeRepository skr = new SystemKnowledgeRepositoryImpl();
            
            //Assert results.size > 0 

        }
        //[Test]
        public void TestTrace() {
            string uri = string.Empty;
            RepositoryContext context = new RepositoryContext();
            context.TraceType = "type";
            SystemKnowledgeRepository skr = new SystemKnowledgeRepositoryImpl();
            List<Artifact> results = skr.trace(uri,context);
            //Assert results.size > 0 
        }
        //[Test]
        public void TestVisualize_URI() {
            string uri = string.Empty;
            SystemKnowledgeRepository skr = new SystemKnowledgeRepositoryImpl();
            string result = skr.visualize(uri);
            //Assert not null result
            
        }
        //[Test]
        public void TestVisualize() {
            Artifact artifact = new Artifact();
            SystemKnowledgeRepository skr = new SystemKnowledgeRepositoryImpl();
            string result = skr.visualize(artifact);
            //Assert not null result
        }
        //[Test]
        public void TestNormalize() {
            RepositoryContext context = new RepositoryContext();
            context.Text = "text";
            SystemKnowledgeRepository skr = new SystemKnowledgeRepositoryImpl();
            List<Artifact> results = skr.normalize(context);
            //Assert results.size > 0 
        }
        


    }
}
