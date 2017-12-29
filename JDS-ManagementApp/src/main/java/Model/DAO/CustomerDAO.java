package Model.DAO;

import Model.DTO.Customer;
import Model.MODEL.Page;
import Model.MODEL.PageQuery;

public interface CustomerDAO {
	public Customer save(Customer customer);
	
	public Customer update(Customer customer);
	
	public void delete(int id);
	
	public Customer findById(int id);
	
	public Iterable<Customer> findAll();
	
	public Customer findByPhoneNumber(String phoneNumber);
	
	public Page paginateCustomer(PageQuery pageQuery);
}
