package com.kpmg.te.retail.supplierportal.asninvoices.controller;

import java.sql.SQLException;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.kpmg.te.retail.supplierportal.asninvoices.dao.ASNDao;
import com.kpmg.te.retail.supplierportal.asninvoices.entity.ASNItemDetails;
import com.kpmg.te.retail.supplierportal.asninvoices.entity.ASNMaster;
import com.kpmg.te.retail.supplierportal.asninvoices.entity.ASNStores;
import com.kpmg.te.retail.supplierportal.asninvoices.entity.ASNSupplierSites;

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

	public ArrayList<ASNItemDetails> getPoItems(String[] poIdList) throws ClassNotFoundException, SQLException {
		ArrayList<ASNItemDetails> asnItemDetailsList = asnDao.getPOitems(poIdList);
		return asnItemDetailsList;
	}
	
	public String generateEway() {
		return asnDao.generateEway();
	}

	public String generateAWB() {
		return asnDao.generateAWB();
	}



}
