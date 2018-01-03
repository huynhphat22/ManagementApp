package Model.DTO;


import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;


@Embeddable
public class MenuDepartmentId implements  Serializable   {

	private int departmentId;
	private int foodId;

	public MenuDepartmentId() {
	}

	public MenuDepartmentId(int departmentId, int foodId) {
		this.departmentId = departmentId;
		this.foodId = foodId;
	}

	@Column(name = "departmentID", nullable = false)
	public int getDepartmentId() {
		return this.departmentId;
	}

	public void setDepartmentId(int departmentId) {
		this.departmentId = departmentId;
	}

	@Column(name = "foodID", nullable = false)
	public int getFoodId() {
		return this.foodId;
	}

	public void setFoodId(int foodId) {
		this.foodId = foodId;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof MenuDepartmentId))
			return false;
		MenuDepartmentId castOther = (MenuDepartmentId) other;

		return (this.getDepartmentId() == castOther.getDepartmentId()) && (this.getFoodId() == castOther.getFoodId());
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + this.getDepartmentId();
		result = 37 * result + this.getFoodId();
		return result;
	}

}
