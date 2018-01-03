package Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class DepartmentController {

	@RequestMapping(value = "/Departments", method = RequestMethod.GET)
	public ModelAndView init() {
		return new ModelAndView("departments");
	}
}
