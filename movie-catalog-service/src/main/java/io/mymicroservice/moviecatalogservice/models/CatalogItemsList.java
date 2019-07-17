package io.mymicroservice.moviecatalogservice.models;

import java.util.List;

public class CatalogItemsList {

	List<CatalogItem> catalogItems;

	public CatalogItemsList() {}
	
	
	public CatalogItemsList(List<CatalogItem> catalogItems) {
		super();
		this.catalogItems = catalogItems;
	}


	public List<CatalogItem> getCatalogItems() {
		return catalogItems;
	}

	public void setCatalogItems(List<CatalogItem> catalogItems) {
		this.catalogItems = catalogItems;
	}
	
	
}
