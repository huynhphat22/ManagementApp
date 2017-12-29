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
import org.springframework.transaction.annotation.Transactional;

import Model.DTO.Food;
import Model.MODEL.Page;
import Model.MODEL.PageQuery;

@Transactional
public class FoodDAOImpl implements FoodDAO {

	@Autowired
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public Food save(Food food) {
		// TODO Auto-generated method stub
		Session session = this.sessionFactory.getCurrentSession();
		session.persist(food);
		return food;
	}

	@Override
	public Food update(Food food) {
		// TODO Auto-generated method stub
		Session session = this.sessionFactory.getCurrentSession();
		session.update(food);
		return food;
	}

	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub
		Food food = this.findById(id);
		if (food != null) {
			Session session = this.sessionFactory.getCurrentSession();
			session.delete(food);
		}
	}

	@Override
	public Food findById(int id) {
		// TODO Auto-generated method stub
		Session session = this.sessionFactory.getCurrentSession();
		Food food = session.get(Food.class, id);
		return food;
	}

	@Override
	public Iterable<Food> findAll() {
		// TODO Auto-generated method stub
		Session session = this.sessionFactory.getCurrentSession();
		Iterable<Food> list = session.createQuery("FROM Food").list();
		return list;
	}


	@Override
	public long count() {
		// TODO Auto-generated method stub
		Session session = this.sessionFactory.getCurrentSession();
		Criteria criteriaCount = session.createCriteria(Food.class);
		criteriaCount.setProjection(Projections.rowCount());
		return (long) criteriaCount.uniqueResult();
	}

	@Override
	public Page paginateFood(PageQuery pageQuery) {
		// TODO Auto-generated method stub
		int start = (pageQuery.getPage() - 1) * pageQuery.getSize();
		long count = 0;
		long totalPages = 0;
		Session session = this.sessionFactory.getCurrentSession();

		Criteria criteria = session.createCriteria(Food.class);
		criteria.setFirstResult(start);
		criteria.setMaxResults(pageQuery.getSize());
		criteria.addOrder(pageQuery.isAsc() ? Order.asc(pageQuery.getSortBy()) : Order.desc(pageQuery.getSortBy()));
		
		if(pageQuery.getSearchBy() != null && pageQuery.getSearchText() != null) {
			System.out.println(pageQuery.getSearchText() +  pageQuery.getSearchBy());
			Criterion criterion = Restrictions.like(pageQuery.getSearchBy(), pageQuery.getSearchText(), MatchMode.ANYWHERE);
			criteria.add(criterion);
			Query query = session.createQuery("SELECT COUNT(*) FROM Food f WHERE f." + pageQuery.getSearchBy()
					+ " LIKE CONCAT('%',:searchText,'%')");
			query.setParameter("searchText", pageQuery.getSearchText());
			count = (long)query.uniqueResult();
		}
		else {
			count = (long)session.createQuery("select count(*) from Food").uniqueResult();
		}
		
		totalPages = (count % pageQuery.getSize() != 0) ? (count/pageQuery.getSize()) + 1 : count/pageQuery.getSize();
		Page page = new Page((Iterable<Food>)criteria.list(), totalPages);
		return page;
	}

	
}
