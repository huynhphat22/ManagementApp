package Model.DTO;
// Generated Nov 8, 2017 7:11:15 PM by Hibernate Tools 5.2.3.Final

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Customer generated by hbm2java
 */
@Entity
@Table(name = "customer", catalog = "restaurant")
public class Customer implements java.io.Serializable {

	private Integer custumerId;
	private String customerName;
	private String address;
	private String phoneNumber;
	private String customerType;
	private Boolean flags;

	public Customer() {
	}

	public Customer(String customerName, String address, String phoneNumber, String customerType) {
		this.customerName = customerName;
		this.address = address;
		this.phoneNumber = phoneNumber;
		this.customerType = customerType;
	}

	public Customer(String customerName, String address, String phoneNumber, String customerType, Boolean flags) {
		this.customerName = customerName;
		this.address = address;
		this.phoneNumber = phoneNumber;
		this.customerType = customerType;
		this.flags = flags;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "custumerID", unique = true, nullable = false)
	public Integer getCustumerId() {
		return this.custumerId;
	}

	public void setCustumerId(Integer custumerId) {
		this.custumerId = custumerId;
	}

	@Column(name = "customerName", nullable = false, length = 100)
	public String getCustomerName() {
		return this.customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
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

	@Column(name = "customerType", nullable = false, length = 50)
	public String getCustomerType() {
		return this.customerType;
	}

	public void setCustomerType(String customerType) {
		this.customerType = customerType;
	}

	@Column(name = "flags")
	public Boolean getFlags() {
		return this.flags;
	}

	public void setFlags(Boolean flags) {
		this.flags = flags;
	}

}
