package org.sap.era.dao;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Random;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.sap.era.persistence.AssignedOrgnazition;
import org.sap.era.persistence.Orgnazition;
import org.sap.era.persistence.PeriodicTableGroupAssignment;
import org.sap.era.persistence.Person;
import org.sap.era.persistence.ReportTask;
import org.sap.era.persistence.ReportTaskItem;
import org.sap.era.persistence.TableGroupAssignment;
import org.sap.era.persistence.TimeCoordinate;
import org.sap.era.persistence.enums.EIntervalTag;
import org.sap.era.persistence.enums.EOriginTag;
import org.sap.era.persistence.enums.EPeriodTag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component(value = "dataInitializationContext")
public class DataInitializationContext {

	private final static Random R_INST = new Random();

	@Autowired
	@Resource
	private PersonDAO personDao;

	@Autowired
	@Resource
	private OrgnazitionDAO orgnazitionDAO;

	@Autowired
	@Resource
	private AssignedOrganizationDAO assignedOrganizationDAO;

	@Autowired
	@Resource
	private CellModelDAO cellModelDAO;

	@Autowired
	@Resource
	private TableModelDAO tableModelDAO;

	@Autowired
	@Resource
	private TableGroupModelDAO tableGroupModelDAO;

	@Autowired
	@Resource
	private TableGroupAssignmentDAO tableGroupAssignmentDAO;

	@Autowired
	@Resource
	private PeriodicTableGroupAssignmentDAO periodicTableGroupAssignmentDAO;

	@Autowired
	@Resource
	private ReportTaskItemDAO reportTaskItemDAO;

	@Autowired
	@Resource
	private ReportTaskDAO reportTaskDAO;

	public void setReportTaskDAO(ReportTaskDAO reportTaskDAO) {
		this.reportTaskDAO = reportTaskDAO;
	}

	public void setReportTaskItemDAO(ReportTaskItemDAO reportTaskItemDAO) {
		this.reportTaskItemDAO = reportTaskItemDAO;
	}

	public void setPersonDao(PersonDAO personDao) {
		this.personDao = personDao;
	}

	public void setOrgnazitionDAO(OrgnazitionDAO orgnazitionDAO) {
		this.orgnazitionDAO = orgnazitionDAO;
	}

	public void setAssignedOrganizationDAO(AssignedOrganizationDAO assignedOrganizationDAO) {
		this.assignedOrganizationDAO = assignedOrganizationDAO;
	}

	public void setCellModelDAO(CellModelDAO cellModelDAO) {
		this.cellModelDAO = cellModelDAO;
	}

	public void setTableModelDAO(TableModelDAO tableModelDAO) {
		this.tableModelDAO = tableModelDAO;
	}

	public void setTableGroupModelDAO(TableGroupModelDAO tableGroupModelDAO) {
		this.tableGroupModelDAO = tableGroupModelDAO;
	}

	public void setTableGroupAssignmentDAO(TableGroupAssignmentDAO tableGroupAssignmentDAO) {
		this.tableGroupAssignmentDAO = tableGroupAssignmentDAO;
	}

	public void setPeriodicTableGroupAssignmentDAO(PeriodicTableGroupAssignmentDAO periodicTableGroupAssignmentDAO) {
		this.periodicTableGroupAssignmentDAO = periodicTableGroupAssignmentDAO;
	}

	@PostConstruct
	public void initial() {
		if (checkWhetherShouldInitial()) {
			initialOrg();
			initialPerson();
			// initialReportAssignment();
			initialPeriodicReportAssignment();
			initReportTasks();
		}
	}

	private boolean checkWhetherShouldInitial() {
		return orgnazitionDAO.getAllOrgnazitions().size() == 0;
	}

	private void initialOrg() {
		// Create levels of organizations
		// 1. Loop the level
		final int MAX_LEVEL = R_INST.nextInt(3) + 2;
		List<Orgnazition> orgsWithLevel = new ArrayList<Orgnazition>(1);
		Orgnazition rootOrg = createOrg(0, 0);
		orgsWithLevel.add(rootOrg);
		rootOrg = orgnazitionDAO.persist(rootOrg);
		int currentLevel = 1;
		while (currentLevel++ < MAX_LEVEL) {
			orgsWithLevel = createSubOrgs(currentLevel, orgsWithLevel);
		}
	}

