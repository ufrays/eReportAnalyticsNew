package org.sap.era.service.excel;

public class ParseException extends Exception {
  private	String msg;
	
	public ParseException(String errmsg){
		msg = errmsg;
	}

	public String getMessage(){
		return msg;
	}
}
