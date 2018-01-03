package Model.DAO;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.transaction.annotation.Transactional;

import Model.DTO.DateRevenue;
import Model.DTO.DateRevenueId;
import Model.MODEL.ReportQuery;
import Model.MODEL.TotalReportModel;

@Transactional
public class DateRevenueDAOImpl implements DateRevenueDAO {

	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public DateRevenue save(DateRevenue dateRevenue) {
		// TODO Auto-generated method stub
		Session session = this.sessionFactory.getCurrentSession();
		dateRevenue.setFlags(true);
		dateRevenue.setDateCreated(new Date());
		session.persist(dateRevenue);
		return dateRevenue;
	}

	@Override
	public DateRevenue update(DateRevenue dateRevenue) {
		// TODO Auto-generated method stub
		Session session = this.sessionFactory.getCurrentSession();
		session.persist(dateRevenue);
		return dateRevenue;
	}

	@Override
	public void delete(DateRevenueId id) {
		// TODO Auto-generated method stub

		DateRevenue dateRevenue = this.findById(id);
		if (dateRevenue != null) {
			Session session = this.sessionFactory.getCurrentSession();
			dateRevenue.setFlags(false);
			session.update(dateRevenue);
		}

	}

	@Override
	public DateRevenue findById(DateRevenueId id) {
		// TODO Auto-generated method stub
		Session session = this.sessionFactory.getCurrentSession();
		DateRevenue dateRevenue = session.get(DateRevenue.class, id);
		return dateRevenue;
	}

	@SuppressWarnings("unchecked")
	public List<TotalReportModel> getDateRevenue(ReportQuery param) {
		Session session = this.sessionFactory.getCurrentSession();
		List<TotalReportModel> result = new ArrayList<>();
		String queryFormat = "Select %s";
		queryFormat += "From DateRevenue a ";
		queryFormat += "Where %s and a.flags='1'";
		queryFormat += "Group by %s ";
		queryFormat += "Order by %s ";

		String query = "";
		switch (param.getPeriodType()) {
		case 0:
			query = String.format(queryFormat,
					"distinct new Model.MODEL.TotalReportModel(hour(a.id.dateOfRevenue), sum(a.price))",
					"date(a.id.dateOfRevenue) = date('" + param.getDate().toString() + "')", "hour(a.id.dateOfRevenue)",
					"hour(a.id.dateOfRevenue) asc");
			break;
		case 1:
			query = String.format(queryFormat,
					"distinct new Model.MODEL.TotalReportModel(dayname(a.id.dateOfRevenue), sum(a.price))",
					"weekofyear(a.id.dateOfRevenue) = weekofyear('" + param.getDate().toString() + "')",
					"dayname(a.id.dateOfRevenue)", "weekday(a.id.dateOfRevenue) asc");
			break;

		case 2:
			query = String.format(queryFormat,
					"distinct new Model.MODEL.TotalReportModel(day(a.id.dateOfRevenue), sum(a.price))",
					"month(a.id.dateOfRevenue) = month('" + param.getDate().toString() + "')",
					"day(a.id.dateOfRevenue)", "day(a.id.dateOfRevenue) asc");
			break;

		case 3:
			query = String.format(queryFormat,
					"distinct new Model.MODEL.TotalReportModel(month(a.id.dateOfRevenue), sum(a.price))",
					"quarter(a.id.dateOfRevenue) = '" + param.getQuarter() + "'", "month(a.id.dateOfRevenue)",
					"month(a.id.dateOfRevenue) asc");
			break;

		case 4:
			query = String.format(queryFormat,
					"distinct new Model.MODEL.TotalReportModel(month(a.id.dateOfRevenue), sum(a.price))",
					"year(a.id.dateOfRevenue) = '" + param.getYear() + "'", "month(a.id.dateOfRevenue)",
					"month(a.id.dateOfRevenue) asc");
			break;

		default:
			break;
		}

		result = (List<TotalReportModel>) session.createQuery(query).list();

		return result;
	}

	@Override
	public Iterable<DateRevenue> findAll() {
		// TODO Auto-generated method stub
		Session session = this.sessionFactory.getCurrentSession();
		Iterable<DateRevenue> list = session.createQuery("From DateRevenue").list();
		return list;
	}

}
