package org.sap.era.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.sap.era.persistence.AssignedOrgnazition;
import org.sap.era.persistence.Orgnazition;
import org.sap.era.persistence.Person;
import org.sap.era.persistence.TableGroupAssignment;
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
	private OrgnazitionDAO orgDao;

	@Autowired
	@Resource
	private AssignedOrganizationDAO aoDao;

	@Autowired
	@Resource
	private CellModelDAO cmDao;

	@Autowired
	@Resource
	private TableModelDAO tmDao;

	@Autowired
	@Resource
	private TableGroupModelDAO tgmDao;

	@Autowired
	@Resource
	private TableGroupAssignmentDAO tgaDao;

	@PostConstruct
	public void initial() {
		if (checkWhetherShouldInitial()) {
			initialOrg();
			initialPerson();
			initialReportAssignment();
		}
	}

	private boolean checkWhetherShouldInitial() {
		return orgDao.getAllOrgnazitions().size() == 0;
	}

	private void initialOrg() {
		// Create levels of organizations
		// 1. Loop the level
		final int MAX_LEVEL = R_INST.nextInt(3) + 2;
		List<Orgnazition> orgsWithLevel = new ArrayList<Orgnazition>(1);
		Orgnazition rootOrg = createOrg(0, 0);
		orgsWithLevel.add(rootOrg);
		rootOrg = orgDao.persist(rootOrg);
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
		return orgDao.persist(orgs);
	}

	private Orgnazition createOrg(int idx, int level) {
		Orgnazition subOrg = new Orgnazition();
		subOrg.setName("Org_level_" + idx);
		subOrg.setOrgnazitionLevel(level);
		subOrg.setParentName("#");
		subOrg.setTypeID("0");
		subOrg.setType("0");
		subOrg.setReportDirect("1");
		subOrg.setOrgnazitionLevel(0);
		subOrg.setDescription("Demo Head Quater " + idx);
		return subOrg;
	}

	private void initialPerson() {
		List<Orgnazition> orgList = orgDao.getAllOrgnazitions();
		if (orgList == null || orgList.size() == 0) {
			// TODO: Throw exception if necessary
			return;
		}
		ArrayList<Person> persons = new ArrayList<Person>(orgList.size() * 2);
		for (Orgnazition org : orgList) {
			int createSize = R_INST.nextInt(4) + 2;
			for (int idx = 0; idx < createSize; idx++) {
				Person person = new Person();
				person.setFirstName("Test_Person ");
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
		List<Orgnazition> orgListHost = orgDao.getAllOrgnazitions();
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
			tga = tgaDao.persist(tga);
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
			aoDao.persist(assignedOrgs);
		}
	}
}
