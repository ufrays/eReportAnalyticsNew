package org.sap.era.web.controller;

import java.util.List;

import javax.annotation.Resource;

import org.sap.era.dto.ReportTaskDTO;
import org.sap.era.persistence.ReportTask;
import org.sap.era.service.ReportTaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@RequestMapping
public class TaskScheduleController {

	
	@Autowired
	@Resource
	private ReportTaskService reportTaskService;
	
	@RequestMapping(value = "/createReportTask", method = RequestMethod.GET)
	@ResponseBody
	public String createReportTask(ReportTaskDTO dto){
		reportTaskService.createReportTask(dto);
		return "The report task was created!";
	}
	
	@RequestMapping(value = "/taskSchedule/getReportTaskListByDTO", method = RequestMethod.GET)
	@ResponseBody
	public List<ReportTask> getReportTaskListByDTO(ReportTaskDTO reportTaskDTO) {
		List<ReportTask> list = reportTaskService.getReportTaskListByDTO(reportTaskDTO);
		return list;
	}

	
}
