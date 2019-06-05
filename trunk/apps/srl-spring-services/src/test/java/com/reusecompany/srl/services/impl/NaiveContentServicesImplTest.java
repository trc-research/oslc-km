package com.reusecompany.srl.services.impl;

import static org.junit.Assert.*;

import org.junit.Test;

import com.reusecompany.srl.services.Content2SRL;

public class NaiveContentServicesImplTest {

	@Test
	public void testList() {
		Content2SRL ds = new NaiveContentServicesImpl();
		assertEquals(4, ds.list().size());
	}

	@Test
	public void testGetById() {
		Content2SRL ds = new NaiveContentServicesImpl();
		assertEquals("11",ds.getArtifactById("11").getId());
	}

}
