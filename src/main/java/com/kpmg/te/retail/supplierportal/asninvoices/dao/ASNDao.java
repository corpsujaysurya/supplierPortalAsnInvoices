package com.kpmg.te.retail.supplierportal.asninvoices.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.kpmg.te.retail.supplierportal.asninvoices.constants.ASNInvoiceConstants;
import com.kpmg.te.retail.supplierportal.asninvoices.entity.ASNItemDetails;
import com.kpmg.te.retail.supplierportal.asninvoices.entity.ASNMaster;
import com.kpmg.te.retail.supplierportal.asninvoices.entity.ASNStores;
import com.kpmg.te.retail.supplierportal.asninvoices.entity.ASNSupplierSites;
import com.kpmg.te.retail.supplierportal.asninvoices.entity.PurchaseOrderMaster;
import com.kpmg.te.retail.supplierportal.asninvoices.utils.ASNInvoiceUtils;

@Component
public class ASNDao {

	private static final Logger logger = Logger.getLogger(ASNDao.class.getName());

	@Autowired
	ASNInvoiceUtils asnInvoiceUtils;

	public Connection getConnectioDetails() throws ClassNotFoundException, SQLException {
		String myDriver = ASNInvoiceConstants.myDriver;
		String myUrl = ASNInvoiceConstants.myUrl;
		Class.forName(myDriver);
		Connection conn = DriverManager.getConnection(myUrl, ASNInvoiceConstants.mySQL_ID,
				ASNInvoiceConstants.mySQL_pass);
		return conn;
	}

	public void closeConnection(Connection conn) throws SQLException {
		conn.close();
	}

	public ArrayList<ASNMaster> getAsnListingData() throws ClassNotFoundException, SQLException {
		ASNMaster asnMasterObj;
		ArrayList<ASNMaster> asnMasterList = new ArrayList<ASNMaster>();
		Connection conn = getConnectioDetails();
		String query = "SELECT  ASN_ID,ASN_CREATION_DATE,SHIPPING_DATE,DC_NO,ASN_STATUS from SUPPLIER_PORTAL.ASN_MASTER ORDER BY ASN_CREATION_DATE DESC LIMIT 20";
		Statement st = conn.createStatement();
		ResultSet rs = st.executeQuery(query);
		while (rs.next()) {
			asnMasterObj = new ASNMaster();
			asnMasterObj.setAsnId(rs.getString("ASN_ID"));
			asnMasterObj.setAsnCreationDate(rs.getString("ASN_CREATION_DATE"));
			asnMasterObj.setShippingDate(rs.getString("SHIPPING_DATE"));
			asnMasterObj.setDeliveryNoteNo(rs.getString("DC_NO"));
			asnMasterObj.setAsnStatus(rs.getString("ASN_STATUS"));
			asnMasterList.add(asnMasterObj);
		}
		logger.info("[C]ASNDao::[M]getAsnListingData -> The ASN list is: " + asnMasterList.toString());
		return asnMasterList;
	}

