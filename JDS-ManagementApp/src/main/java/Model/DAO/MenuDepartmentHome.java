package Model.DAO;
// Generated Nov 8, 2017 7:12:49 PM by Hibernate Tools 5.2.3.Final

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import Model.DTO.MenuDepartment;
import Model.DTO.MenuDepartmentId;

/**
 * Home object for domain model class MenuDepartment.
 * @see Model.DAO.MenuDepartment
 * @author Hibernate Tools
 */
@Stateless
public class MenuDepartmentHome {

	private static final Log log = LogFactory.getLog(MenuDepartmentHome.class);

	@PersistenceContext
	private EntityManager entityManager;

	public void persist(MenuDepartment transientInstance) {
		log.debug("persisting MenuDepartment instance");
		try {
			entityManager.persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void remove(MenuDepartment persistentInstance) {
		log.debug("removing MenuDepartment instance");
		try {
			entityManager.remove(persistentInstance);
			log.debug("remove successful");
		} catch (RuntimeException re) {
			log.error("remove failed", re);
			throw re;
		}
	}

	public MenuDepartment merge(MenuDepartment detachedInstance) {
		log.debug("merging MenuDepartment instance");
		try {
			MenuDepartment result = entityManager.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public MenuDepartment findById(MenuDepartmentId id) {
		log.debug("getting MenuDepartment instance with id: " + id);
		try {
			MenuDepartment instance = entityManager.find(MenuDepartment.class, id);
			log.debug("get successful");
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
}
