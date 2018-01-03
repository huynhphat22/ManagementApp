package Model.MODEL;

public class FoodInfo {

	private int foodId;
	private int price;
	private int quantity;
	private String foodName;
	private String image;
	
	public FoodInfo(){}

	public FoodInfo(int foodId, int price, int quantity, String foodName, String image) {
		this.foodId = foodId;
		this.price = price;
		this.quantity = quantity;
		this.foodName = foodName;
		this.image = image;
	}

	public int getFoodId() {
		return foodId;
	}

	public void setFoodId(int foodId) {
		this.foodId = foodId;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public String getFoodName() {
		return foodName;
	}

	public void setFoodName(String foodName) {
		this.foodName = foodName;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}
	
	
	

	
	
}
