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

import Model.DAO.MenuDepartmentDAO;
import Model.DTO.MenuDepartment;
import Model.DTO.MenuDepartmentId;
import Model.MODEL.Page;
import Model.MODEL.PageQuery;

@RestController
public class RestMenuDepartmentController {

	@Autowired
	private MenuDepartmentDAO menuDepartmentDAO;
	
	
	@RequestMapping(value="/api/menuDepartment", method = RequestMethod.GET,  produces =  MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Iterable<MenuDepartment>> findAll(){
		try {
			return new ResponseEntity<>(this.menuDepartmentDAO.findAll(), HttpStatus.OK);
		}
		catch(Exception e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@RequestMapping(value="/api/menuDepartment", method = RequestMethod.POST)
	public ResponseEntity<Void> save(@RequestBody MenuDepartment menuDepartment){
		try {
			this.menuDepartmentDAO.save(menuDepartment);
		}
		catch(Exception e) {
			return new ResponseEntity<>(HttpStatus.CONFLICT);
		}
		return new ResponseEntity<>(HttpStatus.CREATED);
	}
	
	@RequestMapping(value="/api/menuDepartment", method = RequestMethod.PUT)
	public ResponseEntity<Void> update(@RequestBody MenuDepartment menuDepartment){
		if(this.menuDepartmentDAO.findById(menuDepartment.getId()) == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		try {
			this.menuDepartmentDAO.update(menuDepartment);
		}
		catch(Exception e) {
			return new ResponseEntity<>(HttpStatus.CONFLICT);
		}
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@RequestMapping(value="/api/menuDepartment/id", method = RequestMethod.POST)
	public ResponseEntity<MenuDepartment> findById(@RequestBody MenuDepartmentId id){
		try {
			return new ResponseEntity<>(this.menuDepartmentDAO.findById(id), HttpStatus.OK);
		}
		catch(Exception e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	
	@RequestMapping(value="/api/menuDepartment/pagination/{departmentId}", method = RequestMethod.POST)
	public ResponseEntity<Page> paginate(@RequestBody PageQuery pageQuery, @PathVariable("departmentId") int departmentId ){
		try {
			return new ResponseEntity<>(this.menuDepartmentDAO.paginateMenuDepartment(pageQuery, departmentId), HttpStatus.OK);
		}
		catch(Exception e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	
}
