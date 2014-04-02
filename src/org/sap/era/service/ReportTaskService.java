package org.sap.era.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.sap.era.dao.OrgnazitionDAO;
import org.sap.era.dao.ReportTaskDAO;
import org.sap.era.dao.ReportTaskItemDAO;
import org.sap.era.dto.Constant;
import org.sap.era.dto.ReportTaskDTO;
import org.sap.era.persistence.Orgnazition;
import org.sap.era.persistence.ReportTask;
import org.sap.era.persistence.ReportTaskItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service(value = "reportTaskService")
public class ReportTaskService {

	@Autowired
	@Resource
	private ReportTaskItemDAO reportTaskItemDAO;

	@Autowired
	@Resource
	private OrgnazitionDAO orgnazitionDAO;

	@Autowired
	@Resource
	private ReportTaskDAO reportTaskDAO;

	public List<ReportTaskItem> GetAllReportTaskItem() {
		return reportTaskItemDAO.getAllReportTaskItem();
	}

	public List<ReportTaskItem> GetReportTaskItemByOrgID(String orgID) {
		return reportTaskItemDAO.GetReportTaskByOrgID(orgID);
	}

	public List<ReportTask> getReportTaskListByDTO(ReportTaskDTO dto) {
		return reportTaskDAO.getReportTaskListByDTO(dto);
	}

	/*
	 * create report task manually.
	 */
	public long createReportTask(ReportTaskDTO dto) {
		List<Orgnazition> listOrg;
		List<ReportTaskItem> itemList = new ArrayList();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		//ReportTask
		ReportTask reportTask = new ReportTask();
		reportTask.setDurationID(dto.getDurationID());
		reportTask.setDurationFlag(dto.getDurationFlag());
		reportTask.setTableGroupModel(dto.getTableGroupModel());
		reportTask.setTableGroupModelName(dto.getTableGroupModelName());
		reportTask.setStatus(Constant.REPORT_TASK_STATUS_NEW);
		try {
			String strStartDate = dto.getStartDate().substring(0,10);
			String strEndDate = dto.getEndDate().substring(0,10);
			reportTask.setStartDate( sdf.parse(strStartDate) );
			reportTask.setEndDate(sdf.parse(strEndDate) );
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		reportTask.setReportMode("");
		if (dto.getOrgnazitions().size() > 0) {
			listOrg = dto.getOrgnazitions();
			// itemList - ReportTaskItem
			ReportTaskItem item = new ReportTaskItem();
			for (int i = 0; i < listOrg.size(); i++) {
				item.setOrgnazition(listOrg.get(i));
				//item.setReportTask(reportTask);
				item.setItemStatus(Constant.REPORT_TASK_ITEM_STATUS_NEW);
				itemList.add(item);
			}
			reportTask.setReportTaskItem(itemList);
		}
		return reportTaskDAO.createNewReportTask(reportTask);
	}

}
