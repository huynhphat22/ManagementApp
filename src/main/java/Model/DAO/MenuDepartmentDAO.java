package Model.DAO;

import Model.DTO.MenuDepartment;
import Model.DTO.MenuDepartmentId;
import Model.MODEL.Page;
import Model.MODEL.PageQuery;

public interface MenuDepartmentDAO {
	
	public MenuDepartment save(MenuDepartment menuDepartment);

	public MenuDepartment update(MenuDepartment menuDepartment);

	public void delete(MenuDepartmentId id);

	public MenuDepartment findById(MenuDepartmentId id);

	public Iterable<MenuDepartment> findAll();

	
	public Page paginateMenuDepartment(PageQuery pageQuery, int departmentId);
}
