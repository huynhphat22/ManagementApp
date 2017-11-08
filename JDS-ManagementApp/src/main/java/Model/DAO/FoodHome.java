package Model.DAO;
// Generated Nov 8, 2017 7:12:49 PM by Hibernate Tools 5.2.3.Final

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import Model.DTO.Food;

/**
 * Home object for domain model class Food.
 * @see Model.DAO.Food
 * @author Hibernate Tools
 */
@Stateless
public class FoodHome {

	private static final Log log = LogFactory.getLog(FoodHome.class);

	@PersistenceContext
	private EntityManager entityManager;

	public void persist(Food transientInstance) {
		log.debug("persisting Food instance");
		try {
			entityManager.persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void remove(Food persistentInstance) {
		log.debug("removing Food instance");
		try {
			entityManager.remove(persistentInstance);
			log.debug("remove successful");
		} catch (RuntimeException re) {
			log.error("remove failed", re);
			throw re;
		}
	}

	public Food merge(Food detachedInstance) {
		log.debug("merging Food instance");
		try {
			Food result = entityManager.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public Food findById(Integer id) {
		log.debug("getting Food instance with id: " + id);
		try {
			Food instance = entityManager.find(Food.class, id);
			log.debug("get successful");
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
}
