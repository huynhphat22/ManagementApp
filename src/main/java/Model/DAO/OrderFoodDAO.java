package Model.DAO;

import Model.DTO.OrderFood;
import Model.MODEL.Page;
import Model.MODEL.PageQuery;

public interface OrderFoodDAO {

	public OrderFood save(OrderFood orderFood);
	
	public OrderFood update(OrderFood orderFood);
	
	public void delete(int id);
	
	public OrderFood findById(int id);
	
	public Iterable<OrderFood> findAll();
	
	public Page paginateOrderFood(PageQuery pageQuery);
	
}
