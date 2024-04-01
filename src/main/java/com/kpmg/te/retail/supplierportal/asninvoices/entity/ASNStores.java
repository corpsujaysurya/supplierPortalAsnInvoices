package com.kpmg.te.retail.supplierportal.asninvoices.entity;

public class ASNStores {

	private String storeId;
	private String storeName;
	private String storeAddr;
	private String storeStatus;

	public String getStoreId() {
		return storeId;
	}

	public void setStoreId(String storeId) {
		this.storeId = storeId;
	}

	public String getStoreName() {
		return storeName;
	}

	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}

	public String getStoreAddr() {
		return storeAddr;
	}

	public void setStoreAddr(String storeAddr) {
		this.storeAddr = storeAddr;
	}

	public String getStoreStatus() {
		return storeStatus;
	}

	public void setStoreStatus(String storeStatus) {
		this.storeStatus = storeStatus;
	}

	@Override
	public String toString() {
		return "ASNStores [storeId=" + storeId + ", storeName=" + storeName + ", storeAddr=" + storeAddr
				+ ", storeStatus=" + storeStatus + "]";
	}

}
