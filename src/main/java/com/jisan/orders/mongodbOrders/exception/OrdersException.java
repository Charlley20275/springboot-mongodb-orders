package com.jisan.orders.mongodbOrders.exception;

public class OrdersException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public OrdersException(String message) {
		super(message);
	}
	public static String NotFoundException(String orderId) {
		return "Order with "+orderId+"not found";
	}
	public static String OrderAlreadyExist() {
		return "Order with given name already exist";
	}
}
