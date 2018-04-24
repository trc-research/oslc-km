using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using OSLC_KM_SRL.srl;
using OSLC_KM_SRL.facade;
using OSLC_KM_SRL.Sample.facade_impl;

namespace OSLC_KM_Sample.test {
    class SystemAssetStoreImplTest {

        //[Test]
        public void TestCreate() {
            Artifact artifact = new Artifact();
            SystemAssetStore sas = new SystemAssetStoreImpl();
            string uri = sas.create(artifact);
            //Assert uri not null
           
        }

        //[Test]
        public void TestRetrieve() {
            string uri = string.Empty;
            SystemAssetStore sas = new SystemAssetStoreImpl();
            Artifact artifact = sas.retrieve(uri);
            //Assert artifact not null + content
        }

        //[Test]
        public void TestUpdate() {
            Artifact artifact = new Artifact();
            SystemAssetStore sas = new SystemAssetStoreImpl();
            bool result = sas.update(artifact);
            //Assert result == true
        }

        //[Test]
        public void TestDelete() {
            string uri = string.Empty;
            SystemAssetStore sas = new SystemAssetStoreImpl();
            bool result = sas.delete(uri);
            //Assert result == true
            Artifact artifact = sas.retrieve(uri);
            //Assert artifact == null
       
        }

        //[Test]
        public void TestList() {
            SystemAssetStore sas = new SystemAssetStoreImpl();
            List<Artifact> results = sas.list();
            //Assert results.size > 0 
        }

        //[Test]
        public void TestSearch() {
            SearchContext context = new SearchContext();
            context.Text = "text";
            SystemAssetStore sas = new SystemAssetStoreImpl();
            List<Artifact> results = sas.search(context);
            //Assert results.size > 0 
        }
    }
}
