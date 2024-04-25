package com.kpmg.te.retail.supplierportal.asninvoices.entity;

import java.util.Objects;

public class ASNMaster {

	private String uniqueId;
	private String asnId;
	private String asnCreationDate;
	private String shippingDate;
	private String deliveryNoteNo;
	private String asnStatus;
	private String poNum;
	private String containerDetails;
	private String containerCount;
	private String containerId;

	private String shippedQty;
	private String estimatedDelDate;
	private String ewayNo;
	private String awbNo;
	private String driverName;
	private String vehicleNo;
	private String modeOfTransport;
	private String transportCompName;
	private String consignmentWeight;
	private String driverLicenseNo;
	private String shippingAddr;
	private String delAddr;
	private String consignmentCost;
	private String permitLevel;
	private String vehicleEngNo;
	private String vehicleChassiesNo;
	private String retailerStore;
	private String supplierSite;
	private String preferredDelDate;
	private String preferredDelTime;

	public String getUniqueId() {
		return uniqueId;
	}

	public void setUniqueId(String uniqueId) {
		this.uniqueId = uniqueId;
	}

	public String getAsnId() {
		return asnId;
	}

	public void setAsnId(String asnId) {
		this.asnId = asnId;
	}

	public String getAsnCreationDate() {
		return asnCreationDate;
	}

	public void setAsnCreationDate(String asnCreationDate) {
		this.asnCreationDate = asnCreationDate;
	}

	public String getContainerId() {
		return containerId;
	}

	public void setContainerId(String containerId) {
		this.containerId = containerId;
	}

	public String getShippingDate() {
		return shippingDate;
	}

	public void setShippingDate(String shippingDate) {
		this.shippingDate = shippingDate;
	}

	public String getDeliveryNoteNo() {
		return deliveryNoteNo;
	}

	public String getContainerCount() {
		return containerCount;
	}

	public void setContainerCount(String containerCount) {
		this.containerCount = containerCount;
	}

	public void setDeliveryNoteNo(String deliveryNoteNo) {
		this.deliveryNoteNo = deliveryNoteNo;
	}

	public String getAsnStatus() {
		return asnStatus;
	}

	public void setAsnStatus(String asnStatus) {
		this.asnStatus = asnStatus;
	}

	public String getPoNum() {
		return poNum;
	}

	public void setPoNum(String poNum) {
		this.poNum = poNum;
	}

	public String getContainerDetails() {
		return containerDetails;
	}

	public void setContainerDetails(String containerDetails) {
		this.containerDetails = containerDetails;
	}

	public String getShippedQty() {
		return shippedQty;
	}

	public void setShippedQty(String shippedQty) {
		this.shippedQty = shippedQty;
	}

	public String getEstimatedDelDate() {
		return estimatedDelDate;
	}

	public void setEstimatedDelDate(String estimatedDelDate) {
		this.estimatedDelDate = estimatedDelDate;
	}

	public String getEwayNo() {
		return ewayNo;
	}

	public void setEwayNo(String ewayNo) {
		this.ewayNo = ewayNo;
	}

	public String getAwbNo() {
		return awbNo;
	}

	public void setAwbNo(String awbNo) {
		this.awbNo = awbNo;
	}

	public String getDriverName() {
		return driverName;
	}

	public void setDriverName(String driverName) {
		this.driverName = driverName;
	}

	public String getVehicleNo() {
		return vehicleNo;
	}

	public void setVehicleNo(String vehicleNo) {
		this.vehicleNo = vehicleNo;
	}

	public String getModeOfTransport() {
		return modeOfTransport;
	}

	public void setModeOfTransport(String modeOfTransport) {
		this.modeOfTransport = modeOfTransport;
	}

	public String getTransportCompName() {
		return transportCompName;
	}

	public void setTransportCompName(String transportCompName) {
		this.transportCompName = transportCompName;
	}

	public String getConsignmentWeight() {
		return consignmentWeight;
	}

	public void setConsignmentWeight(String consignmentWeight) {
		this.consignmentWeight = consignmentWeight;
	}

	public String getDriverLicenseNo() {
		return driverLicenseNo;
	}

	public void setDriverLicenseNo(String driverLicenseNo) {
		this.driverLicenseNo = driverLicenseNo;
	}

	public String getShippingAddr() {
		return shippingAddr;
	}

	public void setShippingAddr(String shippingAddr) {
		this.shippingAddr = shippingAddr;
	}

	public String getDelAddr() {
		return delAddr;
	}

	public void setDelAddr(String delAddr) {
		this.delAddr = delAddr;
	}

	public String getConsignmentCost() {
		return consignmentCost;
	}

	public void setConsignmentCost(String consignmentCost) {
		this.consignmentCost = consignmentCost;
	}

	public String getPermitLevel() {
		return permitLevel;
	}

	public void setPermitLevel(String permitLevel) {
		this.permitLevel = permitLevel;
	}

	public String getVehicleEngNo() {
		return vehicleEngNo;
	}

