package org.sap.era.web.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.sap.era.persistence.Orgnazition;
import org.sap.era.persistence.Person;
import org.sap.era.service.OrgnazitionService;
import org.sap.era.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping
public class OrgnazitionController {

	@Autowired
	@Resource
	OrgnazitionService orgnazitionService;

	@Autowired
	@Resource
	PersonService personService;

	public OrgnazitionController() {
		// ApplicationContext context = new ClassPathXmlApplicationContext(new
		// String[] { "beans.xml" });
		// orgnazitionService = context.getBean("orgnazitionService",
		// OrgnazitionService.class);
		// personService = context.getBean("personService",
		// PersonService.class);
	}

	@RequestMapping("/getAllOrgnazition")
	@ResponseBody
	public List<Orgnazition> getAllOrgnazition() {
		List<Orgnazition> list = orgnazitionService.getOrgnazitionOfTop();
		return list;
	}

	@RequestMapping(value = "/saveOrgnazition", method = RequestMethod.GET)
	@ResponseBody
	public String saveOrgnazition(Orgnazition orgnazition) {
		orgnazitionService.saveOrgnazition(orgnazition);
		return null;
	}

	@RequestMapping(value = "/addOrgnazition", method = RequestMethod.GET)
	@ResponseBody
	public String addOrgnazition(Orgnazition orgnazition) {
		String parentID = orgnazition.getParentID();
		Orgnazition parentOrg = orgnazitionService.getOrgnazitionByID(parentID);
		orgnazition.setOrgnazitionLevel(parentOrg.getOrgnazitionLevel() + 1);
		parentOrg.addOrgChild(orgnazition);
		orgnazitionService.saveOrgnazition(parentOrg);
		return "The Orgnazition-[" + orgnazition.getName() + "] was saved!";
	}

	@RequestMapping(value = "/deleteOrgnazition", method = RequestMethod.GET)
	@ResponseBody
	public String deleteOrgnazition(HttpServletRequest request) {
		String id = request.getParameter("id");
		orgnazitionService.deleteOrgnazition(id);
		return "The Orgnazition-[" + id + "] was deleted!";
	}

	@RequestMapping("/init")
	@ResponseBody
	public String init() {
		Orgnazition orgHost = new Orgnazition();
		orgHost.setName("Demo_Headquater");
		orgHost.setParentName("#");
		orgHost.setTypeID("0");
		orgHost.setType("0");
		orgHost.setParentOrgnazition(null);
		orgHost.setReportDirect("1");
		orgHost.setOrgnazitionLevel(0);
		orgHost.setDescription("Demo_Headquater");
		orgnazitionService.addOrgnazition(orgHost);
		List<Orgnazition> orgListHost = orgnazitionService.getAllOrgnazitions();
		Orgnazition temp = orgListHost.get(0);
		for (int i = 0; i < 10; i++) {
			Orgnazition org = new Orgnazition();
			org.setName("TestOrgnazition_00" + String.valueOf(i));
			org.setParentName("ROOT");
			org.setTypeID("0");
			org.setType("0");
			org.setParentOrgnazition(temp);
			org.setReportDirect("1");
			org.setParentOrgnazition(orgListHost.get(0));
			org.setOrgnazitionLevel(orgHost.getOrgnazitionLevel() + 1);
			org.setDescription("TestOrgnazition_00" + String.valueOf(i));
			temp.addOrgChild(org);
		}
		orgnazitionService.saveOrgnazition(temp);
		List<Orgnazition> orgList = orgnazitionService.getAllOrgnazitions();
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
				personService.addPerson(person1);
			}

		}

		return "Success";
	}

}
