package org.sap.era.web.controller;

import java.util.List;
import org.sap.era.persistence.Person;
import org.sap.era.service.PersonService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller  
@RequestMapping  
public class BusinessUserController {

	PersonService personService;
	
	public BusinessUserController(){
		ApplicationContext context = new ClassPathXmlApplicationContext(new String[] { "beans.xml" });
		personService = context.getBean("personService", PersonService.class);
	}
	
    @RequestMapping("/getAllPersons")
    @ResponseBody
    public List<Person> getAllPersons(){
    	List<Person> list = personService.getAllPersons();
    	return list;
    }
	
    
    
    
    
	
	
}
