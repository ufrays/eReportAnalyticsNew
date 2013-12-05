package org.sap.era.service;

import java.util.List;

import javax.annotation.Resource;

import org.sap.era.dao.PersonDAO;
import org.sap.era.persistence.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service(value = "personService")
public class PersonService {

	@Autowired
	@Resource
	private PersonDAO personDao;

	public void setPersonDao(PersonDAO personDao) {
		this.personDao = personDao;
	}

	public void addPerson(String firstName, String lastName) {
		Person person = new Person();
		person.setFirstName(firstName);
		person.setLastName(lastName);
		this.personDao.addPerson(person);
	}

	public void addPerson(Person person) {
		this.personDao.addPerson(person);
	}

	public List<Person> getAllPersons() {
		return this.personDao.getAllPersons();
	}

	public PersonDAO getPersonDAO() {
		return personDao;
	}

	public void setPersonDAO(PersonDAO personDAO) {
		this.personDao = personDAO;
	}

	public List<Person> getPersonsByOrgID(String orgID) {
		return this.personDao.getPersonsByOrgID(orgID);
	}

	public List<Person> getPersonsByUserName(String userName) {
		return this.personDao.getPersonsByUserName(userName);
	}

	public Person getPersonsByID(long id) {
		return this.personDao.getPersonsByID(id);
	}

}