	private List<Orgnazition> createSubOrgs(int level, List<Orgnazition> parentOrgs) {
		ArrayList<Orgnazition> orgs = new ArrayList<Orgnazition>(parentOrgs.size() * 3);
		for (Orgnazition parentOrg : parentOrgs) {
			int creatingSize = R_INST.nextInt(3) + 3;
			for (int idx = 0; idx < creatingSize; idx++) {
				Orgnazition subOrg = createOrg(idx, level);
				parentOrg.getChildOrgnazition().add(subOrg);
				subOrg.setParentOrgnazition(parentOrg);
				orgs.add(subOrg);
			}
		}
		ArrayList<Orgnazition> result = orgnazitionDAO.persist(orgs);
		// orgnazitionDAO.merge(parentOrgs);

		return result;
	}

	private Orgnazition createOrg(int idx, int level) {
		Orgnazition subOrg = new Orgnazition();
		subOrg.setName("Org_with_level_" + level + "_" + idx);
		subOrg.setOrgnazitionLevel(level);
		subOrg.setParentName("#");
		subOrg.setTypeID("0");
		subOrg.setType("0");
		subOrg.setReportDirect("1");
		subOrg.setDescription("Org_" + level + "_" + idx);
		return subOrg;
	}

	private void initialPerson() {
		List<Orgnazition> orgList = orgnazitionDAO.getAllOrgnazitions();
		if (orgList == null || orgList.size() == 0) {
			// TODO: Throw exception if necessary
			return;
		}
		ArrayList<Person> persons = new ArrayList<Person>(orgList.size() * 2);
		for (Orgnazition org : orgList) {
			int createSize = R_INST.nextInt(4) + 2;
			for (int idx = 0; idx < createSize; idx++) {
				Person person = new Person();
				person.setFirstName("Test Person");
				person.setLastName("00" + String.valueOf(idx));
				person.setName(person.getLastName() + "_" + person.getFirstName());
				person.setOrgnazition(org);
				org.getPerson().add(person);
				person.setPassword("INIT69");
				persons.add(person);
			}
		}
		personDao.persist(persons);
	}

	private void initialReportAssignment() {
		List<Orgnazition> orgListHost = orgnazitionDAO.getAllOrgnazitions();
		if (orgListHost == null || orgListHost.size() == 0) {
			// TODO: Throw exception if necessary
			return;
		}
		int fetchSize = orgListHost.size();

		for (int i = 0; i < 10; i++) {
			TableGroupAssignment tga = new TableGroupAssignment();
			tga.setName("Test Report Assignment " + R_INST.nextInt(900) + 100);
			tga.setDurationModel("Duration Model Type A");
			tga.setDescription("Test Report Assignment " + R_INST.nextInt(9000) + 1000);
			tga = tableGroupAssignmentDAO.persist(tga);
			// Create assigned organizations
			fetchSize = R_INST.nextInt(fetchSize);
			fetchSize = fetchSize < 1 ? 1 : fetchSize;
			List<Orgnazition> originalOrgs = orgListHost.subList(0, fetchSize);
			List<AssignedOrgnazition> assignedOrgs = new ArrayList<AssignedOrgnazition>(originalOrgs.size());
			for (int j = 0; j < originalOrgs.size(); j++) {
				AssignedOrgnazition assignedOrg = new AssignedOrgnazition();
				assignedOrg.setOrgnazition(originalOrgs.get(j));
				assignedOrg.setTableGroupAssignment(tga);
				tga.getAssignedOrgnazition().add(assignedOrg);
				assignedOrgs.add(assignedOrg);
			}
			assignedOrganizationDAO.persist(assignedOrgs);
		}
	}

