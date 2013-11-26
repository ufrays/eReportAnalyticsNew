package org.sap.era.service;

import org.sap.era.dao.PersonDAO;

import java.util.List;

import org.sap.era.persistence.Person;

public class PersonService {

	private PersonDAO personDAO;

	public void addPerson(String firstName, String lastName) {
		Person person = new Person();
		person.setFirstName(firstName);
		person.setLastName(lastName);
		this.personDAO.addPerson(person);
	}

	public void addPerson(Person person) {
		this.personDAO.addPerson(person);
	}

	public List<Person> getAllPersons() {
		return this.personDAO.getAllPersons();
	}

	public PersonDAO getPersonDAO() {
		return personDAO;
	}

	public void setPersonDAO(PersonDAO personDAO) {
		this.personDAO = personDAO;
	}

	public List<Person> getPersonsByOrgID(String orgID) {
		return this.personDAO.getPersonsByOrgID(orgID);
	}

	public List<Person> getPersonsByUserName(String userName) {
		return this.personDAO.getPersonsByUserName(userName);
	}

}
