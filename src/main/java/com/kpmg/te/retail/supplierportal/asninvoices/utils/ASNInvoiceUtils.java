package com.kpmg.te.retail.supplierportal.asninvoices.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

import org.springframework.stereotype.Component;

@Component
public class ASNInvoiceUtils {
	
	public String getDateTime() {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
		   LocalDateTime now = LocalDateTime.now();
		   return now.toString();
	}
	
	public String setRandomUUID() {
		return UUID.randomUUID().toString();
	}


}
