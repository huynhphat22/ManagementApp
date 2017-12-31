package Model.DAO;

import java.util.Date;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.transaction.annotation.Transactional;

import Model.DTO.Staff;
import Model.MODEL.Page;
import Model.MODEL.PageQuery;

@Transactional
public class StaffDAOImpl implements StaffDAO {

	private SessionFactory sessionFactory;
	

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public Staff save(Staff staff) {
		// TODO Auto-generated method stub
		Session session = this.sessionFactory.getCurrentSession();
		staff.setDateCreated(new Date());
		staff.setFlags(true);
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

	@Override
	public Page paginateStaff(PageQuery pageQuery) {
		// TODO Auto-generated method stub
		int start = (pageQuery.getPage() - 1) * pageQuery.getSize();
		long count = 0;
		long totalPages = 0;
		Session session = this.sessionFactory.getCurrentSession();

		Criteria criteria = session.createCriteria(Staff.class);
		Criteria criteriaCount = session.createCriteria(Staff.class);
		criteria.setFirstResult(start);
		criteria.setMaxResults(pageQuery.getSize());
		criteria.addOrder(pageQuery.isAsc() ? Order.asc(pageQuery.getSortBy()) : Order.desc(pageQuery.getSortBy()));
		
		if(pageQuery.getSearchBy() != null && pageQuery.getSearchText() != null) {
			System.out.println(pageQuery.getSearchText() +  pageQuery.getSearchBy());
			Criterion criterion = Restrictions.like(pageQuery.getSearchBy(), pageQuery.getSearchText(), MatchMode.ANYWHERE);
			criteria.add(criterion);
			criteriaCount.add(criterion);
		}
		
		Iterable<Staff> list = criteria.list();
		count = (long) criteriaCount.setProjection(Projections.rowCount()).uniqueResult();
		totalPages = (count % pageQuery.getSize() != 0) ? (count/pageQuery.getSize()) + 1 : count/pageQuery.getSize();
		Page page = new Page(list , totalPages);
		System.out.println("count : " + count );
		System.out.println("page : "  + page.getContent());
		return page;
	}

	@Override
	public Staff findByUsername(String username) {
		Session session = this.sessionFactory.getCurrentSession();
		Query query = (Query) session.createQuery("select s from Staff s WHERE s.username = :username ");
		query.setParameter("username", username);
		return (Staff) query.uniqueResult();
		
	}
	
	
}
