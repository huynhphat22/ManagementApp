package RestController;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import Model.DAO.StaffDAO;
import Model.DTO.Staff;
import Model.MODEL.Page;
import Model.MODEL.PageQuery;

@RestController
public class RestStaffController {

	@Autowired
	private StaffDAO staffDAO;
	
	
	@RequestMapping(value="/api/staff", method = RequestMethod.GET,  produces =  MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Iterable<Staff>> findAll(){
		try {
			return new ResponseEntity<>(this.staffDAO.findAll(), HttpStatus.OK);
		}
		catch(Exception e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@RequestMapping(value="/api/staff", method = RequestMethod.POST)
	public ResponseEntity<Void> save(@RequestBody Staff staff){
		try {
			this.staffDAO.save(staff);
		}
		catch(Exception e) {
			return new ResponseEntity<>(HttpStatus.CONFLICT);
		}
		return new ResponseEntity<>(HttpStatus.CREATED);
	}
	
	@RequestMapping(value="/api/staff", method = RequestMethod.PUT)
	public ResponseEntity<Void> update(@RequestBody Staff staff){
		if(this.staffDAO.findById(staff.getStaffId()) == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		try {
			this.staffDAO.update(staff);
		}
		catch(Exception e) {
			return new ResponseEntity<>(HttpStatus.CONFLICT);
		}
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@RequestMapping(value="/api/staff/{id}", method = RequestMethod.GET)
	public ResponseEntity<Staff> findById(@PathVariable int id){
		try {
			return new ResponseEntity<>(this.staffDAO.findById(id), HttpStatus.OK);
		}
		catch(Exception e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	
	@RequestMapping(value="/api/staff/pagination", method = RequestMethod.POST)
	public ResponseEntity<Page> paginate(@RequestBody PageQuery pageQuery){
		try {
			return new ResponseEntity<>(this.staffDAO.paginateStaff(pageQuery), HttpStatus.OK);
		}
		catch(Exception e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	
}
