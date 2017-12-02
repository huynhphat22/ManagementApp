package Model.DAO;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import Model.DTO.Staff;

public class StaffDAOImpl implements StaffDAO {

	@Autowired
	private SessionFactory sessionFactory;
	

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public Staff save(Staff staff) {
		// TODO Auto-generated method stub
		Session session = this.sessionFactory.getCurrentSession();
		session.persist(staff);
		return staff;
	}

	@Override
	public Staff update(Staff staff) {
		// TODO Auto-generated method stub
		Session session = this.sessionFactory.getCurrentSession();
		session.update(staff);
		return staff;
	}

	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub
		Session session = this.sessionFactory.getCurrentSession();
		Staff staff = this.findById(id);
		if(staff != null) {
			session.delete(staff);
		}
	}

	@Override
	public Staff findById(int id) {
		// TODO Auto-generated method stub
		Session session = this.sessionFactory.getCurrentSession();
		Staff staff = session.get(Staff.class, id);
		return staff;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Iterable<Staff> findAll() {
		// TODO Auto-generated method stub
		Session session = this.sessionFactory.getCurrentSession();
		Iterable<Staff> list = session.createQuery("from Staff").list();
		return list;
	}
}
