package Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ReportController {

	@RequestMapping(value="/Report/TotalValue")
	public ModelAndView initTotalValue(HttpServletRequest request,
					HttpServletResponse response){
		return new ModelAndView("totalRevenue");
	}
	
	@RequestMapping(value="/Report/TotalCost")
	public ModelAndView initTotalCost(HttpServletRequest request,
					HttpServletResponse response){
		return new ModelAndView("totalCost");
	}
	
							

}
