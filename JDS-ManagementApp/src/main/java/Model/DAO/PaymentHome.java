package Model.DAO;
// Generated Nov 8, 2017 7:12:49 PM by Hibernate Tools 5.2.3.Final

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import Model.DTO.Payment;

/**
 * Home object for domain model class Payment.
 * @see Model.DAO.Payment
 * @author Hibernate Tools
 */
@Stateless
public class PaymentHome {

	private static final Log log = LogFactory.getLog(PaymentHome.class);

	@PersistenceContext
	private EntityManager entityManager;

	public void persist(Payment transientInstance) {
		log.debug("persisting Payment instance");
		try {
			entityManager.persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void remove(Payment persistentInstance) {
		log.debug("removing Payment instance");
		try {
			entityManager.remove(persistentInstance);
			log.debug("remove successful");
		} catch (RuntimeException re) {
			log.error("remove failed", re);
			throw re;
		}
	}

	public Payment merge(Payment detachedInstance) {
		log.debug("merging Payment instance");
		try {
			Payment result = entityManager.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public Payment findById(Integer id) {
		log.debug("getting Payment instance with id: " + id);
		try {
			Payment instance = entityManager.find(Payment.class, id);
			log.debug("get successful");
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
}
