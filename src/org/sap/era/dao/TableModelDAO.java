package org.sap.era.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.sap.era.persistence.TableModel;
import org.springframework.stereotype.Repository;

@Repository(value = "tableModelDAO")
public class TableModelDAO extends BaseDAO<TableModel, Long> {

	public List<TableModel> getAllTableModelsByTableGroupModel(String tableGroupModelID) {
		EntityManager em = entityManagerFactory.createEntityManager();
		try {
			TypedQuery<TableModel> query = em.createNamedQuery("AllTableModelsByTableGroupModel", TableModel.class);
			query.setParameter("tableGroupModelID", Long.parseLong(tableGroupModelID));
			List<TableModel> tableModelList = query.getResultList();
			return tableModelList;
		} finally {
			if (em != null) {
				em.close();
			}
		}
	}

	public void addTableModel(TableModel tableModel) {
		EntityManager em = entityManagerFactory.createEntityManager();
		try {
			em.getTransaction().begin();
			em.persist(tableModel);
			em.getTransaction().commit();
		} finally {
			if (em != null) {
				em.close();
			}
		}
	}

}
