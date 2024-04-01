package com.kpmg.te.retail.supplierportal.asninvoices.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;
import java.util.UUID;
import java.util.logging.Logger;

import org.springframework.stereotype.Component;

@Component
public class ASNInvoiceUtils {
	
	private static final Logger logger = Logger.getLogger(ASNInvoiceUtils.class.getName());
	
	public String getDateTime() {
		//DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
		   LocalDateTime now = LocalDateTime.now();
		   return now.toString();
	}
	
	public String setRandomUUID() {
		return UUID.randomUUID().toString();
	}
	
	public String generateInvoiceId() {
		String invoiceId = "INV-" + generateRandomNumber(7);
		logger.info(
				"[C]OrderManagementUtils::[M]generateInvoiceId -> The Invoice ID generated is:" + invoiceId.toString());
		return invoiceId;
	}
	
	public String generateCurrentDate() {
		return new SimpleDateFormat("yyyy/MM/dd").format(Calendar.getInstance().getTime());
	}
	
	public String generateRandomNumber(int charLength) {
		return String.valueOf(charLength < 1 ? 0
				: new Random().nextInt((9 * (int) Math.pow(10, charLength - 1)) - 1)
						+ (int) Math.pow(10, charLength - 1));
	}
	

	public String getNextSeventhDate(String curDate) throws ParseException {
		final SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
		final Date date = format.parse(curDate);
		final Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DAY_OF_YEAR, 7);
		return format.format(calendar.getTime());
	}

	public String generateASNId() {
		String asnID = "ASN-" + generateRandomNumber(7);
		logger.info(
				"[C]OrderManagementUtils::[M]generateASNId -> The ASN ID generated is:" + asnID.toString());
		return asnID;
	}


}
