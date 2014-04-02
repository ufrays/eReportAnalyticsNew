package org.sap.era.service.validation;

import org.sap.era.persistence.CellData;

public interface CellValidator {
	/**
	 * Judge if the Cell is for inputting the value.
	 * @param cell
	 * @return
	 */
	public boolean isInputCell(String label);
	
	/**
	 * Judge if the value fits for the defined pattern.
	 * @param value
	 * @param pattern
	 * @return
	 */
	public boolean isValid(String value,String pattern);
	
	
	public String getFormatString(String pattern);
	
	/**
	 * Format the value of the cell.
	 * @param cell
	 * @param pattern
	 * @return
	 */
	public void dateFormate(CellData cell, String pattern);
}
