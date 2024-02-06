package com.kpmg.te.retail.supplierportal.asninvoices.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.kpmg.te.retail.supplierportal.asninvoices.dao.InvoiceDao;
import com.kpmg.te.retail.supplierportal.asninvoices.entity.ASNMaster;
import com.kpmg.te.retail.supplierportal.asninvoices.entity.InvoiceMaster;
import com.kpmg.te.retail.supplierportal.asninvoices.entity.InvoicedItems;
import com.kpmg.te.retail.supplierportal.asninvoices.entity.POItems;
import com.kpmg.te.retail.supplierportal.asninvoices.manager.InvoiceManager;
import com.kpmg.te.retail.supplierportal.asninvoices.utils.ASNInvoiceUtils;

public class InvoiceController {

	@Autowired
	InvoiceManager invoiceManager;

	@Autowired
	InvoiceDao invoiceDao;

	@Autowired
	ASNInvoiceUtils asnInvoiceUtils;

	public ArrayList<InvoiceMaster> getAllInvoiceListingData() throws ClassNotFoundException, SQLException {
		return invoiceDao.getAllInvData();
	}

	public InvoiceMaster getInvoiceDetailsData(String invoiceId) throws ClassNotFoundException, SQLException {
		ArrayList<InvoicedItems> invItemsList = new ArrayList<InvoicedItems>();
		InvoiceMaster invMaster = invoiceDao.getInvoiceDetails(invoiceId);
		invItemsList = invoiceDao.getAllInvoicedItemsFromDB(invMaster.getPoNumber());
		invMaster.setInvoicedItems(invItemsList);
		return invMaster;

	}

	public void sendPaymentReminder(String[] invoiceIdList) throws ClassNotFoundException, SQLException {
		for (String invoice : invoiceIdList) {
			String customerId = invoiceDao.getInvoiceDetails(invoice).getCustomerId();
			invoiceManager.sendPaymentReminders(invoiceDao.getInvoiceDetails(invoice), customerId);
			invoiceDao.updatePaymentReminders(invoice, customerId, asnInvoiceUtils.getDateTime());
		}
	}

	public ArrayList<POItems> getPoItems(String[] poIdList) {
		return invoiceDao.getPoItems(poIdList);
	}

	public void createNewInvoice(List<InvoiceMaster> invoiceMaster) {
		// TODO Auto-generated method stub

	}

	public void saveASN(List<ASNMaster> asnMaster) {
		// TODO Auto-generated method stub
		
	}

}
