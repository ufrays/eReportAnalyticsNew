package org.sap.era.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.sap.era.persistence.CellModel;
import org.springframework.stereotype.Repository;

@Repository(value = "cellModelDAO")
public class CellModelDAO extends BaseDAO<CellModel, Long> {

	public List<CellModel> getAllCellModelsforLabelByTableModel(long tableModelID) {
		EntityManager em = entityManagerFactory.createEntityManager();
		try {
			TypedQuery<CellModel> query = em.createNamedQuery("AllCellModelsfoLabelByTableModel", CellModel.class);
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
			TypedQuery<CellModel> query = em.createNamedQuery("AllCellModelsforParmByTableModel", CellModel.class);
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
			TypedQuery<CellModel> query = em.createNamedQuery("AllCellModelsforDataByTableModel", CellModel.class);
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
			TypedQuery<CellModel> query = em.createNamedQuery("AllCellModelsByTableModel", CellModel.class);
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
