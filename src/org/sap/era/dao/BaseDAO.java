package org.sap.era.dao;

import java.util.List;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.LockModeType;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

import org.springframework.beans.factory.annotation.Autowired;

//@Repository(value = "baseDAO")
public class BaseDAO<TEntity, Tid> {

	@Autowired
	@Resource(name = "entity-manager-factory")
	protected EntityManagerFactory entityManagerFactory;

	/**
	 * 
	 * @author I071053
	 * @return
	 */
	public EntityManagerFactory getEntityManagerFactory() {
		return entityManagerFactory;
	}

	/**
	 * 
	 * @author I071053
	 * @param emf
	 */
	public void setEntityManagerFactory(EntityManagerFactory emf) {
		this.entityManagerFactory = emf;
	}

	/**
	 * 
	 * @author I071053
	 * @param entity
	 * @return
	 */
	public TEntity persist(final TEntity entity) {
		return new TransactionWrapper<TEntity>() {
			@Override
			protected TEntity runAsTransactional(EntityManager em) {
				em.persist(entity);
				return entity;
			}
		}.run();
	}

	/**
	 * 
	 * @author I071053
	 * @param entities
	 * @return
	 */
	public <TCollection extends List<TEntity>> TCollection persist(final TCollection entities) {
		if (entities == null || entities.size() == 0) {
			// TODO: Throw exception if necessary
			return entities;
		}
		return new TransactionWrapper<TCollection>() {
			@Override
			protected TCollection runAsTransactional(EntityManager em) {
				int entitiesCount = entities.size();
				for (int idx = 0; idx < entitiesCount; idx++) {
					TEntity entity = entities.get(idx);
					em.persist(entity);
				}
				return entities;
			}
		}.run();
	}

	/**
	 * 
	 * @author I071053
	 * @param entity
	 * @return
	 */
	public TEntity merge(final TEntity entity) {
		return new TransactionWrapper<TEntity>() {
			@Override
			protected TEntity runAsTransactional(EntityManager em) {
				return em.merge(entity);
			}
		}.run();
	}

	/**
	 * 
	 * @author I071053
	 * @param entities
	 * @return
	 */
	public <TCollection extends List<TEntity>> TCollection merge(final TCollection entities) {
		if (entities == null || entities.size() == 0) {
			// TODO: Throw exception if necessary
			return entities;
		}
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

	/**
	 * 
	 * @author I071053
	 * @param entity
	 */
	public void delete(final TEntity entity) {
		new TransactionWrapper<Void>() {
			@Override
			protected Void runAsTransactional(EntityManager em) {
				em.remove(entity);
				return null;
			}
		}.run();
	}

	/**
	 * 
	 * @author I071053
	 * @param id
	 * @param entityType
	 */
	public void delete(final Tid id, final Class<TEntity> entityType) {
		new TransactionWrapper<Void>() {
			@Override
			protected Void runAsTransactional(EntityManager em) {
				TEntity entity = em.find(entityType, id, LockModeType.PESSIMISTIC_WRITE);
				if (entity != null) {
					em.remove(entity);
					return null;
				} else {
					// TODO: Throw exception if necessary
					return null;
				}
			}
		}.run();
	}

	/**
	 * 
	 * @author I071053
	 * @param entityType
	 * @param id
	 * @param lockModeType
	 * @return
	 */
	public TEntity retrieve(final Class<TEntity> entityType, final Tid id, final LockModeType lockModeType) {
		return new TransactionWrapper<TEntity>() {
			@Override
			protected TEntity runAsTransactional(EntityManager em) {
				TEntity entity = em.find(entityType, id, lockModeType == null ? LockModeType.PESSIMISTIC_READ : lockModeType);
				return entity;
			}
		}.run();
	}

	/**
	 * 
	 * @author I071053
	 * @param entityType
	 * @param id
	 */
	public TEntity retrieve(final Class<TEntity> entityType, final Tid id) {
		return retrieve(entityType, id, LockModeType.PESSIMISTIC_READ);
	}

	/**
	 * 
	 * @author I071053
	 * @param entityType
	 * @return
	 */
	public List<TEntity> retrieve(final Class<TEntity> entityType) {
		return new TransactionWrapper<List<TEntity>>() {
			@Override
			protected List<TEntity> runAsTransactional(EntityManager em) {
				CriteriaBuilder cb = em.getCriteriaBuilder();
				CriteriaQuery<TEntity> query = cb.createQuery(entityType);
				List<TEntity> allEntities = em.createQuery(query).getResultList();
				return allEntities;
			}
		}.run();
	}

	/**
	 * The wrapper that embed the action as transaction supported
	 * 
	 * @author I071053
	 * 
	 * @param <TResult>
	 */
	protected abstract class TransactionWrapper<TResult> {
		/**
		 * 
		 * @return
		 */
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

		/**
		 * 
		 * @param em
		 * @return
		 */
		protected abstract TResult runAsTransactional(EntityManager em);
	}

}
