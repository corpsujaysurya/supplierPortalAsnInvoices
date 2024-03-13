package com.kpmg.te.retail.supplierportal.asninvoices.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.kpmg.te.retail.supplierportal.asninvoices.constants.ASNInvoiceConstants;
import com.kpmg.te.retail.supplierportal.asninvoices.entity.InvoiceMaster;
import com.kpmg.te.retail.supplierportal.asninvoices.entity.ItemMaster;
import com.kpmg.te.retail.supplierportal.asninvoices.entity.PurchaseOrderMaster;
import com.kpmg.te.retail.supplierportal.asninvoices.utils.ASNInvoiceUtils;
@Component
public class InvoiceDao {

	@Autowired
	ASNInvoiceUtils asnInvoicUtils;

	private static final Logger logger = Logger.getLogger(InvoiceDao.class.getName());

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

	/***********************************************************************************************************************************************/
	/* 													Get Invoice Listing Data																  */
	/*********************************************************************************************************************************************/
	
	public ArrayList<InvoiceMaster> getAllInvData() throws SQLException, ClassNotFoundException {
		InvoiceMaster inVoiceMasterObj;
		ArrayList<InvoiceMaster> invoiceMasterList = new ArrayList<InvoiceMaster>();
		Connection conn = getConnectioDetails();
		String query = "SELECT  * from SUPPLIER_PORTAL.INVOICE_MASTER ORDER BY CREATED_DATETIME DESC LIMIT 20";
		Statement st = conn.createStatement();
		ResultSet rs = st.executeQuery(query);
		while (rs.next()) {
			inVoiceMasterObj = new InvoiceMaster();
			inVoiceMasterObj.setInvoiceId(rs.getString("INVOICE_ID"));
			inVoiceMasterObj.setInvoiceDate(rs.getString("INVOICE_DATE"));
			inVoiceMasterObj.setInvoiceType(rs.getString("INVOICE_TYPE"));
			inVoiceMasterObj.setCurrency(rs.getString("CURRENCY"));
			inVoiceMasterObj.setInvoiceTotalAmount(rs.getString("INVOICE_TOTAL_AMOUNT"));
			inVoiceMasterObj.setInvoiceDueAmount(rs.getString("INVOICE_DUE_AMOUNT"));
			inVoiceMasterObj.setInvoiceStatus(rs.getString("INVOICE_STATUS"));
			inVoiceMasterObj.setInvoiceOnHoldFlag(rs.getString("INVOICE_ON_HOLD_FLAG"));
			inVoiceMasterObj.setInvoiceDueDate(rs.getString("INVOICE_DUE_DATE"));
			inVoiceMasterObj.setInvoicePaymentStatus(rs.getString("INVOICE_PAYMENT_STATUS"));
			invoiceMasterList.add(inVoiceMasterObj);
		}
		logger.info("[C]InvoiceDao::[M]getAllInvData -> The Invoice list is: " + invoiceMasterList.toString());
		return invoiceMasterList;
	}

