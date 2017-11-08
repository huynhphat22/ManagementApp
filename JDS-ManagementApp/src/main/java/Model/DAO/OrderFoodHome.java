package Model.DAO;
// Generated Nov 8, 2017 7:12:49 PM by Hibernate Tools 5.2.3.Final

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import Model.DTO.OrderFood;

/**
 * Home object for domain model class OrderFood.
 * @see Model.DAO.OrderFood
 * @author Hibernate Tools
 */
@Stateless
public class OrderFoodHome {

	private static final Log log = LogFactory.getLog(OrderFoodHome.class);

	@PersistenceContext
	private EntityManager entityManager;

	public void persist(OrderFood transientInstance) {
		log.debug("persisting OrderFood instance");
		try {
			entityManager.persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void remove(OrderFood persistentInstance) {
		log.debug("removing OrderFood instance");
		try {
			entityManager.remove(persistentInstance);
			log.debug("remove successful");
		} catch (RuntimeException re) {
			log.error("remove failed", re);
			throw re;
		}
	}

	public OrderFood merge(OrderFood detachedInstance) {
		log.debug("merging OrderFood instance");
		try {
			OrderFood result = entityManager.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public OrderFood findById(Integer id) {
		log.debug("getting OrderFood instance with id: " + id);
		try {
			OrderFood instance = entityManager.find(OrderFood.class, id);
			log.debug("get successful");
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
}
