package Model.DTO;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * DateRevenueId generated by hbm2java
 */
@Embeddable
public class DateRevenueId implements  Serializable {

	private int departmentId;
	private Date dateOfRevenue;

	public DateRevenueId() {
	}

	public DateRevenueId(int departmentId, Date dateOfRevenue) {
		this.departmentId = departmentId;
		this.dateOfRevenue = dateOfRevenue;
	}

	@Column(name = "departmentID", nullable = false)
	public int getDepartmentId() {
		return this.departmentId;
	}

	public void setDepartmentId(int departmentId) {
		this.departmentId = departmentId;
	}

	@Column(name = "dateOfRevenue", nullable = false, length = 19)
	public Date getDateOfRevenue() {
		return this.dateOfRevenue;
	}

	public void setDateOfRevenue(Date dateOfRevenue) {
		this.dateOfRevenue = dateOfRevenue;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof DateRevenueId))
			return false;
		DateRevenueId castOther = (DateRevenueId) other;

		return (this.getDepartmentId() == castOther.getDepartmentId())
				&& ((this.getDateOfRevenue() == castOther.getDateOfRevenue())
						|| (this.getDateOfRevenue() != null && castOther.getDateOfRevenue() != null
								&& this.getDateOfRevenue().equals(castOther.getDateOfRevenue())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + this.getDepartmentId();
		result = 37 * result + (getDateOfRevenue() == null ? 0 : this.getDateOfRevenue().hashCode());
		return result;
	}

}
