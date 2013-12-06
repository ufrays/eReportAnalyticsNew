package org.sap.era.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.sap.era.persistence.TableGroupModel;
import org.springframework.stereotype.Repository;

@Repository(value = "tableGroupModelDAO")
public class TableGroupModelDAO extends BaseDAO<TableGroupModel, Long> {

	public List<TableGroupModel> getAllTableGroupModels() {
		EntityManager em = entityManagerFactory.createEntityManager();
		try {
			TypedQuery<TableGroupModel> query = em.createNamedQuery("AllTableGroupModels", TableGroupModel.class);
			List<TableGroupModel> tableGroupModelList = query.getResultList();
			return tableGroupModelList;
		} finally {
			if (em != null) {
				em.close();
			}
		}
	}

	public List<TableGroupModel> getAllReleasedTableGroupModels() {
		EntityManager em = entityManagerFactory.createEntityManager();
		try {
			TypedQuery<TableGroupModel> query = em.createNamedQuery("AllReleasedTableGroupModel", TableGroupModel.class);
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
			TypedQuery<TableGroupModel> query = em.createNamedQuery("GetTableGroupModelByID", TableGroupModel.class);
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

	public void updateTableGroupModel(TableGroupModel tableGroupModel) {
		EntityManager em = entityManagerFactory.createEntityManager();
		try {
			em.getTransaction().begin();
			em.merge(tableGroupModel);

			em.getTransaction().commit();
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
