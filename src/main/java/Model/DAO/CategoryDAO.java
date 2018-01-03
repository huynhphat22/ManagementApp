package Model.DAO;

import Model.DTO.Category;

public interface CategoryDAO {
	
	public Category save(Category category);
	
	public Category update(Category category);
	
	public void delete(int id);
	
	public Category findById(int id);
	
	public Iterable<Category> findAll();
	
	public long count();
	
	public Iterable<Category> findByDepartmentId(int departmentId);
}
