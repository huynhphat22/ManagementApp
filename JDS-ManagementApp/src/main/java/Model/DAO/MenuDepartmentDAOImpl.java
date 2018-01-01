package Model.DAO;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.transaction.annotation.Transactional;

import Model.DTO.MenuDepartment;
import Model.DTO.MenuDepartmentId;
import Model.MODEL.Page;
import Model.MODEL.PageQuery;

@Transactional
public class MenuDepartmentDAOImpl implements MenuDepartmentDAO {

	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public MenuDepartment save(MenuDepartment menuDepartment) {
		// TODO Auto-generated method stub
		Session session = this.sessionFactory.getCurrentSession();
		session.persist(menuDepartment);
		return menuDepartment;
	}

	@Override
	public MenuDepartment update(MenuDepartment menuDepartment) {
		// TODO Auto-generated method stub
		Session session = this.sessionFactory.getCurrentSession();
		session.update(menuDepartment);
		return menuDepartment;
	}

	@Override
	public void delete(MenuDepartmentId id) {
		// TODO Auto-generated method stub

		MenuDepartment menuDepartment = this.findById(id);
		if (menuDepartment != null) {
			Session session = this.sessionFactory.getCurrentSession();
			session.delete(menuDepartment);
		}

	}

	@Override
	public MenuDepartment findById(MenuDepartmentId id) {
		// TODO Auto-generated method stub
		Session session = this.sessionFactory.getCurrentSession();
		MenuDepartment menuDepartment = session.get(MenuDepartment.class, id);
		return menuDepartment;
	}

	@Override
	public Iterable<MenuDepartment> findAll() {
		// TODO Auto-generated method stub
		Session session = this.sessionFactory.getCurrentSession();
		Iterable<MenuDepartment> list = session.createQuery("From MenuDepartment").list();
		return list;
	}

	

	@Override
	public Page paginateMenuDepartment(PageQuery pageQuery, int departmentId) {
		// TODO Auto-generated method stub
		int start = (pageQuery.getPage() - 1) * pageQuery.getSize();
		long count = 0;
		long totalPages = 0;
		Session session = this.sessionFactory.getCurrentSession();

		Criteria criteria = session.createCriteria(MenuDepartment.class)
				.add(Restrictions.eq("id.departmentId", departmentId));
		Criteria criteriaCount = session.createCriteria(MenuDepartment.class).add(Restrictions.eq("id.departmentId", departmentId));
		criteria.setFirstResult(start);
		criteria.setMaxResults(pageQuery.getSize());
		criteria.addOrder(pageQuery.isAsc() ? Order.asc(pageQuery.getSortBy()) : Order.desc(pageQuery.getSortBy()));

		if(pageQuery.getSearchBy() != null && pageQuery.getSearchText() != null) {
			System.out.println(pageQuery.getSearchText() +  pageQuery.getSearchBy());
			Criterion criterion = null;
			try{
				int number = Integer.parseInt(pageQuery.getSearchText());
				criterion = Restrictions.eq(pageQuery.getSearchBy(), number);
			}
			catch(Exception e){
				criterion = Restrictions.like(pageQuery.getSearchBy(), pageQuery.getSearchText(), MatchMode.ANYWHERE);
			}
			criteria.add(criterion);
			criteriaCount.add(criterion);
		}
		
		Iterable<MenuDepartment> list = criteria.list();
		count = (long) criteriaCount.setProjection(Projections.rowCount()).uniqueResult();
		totalPages = (count % pageQuery.getSize() != 0) ? (count / pageQuery.getSize()) + 1
				: count / pageQuery.getSize();
		Page page = new Page(list, totalPages);
		System.out.println("count : " + count);
		System.out.println("page : " + page.getContent());
		return page;
	}

	@Override
	public Iterable<MenuDepartment> findAllByDepartmentId(int departmentId) {
		// TODO Auto-generated method stub
		Session session = this.sessionFactory.getCurrentSession();
		Query query = session.createQuery("From MenuDepartment md where md.id.departmentId = :departmentId");
		query.setParameter("departmentId", departmentId);
		Iterable<MenuDepartment> list = query.list();
		return list;
	}

}
