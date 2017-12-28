package Model.DAO;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import Model.DTO.OrderDetail;
import Model.DTO.OrderDetailId;

public class OrderDetailDAOImpl implements OrderDetailDAO {
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public OrderDetail save(OrderDetail orderDetail) {
		// TODO Auto-generated method stub
		Session session = this.sessionFactory.getCurrentSession();
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
			session.delete(orderDetail);
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
	public Iterable<OrderDetail> findAll() {
		// TODO Auto-generated method stub
		Session session = this.sessionFactory.getCurrentSession();
		Iterable<OrderDetail> list = session.createQuery("From OrderDetail").list();
		return list ;
	}

}
