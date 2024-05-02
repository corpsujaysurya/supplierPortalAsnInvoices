package com.kpmg.te.retail.supplierportal.asninvoices.manager;

import java.sql.SQLException;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.kpmg.te.retail.supplierportal.asninvoices.dao.ASNDao;

@Component
public class ASNManager {
	
	@Autowired
	ASNDao asnDao;
	
	private static final Logger logger = Logger.getLogger(ASNManager.class.getName());

	public Boolean checkASNexists(String asnId) throws ClassNotFoundException, SQLException {
		return asnDao.checkIfASNidExists(asnId);
	}

}
