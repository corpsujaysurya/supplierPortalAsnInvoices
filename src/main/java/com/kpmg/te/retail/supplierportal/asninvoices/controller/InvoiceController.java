package com.kpmg.te.retail.supplierportal.asninvoices.controller;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.kpmg.te.retail.supplierportal.asninvoices.dao.InvoiceDao;
import com.kpmg.te.retail.supplierportal.asninvoices.entity.InvoiceMaster;
import com.kpmg.te.retail.supplierportal.asninvoices.entity.ItemMaster;
import com.kpmg.te.retail.supplierportal.asninvoices.entity.PurchaseOrderMaster;
import com.kpmg.te.retail.supplierportal.asninvoices.manager.InvoiceManager;
import com.kpmg.te.retail.supplierportal.asninvoices.utils.ASNInvoiceUtils;

@Component
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
		InvoiceMaster invMaster = invoiceDao.getInvoiceDetails(invoiceId);
		return invMaster;

	}

	public void sendPaymentReminder(String[] invoiceIdList) throws ClassNotFoundException, SQLException {
		for (String invoice : invoiceIdList) {
			String customerId = invoiceDao.getInvoiceDetails(invoice).getCustomerId();
			invoiceManager.sendPaymentReminders(invoiceDao.getInvoiceDetails(invoice), customerId);
			invoiceDao.updatePaymentReminders(invoice, customerId, asnInvoiceUtils.getDateTime());
		}
	}

	public ArrayList<PurchaseOrderMaster> getPoItems(String[] poIdList) throws ClassNotFoundException, SQLException {
		return invoiceDao.getPoItems(poIdList);
	}

	public String createNewInvoice(InvoiceMaster invoiceMaster) throws ClassNotFoundException, ParseException {
		return invoiceDao.createNewInvoice(invoiceMaster);

	}

	public ArrayList<String> getStoreDetailsData() throws ClassNotFoundException, SQLException {
		return invoiceDao.getStoreDetails();
	}

	public ArrayList<ItemMaster> getItemCost(String[] itemIdList) throws ClassNotFoundException, SQLException {
		return invoiceDao.getItemCost(itemIdList);
	}

}
