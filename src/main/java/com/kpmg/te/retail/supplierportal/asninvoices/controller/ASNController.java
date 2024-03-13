package com.kpmg.te.retail.supplierportal.asninvoices.controller;

import java.sql.SQLException;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.kpmg.te.retail.supplierportal.asninvoices.dao.ASNDao;
import com.kpmg.te.retail.supplierportal.asninvoices.entity.ASNMaster;
import com.kpmg.te.retail.supplierportal.asninvoices.entity.ASNStores;
import com.kpmg.te.retail.supplierportal.asninvoices.entity.ASNSupplierSites;
import com.kpmg.te.retail.supplierportal.asninvoices.entity.PurchaseOrderMaster;

@Component
public class ASNController {
	
	@Autowired
	ASNDao asnDao;
	
	public ArrayList<ASNMaster> getAllASNListingData() throws ClassNotFoundException, SQLException {
		return asnDao.getAsnListingData();
	}

	public ASNMaster getAllASNDetailsData(String asnId) throws ClassNotFoundException, SQLException {
		return asnDao.getAsnDetailsData(asnId);
	}

	public ArrayList<ASNStores> getAllASNStoresData() throws ClassNotFoundException, SQLException {
		return asnDao.getAsnStoresData();
	}

	public ArrayList<ASNSupplierSites> getAllSupplierSiteData() throws ClassNotFoundException, SQLException {
		return asnDao.getAsnSupplierSites();
	}

	public String saveASN(ASNMaster asnMaster) throws ClassNotFoundException, SQLException {
		String status = asnDao.saveASNdata(asnMaster);
		return status;
		
	}

	public ArrayList<PurchaseOrderMaster> getPoItems(String[] poIdList) throws ClassNotFoundException, SQLException {
		ArrayList<PurchaseOrderMaster> poMasterItemsList = asnDao.getPOitems(poIdList);
		return poMasterItemsList;
	}



}
