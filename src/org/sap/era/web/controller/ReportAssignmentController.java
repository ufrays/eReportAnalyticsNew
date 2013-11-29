package org.sap.era.web.controller;

import java.util.List;

import javax.annotation.Resource;

import org.sap.era.persistence.TableGroupAssignment;
import org.sap.era.service.TableGroupAssignmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping()
public class ReportAssignmentController {

	@Autowired
	@Resource
	private TableGroupAssignmentService tgaService;

	public ReportAssignmentController() {
		// ApplicationContext context = new ClassPathXmlApplicationContext(new
		// String[] { "beans.xml" });
		// tgaService = context.getBean("tableGroupAssignmentService",
		// TableGroupAssignmentService.class);
	}

	@ResponseBody
	@RequestMapping("/reportAdmin/create")
	public ModelAndView toAddReportAssignment() {
		TableGroupAssignment ra = new TableGroupAssignment();
		ra.setDescription("test");
		return new ModelAndView("/reportAdmin/AddReportAssignment", "ra", ra);
	}

	@ResponseBody
	@RequestMapping("/report-assignment/get-all")
	public List<TableGroupAssignment> getAll() {
		return tgaService.getAllTableGroupAssignments();
	}
}
