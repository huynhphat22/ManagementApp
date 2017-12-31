package Model.DAO;

import java.util.Date;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.transaction.annotation.Transactional;

import Model.DTO.DateCost;
import Model.DTO.DateCostId;

@Transactional
public class DateCostDAOImpl implements DateCostDAO {

	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public DateCost save(DateCost dateCost) {
		// TODO Auto-generated method stub
		Session session = this.sessionFactory.getCurrentSession();
		dateCost.setFlags(true);
		dateCost.setDateCreated(new Date());
		session.persist(dateCost);
		return dateCost;
	}

	@Override
	public DateCost update(DateCost dateCost) {
		// TODO Auto-generated method stub
		Session session = this.sessionFactory.getCurrentSession();
		session.update(dateCost);
		return dateCost;
	}

	@Override
	public void delete(DateCostId id) {
		// TODO Auto-generated method stub
		
		DateCost dateCost = this.findById(id);
		if(dateCost != null) {
			Session session = this.sessionFactory.getCurrentSession();
			dateCost.setFlags(false);
			session.update(dateCost);
		}
		
	}

	@Override
	public DateCost findById(DateCostId id) {
		// TODO Auto-generated method stub
		Session session = this.sessionFactory.getCurrentSession();
		DateCost dateCost = session.get(DateCost.class, id);
		return dateCost;
	}

	@Override
	public Iterable<DateCost> findAll() {
		// TODO Auto-generated method stub
		Session session = this.sessionFactory.getCurrentSession();
		Iterable<DateCost> list = session.createQuery("From DateCost").list();
		return list ;
	}

}
