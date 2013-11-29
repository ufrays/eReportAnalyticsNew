package org.sap.era.service.validation;

import java.util.List;

import org.sap.era.persistence.CellData;

public class ValidatorSheetErrors {
	
	private int sheetID;
	
	private String sheetName;
	
	List<ValidatorError> formatErrors;
	
	List<ValidatorError> formartWarnings;
	
	List<ValidatorError> modelErrors;
	
	List<CellData> cellDataList;

	public int getSheetID() {
		return sheetID;
	}

	public void setSheetID(int sheetID) {
		this.sheetID = sheetID;
	}

	public String getSheetName() {
		return sheetName;
	}

	public void setSheetName(String sheetName) {
		this.sheetName = sheetName;
	}

	public List<ValidatorError> getFormatErrors() {
		return formatErrors;
	}

	public void setFormatErrors(List<ValidatorError> formatErrors) {
		this.formatErrors = formatErrors;
	}

	public List<ValidatorError> getFormartWarnings() {
		return formartWarnings;
	}

	public void setFormartWarnings(List<ValidatorError> formartWarnings) {
		this.formartWarnings = formartWarnings;
	}

	public List<ValidatorError> getModelErrors() {
		return modelErrors;
	}

	public void setModelErrors(List<ValidatorError> modelErrors) {
		this.modelErrors = modelErrors;
	}

	public List<CellData> getCellDataList() {
		return cellDataList;
	}

	public void setCellDataList(List<CellData> cellDataList) {
		this.cellDataList = cellDataList;
	}


}