	public ASNMaster getAsnDetailsData(String asnId) throws SQLException, ClassNotFoundException {
		ASNMaster asnMasterObj = null;
		Connection conn = getConnectioDetails();
		String query = "SELECT  * from SUPPLIER_PORTAL.ASN_MASTER WHERE ASN_ID = '" + asnId + "'";
		Statement st = conn.createStatement();
		ResultSet rs = st.executeQuery(query);
		while (rs.next()) {
			asnMasterObj = new ASNMaster();
			asnMasterObj.setUniqueId(rs.getString("UNIQUE_ID"));
			asnMasterObj.setAsnId(rs.getString("ASN_ID"));
			asnMasterObj.setAsnStatus(rs.getString("ASN_STATUS"));
			asnMasterObj.setAsnCreationDate(rs.getString("ASN_CREATION_DATE"));
			asnMasterObj.setShippingDate(rs.getString("SHIPPING_DATE"));
			asnMasterObj.setEstimatedDelDate(rs.getString("ETA"));
			asnMasterObj.setContainerCount(rs.getString("CONTAINER_COUNT"));
			asnMasterObj.setContainerDetails(rs.getString("CONTAINER_DETAILS"));
			asnMasterObj.setContainerId(rs.getString("CONTAINER_ID"));
			asnMasterObj.setShippedQty(rs.getString("SHIPPED_QTY"));
			asnMasterObj.setPoNum(rs.getString("PO_DETAILS"));
			asnMasterObj.setConsignmentCost(rs.getString("CONSIGNMENT_COST"));
			asnMasterObj.setDeliveryNoteNo(rs.getString("DC_NO"));
			asnMasterObj.setEwayNo(rs.getString("EWAY_NO"));
			asnMasterObj.setAwbNo(rs.getString("AWB_NO"));
			asnMasterObj.setDriverName(rs.getString("DRIVER_NAME"));
			asnMasterObj.setVehicleNo(rs.getString("VEHICLE_NO"));
			asnMasterObj.setModeOfTransport(rs.getString("TRANSPORT_MODE"));
			asnMasterObj.setTransportCompName(rs.getString("TRANSPORT_COMPANY_NAME"));
			asnMasterObj.setConsignmentWeight(rs.getString("GROSS_CONTANER_WEIGHT"));
			asnMasterObj.setDriverLicenseNo(rs.getString("DL_NO"));
			asnMasterObj.setPermitLevel(rs.getString("PERMIT_LEVEL"));
			asnMasterObj.setVehicleEngNo(rs.getString("ENGINE_NO"));
			asnMasterObj.setVehicleChassiesNo(rs.getString("CHASSIS_NO"));
			asnMasterObj.setShippingAddr(rs.getString("SUPPLIER_SITE"));
			asnMasterObj.setDelAddr(rs.getString("RETAILER_STORE"));
		}
		logger.info("[C]ASNDao::[M]getAsnDetailsData -> The ASN Details are: " + asnMasterObj.toString());
		return asnMasterObj;
	}

	public ArrayList<ASNStores> getAsnStoresData() throws SQLException, ClassNotFoundException {
		ASNStores asnStores;
		ArrayList<ASNStores> asnStoresList = new ArrayList<ASNStores>();
		Connection conn = getConnectioDetails();
		String query = "SELECT  * from SUPPLIER_PORTAL.STORE_DETAILS WHERE STORE_STATUS = 'ACTIVE'";
		Statement st = conn.createStatement();
		ResultSet rs = st.executeQuery(query);
		while (rs.next()) {
			asnStores = new ASNStores();
			asnStores.setStoreName(rs.getString("STORE_NAME"));
			asnStores.setStoreAddr(rs.getString("STORE_ADDR"));
			asnStores.setStoreStatus(rs.getString("STORE_STATUS"));
			asnStores.setStoreId(rs.getString("STORE_ID"));
			
			asnStoresList.add(asnStores);
		}
		logger.info("[C]ASNDao::[M]getAsnStoresData -> The ASN Stores list is: " + asnStoresList.toString());
		return asnStoresList;
	}

	public ArrayList<ASNSupplierSites> getAsnSupplierSites() throws SQLException, ClassNotFoundException {
		ASNSupplierSites asnSupplierSites;
		ArrayList<ASNSupplierSites> asnSupplierSitesList = new ArrayList<ASNSupplierSites>();
		Connection conn = getConnectioDetails();
		String query = "SELECT  * from SUPPLIER_PORTAL.SUPPLIER_PORTAL_SITES";
		Statement st = conn.createStatement();
		ResultSet rs = st.executeQuery(query);
		while (rs.next()) {
			asnSupplierSites = new ASNSupplierSites();
			asnSupplierSites.setSiteId(rs.getString("site_id"));
			asnSupplierSites.setSiteName(rs.getString("site_name"));
			asnSupplierSites.setSiteAddr(rs.getString("site_address"));
			asnSupplierSites.setSiteLocation(rs.getString("site_location"));
			asnSupplierSites.setSiteContact(rs.getString("site_contact"));
			asnSupplierSitesList.add(asnSupplierSites);
		}
		logger.info("[C]ASNDao::[M]getAsnSupplierSites -> The ASN Supplier Site list is: "
				+ asnSupplierSitesList.toString());
		return asnSupplierSitesList;
	}


