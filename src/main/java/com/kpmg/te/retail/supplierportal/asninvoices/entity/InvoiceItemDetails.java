package com.kpmg.te.retail.supplierportal.asninvoices.entity;

public class InvoiceItemDetails {

	private String itemId;
	private String itemName;
	private String poNum;
	private String orderedQty;

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

	@Override
	public String toString() {
		return " [itemId=" + itemId + ", itemName=" + itemName + ", poNum=" + poNum + ", orderedQty="
				+ orderedQty + "]";
	}

}
