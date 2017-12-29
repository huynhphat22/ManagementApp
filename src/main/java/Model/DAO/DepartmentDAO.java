package Model.DAO;

import Model.DTO.Department;
import Model.MODEL.Page;
import Model.MODEL.PageQuery;

public interface DepartmentDAO {
	
	public Department save(Department department);
	
	public Department update(Department department);
	
	public void delete(int id);
	
	public Department findById(int id);
	
	public Iterable<Department> findAll();
	
	public Page paginateDepartment(PageQuery pageQuery);
	
}
