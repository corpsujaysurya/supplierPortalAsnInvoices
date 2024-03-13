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
import com.kpmg.te.retail.supplierportal.asninvoices.entity.InvoiceMaster;
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
			inVoiceMasterObj.setItemDetails(rs.getString("ITEM_DETAILS")); //Contains PO Data as JSON to be parsed by UI
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
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery(query);
			while (rs.next()) {
				poMaster = new PurchaseOrderMaster();
				poMaster.setItemDetails(rs.getString("ITEM_DETAILS"));
				poItemsList.add(poMaster);
			}
		}
		return poItemsList;
	}
}