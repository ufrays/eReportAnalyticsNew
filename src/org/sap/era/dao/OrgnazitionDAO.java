package org.sap.era.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

import org.sap.era.persistence.Orgnazition;
import org.sap.era.persistence.Person;


public class OrgnazitionDAO {

	private EntityManagerFactory entityManagerFactory;

	public EntityManagerFactory getEntityManagerFactory() {
		return entityManagerFactory;
	}

	public void setEntityManagerFactory(
			EntityManagerFactory entityManagerFactory) {
		this.entityManagerFactory = entityManagerFactory;
	}
	
	//
	public List<Orgnazition> getAllOrgnazitions() {
		EntityManager em = entityManagerFactory.createEntityManager();
		try {
			List<Orgnazition> orgnazitions = em.createNamedQuery("AllOrgnazitions",Orgnazition.class).getResultList();
			return orgnazitions;
		} finally {
			if (em != null) {
				em.close();
			}
		}
		
		
	}
	
	public List<Orgnazition> getOrgnazitionsByParentID(String parentID){
		EntityManager em = entityManagerFactory.createEntityManager();
		try {
			Query query = em.createNamedQuery("OrgnazitionsByParentID",Orgnazition.class);
			query.setParameter("parentID", Integer.parseInt(parentID));
			List<Orgnazition> orgnazitions = query.getResultList();
			return orgnazitions;
		} finally {
			if (em != null) {
				em.close();
			}
		}
		
	}
	
	public Orgnazition getOrgnazitionByID(String ID){
		EntityManager em = entityManagerFactory.createEntityManager();
		try {
			Orgnazition org = em.find(Orgnazition.class, Long.parseLong(ID));
			return org;
		} finally {
			if (em != null) {
				em.close();
			}
		}
		
	}
	
	//OrgnazitionOfTop
	public List<Orgnazition> getOrgnazitionOfTop(){
		EntityManager em = entityManagerFactory.createEntityManager();
		try {
			Query query = em.createNamedQuery("OrgnazitionOfTop",Orgnazition.class);
			List<Orgnazition> orgnazitions = query.getResultList();
			return orgnazitions;
		} finally {
			if (em != null) {
				em.close();
			}
		}
		
	}
	
	public void addOrgnazition(Orgnazition orgnazition) {
		EntityManager em = entityManagerFactory.createEntityManager();
		try {
			em.getTransaction().begin();
			em.persist(orgnazition);
			em.getTransaction().commit();
		} finally {
			if (em != null) {
				em.close();
			}
		}
	}
	
	public void saveOrgnazition(Orgnazition orgnazition) {
		EntityManager em = entityManagerFactory.createEntityManager();
		try {
			em.getTransaction().begin();
			em.merge(orgnazition);
			em.getTransaction().commit();
		} finally {
			if (em != null) {
				em.close();
			}
		}
	}
	
	public void deleteOrgnazition(Orgnazition orgnazition){
		EntityManager em = entityManagerFactory.createEntityManager();
		try {
			em.getTransaction().begin();
			em.merge(orgnazition);
			em.remove(orgnazition);
			em.getTransaction().commit();
		} finally {
			if (em != null) {
				em.close();
			}
		}
	}

}
