package com.kpmg.te.retail.supplierportal.asninvoices.utils;

public class Test {
	
    static Character traverseString(String str)
    {
        // Traverse the string
    	char resultChar = 0; 
        for (int i = 0; i < str.length(); i++) {
        	Character c = str.charAt(i);
        	Character cNxt = str.charAt(i+1);
        	if (c.compareTo(cNxt)== 0) {
        		continue; 
        	}else {
        		resultChar = c;
        		return resultChar;
        	}
        }
		return resultChar;
    }
 
    public static void main(String[] args)
    {
        String str = "racecars";
        Character resultChar = traverseString(str);
        System.out.println(resultChar);
    }

}
