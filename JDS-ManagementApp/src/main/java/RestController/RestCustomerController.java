
package RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import Model.DAO.CustomerDAO;
import Model.DTO.Customer;
import Model.MODEL.Page;
import Model.MODEL.PageQuery;
import Model.MODEL.PasswordChange;

@RestController
public class RestCustomerController {

	@Autowired
	private CustomerDAO customerDAO;
	
	
	@RequestMapping(value="/api/customer", method = RequestMethod.GET,  produces =  MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Iterable<Customer>> findAll(){
		try {
			return new ResponseEntity<>(this.customerDAO.findAll(), HttpStatus.OK);
		}
		catch(Exception e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@RequestMapping(value="/api/customer", method = RequestMethod.POST)
	public ResponseEntity<Void> save(@RequestBody Customer customer){
		System.out.println(customer.getPassword());
		String password = new BCryptPasswordEncoder().encode(customer.getPassword());
		customer.setPassword(password);
		try {
			this.customerDAO.save(customer);
		}
		catch(Exception e) {
			return new ResponseEntity<>(HttpStatus.CONFLICT);
		}
		return new ResponseEntity<>(HttpStatus.CREATED);
	}
	
	@RequestMapping(value="/api/customer/resetPassword/{id}", method = RequestMethod.GET)
	public ResponseEntity<Void> resetPassword(@PathVariable("id") int id ){
		Customer customer = this.customerDAO.findById(id);
		if(customer == null){
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		System.out.println("r2");
		BCryptPasswordEncoder bpe = new BCryptPasswordEncoder();		
		String password = bpe.encode("123456");
		customer.setPassword(password);
		try {
			this.customerDAO.update(customer);
		}
		catch(Exception e) {
			return new ResponseEntity<>(HttpStatus.CONFLICT);
		}
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@RequestMapping(value="/api/customer", method = RequestMethod.PUT)
	public ResponseEntity<Void> update(@RequestBody Customer customer){
		Customer currentCustomer = this.customerDAO.findById(customer.getCustomerId());
		if(currentCustomer == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		customer.setPassword(currentCustomer.getPassword());
		
		try {
			this.customerDAO.update(customer);
		}
		catch(Exception e) {
			return new ResponseEntity<>(HttpStatus.CONFLICT);
		}
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@RequestMapping(value="/api/customer/{id}", method = RequestMethod.GET)
	public ResponseEntity<Customer> findById(@PathVariable int id){
		try {
			return new ResponseEntity<>(this.customerDAO.findById(id), HttpStatus.OK);
		}
		catch(Exception e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@RequestMapping(value="/api/customer/pagination", method = RequestMethod.POST)
	public ResponseEntity<Page> paginate(@RequestBody PageQuery pageQuery){
		try {
			return new ResponseEntity<>(this.customerDAO.paginateCustomer(pageQuery), HttpStatus.OK);
		}
		catch(Exception e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	
}
	