package Model.DAO;
// Generated Nov 8, 2017 7:12:49 PM by Hibernate Tools 5.2.3.Final

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import Model.DTO.Staff;

/**
 * Home object for domain model class Staff.
 * @see Model.DAO.Staff
 * @author Hibernate Tools
 */
@Stateless
public class StaffHome {

	private static final Log log = LogFactory.getLog(StaffHome.class);

	@PersistenceContext
	private EntityManager entityManager;

	public void persist(Staff transientInstance) {
		log.debug("persisting Staff instance");
		try {
			entityManager.persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void remove(Staff persistentInstance) {
		log.debug("removing Staff instance");
		try {
			entityManager.remove(persistentInstance);
			log.debug("remove successful");
		} catch (RuntimeException re) {
			log.error("remove failed", re);
			throw re;
		}
	}

	public Staff merge(Staff detachedInstance) {
		log.debug("merging Staff instance");
		try {
			Staff result = entityManager.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public Staff findById(Integer id) {
		log.debug("getting Staff instance with id: " + id);
		try {
			Staff instance = entityManager.find(Staff.class, id);
			log.debug("get successful");
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
}
