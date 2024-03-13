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
		String query = "SELECT  ASNID,ASNCREATIONDATE,SHIPPINGDATE,DELIVERYNOTENO,ASNSTATUS from SUPPLIER_PORTAL.SUPPLIER_ASN_MASTER ORDER BY ASNCREATIONDATE DESC LIMIT 20";
		Statement st = conn.createStatement();
		ResultSet rs = st.executeQuery(query);
		while (rs.next()) {
			asnMasterObj = new ASNMaster();
			asnMasterObj.setAsnId(rs.getString("ASNID"));
			asnMasterObj.setAsnCreationDate(rs.getString("ASNCREATIONDATE"));
			asnMasterObj.setShippingDate(rs.getString("SHIPPINGDATE"));
			asnMasterObj.setDeliveryNoteNo(rs.getString("DELIVERYNOTENO"));
			asnMasterObj.setAsnStatus(rs.getString("ASNSTATUS"));
			asnMasterList.add(asnMasterObj);
		}
		logger.info("[C]ASNDao::[M]getAsnListingData -> The ASN list is: " + asnMasterList.toString());
		return asnMasterList;
	}

	public ASNMaster getAsnDetailsData(String asnId) throws SQLException, ClassNotFoundException {
		ASNMaster asnMasterObj = null;
		Connection conn = getConnectioDetails();
		String query = "SELECT  * from SUPPLIER_PORTAL.SUPPLIER_ASN_MASTER WHERE ASNID = '" + asnId + "'";
		Statement st = conn.createStatement();
		ResultSet rs = st.executeQuery(query);
		while (rs.next()) {
			asnMasterObj = new ASNMaster();
			asnMasterObj.setAsnId(rs.getString("ASNID"));
			asnMasterObj.setAsnCreationDate(rs.getString("ASNCREATIONDATE"));
			asnMasterObj.setShippingDate(rs.getString("SHIPPINGDATE"));
			asnMasterObj.setDeliveryNoteNo(rs.getString("DELIVERYNOTENO"));
			asnMasterObj.setAsnStatus(rs.getString("ASNSTATUS"));
			asnMasterObj.setPoNum(rs.getString("PONUM"));
			asnMasterObj.setContainerDetails(rs.getString("CONTAINERDETAILS"));
			asnMasterObj.setShippedQty(rs.getString("SHIPPEDQTY"));
			asnMasterObj.setEstimatedDelDate(rs.getString("ESTIMATEDDELDATE"));
			asnMasterObj.setEwayNo(rs.getString("EWAYNO"));
			asnMasterObj.setAwbNo(rs.getString("AWBNO"));
			asnMasterObj.setDriverName(rs.getString("DRIVERNAME"));
			asnMasterObj.setVehicleNo(rs.getString("VEHICLENO"));
			asnMasterObj.setModeOfTransport(rs.getString("MODEOFTRANSPORT"));
			asnMasterObj.setTransportCompName(rs.getString("TRANSPORTCOMPNAME"));
			asnMasterObj.setConsignmentWeight(rs.getString("CONSIGNMENTWEIGHT"));
			asnMasterObj.setDriverLicenseNo(rs.getString("DRIVERLICENSENO"));
			asnMasterObj.setShippingAddr(rs.getString("SHIPPINGADDR"));
			asnMasterObj.setDelAddr(rs.getString("DELADDR"));
			asnMasterObj.setConsignmentCost(rs.getString("CONSIGNMENTCOST"));
			asnMasterObj.setPermitLevel(rs.getString("PERMITLEVEL"));
			asnMasterObj.setVehicleEngNo(rs.getString("VEHICLEENGNO"));
			asnMasterObj.setVehicleChassiesNo(rs.getString("VEHICLECHASSIESNO"));
			asnMasterObj.setEwayBillNo(rs.getString("EWAYBILLNO"));
			asnMasterObj.setEwayBillDate(rs.getString("EWAYBILLDATE"));
			asnMasterObj.setPreferredDelDate(rs.getString("PREFERREDDELDATE"));
			asnMasterObj.setPreferredDelTime(rs.getString("PREFERREDDELTIME"));
		}
		logger.info("[C]ASNDao::[M]getAsnDetailsData -> The ASN Details are: " + asnMasterObj.toString());
		return asnMasterObj;
	}

	public ArrayList<ASNStores> getAsnStoresData() throws SQLException, ClassNotFoundException {
		ASNStores asnStores;
		ArrayList<ASNStores> asnStoresList = new ArrayList<ASNStores>();
		Connection conn = getConnectioDetails();
		String query = "SELECT  * from SUPPLIER_PORTAL.SUPPLIER_ASN_STORES";
		Statement st = conn.createStatement();
		ResultSet rs = st.executeQuery(query);
		while (rs.next()) {
			asnStores = new ASNStores();
			asnStores.setStoreName(rs.getString("STORENAME"));
			asnStores.setStoreAddr(rs.getString("STOREADDR"));
			asnStores.setStoreStatus(rs.getString("STORESTATUS"));
			asnStoresList.add(asnStores);
		}
		logger.info("[C]ASNDao::[M]getAsnStoresData -> The ASN Stores list is: " + asnStoresList.toString());
		return asnStoresList;
	}

	public ArrayList<ASNSupplierSites> getAsnSupplierSites() throws SQLException, ClassNotFoundException {
		ASNSupplierSites asnSupplierSites;
		ArrayList<ASNSupplierSites> asnSupplierSitesList = new ArrayList<ASNSupplierSites>();
		Connection conn = getConnectioDetails();
		String query = "SELECT  * from SUPPLIER_PORTAL.SUPPLIER_ASN_SUPPLIER_SITES";
		Statement st = conn.createStatement();
		ResultSet rs = st.executeQuery(query);
		while (rs.next()) {
			asnSupplierSites = new ASNSupplierSites();
			asnSupplierSites.setSiteName(rs.getString("SITENAME"));
			asnSupplierSites.setSiteAddr(rs.getString("SITEADDR"));
			asnSupplierSites.setSiteStatus(rs.getString("SITESTATUS"));
			asnSupplierSitesList.add(asnSupplierSites);
		}
		logger.info("[C]ASNDao::[M]getAsnSupplierSites -> The ASN Supplier Site list is: "
				+ asnSupplierSitesList.toString());
		return asnSupplierSitesList;
	}

	public String saveASNdata(ASNMaster asnMaster) throws SQLException, ClassNotFoundException {
		Connection conn = null;
		String status = new String();
		try {
			conn = getConnectioDetails();
			PreparedStatement pstmt = conn.prepareStatement(
					"INSERT INTO SUPPLIER_PORTAL.SUPPLIER_ASN_MASTER (ASNID,ASNCREATIONDATE,SHIPPINGDATE,DELIVERYNOTENO,ASNSTATUS,PONUM,CONTAINERDETAILS,SHIPPEDQTY,"
							+ "ESTIMATEDDELDATE,EWAYNO,AWBNO,DRIVERNAME,VEHICLENO,MODEOFTRANSPORT,TRANSPORTCOMPNAME,CONSIGNMENTWEIGHT,DRIVERLICENSENO,"
							+ "SHIPPINGADDR,DELADDR,CONSIGNMENTCOST,PERMITLEVEL,VEHICLEENGNO,VEHICLECHASSIESNO,EWAYBILLNO,EWAYBILLDATE,PREFERREDDELDATE,PREFERREDDELTIME)"
							+ " VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
			pstmt.setString(1, asnMaster.getAsnId());
			pstmt.setString(2, asnMaster.getAsnCreationDate());
			pstmt.setString(3, asnMaster.getShippingDate());
			pstmt.setString(4, asnMaster.getDeliveryNoteNo());
			pstmt.setString(5, asnMaster.getAsnStatus());
			pstmt.setString(6, asnMaster.getPoNum());

			pstmt.setString(7, asnMaster.getContainerDetails());
			pstmt.setString(8, asnMaster.getShippedQty());
			pstmt.setString(9, asnMaster.getEstimatedDelDate());
			pstmt.setString(10, asnMaster.getEwayBillNo());
			pstmt.setString(11, asnMaster.getAwbNo());

			pstmt.setString(12, asnMaster.getDriverName());
			pstmt.setString(13, asnMaster.getVehicleNo());
			pstmt.setString(14, asnMaster.getModeOfTransport());
			pstmt.setString(15, asnMaster.getTransportCompName());
			pstmt.setString(16, asnMaster.getConsignmentWeight());

			pstmt.setString(17, asnMaster.getDriverLicenseNo());
			pstmt.setString(18, asnMaster.getShippingAddr());
			pstmt.setString(19, asnMaster.getDelAddr());
			pstmt.setString(20, asnMaster.getConsignmentCost());
			pstmt.setString(21, asnMaster.getPermitLevel());
			pstmt.setString(22, asnMaster.getVehicleEngNo());

			pstmt.setString(23, asnMaster.getVehicleChassiesNo());
			pstmt.setString(24, asnMaster.getEwayBillNo());
			pstmt.setString(25, asnMaster.getPreferredDelDate());
			pstmt.setString(26, asnMaster.getPreferredDelTime());

			int updateStatusCode = pstmt.executeUpdate();
			status = (updateStatusCode == 1 ? ("Record inserted in DB Successfully") : ("Onboarding failed"));
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeConnection(conn);
		}
		return status;
	}

	public ArrayList<PurchaseOrderMaster> getPOitems(String[] poIdList) throws SQLException, ClassNotFoundException {
		ArrayList<PurchaseOrderMaster> poMasterList = new ArrayList<PurchaseOrderMaster>();
		PurchaseOrderMaster poMaster = null;
		Connection conn = getConnectioDetails();
		for (String po : poIdList) {
			String query = "SELECT  * from SUPPLIER_PORTAL.PURCHASE_ORDER_MASTER WHERE PO_NUMBER = '" + po + "'";
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery(query);
			while (rs.next()) {
				poMaster = new PurchaseOrderMaster();
				poMaster.setPoNumber(rs.getString("ITEM_DETAILS"));
				poMasterList.add(poMaster);
			}
		}
		return poMasterList;
		// **** ITEM DETAILS FIELD SHOULD BE A JSON OBJECT ***************//
	}

}
