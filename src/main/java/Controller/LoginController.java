package Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LoginController {
	// Return to login page
	@RequestMapping(value = "/Login", method = RequestMethod.GET)
	ModelAndView login(@RequestParam(value = "error", required = false) String error,
			@RequestParam(value = "logout", required = false) String logout) {
		ModelAndView mav = new ModelAndView("login");
		if (error != null) {
			mav.addObject("error", "Invalid email or password");
		}
		if (logout != null) {
			mav.addObject("message", "You've been logged out successfully");
		}

		mav.addObject("title", "Login Page");
		return mav;
	}
}
