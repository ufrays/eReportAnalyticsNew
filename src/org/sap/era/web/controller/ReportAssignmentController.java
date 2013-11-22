package org.sap.era.web.controller;

import org.sap.era.persistence.TableGroupAssignment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller  
@RequestMapping  
public class ReportAssignmentController {

    @RequestMapping("reportAdmin/toAddReportAssignment")
    public ModelAndView toAddReportAssignment() {  
    	TableGroupAssignment ra = new TableGroupAssignment();
    	ra.setDescription("test");
    	return new ModelAndView("/reportAdmin/AddReportAssignment","ra",ra);
    }
	
	
}
