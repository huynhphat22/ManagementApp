package Model.DAO;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import Model.DTO.Payment;

public class PaymentDAOImpl implements PaymentDAO{

	@Autowired
	private SessionFactory sessionFactory;
	

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public Payment save(Payment payment) {
		// TODO Auto-generated method stub
		Session session = this.sessionFactory.getCurrentSession();
		session.persist(payment);
		return payment;
	}

	@Override
	public Payment update(Payment payment) {
		// TODO Auto-generated method stub
		Session session = this.sessionFactory.getCurrentSession();
		session.update(payment);
		return payment;
	}

	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub
		Session session = this.sessionFactory.getCurrentSession();
		Payment payment = this.findById(id);
		if(payment != null) {
			session.delete(payment);
		}
	}

	@Override
	public Payment findById(int id) {
		// TODO Auto-generated method stub
		Session session = this.sessionFactory.getCurrentSession();
		Payment payment = session.get(Payment.class, id);
		return payment;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Iterable<Payment> findAll() {
		// TODO Auto-generated method stub
		Session session = this.sessionFactory.getCurrentSession();
		Iterable<Payment> list = session.createQuery("from Payment").list();
		return list;
	}
}
