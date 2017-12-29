package Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import Model.DAO.DepartmentDAO;
import Model.DTO.Department;

@Controller
public class MenuController {

	@Autowired
	DepartmentDAO departmentDAO;
	
	@RequestMapping(value = "/Menu", method = RequestMethod.GET)
	public ModelAndView init(@RequestParam("deptId") int deptId) {
		ModelAndView mav = new ModelAndView("menu");
		Department department = this.departmentDAO.findById(deptId);
		mav.addObject("department", department);
		return mav;
	}
}
