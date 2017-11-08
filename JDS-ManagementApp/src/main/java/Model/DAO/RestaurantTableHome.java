package Model.DAO;
// Generated Nov 8, 2017 7:12:49 PM by Hibernate Tools 5.2.3.Final

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import Model.DTO.RestaurantTable;

/**
 * Home object for domain model class RestaurantTable.
 * @see Model.DAO.RestaurantTable
 * @author Hibernate Tools
 */
@Stateless
public class RestaurantTableHome {

	private static final Log log = LogFactory.getLog(RestaurantTableHome.class);

	@PersistenceContext
	private EntityManager entityManager;

	public void persist(RestaurantTable transientInstance) {
		log.debug("persisting RestaurantTable instance");
		try {
			entityManager.persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void remove(RestaurantTable persistentInstance) {
		log.debug("removing RestaurantTable instance");
		try {
			entityManager.remove(persistentInstance);
			log.debug("remove successful");
		} catch (RuntimeException re) {
			log.error("remove failed", re);
			throw re;
		}
	}

	public RestaurantTable merge(RestaurantTable detachedInstance) {
		log.debug("merging RestaurantTable instance");
		try {
			RestaurantTable result = entityManager.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public RestaurantTable findById(Integer id) {
		log.debug("getting RestaurantTable instance with id: " + id);
		try {
			RestaurantTable instance = entityManager.find(RestaurantTable.class, id);
			log.debug("get successful");
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
}
