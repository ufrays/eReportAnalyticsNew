package org.sap.era.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.sap.era.common.EnumHelper;
import org.sap.era.dao.AssignedOrganizationDAO;
import org.sap.era.dao.OrgnazitionDAO;
import org.sap.era.dao.TableGroupAssignmentDAO;
import org.sap.era.dao.TableGroupModelDAO;
import org.sap.era.dto.TableGroupAssignmentEditDTO;
import org.sap.era.dto.TableGroupAssignmentGeneralDTO;
import org.sap.era.persistence.AssignedOrgnazition;
import org.sap.era.persistence.Orgnazition;
import org.sap.era.persistence.TableGroupAssignment;
import org.sap.era.persistence.TableGroupModel;
import org.sap.era.persistence.enums.EIntervalTag;
import org.sap.era.persistence.enums.EOriginTag;
import org.sap.era.persistence.enums.EPeriodTag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service(value = "tableGroupAssignmentService")
public class TableGroupAssignmentService {

	@Autowired
	@Resource
	private TableGroupAssignmentDAO tgaDao;

	@Autowired
	@Resource
	private AssignedOrganizationDAO aoDao;

	@Autowired
	@Resource
	private OrgnazitionDAO orgDao;

	@Autowired
	@Resource
	private TableGroupModelDAO tgmDao;

	public void setTableGroupAssignmentDAO(TableGroupAssignmentDAO tableGroupAssignmentDAO) {
		this.tgaDao = tableGroupAssignmentDAO;
	}

	public void setAoDao(AssignedOrganizationDAO aoDao) {
		this.aoDao = aoDao;
	}

	public void setOrgDao(OrgnazitionDAO orgDao) {
		this.orgDao = orgDao;
	}

	public void setTgmDao(TableGroupModelDAO tgmDao) {
		this.tgmDao = tgmDao;
	}

	/**
	 * @author I071053
	 * @return
	 */
	public List<TableGroupAssignmentGeneralDTO> getAllTableGroupAssignments() {
		List<TableGroupAssignment> allTgas = tgaDao.getAllTableGroupAssignments();
		ArrayList<TableGroupAssignmentGeneralDTO> dtos = new ArrayList<TableGroupAssignmentGeneralDTO>(allTgas.size());
		for (TableGroupAssignment tga : allTgas) {
			TableGroupAssignmentGeneralDTO dto = new TableGroupAssignmentGeneralDTO();
			dto.setTableGroupAssignment(tga);
			dto.setTableGroupModel(tga.getTableGroupModel());
			dtos.add(dto);
		}
		return dtos;
	}

	/**
	 * @author I071053
	 * @param id
	 * @return
	 */
	public TableGroupAssignmentEditDTO getTableGroupAssignmentById(long reportAssignmentId) {
		TableGroupAssignment tga = tgaDao.retrieve(TableGroupAssignment.class, reportAssignmentId);
		return convertTgaToEditDto(tga);
	}

	/**
	 * 
	 * @param tableGroupAssignment
	 */
	public void addTableGroupAssignment(TableGroupAssignment tableGroupAssignment) {
		tgaDao.addTableGroupAssignment(tableGroupAssignment);
	}

	/**
	 * 
	 * @param tableGroupAssignmentID
	 * @return
	 */
	public List<Orgnazition> getUnselectedOrgnazition(long tableGroupAssignmentID) {
		return tgaDao.getUnselectedOrgnazition(tableGroupAssignmentID);
	}

	/**
	 * @author I071053
	 * @param id
	 */
	public void delete(long id) {
		tgaDao.delete(id, TableGroupAssignment.class);
	}

	/**
	 * @author I071053
	 * @param tga
	 * @return
	 */
	public TableGroupAssignment create(TableGroupAssignment tga) {
		return tgaDao.persist(tga);
	}

