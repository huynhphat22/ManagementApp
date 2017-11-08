package Model.DAO;
// Generated Nov 8, 2017 7:12:49 PM by Hibernate Tools 5.2.3.Final

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import Model.DTO.MonthCost;
import Model.DTO.MonthCostId;

/**
 * Home object for domain model class MonthCost.
 * @see Model.DAO.MonthCost
 * @author Hibernate Tools
 */
@Stateless
public class MonthCostHome {

	private static final Log log = LogFactory.getLog(MonthCostHome.class);

	@PersistenceContext
	private EntityManager entityManager;

	public void persist(MonthCost transientInstance) {
		log.debug("persisting MonthCost instance");
		try {
			entityManager.persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void remove(MonthCost persistentInstance) {
		log.debug("removing MonthCost instance");
		try {
			entityManager.remove(persistentInstance);
			log.debug("remove successful");
		} catch (RuntimeException re) {
			log.error("remove failed", re);
			throw re;
		}
	}

	public MonthCost merge(MonthCost detachedInstance) {
		log.debug("merging MonthCost instance");
		try {
			MonthCost result = entityManager.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public MonthCost findById(MonthCostId id) {
		log.debug("getting MonthCost instance with id: " + id);
		try {
			MonthCost instance = entityManager.find(MonthCost.class, id);
			log.debug("get successful");
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
}
