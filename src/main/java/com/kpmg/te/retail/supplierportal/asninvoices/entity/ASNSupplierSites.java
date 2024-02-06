package com.kpmg.te.retail.supplierportal.asninvoices.entity;

public class ASNSupplierSites {

	private String siteName;
	private String siteAddr;
	private String siteStatus;

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

	@Override
	public String toString() {
		return "ASNSupplierSites [siteName=" + siteName + ", siteAddr=" + siteAddr + ", siteStatus=" + siteStatus + "]";
	}

}
