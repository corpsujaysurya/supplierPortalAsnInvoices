package com.kpmg.te.retail.supplierportal.asninvoices.entity;

public class ASNItemDetails {

	private String itemId;
	private String itemName;
	private String poNum;
	private String orderedQty;
	private String deliveredQty;
	private String remainingQty;
	private String shippedQty;

	public String getItemId() {
		return itemId;
	}

	public void setItemId(String itemId) {
		this.itemId = itemId;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public String getPoNum() {
		return poNum;
	}

	public void setPoNum(String poNum) {
		this.poNum = poNum;
	}

	public String getOrderedQty() {
		return orderedQty;
	}

	public void setOrderedQty(String orderedQty) {
		this.orderedQty = orderedQty;
	}

	public String getDeliveredQty() {
		return deliveredQty;
	}

	public void setDeliveredQty(String deliveredQty) {
		this.deliveredQty = deliveredQty;
	}

	public String getRemainingQty() {
		return remainingQty;
	}

	public void setRemainingQty(String remainingQty) {
		this.remainingQty = remainingQty;
	}

	public String getShippedQty() {
		return shippedQty;
	}

	public void setShippedQty(String shippedQty) {
		this.shippedQty = shippedQty;
	}

	@Override
	public String toString() {
		return "ASNItemDetails [itemId=" + itemId + ", itemName=" + itemName + ", poNum=" + poNum + ", orderedQty="
				+ orderedQty + ", deliveredQty=" + deliveredQty + ", remainingQty=" + remainingQty + ", shippedQty="
				+ shippedQty + "]";
	}

}