	/***********************************************************************************************************************************************/
	/* 													Get Invoice Details Data																  */
	/*********************************************************************************************************************************************/
	public InvoiceMaster getInvoiceDetails(String invoiceId) throws ClassNotFoundException, SQLException {
		InvoiceMaster inVoiceMasterObj = null;
		Connection conn = getConnectioDetails();
		String query = "SELECT  * from SUPPLIER_PORTAL.INVOICE_MASTER WHERE INVOICE_ID = '" + invoiceId + "'";
		Statement st = conn.createStatement();
		ResultSet rs = st.executeQuery(query);
		while (rs.next()) {
			inVoiceMasterObj = new InvoiceMaster();
			inVoiceMasterObj.setUniqueId(rs.getString("UNIQUE_ID"));
			inVoiceMasterObj.setInvoiceId(rs.getString("INVOICE_ID"));
			inVoiceMasterObj.setInvoiceDate(rs.getString("INVOICE_DATE"));
			inVoiceMasterObj.setInvoiceType(rs.getString("INVOICE_TYPE"));
			inVoiceMasterObj.setInvoiceStatus(rs.getString("INVOICE_STATUS"));
			inVoiceMasterObj.setCurrency(rs.getString("CURRENCY"));

			inVoiceMasterObj.setAttachments(rs.getString("ATTACHMENTS"));
			inVoiceMasterObj.setTotalItemQty(rs.getString("TOTAL_ITEM_QUANTIY"));
			inVoiceMasterObj.setTotalUniqueItems(rs.getString("TOTAL_UNIQUE_ITEMS"));
			inVoiceMasterObj.setDeliveryAddress(rs.getString("DELIVERY_ADDRESS"));
			inVoiceMasterObj.setSenderAddress(rs.getString("SENDER_ADDRESS"));

			inVoiceMasterObj.setInvoiceTotalAmount(rs.getString("INVOICE_TOTAL_AMOUNT"));
			inVoiceMasterObj.setInvoiceDueAmount(rs.getString("INVOICE_DUE_AMOUNT"));
			inVoiceMasterObj.setNettedAmt(rs.getString("NETTED_AMOUNT"));
			inVoiceMasterObj.setInvoicePaymentStatus(rs.getString("INVOICE_PAYMENT_STATUS"));
			inVoiceMasterObj.setInvoiceOnHoldFlag(rs.getString("INVOICE_ON_HOLD_FLAG"));
			inVoiceMasterObj.setInvoiceDueDate(rs.getString("INVOICE_DUE_DATE"));
			inVoiceMasterObj.setPoNumber(rs.getString("PO_NUMBER"));
			inVoiceMasterObj.setCreated_datetime(rs.getString("CREATED_DATETIME"));
			inVoiceMasterObj.setCustomerId(rs.getString("CUSTOMER_ID"));
			//inVoiceMasterObj.setItemDetails(rs.getString("ITEM_DETAILS")); //Contains PO Data as JSON to be parsed by UI
			String invoicedItemsList = rs.getString("INVOICED_ITEM_DETAILS");
			inVoiceMasterObj.setInvoicedItems(invoicedItemsList);
		}
		logger.info("[C]InvoiceDao::[M]getInvoiceDetails -> The Invoice details is: " + inVoiceMasterObj.toString());
		return inVoiceMasterObj;
	}


	public String updatePaymentReminders(String invoiceId, String customerId, String dateTime) throws ClassNotFoundException {
		PreparedStatement preparedStatement;
		try {
			Connection conn = getConnectioDetails();
			conn.setAutoCommit(true);
			String insertQuery = "INSERT INTO SUPPLIER_PORTAL.INVOICE_REMINDER(UNIQUE_ID,INVOICE_ID,CUSTOMER_ID,REMINDER_DATE)"
					+ " VALUES" + "(?, ?, ?, ?)";
			logger.info(insertQuery);
			preparedStatement = conn.prepareStatement(insertQuery);
			preparedStatement.setString(1, asnInvoicUtils.setRandomUUID());
			preparedStatement.setString(2, invoiceId);
			preparedStatement.setString(3, customerId);
			preparedStatement.setString(4, dateTime);
			preparedStatement.addBatch();
			preparedStatement.executeBatch();
			preparedStatement.close();
			conn.close();
			return " Record Inserterd Successfully into Invoices reminder table";
		} catch (SQLException ex) {
			System.err.println("SQLException information");
			while (ex != null) {
				System.err.println("Error msg: " + ex.getMessage());
				ex = ex.getNextException();
			}
			throw new RuntimeException("Error");

		}
	}

	public ArrayList<PurchaseOrderMaster> getPoItems(String[] poIdList) throws SQLException, ClassNotFoundException {
		ArrayList<PurchaseOrderMaster> poItemsList = new ArrayList<PurchaseOrderMaster>();
		PurchaseOrderMaster poMaster = null;
		Connection conn = getConnectioDetails();
		for (String po : poIdList) {
			String query = "SELECT  * from SUPPLIER_PORTAL.PURCHASE_ORDER_MASTER WHERE PO_NUMBER = '" + po + "'";
			logger.info(query);
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery(query);
			while (rs.next()) {
				//logger.info("The result set data is: " +  rs.toString());
				poMaster = new PurchaseOrderMaster();
				poMaster.setPoNumber(po);
				poMaster.setItemDetails(rs.getString("ITEM_DETAILS"));
				//logger.info(poMaster.toString());
				poItemsList.add(poMaster);
				logger.info("The poItemsList is: " + poItemsList.toString());
			}
		}
		return poItemsList;
	}

