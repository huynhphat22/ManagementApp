package Model.DAO;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import Model.DTO.OrderFood;

public class OrderFoodDAOImpl implements OrderFoodDAO {

	@Autowired
	private SessionFactory sessionFactory;
	

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public OrderFood save(OrderFood orderFood) {
		// TODO Auto-generated method stub
		Session session = this.sessionFactory.getCurrentSession();
		session.persist(orderFood);
		return orderFood;
	}

	@Override
	public OrderFood update(OrderFood orderFood) {
		// TODO Auto-generated method stub
		Session session = this.sessionFactory.getCurrentSession();
		session.update(orderFood);
		return orderFood;
	}

	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub
		Session session = this.sessionFactory.getCurrentSession();
		OrderFood orderFood = this.findById(id);
		if(orderFood != null) {
			session.delete(orderFood);
		}
	}

	@Override
	public OrderFood findById(int id) {
		// TODO Auto-generated method stub
		Session session = this.sessionFactory.getCurrentSession();
		OrderFood orderFood = session.get(OrderFood.class, id);
		return orderFood;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Iterable<OrderFood> findAll() {
		// TODO Auto-generated method stub
		Session session = this.sessionFactory.getCurrentSession();
		Iterable<OrderFood> list = session.createQuery("from OrderFood").list();
		return list;
	}
}
