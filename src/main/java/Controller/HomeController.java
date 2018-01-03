package Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {
	@RequestMapping(value = "/Home", method = RequestMethod.GET)
	public ModelAndView init() {
		return new ModelAndView("home");
	}
}
