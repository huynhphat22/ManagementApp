package Model.DAO;

import Model.DTO.MonthCost;
import Model.MODEL.Page;
import Model.MODEL.PageQuery;

public interface MonthCostDAO {

public MonthCost save(MonthCost monthCost);
	
	public MonthCost update(MonthCost monthCost);
	
	public void delete(int id);
	
	public MonthCost findById(int id);
	
	public Iterable<MonthCost> findAll();
	
	public Page paginateMonthCost(PageQuery pageQuery, int departmentId);
}
