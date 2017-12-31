package Model.DAO;

import java.util.Date;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import Model.DTO.Category;
import Model.DTO.Department;
import Model.MODEL.Page;
import Model.MODEL.PageQuery;



@Transactional
public class CategoryDAOImpl implements CategoryDAO{

	private SessionFactory sessionFactory;
	

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public Category save(Category category) {
		// TODO Auto-generated method stub
		Session session = this.sessionFactory.getCurrentSession();
		category.setFlags(true);
		category.setDateCreated(new Date());
		session.persist(category);
		return category;
	}

	@Override
	public Category update(Category category) {
		// TODO Auto-generated method stub
		Session session = this.sessionFactory.getCurrentSession();
		session.update(category);
		return category;
	}

	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub
		Session session = this.sessionFactory.getCurrentSession();
		Category category = this.findById(id);
		if(category != null) {
			category.setFlags(false);
			session.update(category);
		}
	}

	@Override
	public Category findById(int id) {
		// TODO Auto-generated method stub
		Session session = this.sessionFactory.getCurrentSession();
		Category category = session.get(Category.class, id);
		return category;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Iterable<Category> findAll() {
		// TODO Auto-generated method stub
		Session session = this.sessionFactory.getCurrentSession();
		Iterable<Category> list = session.createQuery("from Category").list();
		return list;
	}

	

	

	/*public Iterable<Category> findByDepartmentId(int departmentId) {
		// TODO Auto-generated method stub
		
		Session session = this.sessionFactory.getCurrentSession();
		String hql = "SELECT DISTINCT c FROM Category c INNER JOIN Food f ON c.categoryId = f.categoryId "
				+ "INNER JOIN MenuDepartment md ON (f.foodId = md.id.foodId and md.id.departmentId = " + departmentId +  ")";
		Iterable<Category> list = session.createQuery(hql).list();
		return list;
	}*/

	@Override
	public Page paginateCategory(PageQuery pageQuery) {
		// TODO Auto-generated method stub
		int start = (pageQuery.getPage() - 1) * pageQuery.getSize();
		long count = 0;
		long totalPages = 0;
		Session session = this.sessionFactory.getCurrentSession();

		Criteria criteria = session.createCriteria(Category.class);
		Criteria criteriaCount = session.createCriteria(Category.class);
		criteria.setFirstResult(start);
		criteria.setMaxResults(pageQuery.getSize());
		criteria.addOrder(pageQuery.isAsc() ? Order.asc(pageQuery.getSortBy()) : Order.desc(pageQuery.getSortBy()));
		
		if(pageQuery.getSearchBy() != null && pageQuery.getSearchText() != null) {
			System.out.println(pageQuery.getSearchText() +  pageQuery.getSearchBy());
			Criterion criterion = Restrictions.like(pageQuery.getSearchBy(), pageQuery.getSearchText(), MatchMode.ANYWHERE);
			criteria.add(criterion);
			criteriaCount.add(criterion);
		}
		
		Iterable<Category> list = criteria.list();
		count = (long) criteriaCount.setProjection(Projections.rowCount()).uniqueResult();
		totalPages = (count % pageQuery.getSize() != 0) ? (count/pageQuery.getSize()) + 1 : count/pageQuery.getSize();
		Page page = new Page(list , totalPages);
		System.out.println("count : " + count );
		System.out.println("page : "  + page.getContent());
		return page;
	}

	@Override
	public Page findByDepartmentId(PageQuery pageQuery, int departmentId) {
		// TODO Auto-generated method stub
		return null;
	}

}
