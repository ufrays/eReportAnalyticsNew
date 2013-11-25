package org.sap.era.service.validation;

public class ValidatorError {
	public static final Integer LEVEL_NOERROR = 0;
	public static final Integer LEVEL_ERROR = 1;
	public static final Integer LEVEL_WARRING = 2;
	
	private String error;
	private Integer level;
	
	
	public ValidatorError(String error,Integer level){
		this.error = error;
		this.level = level;
	}
	public String getError() {
		return error;
	}
	public void setError(String error) {
		this.error = error;
	}
	public Integer getLevel() {
		return level;
	}
	public void setLevel(Integer level) {
		this.level = level;
	}
}
