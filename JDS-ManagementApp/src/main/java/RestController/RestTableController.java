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

import Model.DAO.RestaurantTableDAO;
import Model.DTO.RestaurantTable;
import Model.MODEL.Page;
import Model.MODEL.PageQuery;

@RestController
public class RestTableController {

	@Autowired
	private RestaurantTableDAO restaurantTableDAO;
	
	
	@RequestMapping(value="/api/restaurantTable", method = RequestMethod.GET,  produces =  MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Iterable<RestaurantTable>> findAll(){
		try {
			return new ResponseEntity<>(this.restaurantTableDAO.findAll(), HttpStatus.OK);
		}
		catch(Exception e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@RequestMapping(value="/api/restaurantTable", method = RequestMethod.POST)
	public ResponseEntity<Void> save(@RequestBody RestaurantTable restaurantTable){
		try {
			this.restaurantTableDAO.save(restaurantTable);
		}
		catch(Exception e) {
			return new ResponseEntity<>(HttpStatus.CONFLICT);
		}
		return new ResponseEntity<>(HttpStatus.CREATED);
	}
	
	@RequestMapping(value="/api/restaurantTable", method = RequestMethod.PUT)
	public ResponseEntity<Void> update(@RequestBody RestaurantTable restaurantTable){
		if(this.restaurantTableDAO.findById(restaurantTable.getTableId()) == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		try {
			this.restaurantTableDAO.update(restaurantTable);
		}
		catch(Exception e) {
			return new ResponseEntity<>(HttpStatus.CONFLICT);
		}
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@RequestMapping(value="/api/restaurantTable/{id}", method = RequestMethod.GET)
	public ResponseEntity<RestaurantTable> findById(@PathVariable int id){
		try {
			return new ResponseEntity<>(this.restaurantTableDAO.findById(id), HttpStatus.OK);
		}
		catch(Exception e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	
	@RequestMapping(value="/api/restaurantTable/pagination/{departmentId}", method = RequestMethod.POST)
	public ResponseEntity<Page> paginate(@RequestBody PageQuery pageQuery, @PathVariable("departmentId") int departmentId ){
		try {
			return new ResponseEntity<>(this.restaurantTableDAO.paginateRestaurantTable(pageQuery, departmentId), HttpStatus.OK);
		}
		catch(Exception e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
}
