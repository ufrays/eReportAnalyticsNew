package org.sap.era.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

import org.sap.era.persistence.Orgnazition;
import org.sap.era.persistence.TableGroupAssignment;

public class TableGroupAssignmentDAO {

	private EntityManagerFactory entityManagerFactory;

	public EntityManagerFactory getEntityManagerFactory() {
		return entityManagerFactory;
	}

	public void setEntityManagerFactory(
			EntityManagerFactory entityManagerFactory) {
		this.entityManagerFactory = entityManagerFactory;
	}
	
	//
	public List<TableGroupAssignment> getAllTableGroupAssignments(){
		EntityManager em = entityManagerFactory.createEntityManager();
		try {
			List<TableGroupAssignment> tableGroupAssignments = em.createNamedQuery("AllTableGroupAssignments",TableGroupAssignment.class).getResultList();
			return tableGroupAssignments;
		} finally {
			if (em != null) {
				em.close();
			}
		}
		
	}
	
	//
	public TableGroupAssignment getTableGroupAssignmentByID(long ID){
		EntityManager em = entityManagerFactory.createEntityManager();
		try {
			List<TableGroupAssignment> tableGroupAssignments;
			Query query = em.createNamedQuery("GetTableGroupAssignmentByID",TableGroupAssignment.class);
			query.setParameter("assignmentID", ID);
			tableGroupAssignments = query.getResultList();
			if (tableGroupAssignments!=null && tableGroupAssignments.size()>0){
				return tableGroupAssignments.get(0);
			}else{
				return null;
			}
		} finally {
			if (em != null) {
				em.close();
			}
		}
		
	}
	
	//
	public void addTableGroupAssignment(TableGroupAssignment tableGroupAssignment){
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
	
	//
	public List<Orgnazition> getUnselectedOrgnazition(long tableGroupAssignmentID){
		EntityManager em = entityManagerFactory.createEntityManager();
		Query query = em.createQuery("select org from Orgnazition org where org.id is not in" +
				" (select ao.orgnaztion.id from AssginedOrgnazition ao where ao.tableGroupAssignment.id=:assignmentID)");
		query.setParameter("assignmentID", tableGroupAssignmentID);
		List<Orgnazition> orgList = query.getResultList();
		return orgList;
	}
	
	
}
