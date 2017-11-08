package Model.DAO;
// Generated Nov 8, 2017 7:12:49 PM by Hibernate Tools 5.2.3.Final

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import Model.DTO.DateCost;
import Model.DTO.DateCostId;

/**
 * Home object for domain model class DateCost.
 * @see Model.DAO.DateCost
 * @author Hibernate Tools
 */
@Stateless
public class DateCostHome {

	private static final Log log = LogFactory.getLog(DateCostHome.class);

	@PersistenceContext
	private EntityManager entityManager;

	public void persist(DateCost transientInstance) {
		log.debug("persisting DateCost instance");
		try {
			entityManager.persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void remove(DateCost persistentInstance) {
		log.debug("removing DateCost instance");
		try {
			entityManager.remove(persistentInstance);
			log.debug("remove successful");
		} catch (RuntimeException re) {
			log.error("remove failed", re);
			throw re;
		}
	}

	public DateCost merge(DateCost detachedInstance) {
		log.debug("merging DateCost instance");
		try {
			DateCost result = entityManager.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public DateCost findById(DateCostId id) {
		log.debug("getting DateCost instance with id: " + id);
		try {
			DateCost instance = entityManager.find(DateCost.class, id);
			log.debug("get successful");
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
}