	/**
	 * @author I071053
	 * @param reportAssignmentId
	 * @return
	 */
	public List<Orgnazition> getAssignedOrgList(long reportAssignmentId) {
		List<AssignedOrgnazition> assignedOrgs = tgaDao.retrieve(TableGroupAssignment.class, reportAssignmentId).getAssignedOrgnazition();
		ArrayList<Orgnazition> orgs = new ArrayList<Orgnazition>(assignedOrgs.size());
		for (AssignedOrgnazition ao : assignedOrgs) {
			orgs.add(ao.getOrgnazition());
		}
		return orgs;
	}

	/**
	 * 
	 * @param tga
	 * @return
	 */
	public TableGroupAssignmentEditDTO save(TableGroupAssignmentEditDTO tgaDto) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * 
	 * @param id
	 * @return
	 */
	public void generateTasks(long id) {
		// TODO Auto-generated method stub
	}

	/*
	 * Private & Protected Methods
	 */

	private TableGroupAssignmentEditDTO convertTgaToEditDto(TableGroupAssignment tga) {

		TableGroupAssignmentEditDTO dto = initialEditDto();

		dto.setTableGroupAssignment(tga);
		dto.setTableGroupModel(tga.getTableGroupModel());
		dto.setAssignedOrgnazitions(tga.getAssignedOrgnazition());
		ArrayList<Orgnazition> orgs = new ArrayList<Orgnazition>(tga.getAssignedOrgnazition().size());
		for (AssignedOrgnazition ao : tga.getAssignedOrgnazition()) {
			orgs.add(ao.getOrgnazition());
		}
		dto.setOrgnazitions(orgs);
		return dto;
	}

	private TableGroupAssignmentEditDTO initialEditDto() {
		TableGroupAssignmentEditDTO dto = new TableGroupAssignmentEditDTO();
		// dto.setAllOrgnazitions(orgDao.retrieve(Orgnazition.class));
		dto.setAllTableGroupModels(tgmDao.retrieve(TableGroupModel.class));

		ArrayList<EIntervalTag> intervalTagEnums = EnumHelper.getOptionsFromEnum(EIntervalTag.class);
		ArrayList<Map<String, String>> intervalTagOptions = new ArrayList<Map<String, String>>(intervalTagEnums.size());
		for (EIntervalTag eIntervalTag : intervalTagEnums) {
			Map<String, String> option = new HashMap<String, String>(2);
			option.put("index", String.valueOf(eIntervalTag.getIndex()));
			option.put("name", eIntervalTag.getName());
			intervalTagOptions.add(option);
		}
		dto.setIntervalTagOptions(intervalTagOptions);

		ArrayList<EOriginTag> originTagEnums = EnumHelper.getOptionsFromEnum(EOriginTag.class);
		ArrayList<Map<String, String>> originTagOptions = new ArrayList<Map<String, String>>(originTagEnums.size());
		for (EOriginTag eOriginTag : originTagEnums) {
			Map<String, String> option = new HashMap<String, String>(2);
			option.put("index", String.valueOf(eOriginTag.getIndex()));
			option.put("name", eOriginTag.getName());
			originTagOptions.add(option);
		}
		dto.setOriginTagOptions(originTagOptions);

		ArrayList<EPeriodTag> periodTagEnums = EnumHelper.getOptionsFromEnum(EPeriodTag.class);
		ArrayList<Map<String, String>> periodTagOptions = new ArrayList<Map<String, String>>(periodTagEnums.size());
		for (EPeriodTag ePeriodTag : periodTagEnums) {
			Map<String, String> option = new HashMap<String, String>(2);
			option.put("index", String.valueOf(ePeriodTag.getIndex()));
			option.put("name", ePeriodTag.getUnitName());
			periodTagOptions.add(option);
		}
		dto.setPeriodTagOptions(periodTagOptions);

		return dto;
	}

	public TableGroupAssignmentEditDTO getEditModel() {
		return initialEditDto();
	}

}
