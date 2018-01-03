package RestController;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import Model.DAO.DateCostDAO;
import Model.DAO.StaffDAO;
import Model.DTO.DateCost;
import Model.DTO.DateCostId;
import Model.MODEL.Page;
import Model.MODEL.PageQuery;

@RestController
public class RestDateCostController {

	@Autowired
	private DateCostDAO dateCostDAO;

	@Autowired
	private StaffDAO staffDAO;

	@RequestMapping(value = "/api/dateCost", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Iterable<DateCost>> findAll() {
		try {
			return new ResponseEntity<>(this.dateCostDAO.findAll(), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@RequestMapping(value = "/api/dateCost", method = RequestMethod.POST)
	public ResponseEntity<Void> save(@RequestBody DateCost dateCost) {
		
		try {
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			String username = authentication.getName();
			System.out.println(username);
			dateCost.setId(new DateCostId(this.staffDAO.findByUsername(username).getDepartmentId(), new Date()));
			this.dateCostDAO.save(dateCost);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.CONFLICT);
		}
		return new ResponseEntity<>(HttpStatus.CREATED);
	}

	@RequestMapping(value = "/api/dateCost", method = RequestMethod.PUT)
	public ResponseEntity<Void> update(@RequestBody DateCost dateCost) {
		
		DateCost dc = this.dateCostDAO.findById(dateCost.getId());
		if ( dc == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		try {
			dateCost.setDateCreated(dc.getDateCreated());
			dateCost.setId(dc.getId());
			this.dateCostDAO.update(dateCost);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.CONFLICT);
		}
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@RequestMapping(value = "/api/dateCost/id", method = RequestMethod.POST)
	public ResponseEntity<DateCost> findById(@RequestBody DateCostId id) {
		try {
			return new ResponseEntity<>(this.dateCostDAO.findById(id), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@RequestMapping(value = "/api/dateCost/department", method = RequestMethod.POST)
	public ResponseEntity<Page> paginate(@RequestBody PageQuery pageQuery) {
		
		try {
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			String username = authentication.getName();
			System.out.println(username);
			int departmentId = this.staffDAO.findByUsername(username).getDepartmentId();
			return new ResponseEntity<>(this.dateCostDAO.paginateDateCost(pageQuery, departmentId), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

}
