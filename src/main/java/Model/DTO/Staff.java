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
 * Staff generated by hbm2java
 */
@Entity
@Table(name = "staff", catalog = "restaurant")
public class Staff  {

	private Integer staffId;
	private String staffName;
	private String address;
	private String phoneNumber;
	private String role;
	private int salary;
	private int departmentId;
	private String username;
	private String password;
	private Date dateCreated;
	private Boolean flags;

	public Staff() {
	}

	public Staff(String staffName, String address, String phoneNumber, String role, int salary, int departmentId,
			String username, String password, Date dateCreated) {
		this.staffName = staffName;
		this.address = address;
		this.phoneNumber = phoneNumber;
		this.role = role;
		this.salary = salary;
		this.departmentId = departmentId;
		this.username = username;
		this.password = password;
		this.dateCreated = dateCreated;
	}

	public Staff(String staffName, String address, String phoneNumber, String role, int salary, int departmentId,
			String username, String password, Date dateCreated, Boolean flags) {
		this.staffName = staffName;
		this.address = address;
		this.phoneNumber = phoneNumber;
		this.role = role;
		this.salary = salary;
		this.departmentId = departmentId;
		this.username = username;
		this.password = password;
		this.dateCreated = dateCreated;
		this.flags = flags;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "staffID", unique = true, nullable = false)
	public Integer getStaffId() {
		return this.staffId;
	}

	public void setStaffId(Integer staffId) {
		this.staffId = staffId;
	}

	@Column(name = "staffName", nullable = false, length = 100)
	public String getStaffName() {
		return this.staffName;
	}

	public void setStaffName(String staffName) {
		this.staffName = staffName;
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

	@Column(name = "role", nullable = false, length = 50)
	public String getRole() {
		return this.role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	@Column(name = "salary", nullable = false)
	public int getSalary() {
		return this.salary;
	}

	public void setSalary(int salary) {
		this.salary = salary;
	}

	@Column(name = "departmentID", nullable = false)
	public int getDepartmentId() {
		return this.departmentId;
	}

	public void setDepartmentId(int departmentId) {
		this.departmentId = departmentId;
	}

	@Column(name = "username", nullable = false, length = 100)
	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Column(name = "password", nullable = false, length = 100)
	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
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