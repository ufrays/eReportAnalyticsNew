package org.sap.era.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import org.sap.era.persistence.TableModel;

public class TableModelDAO {

	private EntityManagerFactory entityManagerFactory;

	public EntityManagerFactory getEntityManagerFactory() {
		return entityManagerFactory;
	}

	public void setEntityManagerFactory(EntityManagerFactory entityManagerFactory) {
		this.entityManagerFactory = entityManagerFactory;
	}

	public List<TableModel> getAllTableModelsByTableGroupModel(String tableGroupModelID) {
		EntityManager em = entityManagerFactory.createEntityManager();
		try {
			Query query = em.createNamedQuery("AllTableModelsByTableGroupModel", TableModel.class);
			query.setParameter("tableGroupModelID", tableGroupModelID);
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
