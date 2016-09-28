package com.demo.util;

public class JsonResult {

	public static final boolean SUCCESS=true;
	public static final boolean FALSE=false;
	
	private boolean result;
	private String message;
	
	public boolean isResult() {
		return result;
	}
	public void setResult(boolean result) {
		this.result = result;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	
}