	public void setVehicleEngNo(String vehicleEngNo) {
		this.vehicleEngNo = vehicleEngNo;
	}

	public String getVehicleChassiesNo() {
		return vehicleChassiesNo;
	}

	public void setVehicleChassiesNo(String vehicleChassiesNo) {
		this.vehicleChassiesNo = vehicleChassiesNo;
	}

	public String getRetailerStore() {
		return retailerStore;
	}

	public void setRetailerStore(String retailerStore) {
		this.retailerStore = retailerStore;
	}

	public String getSupplierSite() {
		return supplierSite;
	}

	public void setSupplierSite(String supplierSite) {
		this.supplierSite = supplierSite;
	}

	public String getPreferredDelDate() {
		return preferredDelDate;
	}

	public void setPreferredDelDate(String preferredDelDate) {
		this.preferredDelDate = preferredDelDate;
	}

	public String getPreferredDelTime() {
		return preferredDelTime;
	}

	public void setPreferredDelTime(String preferredDelTime) {
		this.preferredDelTime = preferredDelTime;
	}

	@Override
	public int hashCode() {
		return Objects.hash(asnCreationDate, asnId, asnStatus, awbNo, consignmentCost, consignmentWeight,
				containerCount, containerDetails, containerId, delAddr, deliveryNoteNo, driverLicenseNo, driverName,
				estimatedDelDate, ewayNo, modeOfTransport, permitLevel, poNum, preferredDelDate, preferredDelTime,
				retailerStore, shippedQty, shippingAddr, shippingDate, supplierSite, transportCompName, uniqueId,
				vehicleChassiesNo, vehicleEngNo, vehicleNo);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ASNMaster other = (ASNMaster) obj;
		return Objects.equals(asnCreationDate, other.asnCreationDate) && Objects.equals(asnId, other.asnId)
				&& Objects.equals(asnStatus, other.asnStatus) && Objects.equals(awbNo, other.awbNo)
				&& Objects.equals(consignmentCost, other.consignmentCost)
				&& Objects.equals(consignmentWeight, other.consignmentWeight)
				&& Objects.equals(containerCount, other.containerCount)
				&& Objects.equals(containerDetails, other.containerDetails)
				&& Objects.equals(containerId, other.containerId) && Objects.equals(delAddr, other.delAddr)
				&& Objects.equals(deliveryNoteNo, other.deliveryNoteNo)
				&& Objects.equals(driverLicenseNo, other.driverLicenseNo)
				&& Objects.equals(driverName, other.driverName)
				&& Objects.equals(estimatedDelDate, other.estimatedDelDate) && Objects.equals(ewayNo, other.ewayNo)
				&& Objects.equals(modeOfTransport, other.modeOfTransport)
				&& Objects.equals(permitLevel, other.permitLevel) && Objects.equals(poNum, other.poNum)
				&& Objects.equals(preferredDelDate, other.preferredDelDate)
				&& Objects.equals(preferredDelTime, other.preferredDelTime)
				&& Objects.equals(retailerStore, other.retailerStore) && Objects.equals(shippedQty, other.shippedQty)
				&& Objects.equals(shippingAddr, other.shippingAddr) && Objects.equals(shippingDate, other.shippingDate)
				&& Objects.equals(supplierSite, other.supplierSite)
				&& Objects.equals(transportCompName, other.transportCompName)
				&& Objects.equals(uniqueId, other.uniqueId)
				&& Objects.equals(vehicleChassiesNo, other.vehicleChassiesNo)
				&& Objects.equals(vehicleEngNo, other.vehicleEngNo) && Objects.equals(vehicleNo, other.vehicleNo);
	}

	@Override
	public String toString() {
		return "ASNMaster [uniqueId=" + uniqueId + ", asnId=" + asnId + ", asnCreationDate=" + asnCreationDate
				+ ", shippingDate=" + shippingDate + ", deliveryNoteNo=" + deliveryNoteNo + ", asnStatus=" + asnStatus
				+ ", poNum=" + poNum + ", containerDetails=" + containerDetails + ", containerCount=" + containerCount
				+ ", containerId=" + containerId + ", shippedQty=" + shippedQty + ", estimatedDelDate="
				+ estimatedDelDate + ", ewayNo=" + ewayNo + ", awbNo=" + awbNo + ", driverName=" + driverName
				+ ", vehicleNo=" + vehicleNo + ", modeOfTransport=" + modeOfTransport + ", transportCompName="
				+ transportCompName + ", consignmentWeight=" + consignmentWeight + ", driverLicenseNo="
				+ driverLicenseNo + ", shippingAddr=" + shippingAddr + ", delAddr=" + delAddr + ", consignmentCost="
				+ consignmentCost + ", permitLevel=" + permitLevel + ", vehicleEngNo=" + vehicleEngNo
				+ ", vehicleChassiesNo=" + vehicleChassiesNo + ", retailerStore=" + retailerStore + ", supplierSite="
				+ supplierSite + ", preferredDelDate=" + preferredDelDate + ", preferredDelTime=" + preferredDelTime
				+ "]";
	}

}
