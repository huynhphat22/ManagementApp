package Model.DAO;

import java.util.Date;

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
import Model.DTO.Food;
import Model.DTO.OrderFood;
import Model.MODEL.Page;
import Model.MODEL.PageQuery;

@Transactional
public class OrderFoodDAOImpl implements OrderFoodDAO {
	@Autowired
	private SessionFactory sessionFactory;
	

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public OrderFood save(OrderFood orderFood) {
		// TODO Auto-generated method stub
		Session session = this.sessionFactory.getCurrentSession();
		orderFood.setFlags(true);
		orderFood.setDateCreated(new Date());
		session.persist(orderFood);
		return orderFood;
	}

	@Override
	public OrderFood update(OrderFood orderFood) {
		// TODO Auto-generated method stub
		Session session = this.sessionFactory.getCurrentSession();
		session.update(orderFood);
		return orderFood;
	}

	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub
		Session session = this.sessionFactory.getCurrentSession();
		OrderFood orderFood = this.findById(id);
		if(orderFood != null) {
			orderFood.setFlags(false);
			session.update(orderFood);
		}
	}

	@Override
	public OrderFood findById(int id) {
		// TODO Auto-generated method stub
		Session session = this.sessionFactory.getCurrentSession();
		OrderFood orderFood = session.get(OrderFood.class, id);
		return orderFood;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Iterable<OrderFood> findAll() {
		// TODO Auto-generated method stub
		Session session = this.sessionFactory.getCurrentSession();
		Iterable<OrderFood> list = session.createQuery("from OrderFood").list();
		return list;
	}

	@Override
	public Page paginateOrderFood(PageQuery pageQuery) {
		// TODO Auto-generated method stub
		int start = (pageQuery.getPage() - 1) * pageQuery.getSize();
		long count = 0;
		long totalPages = 0;
		Session session = this.sessionFactory.getCurrentSession();

		Criteria criteria = session.createCriteria(OrderFood.class);
		Criteria criteriaCount = session.createCriteria(Department.class);
		criteria.setFirstResult(start);
		criteria.setMaxResults(pageQuery.getSize());
		criteria.addOrder(pageQuery.isAsc() ? Order.asc(pageQuery.getSortBy()) : Order.desc(pageQuery.getSortBy()));
		
		if(pageQuery.getSearchBy() != null && pageQuery.getSearchText() != null) {
			System.out.println(pageQuery.getSearchText() +  pageQuery.getSearchBy());
			Criterion criterion = Restrictions.like(pageQuery.getSearchBy(), pageQuery.getSearchText(), MatchMode.ANYWHERE);
			criteria.add(criterion);
			criteriaCount.add(criterion);
		}
		
		Iterable<OrderFood> list = criteria.list();
		count = (long) criteriaCount.setProjection(Projections.rowCount()).uniqueResult();
		totalPages = (count % pageQuery.getSize() != 0) ? (count/pageQuery.getSize()) + 1 : count/pageQuery.getSize();
		Page page = new Page(list , totalPages);
		System.out.println("count : " + count );
		System.out.println("page : "  + page.getContent());
		return page;
	}
}
