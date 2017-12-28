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

import Model.DAO.CategoryDAO;
import Model.DTO.Category;
import Model.MODEL.Page;
import Model.MODEL.PageQuery;

@RestController
public class RestCategoryController {

	@Autowired
	private CategoryDAO categoryDAO;
	
	
	@RequestMapping(value="/api/category", method = RequestMethod.GET,  produces =  MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Iterable<Category>> findAll(){
		try {
			return new ResponseEntity<>(this.categoryDAO.findAll(), HttpStatus.OK);
		}
		catch(Exception e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@RequestMapping(value="/api/category", method = RequestMethod.POST)
	public ResponseEntity<Void> save(@RequestBody Category category){
		try {
			this.categoryDAO.save(category);
		}
		catch(Exception e) {
			return new ResponseEntity<>(HttpStatus.CONFLICT);
		}
		return new ResponseEntity<>(HttpStatus.CREATED);
	}
	
	@RequestMapping(value="/api/category", method = RequestMethod.PUT)
	public ResponseEntity<Void> update(@RequestBody Category category){
		if(this.categoryDAO.findById(category.getCategoryId()) == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		try {
			this.categoryDAO.update(category);
		}
		catch(Exception e) {
			return new ResponseEntity<>(HttpStatus.CONFLICT);
		}
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@RequestMapping(value="/api/category/{id}", method = RequestMethod.GET)
	public ResponseEntity<Category> findById(@PathVariable int id){
		try {
			return new ResponseEntity<>(this.categoryDAO.findById(id), HttpStatus.OK);
		}
		catch(Exception e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	
	@RequestMapping(value="/api/category/pagination", method = RequestMethod.POST)
	public ResponseEntity<Page> paginate(@RequestBody PageQuery pageQuery){
		try {
			return new ResponseEntity<>(this.categoryDAO.paginateCategory(pageQuery), HttpStatus.OK);
		}
		catch(Exception e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	
}
