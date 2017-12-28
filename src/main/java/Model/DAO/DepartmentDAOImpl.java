package Model.DAO;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import Model.DTO.Department;

@Transactional
public class DepartmentDAOImpl implements DepartmentDAO {

	@Autowired
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public Department save(Department department) {
		// TODO Auto-generated method stub
		Session session = this.sessionFactory.getCurrentSession();
		session.persist(department);
		return department;
	}

	@Override
	public Department update(Department department) {
		// TODO Auto-generated method stub
		Session session = this.sessionFactory.getCurrentSession();
		session.update(department);
		return department;
	}

	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub
		Session session = this.sessionFactory.getCurrentSession();
		Department department = this.findById(id);
		if (department != null) {
			session.delete(department);
		}
	}

	@Override
	public Department findById(int id) {
		// TODO Auto-generated method stub
		Session session = this.sessionFactory.getCurrentSession();
		return session.get(Department.class, id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Iterable<Department> findAll() {
		// TODO Auto-generated method stub

		Session session = this.sessionFactory.getCurrentSession();

		Iterable<Department> list = session.createQuery("from Department").list();

		return list;

	}

	public long count() {
		Session session = this.sessionFactory.getCurrentSession();
		Criteria criteriaCount = session.createCriteria(Department.class);
		criteriaCount.setProjection(Projections.rowCount());
		return (long) criteriaCount.uniqueResult();
	}

	public Iterable<Department> paginateDepartment(int page, String sort) {

		int pageSize = 10;
		int start = (page - 1) * pageSize;
		
		Session session = this.sessionFactory.getCurrentSession();

		Criteria criteria = session.createCriteria(Department.class);
		criteria.setFirstResult(start);
		criteria.setMaxResults(pageSize);
		criteria.addOrder(Order.asc(sort));

		Iterable<Department> list = criteria.list();
		return list;
	}

}
