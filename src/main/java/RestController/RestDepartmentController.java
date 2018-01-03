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

import Model.DAO.DepartmentDAO;
import Model.DTO.Department;
import Model.MODEL.Page;
import Model.MODEL.PageQuery;

@RestController
public class RestDepartmentController {

	@Autowired
	private DepartmentDAO departmentDAO;
	
	
	@RequestMapping(value="/api/department", method = RequestMethod.GET,  produces =  MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Iterable<Department>> findAll(){
		try {
			return new ResponseEntity<>(this.departmentDAO.findAll(), HttpStatus.OK);
		}
		catch(Exception e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@RequestMapping(value="/api/department", method = RequestMethod.POST)
	public ResponseEntity<Void> save(@RequestBody Department department){
		try {
			this.departmentDAO.save(department);
		}
		catch(Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.CONFLICT);
		}
		return new ResponseEntity<>(HttpStatus.CREATED);
	}
	
	@RequestMapping(value="/api/department", method = RequestMethod.PUT)
	public ResponseEntity<Void> update(@RequestBody Department department){
		if(this.departmentDAO.findById(department.getDepartmentId()) == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		try {
			this.departmentDAO.update(department);
		}
		catch(Exception e) {
			return new ResponseEntity<>(HttpStatus.CONFLICT);
		}
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@RequestMapping(value="/api/department/{id}", method = RequestMethod.GET)
	public ResponseEntity<Department> findById(@PathVariable int id){
		try {
			return new ResponseEntity<>(this.departmentDAO.findById(id), HttpStatus.OK);
		}
		catch(Exception e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	
	@RequestMapping(value="/api/department/pagination", method = RequestMethod.POST)
	public ResponseEntity<Page> paginate(@RequestBody PageQuery pageQuery){
		try {
			return new ResponseEntity<>(this.departmentDAO.paginateDepartment(pageQuery), HttpStatus.OK);
		}
		catch(Exception e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	
}
