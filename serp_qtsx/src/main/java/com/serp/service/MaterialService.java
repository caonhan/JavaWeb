package com.serp.service;

import java.util.List;

import com.serp.model.Material;

/**
 *@author ThanhVan
 *@version 1.0 Apr 13, 2016
 */
public interface MaterialService {
	public void addMaterial(Material material);

	public void deleteMaterial(Material material);

	public void updateMaterial(Material material);

	public Material getMaterial(String id);

	public List<Material> getAllMaterial();
}
