package Model.DAO;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import Model.DTO.MonthCost;
import Model.DTO.MonthCostId;

public class MonthCostDAOImpl implements MonthCostDAO{

	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public MonthCost save(MonthCost monthCost) {
		// TODO Auto-generated method stub
		Session session = this.sessionFactory.getCurrentSession();
		session.persist(monthCost);
		return monthCost;
	}

	@Override
	public MonthCost update(MonthCost monthCost) {
		// TODO Auto-generated method stub
		Session session = this.sessionFactory.getCurrentSession();
		session.persist(monthCost);
		return monthCost;
	}

	@Override
	public void delete(MonthCostId id) {
		// TODO Auto-generated method stub
		
		MonthCost monthCost = this.findById(id);
		if(monthCost != null) {
			Session session = this.sessionFactory.getCurrentSession();
			session.delete(monthCost);
		}
		
	}

	@Override
	public MonthCost findById(MonthCostId id) {
		// TODO Auto-generated method stub
		Session session = this.sessionFactory.getCurrentSession();
		MonthCost monthCost = session.get(MonthCost.class, id);
		return monthCost;
	}

	@Override
	public Iterable<MonthCost> findAll() {
		// TODO Auto-generated method stub
		Session session = this.sessionFactory.getCurrentSession();
		Iterable<MonthCost> list = session.createQuery("From MonthCost").list();
		return list ;
	}

}
