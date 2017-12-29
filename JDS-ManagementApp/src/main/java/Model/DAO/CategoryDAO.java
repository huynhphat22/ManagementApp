package Model.DAO;

import Model.DTO.Category;
import Model.MODEL.Page;
import Model.MODEL.PageQuery;

public interface CategoryDAO {
	
	public Category save(Category category);
	
	public Category update(Category category);
	
	public void delete(int id);
	
	public Category findById(int id);
	
	public Iterable<Category> findAll();
	
	public long count();
	
	public Iterable<Category> findByDepartmentId(int departmentId);
	
	public Page paginateCategory(PageQuery pageQuery);
}
