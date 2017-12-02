package Model.DAO;

import Model.DTO.DateRevenue;
import Model.DTO.DateRevenueId;

public interface DateRevenueDAO {

public DateRevenue save(DateRevenue dateRevenue);
	
	public DateRevenue update(DateRevenue dateRevenue);
	
	public void delete(DateRevenueId id);
	
	public DateRevenue findById(DateRevenueId id);
	
	public Iterable<DateRevenue> findAll();
}
