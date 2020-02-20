package com.reusecompany.srl.services;

import java.util.List;

import com.reusecompany.srl.model.Artifact;

public interface SRLProviderCatalog {
	public byte connect(Artifact configuration);
	public byte disconnect();
	public List<SRLProvider> getAvailableInstances();
	public SRLProvider selectInstance();
}
