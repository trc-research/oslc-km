package com.reusecompany.srl.services.impl;

import static org.junit.Assert.*;

import org.junit.Test;

import com.reusecompany.srl.services.DataServices;

public class NaiveDataServicesImplTest {

	@Test
	public void testList() {
		DataServices ds = new NaiveDataServicesImpl();
		assertEquals(5, ds.list().size());
	}

	@Test
	public void testGetById() {
		DataServices ds = new NaiveDataServicesImpl();
		assertEquals("1",ds.getById("1").getId());
	}

}
