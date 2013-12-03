package org.sap.era.service;

import java.util.List;

import javax.annotation.Resource;

import org.sap.era.dao.ReportTaskItemDAO;
import org.sap.era.persistence.ReportTaskItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service(value = "reportTaskService")
public class ReportTaskService {

	@Autowired
	@Resource
	private ReportTaskItemDAO reportTaskItemDAO;

	public List<ReportTaskItem> GetAllReportTaskItem() {
		return reportTaskItemDAO.getAllReportTaskItem();
	}

	public List<ReportTaskItem> GetReportTaskItemByOrgID(String orgID) {
		return reportTaskItemDAO.GetReportTaskByOrgID(orgID);
	}

}
