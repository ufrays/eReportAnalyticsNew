package org.sap.era.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.sap.era.persistence.Person;
import org.springframework.stereotype.Repository;

@Repository(value = "personDAO")
public class PersonDAO extends BaseDAO<Person, Long> {

	public List<Person> getAllPersons() {
		EntityManager em = entityManagerFactory.createEntityManager();
		try {
			List<Person> persons = em.createNamedQuery("AllPersons", Person.class).getResultList();
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
			TypedQuery<Person> query = em.createNamedQuery("PersonsByOrgID", Person.class);
			query.setParameter("orgID", Long.parseLong(orgID));
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
			TypedQuery<Person> query = em.createNamedQuery("PersonsByUserName", Person.class);
			query.setParameter("userName", userName);
			List<Person> persons = query.getResultList();
			return persons;
		} finally {
			if (em != null) {
				em.close();
			}
		}
	}

	public Person getPersonsByID(long id) {
		EntityManager em = entityManagerFactory.createEntityManager();
		try {
			TypedQuery<Person> query = em.createNamedQuery("PersonsByID", Person.class);
			query.setParameter("id", id);
			List<Person> persons = query.getResultList();
			return persons.get(0);
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
