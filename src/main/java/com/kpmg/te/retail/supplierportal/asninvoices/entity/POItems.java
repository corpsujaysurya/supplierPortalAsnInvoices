package com.kpmg.te.retail.supplierportal.asninvoices.entity;

public class POItems {

	private String poNumber;
	private String productId;
	private String productName;
	private String productQty;
	private String deliveredQty;
	private String shippingQty;
	private String productCost;

	public String getPoNumber() {
		return poNumber;
	}

	public void setPoNumber(String poNumber) {
		this.poNumber = poNumber;
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

	public String getProductQty() {
		return productQty;
	}

	public void setProductQty(String productQty) {
		this.productQty = productQty;
	}

	public String getDeliveredQty() {
		return deliveredQty;
	}

	public void setDeliveredQty(String deliveredQty) {
		this.deliveredQty = deliveredQty;
	}

	public String getShippingQty() {
		return shippingQty;
	}

	public void setShippingQty(String shippingQty) {
		this.shippingQty = shippingQty;
	}

	public String getProductCost() {
		return productCost;
	}

	public void setProductCost(String productCost) {
		this.productCost = productCost;
	}

	@Override
	public String toString() {
		return "POItems [poNumber=" + poNumber + ", productId=" + productId + ", productName=" + productName
				+ ", productQty=" + productQty + ", deliveredQty=" + deliveredQty + ", shippingQty=" + shippingQty
				+ ", productCost=" + productCost + "]";
	}

}