	private void initialPeriodicReportAssignment() {
		List<Orgnazition> orgListHost = orgnazitionDAO.getAllOrgnazitions();
		if (orgListHost == null || orgListHost.size() == 0) {
			// TODO: Throw exception if necessary
			return;
		}
		int fetchSize = orgListHost.size();

		for (int i = 0; i < 10; i++) {
			PeriodicTableGroupAssignment ptga = new PeriodicTableGroupAssignment();
			ptga.setName("Test Report Assignment " + R_INST.nextInt(900) + 100);
			ptga.setDurationModel("Duration Model Type A");
			ptga.setDescription("Test Report Assignment " + R_INST.nextInt(9000) + 1000);

			// Period type - e.g. Yearly, Monthly
			ptga.setPeriodTag(this.getRandomEnum(EPeriodTag.class));
			// Rule for Create date
			TimeCoordinate createTimeCoordinate = new TimeCoordinate();
			createTimeCoordinate.setIntervalTag(this.getRandomEnum(EIntervalTag.class));
			createTimeCoordinate.setOriginTag(this.getRandomEnum(EOriginTag.class));
			createTimeCoordinate.setOffsetDays(R_INST.nextInt(14) - 7);
			ptga.setCreateTimeCoordinate(createTimeCoordinate);
			// Rule for Start date
			TimeCoordinate startTimeCoordinate = new TimeCoordinate();
			startTimeCoordinate.setIntervalTag(this.getRandomEnum(EIntervalTag.class));
			startTimeCoordinate.setOriginTag(this.getRandomEnum(EOriginTag.class));
			startTimeCoordinate.setOffsetDays(R_INST.nextInt(14) - 7);
			ptga.setStartTimeCoordinate(startTimeCoordinate);
			// Rule for End date
			TimeCoordinate endTimeCoordinate = new TimeCoordinate();
			endTimeCoordinate.setIntervalTag(this.getRandomEnum(EIntervalTag.class));
			endTimeCoordinate.setOriginTag(this.getRandomEnum(EOriginTag.class));
			endTimeCoordinate.setOffsetDays(R_INST.nextInt(14) - 7);
			ptga.setEndTimeCoordinate(endTimeCoordinate);

			ptga = periodicTableGroupAssignmentDAO.persist(ptga);
			// Create assigned organizations
			fetchSize = R_INST.nextInt(fetchSize);
			fetchSize = fetchSize < 1 ? 1 : fetchSize;
			List<Orgnazition> originalOrgs = orgListHost.subList(0, fetchSize);
			List<AssignedOrgnazition> assignedOrgs = new ArrayList<AssignedOrgnazition>(originalOrgs.size());
			for (int j = 0; j < originalOrgs.size(); j++) {
				AssignedOrgnazition assignedOrg = new AssignedOrgnazition();
				assignedOrg.setOrgnazition(originalOrgs.get(j));
				assignedOrg.setTableGroupAssignment(ptga);
				ptga.getAssignedOrgnazition().add(assignedOrg);
				assignedOrgs.add(assignedOrg);
			}
			assignedOrganizationDAO.persist(assignedOrgs);
		}
	}

	private <TEnum> TEnum getRandomEnum(Class<TEnum> enumType) {
		final Random rInst = new Random();
		if (!enumType.isEnum()) {
			// throw new SystemException("Only enum type is allowed!");
			return null;
		}
		ArrayList<TEnum> allEnumFields = new ArrayList<TEnum>();
		for (Field field : enumType.getFields()) {
			if (field.isEnumConstant()) {
				try {
					allEnumFields.add((TEnum) field.get(null));
				} catch (IllegalArgumentException | IllegalAccessException ex) {
					ex.printStackTrace();
					// throw new SystemException(ex.getMessage());
					return null;
				}
			}
		}
		if (allEnumFields.size() > 0) {
			return allEnumFields.get(rInst.nextInt(allEnumFields.size()));
		} else {
			return null;
		}
	}

	private void initReportTasks() {
		List<ReportTask> tasks = this.reportTaskDAO.getAllReportTaskItem();
		// if (tasks == null || tasks.size() == 0) {
		//
		// return;
		// }
		for (int i = 0; i < 10; i++) {
			ReportTask task = new ReportTask();
			List<ReportTaskItem> taskItems = new ArrayList<ReportTaskItem>();
			task.setDurationDepict("TestDurationDesc");
			task.setDurationFlag("flag");
			task.setDurationID("100" + i);
			task.setEndDate(Calendar.getInstance().getTime());
			task.setReportMode("Yearly");
			task.setStartDate(Calendar.getInstance().getTime());
			task.setStatus(1);
			task.setTableGroupModel(1000 + i);
			task.setTableGroupModelName("TestGroupModelName" + i);

			for (int j = 0; j < 10; j++) {
				ReportTaskItem taskItem = new ReportTaskItem();
				taskItem.setFilePath("about:blank");
				taskItem.setItemStatus("Finished");
				taskItem.setOrgnazition(orgnazitionDAO.getOrgnazitionOfTop().get(0));
				taskItem.setReportDate(Calendar.getInstance().getTime());
				taskItem.setReportOrgnazition(null);
				taskItem.setReportPerson(null);
				taskItem.setReportTask(task);
				taskItems.add(taskItem);
			}
			task.setReportTaskItem(taskItems);
			this.reportTaskDAO.merge(task);
		}

	}
}
