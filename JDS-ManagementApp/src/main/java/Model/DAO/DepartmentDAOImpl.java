package Model.DAO;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import Model.DTO.Department;
import Model.MODEL.Page;
import Model.MODEL.PageQuery;

@Transactional
@Service
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
	
	public Page paginateDepartment(PageQuery pageQuery) {
		int start = (pageQuery.getPage() - 1) * pageQuery.getSize();
		long count = 0;
		long totalPages = 0;
		Session session = this.sessionFactory.getCurrentSession();

		Criteria criteria = session.createCriteria(Department.class);
		criteria.setFirstResult(start);
		criteria.setMaxResults(pageQuery.getSize());
		criteria.addOrder(pageQuery.isAsc() ? Order.asc(pageQuery.getSortBy()) : Order.desc(pageQuery.getSortBy()));
		
		if(pageQuery.getSearchBy() != null && pageQuery.getSearchText() != null) {
			System.out.println(pageQuery.getSearchText() +  pageQuery.getSearchBy());
			Criterion criterion = Restrictions.like(pageQuery.getSearchBy(), pageQuery.getSearchText(), MatchMode.ANYWHERE);
			criteria.add(criterion);
			Query query = session.createQuery("SELECT COUNT(*) FROM Department d WHERE d." + pageQuery.getSearchBy()
					+ " LIKE CONCAT('%',:searchText,'%')");
			query.setParameter("searchText", pageQuery.getSearchText());
			count = (long)query.uniqueResult();
		}
		else {
			count = (long)session.createQuery("select count(*) from Department").uniqueResult();
		}
		
		totalPages = (count % pageQuery.getSize() != 0) ? (count/pageQuery.getSize()) + 1 : count/pageQuery.getSize();
		Page page = new Page((Iterable<Department>)criteria.list(), totalPages);
<<<<<<< HEAD
=======
		System.out.println("count : " + count );
		System.out.println("page : "  + page.getContent());
>>>>>>> refs/remotes/origin/1412163
		return page;
	}
}
