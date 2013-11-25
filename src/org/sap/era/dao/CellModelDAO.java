package org.sap.era.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

import org.sap.era.persistence.CellModel;
import org.sap.era.persistence.Person;

public class CellModelDAO {

	private EntityManagerFactory entityManagerFactory;

	public EntityManagerFactory getEntityManagerFactory() {
		return entityManagerFactory;
	}

	public void setEntityManagerFactory(
			EntityManagerFactory entityManagerFactory) {
		this.entityManagerFactory = entityManagerFactory;
	}
	
	
	public List<CellModel> getAllCellModelsforLabelByTableModel(long tableModelID) {
		EntityManager em = entityManagerFactory.createEntityManager();
		try {
			Query query = em.createNamedQuery("AllCellModelsfoLabelByTableModel",CellModel.class);
			query.setParameter("tableModelID", tableModelID);
			List<CellModel> cellModelList = query.getResultList();
			return cellModelList;
		} finally {
			if (em != null) {
				em.close();
			}
		}
	}
	
	public List<CellModel> getAllCellModelsforParmByTableModel(long tableModelID) {
		EntityManager em = entityManagerFactory.createEntityManager();
		try {
			Query query = em.createNamedQuery("AllCellModelsforParmByTableModel",CellModel.class);
			query.setParameter("tableModelID", tableModelID);
			List<CellModel> cellModelList = query.getResultList();
			return cellModelList;
		} finally {
			if (em != null) {
				em.close();
			}
		}
	}
	
	public List<CellModel> getAllCellModelsforDataByTableModel(long tableModelID) {
		EntityManager em = entityManagerFactory.createEntityManager();
		try {
			Query query = em.createNamedQuery("AllCellModelsforDataByTableModel",CellModel.class);
			query.setParameter("tableModelID", tableModelID);
			List<CellModel> cellModelList = query.getResultList();
			return cellModelList;
		} finally {
			if (em != null) {
				em.close();
			}
		}
	}
	
	
	public List<CellModel> getAllCellModelsByTableModel(long tableModelID) {
		EntityManager em = entityManagerFactory.createEntityManager();
		try {
			Query query = em.createNamedQuery("AllCellModelsByTableModel",CellModel.class);
			query.setParameter("tableModelID", tableModelID);
			List<CellModel> cellModelList = query.getResultList();
			return cellModelList;
		} finally {
			if (em != null) {
				em.close();
			}
		}
	}
	
	public void addCellModel(CellModel cellModel) {
		EntityManager em = entityManagerFactory.createEntityManager();
		try {
			em.getTransaction().begin();
			em.persist(cellModel);
			em.getTransaction().commit();
		} finally {
			if (em != null) {
				em.close();
			}
		}
	}
	
}
