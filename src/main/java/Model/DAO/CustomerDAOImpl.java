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

import Model.DTO.Customer;
import Model.MODEL.Page;
import Model.MODEL.PageQuery;

@Transactional
public class CustomerDAOImpl implements CustomerDAO{

	private SessionFactory sessionFactory;
	

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public Customer save(Customer customer) {
		// TODO Auto-generated method stub
		Session session = this.sessionFactory.getCurrentSession();
		customer.setFlags(true);
		customer.setDateCreated(new Date());
		session.persist(customer);
		return customer;
	}

	@Override
	public Customer update(Customer customer) {
		// TODO Auto-generated method stub
		Session session = this.sessionFactory.getCurrentSession();
		session.update(customer);
		return customer;
	}

	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub
		Session session = this.sessionFactory.getCurrentSession();
		Customer customer = this.findById(id);
		if(customer != null) {
			customer.setFlags(false);
			session.update(customer);
		}
	}

	@Override
	public Customer findById(int id) {
		// TODO Auto-generated method stub
		Session session = this.sessionFactory.getCurrentSession();
		Customer customer = session.get(Customer.class, id);
		return customer;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Iterable<Customer> findAll() {
		// TODO Auto-generated method stub
		Session session = this.sessionFactory.getCurrentSession();
		Iterable<Customer> list = session.createQuery("from Customer").list();
		return list;
	}

	@Override
	public Customer findByPhoneNumber(String phoneNumber) {
		// TODO Auto-generated method stub
		Session session = this.sessionFactory.getCurrentSession();
		Query query = session.createQuery("select c from Customer c WHERE c.phoneNumber = :phoneNumber");
		query.setParameter("phoneNumber", phoneNumber);
		
		return (Customer)query.uniqueResult();
	}

	@Override
	public Page paginateCustomer(PageQuery pageQuery) {
		// TODO Auto-generated method stub
		
		int start = (pageQuery.getPage() - 1) * pageQuery.getSize();
		long count = 0;
		long totalPages = 0;
		Session session = this.sessionFactory.getCurrentSession();

		Criteria criteria = session.createCriteria(Customer.class);
		Criteria criteriaCount = session.createCriteria(Customer.class);
		criteria.setFirstResult(start);
		criteria.setMaxResults(pageQuery.getSize());
		criteria.addOrder(pageQuery.isAsc() ? Order.asc(pageQuery.getSortBy()) : Order.desc(pageQuery.getSortBy()));
		
		if(pageQuery.getSearchBy() != null && pageQuery.getSearchText() != null) {
			System.out.println(pageQuery.getSearchText() +  pageQuery.getSearchBy());
			Criterion criterion = Restrictions.like(pageQuery.getSearchBy(), pageQuery.getSearchText(), MatchMode.ANYWHERE);
			criteria.add(criterion);
			criteriaCount.add(criterion);
		}
		
		Iterable<Customer> list = criteria.list();
		count = (long) criteriaCount.setProjection(Projections.rowCount()).uniqueResult();
		totalPages = (count % pageQuery.getSize() != 0) ? (count/pageQuery.getSize()) + 1 : count/pageQuery.getSize();
		Page page = new Page(list , totalPages);
		System.out.println("count : " + count );
		System.out.println("page : "  + page.getContent());
		return page;
	}
}
