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
import org.springframework.stereotype.Repository;

@Repository
public class DataInitializationContext {

	private final static Random R_INST = new Random();

	@Autowired
	@Resource(name = "orgnazitionDAO")
	private OrgnazitionDAO orgDao;

	@Autowired
	@Resource(name = "personDAO")
	private PersonDAO personDao;

	@PostConstruct
	void initial() {
		initialOrg();
		initialPerson();
		initialReportAssignment();
	}

	private void initialReportAssignment() {
		List<Orgnazition> orgListHost = orgDao.getAllOrgnazitions();
		if (orgListHost == null || orgListHost.size() == 0) {
			return;
		}
		int fetchSize = orgListHost.size();

		for (int i = 0; i < 10; i++) {
			TableGroupAssignment tga = new TableGroupAssignment();
			tga.setName("Test Report Assignment");
			// Create assigned organizations
			fetchSize = R_INST.nextInt(fetchSize - 1) + 1;
			List<Orgnazition> originalOrgs = orgListHost.subList(0, fetchSize);
			List<AssignedOrgnazition> assignedOrgs = new ArrayList<AssignedOrgnazition>(originalOrgs.size());
			for (int j = 0; j < originalOrgs.size(); j++) {
				AssignedOrgnazition assignedOrg = new AssignedOrgnazition();
				assignedOrg.setOrgnazition(originalOrgs.get(j));
				assignedOrg.setTableGroupAssignment(tga);
				assignedOrgs.add(assignedOrg);
			}
		}
	}

	private void initialPerson() {
		List<Orgnazition> orgList = orgDao.getAllOrgnazitions();
		Orgnazition org1 = null;
		for (int j = 0; j < orgList.size(); j++) {
			org1 = orgList.get(j);

			for (int k = 0; k < 10; k++) {
				Person person1 = new Person();
				person1.setFirstName("Test_00" + String.valueOf(k));
				person1.setLastName(org1.getName() + "_00" + String.valueOf(k));
				person1.setName(person1.getLastName() + "_" + person1.getFirstName());
				person1.setOrgnazition(org1);
				person1.setPassword("a");
				personDao.addPerson(person1);
			}
		}
	}

	private void initialOrg() {
		// Create levels of organizations
		// 1. Loop the level
		for (int i = 0; i < R_INST.nextInt(2) + 1; i++) {
			// 2. Loop the list of org in the same level
			for (int j = 0; j < R_INST.nextInt(3) + 3; j++) {
				Orgnazition orgHost = new Orgnazition();
				orgHost.setName("Org_level_" + j);
				orgHost.setParentName("#");
				orgHost.setTypeID("0");
				orgHost.setType("0");
				orgHost.setReportDirect("1");
				orgHost.setOrgnazitionLevel(0);
				orgHost.setDescription("Demo Head Quater");
				orgDao.mergeOrgnazition(orgHost);
				// 3. Create the sub orgs list for each org in current level

			}
		}
	}
}
