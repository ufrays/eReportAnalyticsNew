package org.sap.era.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.sap.era.persistence.ReportTaskItem;
import org.springframework.stereotype.Repository;

@Repository(value = "reportTaskItemDAO")
public class ReportTaskItemDAO extends BaseDAO<ReportTaskItem, Long> {

	public List<ReportTaskItem> getAllReportTaskItem() {
		EntityManager em = entityManagerFactory.createEntityManager();
		try {
			List<ReportTaskItem> reportTasks = em.createNamedQuery("GetAllReportTaskItems", ReportTaskItem.class).getResultList();
			return reportTasks;
		} finally {
			if (em != null) {
				em.close();
			}
		}
	}

	public List<ReportTaskItem> GetReportTaskByOrgID(String orgID) {
		EntityManager em = entityManagerFactory.createEntityManager();
		try {
			TypedQuery<ReportTaskItem> query = em.createNamedQuery("GetReportTaskItemByOrgID", ReportTaskItem.class);
			query.setParameter("orgID", Long.parseLong(orgID));
			return query.getResultList();
		} finally {
			if (em != null)
				em.close();
		}
	}
}
