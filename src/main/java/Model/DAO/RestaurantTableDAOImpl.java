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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import Model.DTO.Department;
import Model.DTO.Food;
import Model.DTO.RestaurantTable;
import Model.MODEL.Page;
import Model.MODEL.PageQuery;

@Transactional
public class RestaurantTableDAOImpl implements RestaurantTableDAO {

	private SessionFactory sessionFactory;
	

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public RestaurantTable save(RestaurantTable restaurantTable) {
		// TODO Auto-generated method stub
		Session session = this.sessionFactory.getCurrentSession();
		restaurantTable.setDateCreated(new Date());
		restaurantTable.setFlags(false);
		session.persist(restaurantTable);
		return restaurantTable;
	}

	@Override
	public RestaurantTable update(RestaurantTable restaurantTable) {
		// TODO Auto-generated method stub
		Session session = this.sessionFactory.getCurrentSession();
		session.update(restaurantTable);
		return restaurantTable;
	}

	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub
		Session session = this.sessionFactory.getCurrentSession();
		RestaurantTable restaurantTable = this.findById(id);
		if(restaurantTable != null) {
			session.delete(restaurantTable);
		}
	}

	@Override
	public RestaurantTable findById(int id) {
		// TODO Auto-generated method stub
		Session session = this.sessionFactory.getCurrentSession();
		RestaurantTable restaurantTable = session.get(RestaurantTable.class, id);
		return restaurantTable;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Iterable<RestaurantTable> findAll() {
		// TODO Auto-generated method stub
		Session session = this.sessionFactory.getCurrentSession();
		Iterable<RestaurantTable> list = session.createQuery("from RestaurantTable").list();
		return list;
	}

	@Override
	public Page paginateRestaurantTable(PageQuery pageQuery, int departmentId) {
		// TODO Auto-generated method stub
		System.out.println("zo : " + departmentId );
		int start = (pageQuery.getPage() - 1) * pageQuery.getSize();
		long count = 0;
		long totalPages = 0;
		System.out.println("zo3 : " + departmentId );
		Session session = this.sessionFactory.getCurrentSession();

		
		Criteria criteria = session.createCriteria(RestaurantTable.class).add(Restrictions.eq("departmentId", departmentId));
		Criteria criteriaCount = session.createCriteria(Department.class).add(Restrictions.eq("departmentId", departmentId));
		criteria.setFirstResult(start);
		criteria.setMaxResults(pageQuery.getSize());
		criteria.addOrder(pageQuery.isAsc() ? Order.asc(pageQuery.getSortBy()) : Order.desc(pageQuery.getSortBy()));
		
		System.out.println("zo2 : "  );
		if(pageQuery.getSearchBy() != null && pageQuery.getSearchText() != null) {
			System.out.println(pageQuery.getSearchText() +  pageQuery.getSearchBy());
			Criterion criterion = Restrictions.like(pageQuery.getSearchBy(), pageQuery.getSearchText(), MatchMode.ANYWHERE);
			criteria.add(criterion);
			criteriaCount.add(criterion);
		}
		
		Iterable<RestaurantTable> list = criteria.list();
		count = (long) criteriaCount.setProjection(Projections.rowCount()).uniqueResult();
		totalPages = (count % pageQuery.getSize() != 0) ? (count/pageQuery.getSize()) + 1 : count/pageQuery.getSize();
		Page page = new Page(list , totalPages);
		System.out.println("count : " + count );
		System.out.println("page : "  + page.getContent());
		return page;
	}
}