	public ArrayList<ASNItemDetails> getPOitems(String[] poIdList) throws SQLException, ClassNotFoundException {
		ArrayList<PurchaseOrderMaster> poItemsList = new ArrayList<PurchaseOrderMaster>();
		PurchaseOrderMaster poMaster = null;
		Connection conn = getConnectioDetails();
		ArrayList<ASNItemDetails> asnItemDetailsList = new ArrayList<ASNItemDetails>();
		for (String po : poIdList) {
			String query = "SELECT  * from SUPPLIER_PORTAL.PURCHASE_ORDER_MASTER WHERE PO_NUMBER = '" + po + "'";
			logger.info(query);
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery(query);
			while (rs.next()) {
				// logger.info("The result set data is: " + rs.toString());
				poMaster = new PurchaseOrderMaster();
				poMaster.setPoNumber(po);
				poMaster.setItemDetails(rs.getString("ITEM_DETAILS"));
				// logger.info(poMaster.toString());
				asnInvoiceUtils.jsonASNManipulate(poMaster.getItemDetails(), po, asnItemDetailsList);
				poItemsList.add(poMaster);
				logger.info("The poItemsList is: " + poItemsList.toString());
			}
		}
		return asnItemDetailsList;
	}
	
	public String generateEway() {
		return asnInvoiceUtils.generateEway();
	}

	public String generateAWB() {
		return asnInvoiceUtils.generateAWB();
	}

	public String generateDC() {
		return asnInvoiceUtils.generateDC();
	}

