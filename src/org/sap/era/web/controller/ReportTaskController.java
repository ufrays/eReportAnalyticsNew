package org.sap.era.web.controller;

import java.util.List;

import javax.annotation.Resource;

import org.sap.era.persistence.ReportTaskItem;
import org.sap.era.service.ReportTaskService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping
public class ReportTaskController {

	@Resource
	private ReportTaskService reportTaskService;

	@RequestMapping("/reportTask/getAllReportTaskItems")
	@ResponseBody
	public List<ReportTaskItem> GetAllReportTaskItem() {

		return reportTaskService.GetAllReportTaskItem();
	}

	@RequestMapping(value = "/reportTask/getAllReportTaskItemsByOrgID", method = RequestMethod.GET)
	@ResponseBody
	public List<ReportTaskItem> GetAllReportTaskItemsByOrgID(String orgID) {

		return reportTaskService.GetReportTaskItemByOrgID(orgID);
	}

}
