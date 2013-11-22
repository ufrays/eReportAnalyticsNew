package org.sap.era.main;

import static org.junit.Assert.*;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.sap.era.service.OrgnazitionService;
import org.sap.era.service.PersonService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.ContextConfiguration;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:beans.xml")
public class JpaTest extends AbstractTransactionalJUnit4SpringContextTests {
	
	@Resource
	private PersonService personService;
	@Resource
	private OrgnazitionService orgnazitionService;
	

	@Test
	public void test() {
//		ApplicationContext context = new ClassPathXmlApplicationContext(new String[] { "beans.xml" });
//		personService = context.getBean("personService", PersonService.class);
//		orgnazitionService = context.getBean("orgnazitionService", OrgnazitionService.class);
		//List<TableGroupModel> list = tableGroupModelService.getAllTableGroupModels();
		int a = 1;
		int b = 1;
		int c = 1+1;
		//int test = list.size();
	}

}
