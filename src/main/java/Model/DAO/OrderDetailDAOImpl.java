package Model.DAO;

import java.util.Date;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.transaction.annotation.Transactional;

import Model.DTO.OrderDetail;
import Model.DTO.OrderDetailId;

@Transactional
public class OrderDetailDAOImpl implements OrderDetailDAO {

	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	
	@Override
	public Iterable<OrderDetail> findAll() {
		// TODO Auto-generated method stub
		Session session = this.sessionFactory.getCurrentSession();
		Iterable<OrderDetail> list = session.createQuery("from OrderDetail").list();
		return list;
	}
	
	@Override
	public OrderDetail save(OrderDetail orderDetail) {
		// TODO Auto-generated method stub
		Session session = this.sessionFactory.getCurrentSession();
		orderDetail.setFlags(true);
		orderDetail.setDateCreated(new Date());
		session.persist(orderDetail);
		return orderDetail;
	}

	@Override
	public OrderDetail update(OrderDetail orderDetail) {
		// TODO Auto-generated method stub
		Session session = this.sessionFactory.getCurrentSession();
		session.persist(orderDetail);
		return orderDetail;
	}

	@Override
	public void delete(OrderDetailId id) {
		// TODO Auto-generated method stub
		
		OrderDetail orderDetail = this.findById(id);
		if(orderDetail != null) {
			Session session = this.sessionFactory.getCurrentSession();
			orderDetail.setFlags(false);
			session.update(orderDetail);
		}
		
	}

	@Override
	public OrderDetail findById(OrderDetailId id) {
		// TODO Auto-generated method stub
		Session session = this.sessionFactory.getCurrentSession();
		OrderDetail orderDetail = session.get(OrderDetail.class, id);
		return orderDetail;
	}
	@Override
	
	public Iterable<OrderDetail> findByOrderId(int orderId) {
		// TODO Auto-generated method stub
		Session session = this.sessionFactory.getCurrentSession();
		Query query = session.createQuery("SELECT od from OrderDetail od WHERE od.id.orderId = :orderId ");
		query.setParameter("orderId", orderId);
		Iterable<OrderDetail> list = query.list();
		return list;
	}
}
