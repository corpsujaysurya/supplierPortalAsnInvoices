package com.kpmg.te.retail.supplierportal.asninvoices.entity;

public class POItems {

	private String itemId;
	private String poNumber;
	private String productName;
	private String productQty;
	private String productCost;

	public String getItemId() {
		return itemId;
	}

	public void setItemId(String itemId) {
		this.itemId = itemId;
	}

	public String getPoNumber() {
		return poNumber;
	}

	public void setPoNumber(String poNumber) {
		this.poNumber = poNumber;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getProductQty() {
		return productQty;
	}

	public void setProductQty(String productQty) {
		this.productQty = productQty;
	}

	public String getProductCost() {
		return productCost;
	}

	public void setProductCost(String productCost) {
		this.productCost = productCost;
	}

	@Override
	public String toString() {
		return "POItems [itemId=" + itemId + ", poNumber=" + poNumber + ", productName=" + productName + ", productQty="
				+ productQty + ", productCost=" + productCost + "]";
	}

}
