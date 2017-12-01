package Model.DTO;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


/**
 * MenuDepartment generated by hbm2java
 */
@Entity
@Table(name = "menu_department", catalog = "restaurant")
public class MenuDepartment  {

	private MenuDepartmentId id;
	private Department department;
	private Food food;
	private int price;
	private Boolean flags;

	public MenuDepartment() {
	}

	

	public MenuDepartment(MenuDepartmentId id, Department department,
			Food food, int price, Boolean flags) {
		this.id = id;
		this.department = department;
		this.food = food;
		this.price = price;
		this.flags = flags;
	}



	@EmbeddedId

	@AttributeOverrides({
			@AttributeOverride(name = "departmentId", column = @Column(name = "departmentID", nullable = false)),
			@AttributeOverride(name = "foodId", column = @Column(name = "foodID", nullable = false)) })
	public MenuDepartmentId getId() {
		return this.id;
	}

	public void setId(MenuDepartmentId id) {
		this.id = id;
	}

	@Column(name = "price", nullable = false)
	public int getPrice() {
		return this.price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	@Column(name = "flags")
	public Boolean getFlags() {
		return this.flags;
	}

	public void setFlags(Boolean flags) {
		this.flags = flags;
	}


	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "departmentID", nullable = false, insertable = false,  updatable = false)
	public Department getDepartment() {
		return department;
	}



	public void setDepartment(Department department) {
		this.department = department;
	}


	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "foodID", nullable = false, insertable = false,  updatable = false)
	public Food getFood() {
		return food;
	}



	public void setFood(Food food) {
		this.food = food;
	}	

}
