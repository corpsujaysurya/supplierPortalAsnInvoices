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
	@RequestMapping(path = "/asn/getAllASNListingData", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ArrayList<ASNMaster> getAllANSDataListing() throws ClassNotFoundException, SQLException {
		ArrayList<ASNMaster> asnMasterList = new ArrayList<ASNMaster>();
		asnMasterList = asnController.getAllASNListingData();
		logger.info("[C]ASNService::[M]getAllANSDataListing -> The ASN List to display is: "+asnMasterList.toString());
		return  asnMasterList;
	}
	
	@RequestMapping(path = "/asn/getAllASNDetails", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ASNMaster getAllANSDataListing(@RequestParam(value="asnId") String asnId) throws ClassNotFoundException, SQLException {
		ASNMaster asnMaster = new ASNMaster();
		asnMaster = asnController.getAllASNDetailsData(asnId);
		logger.info("[C]ASNService::[M]getAllANSDataListing -> The ASN List to display is: "+asnMaster.toString());
		return  asnMaster;
	}
	
	@RequestMapping(path = "/asn/getStoresListASN", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ArrayList<ASNStores> getStoresListASN() throws ClassNotFoundException, SQLException {
		ArrayList<ASNStores> asnStoresList = new ArrayList<ASNStores>();
		asnStoresList = asnController.getAllASNStoresData();
		logger.info("[C]ASNService::[M]getAllANSDataListing -> The ASN List to display is: "+asnStoresList.toString());
		return  asnStoresList;
	}
	
	@RequestMapping(path = "/asn/getAllSupplierSites", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ArrayList<ASNSupplierSites> getAllSupplierSites() throws ClassNotFoundException, SQLException {
		ArrayList<ASNSupplierSites> asnSupplierSiteList = new ArrayList<ASNSupplierSites>();
		asnSupplierSiteList = asnController.getAllSupplierSiteData();
		logger.info("[C]ASNService::[M]getAllANSDataListing -> The ASN List to display is: "+asnSupplierSiteList.toString());
		return  asnSupplierSiteList;
	}
	
	@RequestMapping(path = "/asn/getPoItems", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ArrayList<POItems> getPoItemsForASN(@RequestParam(value="poIdArray[]") String[] poIdList) throws ClassNotFoundException, SQLException {
		ArrayList<POItems> poItemsList  = new ArrayList<POItems>();
		asnController.getPoItems(poIdList);
		logger.info("[C]ASNService::[M]getPoItems -> The payment reminder list to display is: "+poItemsList.toString());
		return  poItemsList;
	}
	
	@RequestMapping(path = "/asn/saveASN", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ArrayList<POItems> saveASN(@RequestBody ASNMaster asnMaster) throws ClassNotFoundException, SQLException {
		ArrayList<POItems> poItemsList  = new ArrayList<POItems>();
		asnController.saveASN(asnMaster);
		logger.info("[C]ASNService::[M]getPoItems -> The payment reminder list to display is: "+poItemsList.toString());
		return  poItemsList;
	}
	
	@RequestMapping(path = "/asn/getStoresListInvoices", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ArrayList<ASNStores> getStoresListInvoices() throws ClassNotFoundException, SQLException {
		ArrayList<ASNStores> asnStoresList = new ArrayList<ASNStores>();
		asnStoresList = asnController.getAllASNStoresData();
		logger.info("[C]ASNInvoiceService::[M]getAllANSDataListing -> The ASN List to display is: "+asnStoresList.toString());
		return  asnStoresList;
	}

}
