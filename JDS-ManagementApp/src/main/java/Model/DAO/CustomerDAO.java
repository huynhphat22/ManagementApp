package Model.DAO;

import Model.DTO.Customer;

public interface CustomerDAO {

public Customer save(Customer customer);
	
	public Customer update(Customer customer);
	
	public void delete(int id);
	
	public Customer findById(int id);
	
	public Iterable<Customer> findAll();
}
