package org.sap.era.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;

import org.sap.era.persistence.Orgnazition;
import org.sap.era.persistence.TableGroupAssignment;

public class TableGroupAssignmentDAO {

	private EntityManagerFactory entityManagerFactory;

	public EntityManagerFactory getEntityManagerFactory() {
		return entityManagerFactory;
	}

	public void setEntityManagerFactory(EntityManagerFactory entityManagerFactory) {
		this.entityManagerFactory = entityManagerFactory;
	}

	/**
	 * 
	 * @return
	 */
	public List<TableGroupAssignment> getAllTableGroupAssignments() {
		EntityManager em = entityManagerFactory.createEntityManager();
		try {
			List<TableGroupAssignment> tableGroupAssignments = em.createNamedQuery("AllTableGroupAssignments", TableGroupAssignment.class)
					.getResultList();
			return tableGroupAssignments;
		} finally {
			if (em != null) {
				em.close();
			}
		}

	}

	/**
	 * 
	 * @param id
	 * @return
	 */
	public TableGroupAssignment getTableGroupAssignmentByID(long id) {
		EntityManager em = entityManagerFactory.createEntityManager();
		try {
			List<TableGroupAssignment> tableGroupAssignments;
			TypedQuery<TableGroupAssignment> query = em.createNamedQuery("GetTableGroupAssignmentByID", TableGroupAssignment.class);
			query.setParameter("assignmentID", id);
			tableGroupAssignments = query.getResultList();
			if (tableGroupAssignments != null && tableGroupAssignments.size() > 0) {
				return tableGroupAssignments.get(0);
			} else {
				return null;
			}
		} finally {
			if (em != null) {
				em.close();
			}
		}

	}

	/**
	 * 
	 * @param tableGroupAssignment
	 */
	public void addTableGroupAssignment(TableGroupAssignment tableGroupAssignment) {
		EntityManager em = entityManagerFactory.createEntityManager();
		try {
			em.getTransaction().begin();
			em.persist(tableGroupAssignment);
			em.getTransaction().commit();
		} finally {
			if (em != null) {
				em.close();
			}
		}
	}

	/**
	 * 
	 * @param tableGroupAssignmentID
	 * @return
	 */
	public List<Orgnazition> getUnselectedOrgnazition(long tableGroupAssignmentID) {
		EntityManager em = entityManagerFactory.createEntityManager();
		TypedQuery<Orgnazition> query = em.createQuery("select org from Orgnazition org where org.id is not in"
				+ " (select ao.orgnaztion.id from AssginedOrgnazition ao where ao.tableGroupAssignment.id = :assignmentID)", Orgnazition.class);
		query.setParameter("assignmentID", tableGroupAssignmentID);
		List<Orgnazition> orgList = query.getResultList();
		return orgList;
	}

}
