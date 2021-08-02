package com.jisan.orders.mongodbOrders.Service;

import java.util.List;

import javax.validation.ConstraintViolationException;

import com.jisan.orders.mongodbOrders.exception.OrdersException;
import com.jisan.orders.mongodbOrders.ordersDTO.OrdersDTO;

public interface OrdersService {
 public void createOrders(OrdersDTO order)throws ConstraintViolationException,OrdersException;
 public  List<OrdersDTO> getAllOrders();
 public OrdersDTO getSingleOrder(String orderId) throws OrdersException;
 public void updateOrder(String orderId,OrdersDTO order) throws OrdersException;
 public void deleteOrderById(String orderId) throws OrdersException;
}
