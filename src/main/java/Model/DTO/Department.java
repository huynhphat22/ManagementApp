// default package
// Generated Dec 25, 2017 5:48:08 PM by Hibernate Tools 5.1.4.Final
package Model.DTO;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Department generated by hbm2java
 */
@Entity
@Table(name = "department", catalog = "restaurant")
public class Department {

	private Integer departmentId;
	private String departmentName;
	private String address;
	private String phoneNumber;
	private short numberOfTable;
	private Date dateCreated;
	private Boolean flags;

	public Department() {
	}

	public Department(String departmentName, String address, String phoneNumber, short numberOfTable,
			Date dateCreated) {
		this.departmentName = departmentName;
		this.address = address;
		this.phoneNumber = phoneNumber;
		this.numberOfTable = numberOfTable;
		this.dateCreated = dateCreated;
	}

	public Department(String departmentName, String address, String phoneNumber, short numberOfTable, Date dateCreated,
			Boolean flags) {
		this.departmentName = departmentName;
		this.address = address;
		this.phoneNumber = phoneNumber;
		this.numberOfTable = numberOfTable;
		this.dateCreated = dateCreated;
		this.flags = flags;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "departmentID", unique = true, nullable = false)
	public Integer getDepartmentId() {
		return this.departmentId;
	}

	public void setDepartmentId(Integer departmentId) {
		this.departmentId = departmentId;
	}

	@Column(name = "departmentName", nullable = false, length = 100)
	public String getDepartmentName() {
		return this.departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

	@Column(name = "address", nullable = false, length = 200)
	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Column(name = "phoneNumber", nullable = false, length = 20)
	public String getPhoneNumber() {
		return this.phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	@Column(name = "numberOfTable", nullable = false)
	public short getNumberOfTable() {
		return this.numberOfTable;
	}

	public void setNumberOfTable(short numberOfTable) {
		this.numberOfTable = numberOfTable;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "dateCreated", nullable = false, length = 10)
	public Date getDateCreated() {
		return this.dateCreated;
	}

	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}

	@Column(name = "flags")
	public Boolean getFlags() {
		return this.flags;
	}

	public void setFlags(Boolean flags) {
		this.flags = flags;
	}

}
