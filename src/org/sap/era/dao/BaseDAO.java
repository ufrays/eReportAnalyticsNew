package org.sap.era.dao;

import java.util.List;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository(value = "baseDAO")
public class BaseDAO<TEntity> {

	@Autowired
	@Resource(name = "entityManagerFactory")
	private EntityManagerFactory entityManagerFactory;

	public EntityManagerFactory getEntityManagerFactory() {
		return entityManagerFactory;
	}

	public void setEntityManagerFactory(EntityManagerFactory entityManagerFactory) {
		this.entityManagerFactory = entityManagerFactory;
	}

	public TEntity merge(final TEntity entity) {
		return new TransactionWrapper<TEntity>() {
			@Override
			protected TEntity runAsTransactional(EntityManager em) {
				return em.merge(entity);
			}
		}.run();
	}

	public <TCollection extends List<TEntity>> TCollection batchMerge(final TCollection entities) {
		return new TransactionWrapper<TCollection>() {
			@Override
			protected TCollection runAsTransactional(EntityManager em) {
				int entitiesCount = entities.size();
				for (int idx = 0; idx < entitiesCount; idx++) {
					TEntity entity = entities.get(idx);
					entity = em.merge(entity);
					entities.set(idx, entity);
				}
				return entities;
			}
		}.run();
	}

	protected abstract class TransactionWrapper<TResult> {

		@Autowired
		@Resource(name = "entityManagerFactory")
		private EntityManagerFactory entityManagerFactory;

		public TResult run() {
			EntityManager em = entityManagerFactory.createEntityManager();
			try {
				em.getTransaction().begin();
				TResult result = runAsTransactional(em);
				em.getTransaction().commit();
				return result;
			} finally {
				if (em != null) {
					em.close();
				}
			}
		}

		protected abstract TResult runAsTransactional(EntityManager em);
	}

}
