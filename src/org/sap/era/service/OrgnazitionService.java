package org.sap.era.service;

import java.util.List;

import javax.annotation.Resource;

import org.sap.era.dao.OrgnazitionDAO;
import org.sap.era.persistence.Orgnazition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service(value = "orgnazitionService")
public class OrgnazitionService {

	@Autowired
	@Resource
	private OrgnazitionDAO orgnazitionDAO;

	public void addOrgnazition(Orgnazition orgnazition) {
		this.orgnazitionDAO.mergeOrgnazition(orgnazition);
	}

	public List<Orgnazition> getAllOrgnazitions() {
		return orgnazitionDAO.getAllOrgnazitions();
	}

	public List<Orgnazition> getOrgnazitionsByParentID(String parentID) {
		return orgnazitionDAO.getOrgnazitionsByParentID(parentID);
	}

	public Orgnazition getOrgnazitionByID(String ID) {
		return orgnazitionDAO.getOrgnazitionByID(ID);
	}

	public void saveOrgnazition(Orgnazition orgnazition) {
		this.orgnazitionDAO.saveOrgnazition(orgnazition);
	}

	public void deleteOrgnazition(String id) {
		Orgnazition orgnazition = this.getOrgnazitionByID(id);
		Orgnazition parentOrgnazition = this.getOrgnazitionByID(String.valueOf(orgnazition.getParentOrgnazition().getId()));
		parentOrgnazition.removeOrgChild(orgnazition);
		this.orgnazitionDAO.saveOrgnazition(parentOrgnazition);
	}

	public List<Orgnazition> getOrgnazitionOfTop() {
		return orgnazitionDAO.getOrgnazitionOfTop();
	}

}
