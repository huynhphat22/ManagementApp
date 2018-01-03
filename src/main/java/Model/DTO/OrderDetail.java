// default package
// Generated Dec 25, 2017 5:48:08 PM by Hibernate Tools 5.1.4.Final
package Model.DTO;
import java.util.Date;
import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * OrderDetail generated by hbm2java
 */
@Entity
@Table(name = "order_detail", catalog = "restaurant")
public class OrderDetail  {

	private OrderDetailId id;
	private int quantity;
	private String status;
	private Date dateCreated;
	private Boolean flags;

	public OrderDetail() {
	}

	public OrderDetail(OrderDetailId id, int quantity, Date dateCreated) {
		this.id = id;
		this.quantity = quantity;
		this.dateCreated = dateCreated;
	}

	public OrderDetail(OrderDetailId id, int quantity, String status, Date dateCreated, Boolean flags) {
		this.id = id;
		this.quantity = quantity;
		this.status = status;
		this.dateCreated = dateCreated;
		this.flags = flags;
	}

	@EmbeddedId

	@AttributeOverrides({ @AttributeOverride(name = "orderId", column = @Column(name = "orderID", nullable = false)),
			@AttributeOverride(name = "foodId", column = @Column(name = "foodID", nullable = false)) })
	public OrderDetailId getId() {
		return this.id;
	}

	public void setId(OrderDetailId id) {
		this.id = id;
	}

	@Column(name = "quantity", nullable = false)
	public int getQuantity() {
		return this.quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	@Column(name = "status", length = 60)
	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
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
