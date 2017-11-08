package Model.DAO;
// Generated Nov 8, 2017 7:12:49 PM by Hibernate Tools 5.2.3.Final

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import Model.DTO.DateRevenue;
import Model.DTO.DateRevenueId;

/**
 * Home object for domain model class DateRevenue.
 * @see Model.DAO.DateRevenue
 * @author Hibernate Tools
 */
@Stateless
public class DateRevenueHome {

	private static final Log log = LogFactory.getLog(DateRevenueHome.class);

	@PersistenceContext
	private EntityManager entityManager;

	public void persist(DateRevenue transientInstance) {
		log.debug("persisting DateRevenue instance");
		try {
			entityManager.persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void remove(DateRevenue persistentInstance) {
		log.debug("removing DateRevenue instance");
		try {
			entityManager.remove(persistentInstance);
			log.debug("remove successful");
		} catch (RuntimeException re) {
			log.error("remove failed", re);
			throw re;
		}
	}

	public DateRevenue merge(DateRevenue detachedInstance) {
		log.debug("merging DateRevenue instance");
		try {
			DateRevenue result = entityManager.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public DateRevenue findById(DateRevenueId id) {
		log.debug("getting DateRevenue instance with id: " + id);
		try {
			DateRevenue instance = entityManager.find(DateRevenue.class, id);
			log.debug("get successful");
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
}
