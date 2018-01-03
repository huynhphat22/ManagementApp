package RestController;


import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import Model.DAO.MonthCostDAO;
import Model.DAO.StaffDAO;
import Model.DTO.MonthCost;
import Model.MODEL.Page;
import Model.MODEL.PageQuery;

@RestController
public class RestMonthCostController {

	@Autowired
	private MonthCostDAO monthCostDAO;
	
	@Autowired
	private StaffDAO staffDAO;
	
	
	@RequestMapping(value="/api/monthCost", method = RequestMethod.GET,  produces =  MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Iterable<MonthCost>> findAll(){
		try {
			return new ResponseEntity<>(this.monthCostDAO.findAll(), HttpStatus.OK);
		}
		catch(Exception e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@RequestMapping(value="/api/monthCost", method = RequestMethod.POST)
	public ResponseEntity<Void> save(@RequestBody MonthCost monthCost){
		
		try {
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			String username = authentication.getName();
			int departmentId = this.staffDAO.findByUsername(username).getDepartmentId();
			String date = new SimpleDateFormat("YYYY-MM").format(new Date());
			monthCost.setDepartmentId(departmentId);
			monthCost.setMonthOfCost(date);
			this.monthCostDAO.save(monthCost);
		}
		catch(Exception e) {
			return new ResponseEntity<>(HttpStatus.CONFLICT);
		}
		return new ResponseEntity<>(HttpStatus.CREATED);
	}
	
	@RequestMapping(value="/api/monthCost", method = RequestMethod.PUT)
	public ResponseEntity<Void> update(@RequestBody MonthCost monthCost){
		System.out.println("Z1");
		MonthCost md = this.monthCostDAO.findById(monthCost.getMonthCostId());
		if(md == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		try {
			System.out.println("Z3");
			monthCost.setDateCreated(md.getDateCreated());
			monthCost.setDepartmentId(md.getDepartmentId());
			monthCost.setMonthOfCost(md.getMonthOfCost());
			System.out.println("Z4");
			this.monthCostDAO.update(monthCost);
		}
		catch(Exception e) {
			System.out.println("Z5");
			return new ResponseEntity<>(HttpStatus.CONFLICT);
		}
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@RequestMapping(value="/api/monthCost/{id}", method = RequestMethod.GET)
	public ResponseEntity<MonthCost> findById(@PathVariable int id){
		try {
			return new ResponseEntity<>(this.monthCostDAO.findById(id), HttpStatus.OK);
		}
		catch(Exception e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	
	@RequestMapping(value="/api/monthCost/department", method = RequestMethod.POST)
	public ResponseEntity<Page> paginate(@RequestBody PageQuery pageQuery){
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String username = authentication.getName();
		System.out.println(username);
		try {
			int departmentId = this.staffDAO.findByUsername(username).getDepartmentId();
			return new ResponseEntity<>(this.monthCostDAO.paginateMonthCost(pageQuery, departmentId ), HttpStatus.OK);
		}
		catch(Exception e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	
}
