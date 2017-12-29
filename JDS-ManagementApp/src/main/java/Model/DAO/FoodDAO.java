package Model.DAO;

import Model.DTO.Food;
import Model.MODEL.Page;
import Model.MODEL.PageQuery;

public interface FoodDAO {

public Food save(Food food);
	
	public Food update(Food food);
	
	public void delete(int id);
	
	public Food findById(int id);
	
	public Iterable<Food> findAll();
	
	public Page paginateFood(PageQuery pageQuery);
}
