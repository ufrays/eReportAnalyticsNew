package org.sap.era.dto;

import org.sap.era.persistence.Orgnazition;


public class OrgnazitionDTO extends Orgnazition{

	
	private int personCount;

	public int getPersonCount() {
		return this.getPerson().size();
	}

	public void setPersonCount(int personCount) {
		this.personCount = personCount;
	}
	
	
	
}
