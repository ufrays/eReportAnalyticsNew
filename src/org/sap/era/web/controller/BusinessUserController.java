package org.sap.era.web.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.sap.era.persistence.Person;
import org.sap.era.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping
public class BusinessUserController {

	@Autowired
	@Resource
	PersonService personService;

	public BusinessUserController() {
		// ApplicationContext context = new ClassPathXmlApplicationContext(new
		// String[] { "beans.xml" });
		// personService = context.getBean("personService",
		// PersonService.class);
	}

	@RequestMapping("/getAllPersons")
	@ResponseBody
	public List<Person> getAllPersons() {
		List<Person> list = personService.getAllPersons();
		return list;
	}

	@RequestMapping(value = "/getLoginPerson", method = RequestMethod.GET)
	@ResponseBody
	public Person getLoginPerson(HttpServletRequest request) {
		Person person = (Person) request.getSession().getAttribute("loginUser");
		return person;
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	@ResponseBody
	public Person login(String username, String password, HttpServletRequest request) {
		List<Person> list = personService.getPersonsByUserName(username);
		Person person = null;
		if (list == null || list.size() == 0) {

		} else {
			if (list.get(0).getPassword().equals(password)) {
				person = list.get(0);
				request.getSession().setAttribute("loginUser", person);

			}
		}
		return person;
	}

}