	public String createASN(String asnId, ASNMaster asnMaster, String asnStatus) throws SQLException {
		Connection conn = null;
		String status = new String();
		try {
			conn = getConnectioDetails();
			PreparedStatement pstmt = conn.prepareStatement(
					"INSERT INTO SUPPLIER_PORTAL.SUPPLIER_ASN_MASTER (UNIQUE_ID,ASN_ID,ASN_CREATION_DATE,SHIPPING_DATE,DC_NO,ASN_STATUS,CONTAINER_COUNT,"
					+ "CONTAINER_ID,SHIPPED_QTY,ETA,EWAY_NO,AWB_NO,DRIVER_NAME,VEHICLE_NO,ENGINE_NO,CHASSIS_NO,DL_NO,"
					+ "PERMIT_LEVEL,TRANSPORT_MODE,TRANSPORT_COMPANY_NAME,GROSS_CONTANER_WEIGHT,CONTAINER_DETAILS,RETAILER_STORE,SUPPLIER_SITE,PO_DETAILS,CONSIGNMENT_COST"
							+ " VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
			pstmt.setString(1,asnInvoiceUtils.setRandomUUID());
			pstmt.setString(2, asnId);
			//pstmt.setString(2, asnInvoiceUtils.generateASNId());
			pstmt.setString(3, asnInvoiceUtils.generateCurrentDate());
			pstmt.setString(4, asnMaster.getShippingDate());
			
			pstmt.setString(5, asnMaster.getDeliveryNoteNo());
			pstmt.setString(6, asnStatus);
			pstmt.setString(7, asnMaster.getContainerCount());
			pstmt.setString(8, asnMaster.getContainerId());
			pstmt.setString(9, asnMaster.getShippedQty());
			
			pstmt.setString(10, asnMaster.getEstimatedDelDate());
			pstmt.setString(11, asnMaster.getEwayNo());
			pstmt.setString(12, asnMaster.getAwbNo());
			
			pstmt.setString(13, asnMaster.getDriverName());
			pstmt.setString(14, asnMaster.getVehicleNo());
			pstmt.setString(15, asnMaster.getVehicleEngNo());
			pstmt.setString(16, asnMaster.getVehicleChassiesNo());
			pstmt.setString(17, asnMaster.getDriverLicenseNo());
			pstmt.setString(18, asnMaster.getPermitLevel());
			pstmt.setString(19, asnMaster.getModeOfTransport());
			pstmt.setString(20, asnMaster.getTransportCompName());
			pstmt.setString(21, asnMaster.getConsignmentWeight());
			pstmt.setString(22, asnMaster.getContainerDetails());
			
			pstmt.setString(23, asnMaster.getRetailerStore());
			pstmt.setString(24, asnMaster.getSupplierSite());
			pstmt.setString(25, asnMaster.getPoNum());
			pstmt.setString(26, asnMaster.getConsignmentCost());

			int updateStatusCode = pstmt.executeUpdate();
			status = (updateStatusCode == 1 ? ("Record inserted in DB Successfully") : ("Onboarding failed"));
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeConnection(conn);
		}
		return status;
	}

	public String updateASN(String asnId, ASNMaster asnMaster, String asnStatus) throws SQLException, ClassNotFoundException {
		Connection conn = getConnectioDetails();
		String updateStatus = "Invalid";
		try {
			conn = getConnectioDetails();
			String query = "UPDATE SUPPLIER_PORTAL.SUPPLIER_ASN_MASTER SET ASN_CREATION_DATE = ? ,SHIPPING_DATE = ? ,DC_NO = ? ,"
					     + "ASN_STATUS = ? ,CONTAINER_COUNT = ? ,CONTAINER_ID = ? ,SHIPPED_QTY = ? ,ETA = ? ,EWAY_NO = ? ,AWB_NO = ? ,DRIVER_NAME = ? ,VEHICLE_NO = ? ,"
					     + "ENGINE_NO = ? ,CHASSIS_NO = ? ,DL_NO = ? ,PERMIT_LEVEL = ? ,TRANSPORT_MODE = ? ,TRANSPORT_COMPANY_NAME = ? ,GROSS_CONTANER_WEIGHT = ? ,"
					     + "CONTAINER_DETAILS = ? ,RETAILER_STORE = ? ,SUPPLIER_SITE = ? ,PO_DETAILS = ?  WHERE ASN_ID = ?  ";
			logger.info(query);
			PreparedStatement pstmt = conn.prepareStatement(query);
			pstmt.setString(1, asnMaster.getAsnCreationDate());
			pstmt.setString(2, asnMaster.getShippingDate());
			pstmt.setString(3, asnMaster.getDeliveryNoteNo());
			pstmt.setString(4, asnStatus);
			pstmt.setString(5, asnMaster.getContainerCount());
			pstmt.setString(6, asnMaster.getContainerId());
			pstmt.setString(7, asnMaster.getShippedQty());
			pstmt.setString(8, asnMaster.getEstimatedDelDate());
			pstmt.setString(9, asnMaster.getEwayNo());
			pstmt.setString(10, asnMaster.getAwbNo());
			pstmt.setString(11, asnMaster.getDriverName());
			pstmt.setString(12, asnMaster.getVehicleNo());
			pstmt.setString(13, asnMaster.getVehicleEngNo());
			pstmt.setString(14, asnMaster.getVehicleChassiesNo());
			pstmt.setString(15, asnMaster.getDriverLicenseNo());
			pstmt.setString(16, asnMaster.getPermitLevel());
			pstmt.setString(17, asnMaster.getModeOfTransport());
			pstmt.setString(18, asnMaster.getTransportCompName());
			pstmt.setString(19, asnMaster.getConsignmentWeight());
			pstmt.setString(20, asnMaster.getContainerDetails());
			pstmt.setString(21, asnMaster.getRetailerStore());
			pstmt.setString(22, asnMaster.getSupplierSite());
			pstmt.setString(23, asnMaster.getPoNum());
			pstmt.setString(24, asnId);
			int updateStatusCode = pstmt.executeUpdate();
			logger.info(Integer.toString(updateStatusCode));
			updateStatus = (updateStatusCode == 1) ? ("SUCCESS") : ("FAILURE");
			pstmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeConnection(conn);
		}
		return updateStatus;
	}

	public Boolean checkIfASNidExists(String asnId) throws ClassNotFoundException, SQLException {
		boolean status=false;
		Connection conn = getConnectioDetails();
		String query = "SELECT  ASN_ID FROM SUPPLIER_PORTAL.ASN_MASTER WHERE ASN_ID = ?";
		logger.info(query);
		PreparedStatement pstmt = conn.prepareStatement(query);
		pstmt.setString(1, asnId);
		ResultSet rs = pstmt.executeQuery(query);
		while (rs.next()) {
			String id = rs.getString("ASN_ID");
			if(id!=null && id.trim()!="") {
				status=true;
			}
		}
		return status;
	}

}
