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

import com.kpmg.te.retail.supplierportal.asninvoices.constants.ASNInvoiceConstants;
import com.kpmg.te.retail.supplierportal.asninvoices.entity.InvoiceMaster;
import com.kpmg.te.retail.supplierportal.asninvoices.entity.InvoicedItems;
import com.kpmg.te.retail.supplierportal.asninvoices.entity.POItems;
import com.kpmg.te.retail.supplierportal.asninvoices.utils.ASNInvoiceUtils;

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

	public ArrayList<InvoiceMaster> getAllInvData() throws SQLException, ClassNotFoundException {
		InvoiceMaster inVoiceMasterObj;
		ArrayList<InvoiceMaster> invoiceMasterList = new ArrayList<InvoiceMaster>();
		Connection conn = getConnectioDetails();
		String query = "SELECT  * from SUPPLIER_PORTAL.INVOICEMASTER ORDER BY CREATED_DATETIME DESC LIMIT 20";
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
			invoiceMasterList.add(inVoiceMasterObj);
		}
		logger.info("[C]InvoiceDao::[M]getAllInvData -> The Invoice list is: " + invoiceMasterList.toString());
		return invoiceMasterList;
	}

	public InvoiceMaster getInvoiceDetails(String invoiceId) throws ClassNotFoundException, SQLException {
		InvoiceMaster inVoiceMasterObj = null;
		Connection conn = getConnectioDetails();
		String query = "SELECT  * from SUPPLIER_PORTAL.INVOICEMASTER WHERE INVOICE_ID = '" + invoiceId + "'";
		Statement st = conn.createStatement();
		ResultSet rs = st.executeQuery(query);
		while (rs.next()) {
			inVoiceMasterObj = new InvoiceMaster();
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
			inVoiceMasterObj.setInvoiceStatus(rs.getString("INVOICE_STATUS"));
			inVoiceMasterObj.setInvoiceOnHoldFlag(rs.getString("INVOICE_ON_HOLD_FLAG"));
			inVoiceMasterObj.setInvoiceDueDate(rs.getString("INVOICE_DUE_DATE"));
		}
		logger.info("[C]InvoiceDao::[M]getInvoiceDetails -> The Invoice details is: " + inVoiceMasterObj.toString());
		return inVoiceMasterObj;
	}

	public ArrayList<InvoicedItems> getAllInvoicedItemsFromDB(String string)
			throws ClassNotFoundException, SQLException {
		ArrayList<InvoicedItems> invoicedItemsList = new ArrayList<InvoicedItems>();
		InvoicedItems inVoicedItem = null;
		Connection conn = getConnectioDetails();
		String[] polist = string.split(",");
		for (String po : polist) {
			String query = "SELECT  * from SUPPLIER_PORTAL.INVOICED_ITEMS WHERE PO_NUMBER = '" + po + "'";
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery(query);
			while (rs.next()) {
				inVoicedItem = new InvoicedItems();
				inVoicedItem.setItemId(rs.getString("ITEM_ID"));
				inVoicedItem.setItemName(rs.getString("ITEM_NAME"));
				inVoicedItem.setPoNum(rs.getString("PO_NUMBER"));
				inVoicedItem.setInvoicedQuantity(rs.getInt("INVOICED_QTY"));
				inVoicedItem.setMrp(rs.getString("ITEM_MRP"));
				inVoicedItem.setInvoicedPrice(rs.getString("INVOICED_PRICE"));
				inVoicedItem.setTotalPrice(rs.getString("TOTAL_PRICE"));
				invoicedItemsList.add(inVoicedItem);
			}
		}
		return invoicedItemsList;
	}


	public String updatePaymentReminders(String invoiceId, String customerId, String dateTime) throws ClassNotFoundException {
		PreparedStatement preparedStatement;
		try {
			Connection conn = getConnectioDetails();
			conn.setAutoCommit(true);
			String insertQuery = "INSERT INTO SUPPLIER_PORTAL.INVOICE_REMINDER(INVOICE_ID,CUSTOMER_ID,REMINDER_DATE)"
					+ " VALUES" + "(?, ?)";
			preparedStatement = conn.prepareStatement(insertQuery);
			preparedStatement.setString(1, invoiceId);
			preparedStatement.setString(2, customerId);
			preparedStatement.setString(3, dateTime);
			preparedStatement.addBatch();
			preparedStatement.close();
			conn.close();
			return " Record Inserterd Successfully";
		} catch (SQLException ex) {
			System.err.println("SQLException information");
			while (ex != null) {
				System.err.println("Error msg: " + ex.getMessage());
				ex = ex.getNextException();
			}
			throw new RuntimeException("Error");

		}
	}

	public ArrayList<POItems> getPoItems(String[] poIdList) {
		ArrayList<InvoicedItems> invoicedItemsList = new ArrayList<InvoicedItems>();
		InvoicedItems inVoicedItem = null;
		Connection conn = getConnectioDetails();
		for (String po : poIdList) {
			String query = "SELECT  * from SUPPLIER_PORTAL.POITEMS WHERE PO_NUMBER = '" + po + "'";
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery(query);
			while (rs.next()) {
				inVoicedItem = new InvoicedItems();
				inVoicedItem.setItemId(rs.getString("ITEM_ID"));
				inVoicedItem.setItemName(rs.getString("ITEM_NAME"));
				inVoicedItem.setPoNum(rs.getString("PO_NUMBER"));
				inVoicedItem.setInvoicedQuantity(rs.getInt("INVOICED_QTY"));
				inVoicedItem.setMrp(rs.getString("ITEM_MRP"));
				inVoicedItem.setInvoicedPrice(rs.getString("INVOICED_PRICE"));
				inVoicedItem.setTotalPrice(rs.getString("TOTAL_PRICE"));
				invoicedItemsList.add(inVoicedItem);
			}
		}
		return invoicedItemsList;

	}
}