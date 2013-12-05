package org.sap.era.dao;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;

import org.sap.era.persistence.ReportTask;
import org.springframework.stereotype.Repository;

@Repository(value = "reportTaskDAO")
public class ReportTaskDAO extends BaseDAO<ReportTask, Long> {

	public List<ReportTask> getAllReportTaskItem() {
		EntityManager em = entityManagerFactory.createEntityManager();
		try {

			// @to do
			List<ReportTask> reportTasks = em.createNamedQuery("GetAllReportTaskItems", ReportTask.class).getResultList();
			return reportTasks;
		} finally {
			if (em != null) {
				em.close();
			}
		}
	}

	public Long createNewReportTask(String durationID, String durationDepict, String durationFlag, Date endDate, String reportMode, Date startDate,
			long tableGroupModel, String tableGroupModelName) {
		ReportTask newTask = new ReportTask();
		newTask.setDurationID(durationID);
		newTask.setDurationDepict(durationDepict);
		newTask.setDurationFlag(durationFlag);
		newTask.setEndDate(endDate);
		newTask.setReportMode(reportMode);
		newTask.setStartDate(startDate);
		newTask.setTableGroupModel(tableGroupModel);
		newTask.setTableGroupModelName(tableGroupModelName);

		EntityManager em = entityManagerFactory.createEntityManager();
		try {
			em.persist(newTask);
			return newTask.getId();
		} finally {
			if (em != null)
				em.close();
		}

	}

	public Long createNewReportTask(ReportTask reportTask) {
		if (reportTask != null) {
			EntityManager em = entityManagerFactory.createEntityManager();
			try {
				em.merge(reportTask);
				return reportTask.getId();
			} finally {
				if (em != null)
					em.close();
			}
		}
		return null;
	}
}