	public String createNewInvoice(InvoiceMaster invoiceMaster) throws ParseException, ClassNotFoundException {
		PreparedStatement preparedStatement;
		try {
			Connection conn = getConnectioDetails();
			conn.setAutoCommit(true);
			String insertQuery = "INSERT INTO SUPPLIER_PORTAL.INVOICE_MASTER(UNIQUE_ID, INVOICE_ID,INVOICE_DATE,INVOICE_TYPE, CURRENCY,INVOICE_TOTAL_AMOUNT,INVOICE_DUE_AMOUNT,NETTED_AMOUNT,INVOICE_STATUS,INVOICE_ON_HOLD_FLAG,"
					+ "INVOICE_PAYMENT_STATUS,INVOICE_DUE_DATE,TOTAL_ITEM_QUANTIY,TOTAL_UNIQUE_ITEMS,ATTACHMENTS,DELIVERY_ADDRESS,SENDER_ADDRESS,CREATED_DATETIME,PO_NUMBER,CUSTOMER_ID,INVOICED_ITEM_DETAILS)"
					+ " VALUES" + "(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
			preparedStatement = conn.prepareStatement(insertQuery);
			String invoiceId = asnInvoicUtils.generateInvoiceId();
			preparedStatement.setString(1, asnInvoicUtils.setRandomUUID());
			preparedStatement.setString(2,invoiceId);
			preparedStatement.setString(3, asnInvoicUtils.generateCurrentDate());
			preparedStatement.setString(4, "Standard");
			preparedStatement.setString(5, "INR");
			preparedStatement.setString(6, invoiceMaster.getInvoiceTotalAmount());
			preparedStatement.setString(7, invoiceMaster.getInvoiceDueAmount());
			preparedStatement.setString(8, invoiceMaster.getNettedAmt());
			preparedStatement.setString(9, "IN-PROGRESS");
			preparedStatement.setString(10, "N");
			preparedStatement.setString(11, "NOT PAID");
			preparedStatement.setString(12, asnInvoicUtils.getNextSeventhDate(asnInvoicUtils.generateCurrentDate()));
			preparedStatement.setString(13, invoiceMaster.getTotalItemQty());
			preparedStatement.setString(14, invoiceMaster.getTotalUniqueItems());
			preparedStatement.setString(15, "");
			preparedStatement.setString(16, invoiceMaster.getDeliveryAddress());
			preparedStatement.setString(17, invoiceMaster.getSenderAddress());
			preparedStatement.setString(18, asnInvoicUtils.generateCurrentDate());
			preparedStatement.setString(19, invoiceMaster.getPoNumber());
			preparedStatement.setString(20, invoiceMaster.getCustomerId());
			preparedStatement.setString(21, (invoiceMaster.getInvoicedItems()));
			preparedStatement.addBatch();
			preparedStatement.executeBatch();
			preparedStatement.close();
			conn.close();
			return invoiceId;

		} catch (SQLException ex) {
			System.err.println("SQLException information");
			while (ex != null) {
				System.err.println("Error msg: " + ex.getMessage());
				ex = ex.getNextException();
			}
			throw new RuntimeException("Error");
		}
	}

	public ArrayList<String> getStoreDetails() throws SQLException, ClassNotFoundException {
		String storeName;
		String status = "ACTIVE";
		ArrayList<String> storeNameList = new ArrayList<String>();
		Connection conn = getConnectioDetails();
		String query = "SELECT  * from SUPPLIER_PORTAL.STORE_DETAILS  WHERE STORE_STATUS  = '" + status + "'";
		Statement st = conn.createStatement();
		ResultSet rs = st.executeQuery(query);
		while (rs.next()) {
			storeName = new String();
			storeName = rs.getString("STORE_NAME");
			storeNameList.add(storeName);
		}
		logger.info("[C]InvoiceDao::[M]getAllInvData -> The Store Name list is: " + storeNameList.toString());
		return storeNameList;
	}

	public ArrayList<ItemMaster> getItemCost(String[] itemIdList) throws ClassNotFoundException, SQLException {
		ArrayList<ItemMaster> itemMasterList = new ArrayList<ItemMaster>();
		ItemMaster itemMaster = null;
		Connection conn = getConnectioDetails();
		for (String item : itemIdList) {
			String query = "SELECT  * from SUPPLIER_PORTAL.ITEM_LISTING_MASTER WHERE ITEM_ID = '" + item + "'";
			logger.info(query);
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery(query);
			while (rs.next()) {
				itemMaster = new ItemMaster();
				itemMaster.setItemId(item);
				itemMaster.setPrice(rs.getString("PRICE"));
				itemMasterList.add(itemMaster);
				logger.info("The poItemsList is: " + itemMasterList.toString());
			}
		}
		return itemMasterList;
	}
}