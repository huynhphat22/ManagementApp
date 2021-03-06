package Model.DAO;

import Model.DTO.OrderDetail;
import Model.DTO.OrderDetailId;

public interface OrderDetailDAO {

public OrderDetail save(OrderDetail orderDetail);
	
	public OrderDetail update(OrderDetail orderDetail);
	
	public void delete(OrderDetailId id);
	
	public OrderDetail findById(OrderDetailId id);
	
	public Iterable<OrderDetail> findAll();
}
