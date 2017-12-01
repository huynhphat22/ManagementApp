package Model.DAO;

import Model.DTO.MenuDepartment;
import Model.DTO.MenuDepartmentId;

public interface MenuDepartmentDAO {
	
	public MenuDepartment save(MenuDepartment menuDepartment);

	public MenuDepartment update(MenuDepartment menuDepartment);

	public void delete(MenuDepartmentId id);

	public MenuDepartment findById(MenuDepartmentId id);

	public Iterable<MenuDepartment> findAll();

	public long count();
	
	public long countByDepartmentIdAndCategoryId(int departmentId, int categoryId);
	
	public Iterable<MenuDepartment> findByDepartmentIdAndCategoryId(int departmentId, int categoryId, int page, String sort);
}
