package Service;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import Model.MODEL.ReportQuery;
import Model.MODEL.TotalReportModel;


@Service
@Transactional
public class ReportService {

	@Autowired
	private SessionFactory sessionFactory;

	@SuppressWarnings("unchecked")
	public List<TotalReportModel> getTotalRevenue(ReportQuery param){
		Session session = getSessionFactory().getCurrentSession();
		
		String query = "";
		
		query += "select hour(a.dateOfRevenue) as Hour,";
		query += "		sum(a.price) as Revenue ";
		query += "from DateRevenue a";
		query += "where a.dateCreated = :queryTime";
		
		return session.createQuery(query).setEntity("queryTime", param.getDate()).list();
	}

	/**
	 * @return the sessionFactory
	 */
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	/**
	 * @param sessionFactory the sessionFactory to set
	 */
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
}
