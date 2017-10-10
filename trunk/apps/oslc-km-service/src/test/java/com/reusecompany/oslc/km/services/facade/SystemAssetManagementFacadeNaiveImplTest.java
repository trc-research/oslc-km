package com.reusecompany.oslc.km.services.facade;

import com.reusecompany.oslc.km.srl.Artifact;

import junit.framework.TestCase;

public class SystemAssetManagementFacadeNaiveImplTest extends TestCase {

	public void testGetArtifacts() {
		SystemAssetManagementFacade facade = new SystemAssetManagementFacadeOWLImpl();
		assertEquals(5, facade.getArtifacts().size());
	}

	public void testGetArtifactById() {
		SystemAssetManagementFacade facade = new SystemAssetManagementFacadeOWLImpl();
		assertNotNull(facade.getArtifactById(1));
		assertEquals(Artifact.EMPTY_ARTIFACT, facade.getArtifactById(-1));
	}

	public void testGetArtifactTermsById() {
		SystemAssetManagementFacade facade = new SystemAssetManagementFacadeOWLImpl();
		assertEquals(4, facade.getArtifactTermsById(1).size());
	}

	public void testGetArtifactMetaPropertiesById() {
		SystemAssetManagementFacade facade = new SystemAssetManagementFacadeOWLImpl();
		assertEquals(0, facade.getArtifactMetaPropertiesById(1).size());
	}

	public void testGetArtifactSubArtifactsByIdInt() {
		SystemAssetManagementFacade facade = new SystemAssetManagementFacadeOWLImpl();
		assertEquals(3, facade.getArtifactSubArtifactsById(1).size());
	}

	public void testGetArtifactSubArtifactsByIdIntInt() {
		SystemAssetManagementFacade facade = new SystemAssetManagementFacadeOWLImpl();
		assertNotNull(facade.getArtifactSubArtifactsById(1, 1));
	}

}
