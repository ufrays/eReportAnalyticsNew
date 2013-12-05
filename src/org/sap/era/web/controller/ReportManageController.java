package org.sap.era.web.controller;

import java.util.List;

import javax.annotation.Resource;

import org.sap.era.dto.ReportTaskDTO;
import org.sap.era.persistence.ReportTask;
import org.sap.era.persistence.ReportTaskItem;
import org.sap.era.service.ReportTaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping
public class ReportManageController {

	@Autowired
	@Resource
	private ReportTaskService reportTaskService;

	@RequestMapping(value = "/getReportTaskListByDTO", method = RequestMethod.GET)
	@ResponseBody
	public List<ReportTask> getReportTaskListByDTO(ReportTaskDTO reportTaskDTO) {
		List<ReportTask> list = reportTaskService.getReportTaskListByDTO(reportTaskDTO);
		return list;
	}

	@RequestMapping(value = "/getReportTaskList", method = RequestMethod.GET)
	@ResponseBody
	public List<ReportTask> getReportTaskList() {

		return null;
	}

	@RequestMapping(value = "/getReportTaskItemListByID", method = RequestMethod.GET)
	@ResponseBody
	public List<ReportTaskItem> getReportTaskItemListByID(String id) {

		return null;
	}

	@RequestMapping(value = "/getReportTaskItemByItemID", method = RequestMethod.GET)
	@ResponseBody
	public ReportTask getReportTaskItemByID(String itemID) {

		return null;
	}

}
