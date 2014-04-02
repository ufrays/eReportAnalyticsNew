package org.sap.era.dto;

import java.util.ArrayList;
import java.util.List;

import org.sap.era.persistence.Orgnazition;

public class OrgnazitionDTO extends Orgnazition {


	public OrgnazitionDTO(Orgnazition orgnazition) {
		super.setId(orgnazition.getId());
		super.setTypeID(orgnazition.getTypeID());
		super.setName(orgnazition.getName());
		super.setParentID(orgnazition.getParentID());
		super.setParentName(orgnazition.getParentName());
		super.setType(orgnazition.getType());
		super.setReportDirect(orgnazition.getReportDirect());
		super.setOrgnazitionLevel(orgnazition.getOrgnazitionLevel());
		super.setDescription(orgnazition.getDescription());

		super.setPerson(orgnazition.getPerson());
		super.setParentOrgnazition(orgnazition.getParentOrgnazition());
		super.setChildOrgnazition(orgnazition.getChildOrgnazition());
	}

	public List<OrgnazitionDTO> getChildOrgnazitions() {
		int chilrenCount = super.getChildOrgnazition().size();
		if (chilrenCount > 0) {
			ArrayList<OrgnazitionDTO> dtos = new ArrayList<OrgnazitionDTO>(chilrenCount);
			for (Orgnazition orgnazition : super.getChildOrgnazition()) {
				OrgnazitionDTO dto = new OrgnazitionDTO(orgnazition);
				dtos.add(dto);
			}
			return dtos;
		} else {
			return null;
		}
	}
}
