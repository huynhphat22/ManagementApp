package Model.DTO;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * OrderDetail generated by hbm2java
 */
@Entity
@Table(name = "order_detail", catalog = "restaurant")
public class OrderDetail  {

	private OrderDetailId id;
	private short quantity;
	private String status;
	private Boolean flags;

	public OrderDetail() {
	}

	public OrderDetail(OrderDetailId id, short quantity) {
		this.id = id;
		this.quantity = quantity;
	}

	public OrderDetail(OrderDetailId id, short quantity, String status, Boolean flags) {
		this.id = id;
		this.quantity = quantity;
		this.status = status;
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
	public short getQuantity() {
		return this.quantity;
	}

	public void setQuantity(short quantity) {
		this.quantity = quantity;
	}

	@Column(name = "status", length = 60)
	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Column(name = "flags")
	public Boolean getFlags() {
		return this.flags;
	}

	public void setFlags(Boolean flags) {
		this.flags = flags;
	}

}
