package Model.DAO;

import java.util.Map;

import Model.DTO.Food;
import Model.MODEL.Page;
import Model.MODEL.PageQuery;

public interface FoodDAO {
	
	public Food save(Food food);
	
	public Food update(Food food);

	public void delete(int id);

	public Food findById(int id);

	public Iterable<Food> findAll();

	public long count();

	public Page paginateFood(PageQuery pageQuery);
}
