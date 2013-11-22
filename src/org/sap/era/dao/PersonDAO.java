package org.sap.era.dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

import org.sap.era.persistence.Orgnazition;
import org.sap.era.persistence.Person;

public class PersonDAO {
	private EntityManagerFactory entityManagerFactory;

	public EntityManagerFactory getEntityManagerFactory() {
		return entityManagerFactory;
	}

	public void setEntityManagerFactory(
			EntityManagerFactory entityManagerFactory) {
		this.entityManagerFactory = entityManagerFactory;
	}

	public List<Person> getAllPersons() {
		EntityManager em = entityManagerFactory.createEntityManager();
		try {
			List<Person> persons = em.createNamedQuery("AllPersons",Person.class).getResultList();
			return persons;
		} finally {
			if (em != null) {
				em.close();
			}
		}
	}
	
	public List<Person> getPersonsByOrgID(String orgID) {
		EntityManager em = entityManagerFactory.createEntityManager();
		try {
			Query query = em.createNamedQuery("PersonsByOrgID",Person.class);
			query.setParameter("orgID", Long.parseLong(orgID)) ;
			List<Person> persons = query.getResultList();
			return persons;
		} finally {
			if (em != null) {
				em.close();
			}
		}
	}

	public List<Person> getPersonsByUserName(String userName) {
		EntityManager em = entityManagerFactory.createEntityManager();
		try {
			Query query = em.createNamedQuery("PersonsByUserName",Person.class);
			query.setParameter("userName", userName) ;
			List<Person> persons = query.getResultList();
			return persons;
		} finally {
			if (em != null) {
				em.close();
			}
		}
	}
	
	public void addPerson(Person person) {
		EntityManager em = entityManagerFactory.createEntityManager();
		try {
			em.getTransaction().begin();
			em.persist(person);
			em.getTransaction().commit();
		} finally {
			if (em != null) {
				em.close();
			}
		}
	}
}
