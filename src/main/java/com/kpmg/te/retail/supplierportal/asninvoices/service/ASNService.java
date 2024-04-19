package com.kpmg.te.retail.supplierportal.asninvoices.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kpmg.te.retail.supplierportal.asninvoices.controller.ASNController;
import com.kpmg.te.retail.supplierportal.asninvoices.entity.ASNMaster;
import com.kpmg.te.retail.supplierportal.asninvoices.entity.ASNStores;
import com.kpmg.te.retail.supplierportal.asninvoices.entity.ASNSupplierSites;
import com.kpmg.te.retail.supplierportal.asninvoices.entity.POItems;
import com.kpmg.te.retail.supplierportal.asninvoices.entity.PurchaseOrderMaster;
import com.kpmg.te.retail.supplierportal.asninvoices.manager.ASNManager;
import com.kpmg.te.retail.supplierportal.asninvoices.utils.ASNInvoiceUtils;

@RestController
@RequestMapping("/api/asn/service")
public class ASNService {
	
	@Autowired
	ASNController asnController;
	
	
	@Autowired
	ASNManager asnManager;
	
	@Autowired
	ASNInvoiceUtils asnInvoiceUtils;

	private static final Logger logger = Logger.getLogger(ASNService.class.getName());
	

	/************************************************************************************************************************************************************************** */
	/*													ASN MODULE REST END-POINTS                                                                                             */
	/**************************************************************************************************************************************************************************/
	@RequestMapping(path = "/getAllASNListingData", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ArrayList<ASNMaster> getAllANSDataListing() throws ClassNotFoundException, SQLException {
		ArrayList<ASNMaster> asnMasterList = new ArrayList<ASNMaster>();
		asnMasterList = asnController.getAllASNListingData();
		logger.info("[C]ASNService::[M]getAllANSDataListing -> The ASN List to display is: "+asnMasterList.toString());
		return  asnMasterList;
	}
	
	@RequestMapping(path = "/getAllASNDetails", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ASNMaster getAllANSDataListing(@RequestParam(value="asnId") String asnId) throws ClassNotFoundException, SQLException {
		ASNMaster asnMaster = new ASNMaster();
		asnMaster = asnController.getAllASNDetailsData(asnId);
		logger.info("[C]ASNService::[M]getAllANSDataListing -> The ASN List to display is: "+asnMaster.toString());
		return  asnMaster;
	}
	
	@RequestMapping(path = "/getStoresListASN", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ArrayList<ASNStores> getStoresListASN() throws ClassNotFoundException, SQLException {
		ArrayList<ASNStores> asnStoresList = new ArrayList<ASNStores>();
		asnStoresList = asnController.getAllASNStoresData();
		logger.info("[C]ASNService::[M]getAllANSDataListing -> The ASN List to display is: "+asnStoresList.toString());
		return  asnStoresList;
	}
	
	@RequestMapping(path = "/getAllSupplierSites", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ArrayList<ASNSupplierSites> getAllSupplierSites() throws ClassNotFoundException, SQLException {
		ArrayList<ASNSupplierSites> asnSupplierSiteList = new ArrayList<ASNSupplierSites>();
		asnSupplierSiteList = asnController.getAllSupplierSiteData();
		logger.info("[C]ASNService::[M]getAllANSDataListing -> The ASN List to display is: "+asnSupplierSiteList.toString());
		return  asnSupplierSiteList;
	}
	
	@RequestMapping(path = "/getPoItems", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ArrayList<PurchaseOrderMaster> getPoItemsForASN(@RequestParam(value="poIdArray[]") String[] poIdList) throws ClassNotFoundException, SQLException {
		ArrayList<PurchaseOrderMaster> poItemsList  = new ArrayList<PurchaseOrderMaster>();
		poItemsList = asnController.getPoItems(poIdList);
		logger.info("[C]ASNService::[M]getPoItems -> The purchase order item list to display is: "+poItemsList.toString());
		return  poItemsList;
	}
	
	@RequestMapping(path = "/saveASN", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ArrayList<POItems> saveASN(@RequestBody ASNMaster asnMaster) throws ClassNotFoundException, SQLException {
		ArrayList<POItems> poItemsList  = new ArrayList<POItems>();
		asnController.saveASN(asnMaster);
		logger.info("[C]ASNService::[M]getPoItems -> The payment reminder list to display is: "+poItemsList.toString());
		return  poItemsList;
	}
	
	@RequestMapping(path = "/getStoresListInvoices", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ArrayList<ASNStores> getStoresListInvoices() throws ClassNotFoundException, SQLException {
		ArrayList<ASNStores> asnStoresList = new ArrayList<ASNStores>();
		asnStoresList = asnController.getAllASNStoresData();
		logger.info("[C]ASNInvoiceService::[M]getAllANSDataListing -> The ASN List to display is: "+asnStoresList.toString());
		return  asnStoresList;
	}
	
	@RequestMapping(path = "/generateEway", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public String generateEway() throws ClassNotFoundException, SQLException {
		String ewayBillList = new String();
		ewayBillList = asnController.generateEway();
		logger.info("[C]CustomerOrderService::[M]getCODetails -> The Customer Order Details list to display is: "+ewayBillList.toString());
		return  ewayBillList;
	}
	
	@RequestMapping(path = "/generateAWB", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public String generateAWB() throws ClassNotFoundException, SQLException {
		String awbBill = new  String();
		awbBill = asnController.generateAWB();
		logger.info("[C]CustomerOrderService::[M]generateAWB -> The Customer Order Details list to display is: "+awbBill.toString());
		return  awbBill;
	}

}
