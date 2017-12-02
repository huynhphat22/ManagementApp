package Model.DAO;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import Model.DTO.RestaurantTable;

public class RestaurantTableDAOImpl implements RestaurantTableDAO {

	@Autowired
	private SessionFactory sessionFactory;
	

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public RestaurantTable save(RestaurantTable restaurantTable) {
		// TODO Auto-generated method stub
		Session session = this.sessionFactory.getCurrentSession();
		session.persist(restaurantTable);
		return restaurantTable;
	}

	@Override
	public RestaurantTable update(RestaurantTable restaurantTable) {
		// TODO Auto-generated method stub
		Session session = this.sessionFactory.getCurrentSession();
		session.update(restaurantTable);
		return restaurantTable;
	}

	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub
		Session session = this.sessionFactory.getCurrentSession();
		RestaurantTable restaurantTable = this.findById(id);
		if(restaurantTable != null) {
			session.delete(restaurantTable);
		}
	}

	@Override
	public RestaurantTable findById(int id) {
		// TODO Auto-generated method stub
		Session session = this.sessionFactory.getCurrentSession();
		RestaurantTable restaurantTable = session.get(RestaurantTable.class, id);
		return restaurantTable;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Iterable<RestaurantTable> findAll() {
		// TODO Auto-generated method stub
		Session session = this.sessionFactory.getCurrentSession();
		Iterable<RestaurantTable> list = session.createQuery("from RestaurantTable").list();
		return list;
	}
}
