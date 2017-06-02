package com.bms.domain;

import org.springframework.data.annotation.Id;

public class ProductList {

	@Id
	private String id;
	private String productId;
	private String productName;
	private String Quantity;

	public ProductList() {
	}

	/**
	 * @param title
	 * @param artist
	 * @param releaseYear
	 */
	public ProductList(String productId, String productName, String Quantity) {
		this.productId = productId;
		this.productName = productName;
		this.Quantity = Quantity;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getQuantity() {
		return Quantity;
	}

	public void setQuantity(String quantity) {
		Quantity = quantity;
	}

	@Override
	public String toString() {
		return "ProductList [id=" + id + ", productId=" + productId + ", productName=" + productName + ", Quantity="
				+ Quantity + "]";
	}

}
