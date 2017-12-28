package Model.DAO;

import Model.DTO.MonthCost;
import Model.DTO.MonthCostId;

public interface MonthCostDAO {

public MonthCost save(MonthCost monthCost);
	
	public MonthCost update(MonthCost monthCost);
	
	public void delete(MonthCostId id);
	
	public MonthCost findById(MonthCostId id);
	
	public Iterable<MonthCost> findAll();
}
