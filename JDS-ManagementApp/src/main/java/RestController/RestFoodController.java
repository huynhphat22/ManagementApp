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

import Model.DAO.FoodDAO;
import Model.DTO.Food;
import Model.MODEL.Page;
import Model.MODEL.PageQuery;

@RestController
public class RestFoodController {

	@Autowired
	private FoodDAO foodDAO;
	
	
	@RequestMapping(value="/api/food", method = RequestMethod.GET,  produces =  MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Iterable<Food>> findAll(){
		try {
			return new ResponseEntity<>(this.foodDAO.findAll(), HttpStatus.OK);
		}
		catch(Exception e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@RequestMapping(value="/api/food", method = RequestMethod.POST)
	public ResponseEntity<Void> save(@RequestBody Food food){
		try {
			this.foodDAO.save(food);
		}
		catch(Exception e) {
			return new ResponseEntity<>(HttpStatus.CONFLICT);
		}
		return new ResponseEntity<>(HttpStatus.CREATED);
	}
	
	@RequestMapping(value="/api/food", method = RequestMethod.PUT)
	public ResponseEntity<Void> update(@RequestBody Food food){
		if(this.foodDAO.findById(food.getFoodId()) == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		try {
			this.foodDAO.update(food);
		}
		catch(Exception e) {
			return new ResponseEntity<>(HttpStatus.CONFLICT);
		}
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@RequestMapping(value="/api/food/{id}", method = RequestMethod.GET)
	public ResponseEntity<Food> findById(@PathVariable int id){
		try {
			return new ResponseEntity<>(this.foodDAO.findById(id), HttpStatus.OK);
		}
		catch(Exception e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	
	@RequestMapping(value="/api/food/pagination", method = RequestMethod.POST)
	public ResponseEntity<Page> paginate(@RequestBody PageQuery pageQuery){
		try {
			return new ResponseEntity<>(this.foodDAO.paginateFood(pageQuery), HttpStatus.OK);
		}
		catch(Exception e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	
}
