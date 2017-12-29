package Model.DAO;

import Model.DTO.RestaurantTable;
import Model.MODEL.Page;
import Model.MODEL.PageQuery;

public interface RestaurantTableDAO {

public RestaurantTable save(RestaurantTable restaurantTable);
	
	public RestaurantTable update(RestaurantTable restaurantTable);
	
	public void delete(int id);
	
	public RestaurantTable findById(int id);
	
	public Iterable<RestaurantTable> findAll();
	
	public Page paginateRestaurantTable(PageQuery pageQuery, int departmentId);
}
