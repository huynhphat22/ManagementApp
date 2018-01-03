package Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import Model.DAO.StaffDAO;

@Controller
public class ProfileController {
	@Autowired
	private StaffDAO staffDAO;
	
	@RequestMapping(value = "/Profile", method = RequestMethod.GET)
	public ModelAndView init() {
		ModelAndView mav = new ModelAndView("profile");
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	    String name = auth.getName(); //get logged in username
	    mav.addObject("user", this.staffDAO.findByUsername(name));
	    mav.addObject("username", name);
		return mav;
	}
}
