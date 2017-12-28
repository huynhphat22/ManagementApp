package Model.DAO;

import Model.DTO.Staff;

public interface StaffDAO {

public Staff save(Staff staff);
	
	public Staff update(Staff staff);
	
	public void delete(int id);
	
	public Staff findById(int id);
	
	public Iterable<Staff> findAll();
}
