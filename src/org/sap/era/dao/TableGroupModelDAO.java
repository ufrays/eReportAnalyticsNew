package org.sap.era.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import org.sap.era.persistence.*;

public class TableGroupModelDAO {

	private EntityManagerFactory entityManagerFactory;

	public EntityManagerFactory getEntityManagerFactory() {
		return entityManagerFactory;
	}

	public void setEntityManagerFactory(EntityManagerFactory entityManagerFactory) {
		this.entityManagerFactory = entityManagerFactory;
	}

	public List<TableGroupModel> getAllTableGroupModels() {
		EntityManager em = entityManagerFactory.createEntityManager();
		try {
			Query query = em.createNamedQuery("AllTableGroupModels", TableGroupModel.class);
			List<TableGroupModel> tableGroupModelList = query.getResultList();
			return tableGroupModelList;
		} finally {
			if (em != null) {
				em.close();
			}
		}
	}

	public TableGroupModel getTableGroupModelByID(long groupID) {
		EntityManager em = entityManagerFactory.createEntityManager();
		try {
			Query query = em.createNamedQuery("GetTableGroupModelByID", TableGroupModel.class);
			query.setParameter("groupID", groupID);
			List<TableGroupModel> tableGroupModelList = query.getResultList();
			if (tableGroupModelList != null && tableGroupModelList.size() > 0) {
				return tableGroupModelList.get(0);
			} else {
				return null;
			}

		} finally {
			if (em != null) {
				em.close();
			}
		}
	}

	public void addTableGroupModel(TableGroupModel tableGroupModel) {
		EntityManager em = entityManagerFactory.createEntityManager();
		try {
			em.getTransaction().begin();
			em.persist(tableGroupModel);

			em.getTransaction().commit();
		} finally {
			if (em != null) {
				em.close();
			}
		}
	}
}
