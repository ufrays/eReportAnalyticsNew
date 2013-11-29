package org.sap.era.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.sap.era.persistence.Orgnazition;
import org.springframework.stereotype.Repository;

@Repository(value = "orgnazitionDAO")
public class OrgnazitionDAO extends BaseDAO<Orgnazition, Long> {

	/**
	 * 
	 * @return
	 */
	public List<Orgnazition> getAllOrgnazitions() {
		EntityManager em = entityManagerFactory.createEntityManager();
		try {
			List<Orgnazition> orgnazitions = em.createNamedQuery("AllOrgnazitions", Orgnazition.class).getResultList();
			return orgnazitions;
		} finally {
			if (em != null) {
				em.close();
			}
		}

	}

	public List<Orgnazition> getOrgnazitionsByParentID(String parentID) {
		EntityManager em = entityManagerFactory.createEntityManager();
		try {
			TypedQuery<Orgnazition> query = em.createNamedQuery("OrgnazitionsByParentID", Orgnazition.class);
			query.setParameter("parentID", Integer.parseInt(parentID));
			List<Orgnazition> orgnazitions = query.getResultList();
			return orgnazitions;
		} finally {
			if (em != null) {
				em.close();
			}
		}

	}

	public Orgnazition getOrgnazitionByID(String ID) {
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

	// OrgnazitionOfTop
	public List<Orgnazition> getOrgnazitionOfTop() {
		EntityManager em = entityManagerFactory.createEntityManager();
		try {
			TypedQuery<Orgnazition> query = em.createNamedQuery("OrgnazitionOfTop", Orgnazition.class);
			List<Orgnazition> orgnazitions = query.getResultList();
			return orgnazitions;
		} finally {
			if (em != null) {
				em.close();
			}
		}

	}

	public Orgnazition mergeOrgnazition(Orgnazition orgnazition) {
		EntityManager em = entityManagerFactory.createEntityManager();
		try {
			em.getTransaction().begin();
			orgnazition = em.merge(orgnazition);
			em.getTransaction().commit();
			return orgnazition;
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

	public void deleteOrgnazition(Orgnazition orgnazition) {
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
