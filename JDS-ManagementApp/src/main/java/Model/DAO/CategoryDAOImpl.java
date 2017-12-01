package Model.DAO;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import Model.DTO.Category;

@Transactional
public class CategoryDAOImpl implements CategoryDAO{

	@Autowired
	private SessionFactory sessionFactory;
	

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public Category save(Category category) {
		// TODO Auto-generated method stub
		Session session = this.sessionFactory.getCurrentSession();
		session.persist(category);
		return category;
	}

	@Override
	public Category update(Category category) {
		// TODO Auto-generated method stub
		Session session = this.sessionFactory.getCurrentSession();
		session.update(category);
		return category;
	}

	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub
		Session session = this.sessionFactory.getCurrentSession();
		Category category = this.findById(id);
		if(category != null) {
			session.delete(category);
		}
	}

	@Override
	public Category findById(int id) {
		// TODO Auto-generated method stub
		Session session = this.sessionFactory.getCurrentSession();
		Category category = session.get(Category.class, id);
		return category;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Iterable<Category> findAll() {
		// TODO Auto-generated method stub
		Session session = this.sessionFactory.getCurrentSession();
		Iterable<Category> list = session.createQuery("from Category").list();
		return list;
	}

	

	@Override
	public long count() {
		// TODO Auto-generated method stub
		Session session = this.sessionFactory.getCurrentSession();
		Criteria criteriaCount = session.createCriteria(Category.class);
		criteriaCount.setProjection(Projections.rowCount());
		return (long) criteriaCount.uniqueResult();
	}

	@Override
	public Iterable<Category> findByDepartmentId(int departmentId) {
		// TODO Auto-generated method stub
		
		Session session = this.sessionFactory.getCurrentSession();
		String hql = "SELECT DISTINCT c FROM Category c INNER JOIN Food f ON c.categoryId = f.categoryId "
				+ "INNER JOIN MenuDepartment md ON (f.foodId = md.id.foodId and md.id.departmentId = " + departmentId +  ")";
		Iterable<Category> list = session.createQuery(hql).list();
		return list;
	}

}
