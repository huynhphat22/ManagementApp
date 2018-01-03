package Model.DAO;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.transaction.annotation.Transactional;

import Model.DTO.DateCost;
import Model.DTO.DateCostId;
import Model.MODEL.Page;
import Model.MODEL.PageQuery;
import Model.MODEL.ReportQuery;
import Model.MODEL.TotalReportModel;

@Transactional
public class DateCostDAOImpl implements DateCostDAO {

	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public DateCost save(DateCost dateCost) {
		// TODO Auto-generated method stub
		Session session = this.sessionFactory.getCurrentSession();
		dateCost.setFlags(true);
		dateCost.setDateCreated(new Date());
		session.persist(dateCost);
		return dateCost;
	}

	@Override
	public DateCost update(DateCost dateCost) {
		// TODO Auto-generated method stub
		Session session = this.sessionFactory.getCurrentSession();
		session.update(dateCost);
		return dateCost;
	}

	@Override
	public void delete(DateCostId id) {
		// TODO Auto-generated method stub

		DateCost dateCost = this.findById(id);
		if (dateCost != null) {
			Session session = this.sessionFactory.getCurrentSession();
			dateCost.setFlags(false);
			session.update(dateCost);
		}

	}

	@Override
	public DateCost findById(DateCostId id) {
		// TODO Auto-generated method stub
		Session session = this.sessionFactory.getCurrentSession();
		DateCost dateCost = session.get(DateCost.class, id);
		return dateCost;
	}

	@Override
	public Iterable<DateCost> findAll() {
		// TODO Auto-generated method stub
		Session session = this.sessionFactory.getCurrentSession();
		Iterable<DateCost> list = session.createQuery("From DateCost").list();
		return list;
	}

	@Override
	public Page paginateDateCost(PageQuery pageQuery, int departmentId) {
		// TODO Auto-generated method stub
		int start = (pageQuery.getPage() - 1) * pageQuery.getSize();
		long count = 0;
		long totalPages = 0;
		Session session = this.sessionFactory.getCurrentSession();

		Criteria criteria = session.createCriteria(DateCost.class)
				.add(Restrictions.eq("id.departmentId", departmentId));
		Criteria criteriaCount = session.createCriteria(DateCost.class)
				.add(Restrictions.eq("id.departmentId", departmentId));
		criteria.setFirstResult(start);
		criteria.setMaxResults(pageQuery.getSize());
		criteria.addOrder(pageQuery.isAsc() ? Order.asc(pageQuery.getSortBy()) : Order.desc(pageQuery.getSortBy()));

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

		Iterable<DateCost> list = criteria.list();
		count = (long) criteriaCount.setProjection(Projections.rowCount()).uniqueResult();
		totalPages = (count % pageQuery.getSize() != 0) ? (count / pageQuery.getSize()) + 1
				: count / pageQuery.getSize();
		Page page = new Page(list, totalPages);
		System.out.println("count : " + count);
		System.out.println("page : " + page.getContent());
		return page;
	}

	@SuppressWarnings("unchecked")
	public List<TotalReportModel> getDateCost(ReportQuery param) {
		// TODO Auto-generated method stub
		Session session = this.sessionFactory.getCurrentSession();
		List<TotalReportModel> result = new ArrayList<>();
		String queryFormat = "Select %s ";
		queryFormat += "From DateCost a ";
		queryFormat += "Where %s and a.flags='1'";
		queryFormat += "Group by %s ";
		queryFormat += "Order by %s ";

		String query = "";
		switch (param.getPeriodType()) {
		case 0:
			query = String.format(queryFormat,
					"distinct new Model.MODEL.TotalReportModel(hour(a.id.dateOfCost), sum(a.price))",
					"date(a.id.dateOfCost) = date('" + param.getDate().toString() + "')", "hour(a.id.dateOfCost)",
					"hour(a.id.dateOfCost) asc");
			break;
		case 1:
			query = String.format(queryFormat,
					"distinct new Model.MODEL.TotalReportModel(dayname(a.id.dateOfCost), sum(a.price))",
					"weekofyear(a.id.dateOfCost) = weekofyear('" + param.getDate().toString() + "')",
					"dayname(a.id.dateOfCost)", "weekday(a.id.dateOfCost) asc");
			break;

		case 2:
			query = "select distinct new Model.MODEL.TotalReportModel(month(a.id.dateOfCost), sum(a.price)), month(a.id.dateOfCost) as Month ";
			query += "from DateCost a, MonthCost b ";
			query += "where month(a.id.dateOfCost) = month('" + param.getDate() + "') ";
			query += "and month(str_to_date(b.id.monthOfCost, '%Y-%m')) = month(a.id.dateOfCost) ";
			query += "and a.flags = '1' ";
			query += "and b.flags = '1' ";
			query += "group by Month ";
			query += "order by Month ";
			break;

		case 3:
			query = String.format(queryFormat,
					"distinct new Model.MODEL.TotalReportModel(month(a.id.dateOfCost), sum(a.price))",
					"quarter(a.id.dateOfCost) = '" + param.getQuarter() + "'", "month(a.id.dateOfCost)",
					"month(a.id.dateOfCost) asc");
			break;

		case 4:
			query = String.format(queryFormat,
					"distinct new Model.MODEL.TotalReportModel(month(a.id.dateOfCost), sum(a.price))",
					"year(a.id.dateOfCost) = '" + param.getYear() + "'", "month(a.id.dateOfCost)",
					"month(a.id.dateOfCost) asc");
			break;

		default:
			break;
		}

		result = (List<TotalReportModel>) session.createQuery(query).list();

		return result;
	}

}
