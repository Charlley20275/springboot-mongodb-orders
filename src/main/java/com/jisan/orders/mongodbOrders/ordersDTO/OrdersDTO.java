package com.jisan.orders.mongodbOrders.ordersDTO;

import java.util.Date;

import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.Id;

import com.fasterxml.jackson.annotation.JsonRootName;

@JsonRootName(value="data")
public class OrdersDTO {

	@Id
	private String orderId;
	public OrdersDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public OrdersDTO(String orderId, String productName, Boolean status, Date orderDate, Date deliveryDate) {
		super();
		this.orderId = orderId;
		this.productName = productName;
		this.status = status;
		this.orderDate = orderDate;
		this.deliveryDate = deliveryDate;
	}
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public Boolean getStatus() {
		return status;
	}
	public void setStatus(Boolean status) {
		this.status = status;
	}
	public Date getOrderDate() {
		return orderDate;
	}
	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}
	public Date getDeliveryDate() {
		return deliveryDate;
	}
	public void setDeliveryDate(Date deliveryDate) {
		this.deliveryDate = deliveryDate;
	}
	@NotNull(message ="Product name is mandatory")
	private String productName;
	@NotNull(message = "Please specify the order status as true/false")
	private Boolean status;
	private Date orderDate;
	private Date deliveryDate;
	
	
	
}
