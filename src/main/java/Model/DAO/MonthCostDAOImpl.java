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
import org.springframework.transaction.annotation.Transactional;

import Model.DTO.MonthCost;
import Model.MODEL.Page;
import Model.MODEL.PageQuery;

@Transactional
public class MonthCostDAOImpl implements MonthCostDAO {

	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public MonthCost save(MonthCost monthCost) {
		// TODO Auto-generated method stub
		Session session = this.sessionFactory.getCurrentSession();
		monthCost.setFlags(true);
		monthCost.setDateCreated(new Date());
		session.persist(monthCost);
		return monthCost;
	}

	@Override
	public MonthCost update(MonthCost monthCost) {
		// TODO Auto-generated method stub
		Session session = this.sessionFactory.getCurrentSession();
		session.update(monthCost);
		return monthCost;
	}

	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub

		MonthCost monthCost = this.findById(id);
		if (monthCost != null) {
			Session session = this.sessionFactory.getCurrentSession();
			monthCost.setFlags(false);
			session.update(monthCost);
		}

	}

	@Override
	public MonthCost findById(int id) {
		// TODO Auto-generated method stub
		Session session = this.sessionFactory.getCurrentSession();
		MonthCost monthCost = session.get(MonthCost.class, id);
		return monthCost;
	}

	@Override
	public Iterable<MonthCost> findAll() {
		// TODO Auto-generated method stub
		Session session = this.sessionFactory.getCurrentSession();
		Iterable<MonthCost> list = session.createQuery("From MonthCost").list();
		return list;
	}

	@Override
	public Page paginateMonthCost(PageQuery pageQuery, int departmentId) {
		// TODO Auto-generated method stub
		System.out.println("page" + pageQuery.getPage());
		int start = (pageQuery.getPage() - 1) * pageQuery.getSize();
		long count = 0;
		long totalPages = 0;
		Session session = this.sessionFactory.getCurrentSession();

		Criteria criteria = session.createCriteria(MonthCost.class)
				.add(Restrictions.eq("departmentId", departmentId));
		Criteria criteriaCount = session.createCriteria(MonthCost.class)
				.add(Restrictions.eq("departmentId", departmentId));
		System.out.println("r2");
		criteria.setFirstResult(start);
		criteria.setMaxResults(pageQuery.getSize());
		criteria.addOrder(pageQuery.isAsc() ? Order.asc(pageQuery.getSortBy()) : Order.desc(pageQuery.getSortBy()));
		System.out.println("r3");
		if (pageQuery.getSearchBy() != null && pageQuery.getSearchText() != null) {
			System.out.println(pageQuery.getSearchText() + pageQuery.getSearchBy());
			Criterion criterion = null;
			try {
				int number = Integer.parseInt(pageQuery.getSearchText());
				criterion = Restrictions.eq(pageQuery.getSearchBy(), number);
			} catch (Exception e) {
				criterion = Restrictions.like(pageQuery.getSearchBy(), pageQuery.getSearchText(), MatchMode.ANYWHERE);
			}
			criteria.add(criterion);
			criteriaCount.add(criterion);
		}

		System.out.println("r4");
		Iterable<MonthCost> list = criteria.list();
		System.out.println("r5");
		count = (long) criteriaCount.setProjection(Projections.rowCount()).uniqueResult();
		System.out.println("r6");
		totalPages = (count % pageQuery.getSize() != 0) ? (count / pageQuery.getSize()) + 1
				: count / pageQuery.getSize();
		Page page = new Page(list, totalPages);
		System.out.println("count : " + count);
		System.out.println("page : " + page.getContent());
		return page;
	}

}
