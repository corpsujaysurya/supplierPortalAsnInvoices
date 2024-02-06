package com.kpmg.te.retail.supplierportal.asninvoices.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.kpmg.te.retail.supplierportal.asninvoices.constants.ASNInvoiceConstants;
import com.kpmg.te.retail.supplierportal.asninvoices.entity.ASNMaster;
import com.kpmg.te.retail.supplierportal.asninvoices.entity.ASNStores;
import com.kpmg.te.retail.supplierportal.asninvoices.entity.ASNSupplierSites;
import com.kpmg.te.retail.supplierportal.asninvoices.utils.ASNInvoiceUtils;

@Component
public class ASNDao {

	private static final Logger logger = Logger.getLogger(ASNDao.class.getName());
	
	@Autowired ASNInvoiceUtils asnInvoiceUtils;
	

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
		String query = "SELECT  * from SUPPLIER_PORTAL.SUPPLIER_ASN_MASTER WHERE ASNID = '" + asnId +"'" ;
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
		logger.info("[C]ASNDao::[M]getAsnSupplierSites -> The ASN Supplier Site list is: " + asnSupplierSitesList.toString());
		return asnSupplierSitesList;
	}

	public String saveASNdata(List<ASNMaster> asnMaster) {
		// TODO Auto-generated method stub
		return null;
	}

	public String getPOitems(String[] poIdList) {
		// TODO Auto-generated method stub
		return null;
	}

}
