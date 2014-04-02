package org.sap.era.servlet;

import org.sap.era.service.OrgnazitionService;
import org.sap.era.service.PersonService;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.List;
import org.sap.era.persistence.Orgnazition;
import org.sap.era.persistence.Person;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import com.sap.security.core.server.csi.XSSEncoder;

/**
 * Servlet implementation class PersonServlet
 */
public class PersonServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private PersonService personService;
	private OrgnazitionService orgnazitionService;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public PersonServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.write("<html><head></head><body>");
		String uri = request.getRequestURI();
		if (uri.endsWith("/Add")) {
			doAdd(request, out);
		}

		List<Person> resultList = personService.getAllPersons();
		out.write("<table border=\"1\"><tr><th colspan=\"3\">" + (resultList.isEmpty() ? "" : resultList.size() + " ")
				+ "Entries in the Database</th></tr>");
		if (resultList.isEmpty()) {
			out.write("<tr><td colspan=\"3\">Database is empty</td></tr>");
		} else {
			out.write("<tr><th>First name</th><th>Last name</th><th>Id</th></tr>");
		}
		for (Person p : resultList) {
			out.write("<tr><td>" + (p.getFirstName().length() > 0 ? XSSEncoder.encodeHTML(p.getFirstName()) : "&nbsp") + "</td><td>"
					+ (p.getLastName().length() > 0 ? XSSEncoder.encodeHTML(p.getLastName()) : "&nbsp") + "</td><td>" + p.getId() + "</td></tr>");
		}
		out.write("</table>");
		out.write("<form action=\"Add\">" + "First name:<input type=\"text\" name=\"FirstName\">"
				+ "&nbsp;Last name:<input type=\"text\" name=\"LastName\">" + "&nbsp;<input type=\"submit\" value=\"Add Person\">" + "</form>");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	protected void doAdd(HttpServletRequest request, PrintWriter out) throws ServletException, IOException {
		// personService.addPerson(request.getParameter("FirstName"),request.getParameter("LastName"));
		Orgnazition orgHost = new Orgnazition();
		orgHost.setName("Demo_Headquater");
		orgHost.setParentName("#");
		orgHost.setTypeID("0");
		orgHost.setType("0");
		orgHost.setParentOrgnazition(null);
		orgHost.setReportDirect("1");
		orgHost.setOrgnazitionLevel(0);
		orgnazitionService.addOrgnazition(orgHost);
		List<Orgnazition> orgListHost = orgnazitionService.getAllOrgnazitions();
		for (int i = 0; i < 10; i++) {
			Orgnazition org = new Orgnazition();
			org.setName("TestOrgnazition_00" + String.valueOf(i));
			org.setParentName("ROOT");
			org.setTypeID("0");
			org.setType("0");
			org.setParentOrgnazition(orgListHost.get(0));
			org.setReportDirect("1");
			org.setOrgnazitionLevel(orgHost.getOrgnazitionLevel() + 1);
			orgnazitionService.addOrgnazition(org);
			int oooo = 11;
			oooo = 2;
		}
		//
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
				person1.setPassword("Welcome1");
				personService.addPerson(person1);
			}

		}
	}

	protected void doGenerate(HttpServletRequest request, PrintWriter out) throws ServletException, IOException {
		Orgnazition org = new Orgnazition();
		for (int i = 0; i < 10; i++) {
			org.setName("TestOrgnazition_00" + String.valueOf(i));
			org.setParentName("ROOT");
			orgnazitionService.addOrgnazition(org);
		}
		//
		List<Orgnazition> orgList = orgnazitionService.getAllOrgnazitions();
		Orgnazition org1 = null;
		for (int j = 0; j < orgList.size(); j++) {
			org1 = orgList.get(j);
			Person person1 = new Person();
			for (int k = 0; k < 10; k++) {
				person1.setFirstName("Test_00" + String.valueOf(k));
				person1.setLastName("Name_00" + String.valueOf(k));
				person1.setName(person1.getLastName() + "_" + person1.getFirstName());
				person1.setOrgnazition(org1);
				person1.setPassword("Welcome1");
				personService.addPerson(person1);
			}

		}
	}

	@Override
	public void init() {
		ApplicationContext context = new ClassPathXmlApplicationContext(new String[] { "beans.xml" });
		personService = context.getBean("personService", PersonService.class);
		orgnazitionService = context.getBean("orgnazitionService", OrgnazitionService.class);
	}

}
