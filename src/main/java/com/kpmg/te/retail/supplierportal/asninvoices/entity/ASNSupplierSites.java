package com.kpmg.te.retail.supplierportal.asninvoices.entity;

public class ASNSupplierSites {

	private String siteId;
	private String siteName;
	private String siteAddr;
	private String siteStatus;
	private String siteLocation;
	private String siteContact;

	public String getSiteName() {
		return siteName;
	}

	public void setSiteName(String siteName) {
		this.siteName = siteName;
	}

	public String getSiteAddr() {
		return siteAddr;
	}

	public void setSiteAddr(String siteAddr) {
		this.siteAddr = siteAddr;
	}

	public String getSiteStatus() {
		return siteStatus;
	}

	public void setSiteStatus(String siteStatus) {
		this.siteStatus = siteStatus;
	}

	public String getSiteId() {
		return siteId;
	}

	public void setSiteId(String siteId) {
		this.siteId = siteId;
	}

	public String getSiteLocation() {
		return siteLocation;
	}

	public void setSiteLocation(String siteLocation) {
		this.siteLocation = siteLocation;
	}

	public String getSiteContact() {
		return siteContact;
	}

	public void setSiteContact(String siteContact) {
		this.siteContact = siteContact;
	}

	@Override
	public String toString() {
		return "ASNSupplierSites [siteId=" + siteId + ", siteName=" + siteName + ", siteAddr=" + siteAddr
				+ ", siteStatus=" + siteStatus + ", siteLocation=" + siteLocation + ", siteContact=" + siteContact
				+ "]";
	}

}
