package pt.edge.performancescanner.controller;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class DashboardController {

	@RequestMapping(value = "/admin/users", method = RequestMethod.GET)
	public ModelAndView adminUsers() {
		ModelAndView model = new ModelAndView();
		model.setViewName("/AdminUserManage");
		return model;
	}
	
	@RequestMapping(value = "/admin/reports", method = RequestMethod.GET)
	public ModelAndView adminReports() {
		ModelAndView model = new ModelAndView();
		model.setViewName("/UsersReport");
		return model;
	}

	@RequestMapping(value = "/user/report", method = RequestMethod.GET)
	public ModelAndView userReport() {
		ModelAndView model = new ModelAndView();
		model.setViewName("/SingleReview");
		return model;
	}
	
	@RequestMapping(value = "/user/report/submit", method = RequestMethod.GET)
	public ModelAndView userSubmit() {
		ModelAndView model = new ModelAndView();
		model.setViewName("/Report");
		return model;
	}
	
	
	

	
}
