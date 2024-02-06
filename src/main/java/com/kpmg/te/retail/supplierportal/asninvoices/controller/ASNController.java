package com.kpmg.te.retail.supplierportal.asninvoices.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.kpmg.te.retail.supplierportal.asninvoices.dao.ASNDao;
import com.kpmg.te.retail.supplierportal.asninvoices.entity.ASNMaster;
import com.kpmg.te.retail.supplierportal.asninvoices.entity.ASNStores;
import com.kpmg.te.retail.supplierportal.asninvoices.entity.ASNSupplierSites;

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

	public String saveASN(List<ASNMaster> asnMaster) {
		String status = asnDao.saveASNdata(asnMaster);
		return status;
		
	}

	public String getPoItems(String[] poIdList) {
		String status = asnDao.getPOitems(poIdList);
		return status;
	}



}
