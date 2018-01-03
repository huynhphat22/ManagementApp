package Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import Model.DAO.DepartmentDAO;

@Controller
public class TableController {
	@Autowired
	DepartmentDAO departmentDAO;
	
	@RequestMapping(value = "/Tables", method = RequestMethod.GET)
	public ModelAndView init(@RequestParam("deptId") int deptId) {
		ModelAndView mav = new ModelAndView("tables");
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	    String name = auth.getName(); //get logged in username
	    mav.addObject("username", name);
		return mav;
	}
}
