package Model.DAO;

import Model.DTO.DateCost;
import Model.DTO.DateCostId;

public interface DateCostDAO {

public DateCost save(DateCost dateCost);
	
	public DateCost update(DateCost dateCost);
	
	public void delete(DateCostId id);
	
	public DateCost findById(DateCostId id);
	
	public Iterable<DateCost> findAll();
}
