package com.kpmg.te.retail.supplierportal.asninvoices.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kpmg.te.retail.supplierportal.asninvoices.controller.InvoiceController;
import com.kpmg.te.retail.supplierportal.asninvoices.entity.InvoiceMaster;
import com.kpmg.te.retail.supplierportal.asninvoices.entity.POItems;
import com.kpmg.te.retail.supplierportal.asninvoices.manager.InvoiceManager;
import com.kpmg.te.retail.supplierportal.asninvoices.utils.ASNInvoiceUtils;


@RestController
@RequestMapping("/api/invoice/service")
public class InvoiceService {
	
	@Autowired
	InvoiceController invoiceController;
	
	@Autowired
	InvoiceManager invoiceManager;
	
	@Autowired
	ASNInvoiceUtils asnInvoiceUtils;

	private static final Logger logger = Logger.getLogger(InvoiceService.class.getName());
	
	/************************************************************************************************************************************************************************** */
	/*													INVOICE MODULE REST END-POINTS                                                                                    */
	/**************************************************************************************************************************************************************************/
	@RequestMapping(path = "/invoice/getAllInvoiceData", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ArrayList<InvoiceMaster> getAllInvoiceData() throws ClassNotFoundException, SQLException {
		ArrayList<InvoiceMaster> invoiceMasterList = new ArrayList<InvoiceMaster>();
		invoiceMasterList = invoiceController.getAllInvoiceListingData();
		logger.info("[C]ASNInvoiceService::[M]getAllInvoiceData -> The Invoice List to display is: "+invoiceMasterList.toString());
		return  invoiceMasterList;
	}
	
	@RequestMapping(path = "/invoice/getInvoiceDetails", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public InvoiceMaster getInvoiceDetails(@RequestParam(value="invoiceId") String invoiceId) throws ClassNotFoundException, SQLException {
		InvoiceMaster invoiceDetails = invoiceController.getInvoiceDetailsData(invoiceId);
		logger.info("[C]ASNInvoiceService::[M]getInvoiceDetails -> The Invoice Details list to display is: "+invoiceDetails.toString());
		return  invoiceDetails;
	}
	
	/************************************************************************************************************************************************************************** */
	/*													INVOICE MODULE PAYMENT REMINDER REST END-POINTS                                                                        */
	/**************************************************************************************************************************************************************************/
	
	@RequestMapping(path = "/invoice/sendPaymentReminder", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public String sendPaymentReminder(@RequestParam(value="invoiceIdArray[]") String[] invoiceIdList) throws ClassNotFoundException, SQLException {
		String message  = new String();
		invoiceController.sendPaymentReminder(invoiceIdList);
		logger.info("[C]ASNInvoiceService::[M]sendPaymentReminder -> The payment reminder list to display is: "+invoiceIdList.toString());
		return  message;
	}
	
	
	/************************************************************************************************************************************************************************** */
	/*													INVOICE MODULE CREATE INVOICE REST END-POINTS                                                                          */
	/**************************************************************************************************************************************************************************/
	
	@RequestMapping(path = "/invoice/createInvoice", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public String sendPaymentReminder(@RequestBody List<InvoiceMaster> invoiceMaster) throws ClassNotFoundException, SQLException {
		String message  = new String();
		invoiceController.createNewInvoice(invoiceMaster);
		logger.info("[C]ASNInvoiceService::[M]sendPaymentReminder -> The new invoice details to display is: "+invoiceMaster.toString());
		return  message;
	}
	
	@RequestMapping(path = "/invoice/getPoItems", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ArrayList<POItems> getPoItems(@RequestParam(value="poIdArray[]") String[] poIdList) throws ClassNotFoundException, SQLException {
		ArrayList<POItems> poItemsList  = new ArrayList<POItems>();
		invoiceController.getPoItems(poIdList);
		logger.info("[C]ASNInvoiceService::[M]getPoItems -> The payment reminder list to display is: "+poItemsList.toString());
		return  poItemsList;
	}
	
}
