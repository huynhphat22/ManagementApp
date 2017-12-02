package Model.DAO;

import Model.DTO.OrderFood;

public interface OrderFoodDAO {

public OrderFood save(OrderFood orderFood);
	
	public OrderFood update(OrderFood orderFood);
	
	public void delete(int id);
	
	public OrderFood findById(int id);
	
	public Iterable<OrderFood> findAll();
}
