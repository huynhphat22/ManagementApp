package Model.DAO;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import Model.DTO.DateRevenue;
import Model.DTO.DateRevenueId;

public class DateRevenueDAOImpl implements DateRevenueDAO{

	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public DateRevenue save(DateRevenue dateRevenue) {
		// TODO Auto-generated method stub
		Session session = this.sessionFactory.getCurrentSession();
		session.persist(dateRevenue);
		return dateRevenue;
	}

	@Override
	public DateRevenue update(DateRevenue dateRevenue) {
		// TODO Auto-generated method stub
		Session session = this.sessionFactory.getCurrentSession();
		session.persist(dateRevenue);
		return dateRevenue;
	}

	@Override
	public void delete(DateRevenueId id) {
		// TODO Auto-generated method stub
		
		DateRevenue dateRevenue = this.findById(id);
		if(dateRevenue != null) {
			Session session = this.sessionFactory.getCurrentSession();
			session.delete(dateRevenue);
		}
		
	}

	@Override
	public DateRevenue findById(DateRevenueId id) {
		// TODO Auto-generated method stub
		Session session = this.sessionFactory.getCurrentSession();
		DateRevenue dateRevenue = session.get(DateRevenue.class, id);
		return dateRevenue;
	}

	@Override
	public Iterable<DateRevenue> findAll() {
		// TODO Auto-generated method stub
		Session session = this.sessionFactory.getCurrentSession();
		Iterable<DateRevenue> list = session.createQuery("From DateRevenue").list();
		return list ;
	}

}
