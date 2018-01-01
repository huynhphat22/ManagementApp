package Model.DAO;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.transaction.annotation.Transactional;

import Model.DTO.DateCost;
import Model.DTO.DateCostId;
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
		if(dateCost != null) {
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
		return list ;
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
			query = String.format(queryFormat, "distinct new Model.MODEL.TotalReportModel(hour(a.id.dateOfCost), sum(a.price))",
												"date(a.id.dateOfCost) = date('" + param.getDate().toString() + "')",
												"hour(a.id.dateOfCost)",
												"hour(a.id.dateOfCost) asc");
			break;
		case 1:
			query = String.format(queryFormat, "distinct new Model.MODEL.TotalReportModel(dayname(a.id.dateOfCost), sum(a.price))",
												"weekofyear(a.id.dateOfCost) = weekofyear('" + param.getDate().toString() + "')",
												"dayname(a.id.dateOfCost)",
												"weekday(a.id.dateOfCost) asc");
			break;
			
		case 2:
			query = "select distinct new Model.MODEL.TotalReportModel(month(a.id.dateOfCost), sum(a.price)), month(a.id.dateOfCost) as Month ";
			query += "from DateCost a, MonthCost b ";
			query += "where month(a.id.dateOfCost) = month('"+ param.getDate() +"') ";
			query += "and month(str_to_date(b.id.monthOfCost, '%Y-%m')) = month(a.id.dateOfCost) ";
			query += "and a.flags = '1' ";
			query += "and b.flags = '1' ";
			query += "group by Month ";
			query += "order by Month ";
			break;
			
		case 3:
			query = String.format(queryFormat, "distinct new Model.MODEL.TotalReportModel(month(a.id.dateOfCost), sum(a.price))",
												"quarter(a.id.dateOfCost) = '" + param.getQuarter() + "'",
												"month(a.id.dateOfCost)",
												"month(a.id.dateOfCost) asc");
			break;
			
		case 4:
			query = String.format(queryFormat, "distinct new Model.MODEL.TotalReportModel(month(a.id.dateOfCost), sum(a.price))",
												"year(a.id.dateOfCost) = '" + param.getYear() + "'",
												"month(a.id.dateOfCost)",
												"month(a.id.dateOfCost) asc");
			break;
			
		default:
			break;
		}
		
		result = (List<TotalReportModel>)session.createQuery(query).list();
		
		return result;
	}

}
