package Model.DAO;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import Model.DTO.Department;
import Model.MODEL.Page;
import Model.MODEL.PageQuery;

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
	
	public Page paginateDepartment(PageQuery pageQuery) {
		int start = (pageQuery.getPage() - 1) * pageQuery.getSize();
		long count = 0;
		long totalPages = 0;
		System.out.println("page : " +pageQuery.getPage());
		System.out.println("size : " +pageQuery.getSize());
		System.out.println("sortBy : " +pageQuery.getSortBy());
		System.out.println("asc : " +pageQuery.isAsc());
		Session session = this.sessionFactory.getCurrentSession();

		Criteria criteria = session.createCriteria(Department.class);
		Criteria criteriaCount = session.createCriteria(Department.class);
		criteria.setFirstResult(start);
		criteria.setMaxResults(pageQuery.getSize());
		criteria.addOrder(pageQuery.isAsc() ? Order.asc(pageQuery.getSortBy()) : Order.desc(pageQuery.getSortBy()));
		
		if(pageQuery.getSearchBy() != null && pageQuery.getSearchText() != null) {
			System.out.println(pageQuery.getSearchText() +  pageQuery.getSearchBy());
			Criterion criterion = null;
			try{
				int number = Integer.parseInt(pageQuery.getSearchText());
				criterion = Restrictions.eq(pageQuery.getSearchBy(), number);
			}
			catch(Exception e){
				criterion = Restrictions.like(pageQuery.getSearchBy(), pageQuery.getSearchText(), MatchMode.ANYWHERE);
			}
			criteria.add(criterion);
			criteriaCount.add(criterion);
		}
		
		Iterable<Department> list = criteria.list();
		count = (long) criteriaCount.setProjection(Projections.rowCount()).uniqueResult();
		totalPages = (count % pageQuery.getSize() != 0) ? (count/pageQuery.getSize()) + 1 : count/pageQuery.getSize();
		Page page = new Page(list , totalPages);
		System.out.println("count : " + count );
		System.out.println("page : "  + page.getContent());
		return page;
	}
}
