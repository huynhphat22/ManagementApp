package Model.DAO;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import Model.DTO.Food;

@Transactional
public class FoodDAOImpl implements FoodDAO {

	@Autowired
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public Food save(Food food) {
		// TODO Auto-generated method stub
		Session session = this.sessionFactory.getCurrentSession();
		session.persist(food);
		return food;
	}

	@Override
	public Food update(Food food) {
		// TODO Auto-generated method stub
		Session session = this.sessionFactory.getCurrentSession();
		session.update(food);
		return food;
	}

	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub
		Food food = this.findById(id);
		if (food != null) {
			Session session = this.sessionFactory.getCurrentSession();
			session.delete(food);
		}
	}

	@Override
	public Food findById(int id) {
		// TODO Auto-generated method stub
		Session session = this.sessionFactory.getCurrentSession();
		Food food = session.get(Food.class, id);
		return food;
	}

	@Override
	public Iterable<Food> findAll() {
		// TODO Auto-generated method stub
		Session session = this.sessionFactory.getCurrentSession();
		Iterable<Food> list = session.createQuery("FROM Food").list();
		return list;
	}


	@Override
	public long count() {
		// TODO Auto-generated method stub
		Session session = this.sessionFactory.getCurrentSession();
		Criteria criteriaCount = session.createCriteria(Food.class);
		criteriaCount.setProjection(Projections.rowCount());
		return (long) criteriaCount.uniqueResult();
	}

	@Override
	public Map<Food, Integer> findByDepartmentIdAndCategoryId(int departmentId, int categoryId, int page, String sort) {
		// TODO Auto-generated method stub
		int pageSize = 10;

		int start = (page - 1) * pageSize;
		Session session = this.sessionFactory.getCurrentSession();
		String hql = "SELECT f, md.price FROM Food f INNER JOIN Category c ON (c.categoryId = f.categoryId AND c.categoryId = "
				+ categoryId + ") "
				+ "INNER JOIN MenuDepartment md ON (md.id.foodId = f.foodId AND md.id.departmentId = " + departmentId
				+ ") " + "ORDER BY " + sort + " ASC";
		String hql2 = "SELECT f.foodId, f.image, f.foodName,f.flags , md.price FROM Food f INNER JOIN Category c ON (c.categoryId = f.categoryId AND c.categoryId = "
				+ categoryId + ") "
				+ "INNER JOIN MenuDepartment md ON (md.id.foodId = f.foodId AND md.id.departmentId = " + departmentId
				+ ") " + "ORDER BY " + sort + " ASC";

		Query query = (Query) session.createQuery(hql);
		query.setFirstResult(start);
		query.setMaxResults(pageSize);

		Iterable<Object[]> foods = (Iterable<Object[]>) query.list();
		Map<Food, Integer> foodsAndPrices = null;
		if (foods != null) {
			foodsAndPrices = new HashMap();
			for (Object[] food : foods) {
				foodsAndPrices.put((Food) food[0], (Integer) food[1]);
			}
		}

		return foodsAndPrices;
	}

}
