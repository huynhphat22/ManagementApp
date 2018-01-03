package Controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class FoodController {

	@RequestMapping(value = "/Foods", method = RequestMethod.GET)
	public ModelAndView init() {
		ModelAndView mav = new ModelAndView("foods");
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	    String name = auth.getName(); //get logged in username
	    mav.addObject("username", name);
		return mav;
	}
}
