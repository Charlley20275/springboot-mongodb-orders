package com.jisan.orders.mongodbOrders.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.validation.ConstraintViolationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jisan.orders.mongodbOrders.Repository.ordersRepository;
import com.jisan.orders.mongodbOrders.exception.OrdersException;
import com.jisan.orders.mongodbOrders.ordersDTO.OrdersDTO;

@Service
public class ServiceImpl implements OrdersService {
	@Autowired
	private ordersRepository orderRepo;
	@Override
	public void createOrders(OrdersDTO order) throws ConstraintViolationException,OrdersException{
		
		Optional<OrdersDTO> ordersOptional= orderRepo.findByOrder(order.getProductName());
		if(ordersOptional.isPresent()) {
			throw new OrdersException(OrdersException.OrderAlreadyExist());
			
		}else {
			order.setOrderDate(new Date(System.currentTimeMillis()));
			orderRepo.save(order);
		}
	}
	@Override
	public List<OrdersDTO> getAllOrders() {
		List<OrdersDTO> orders=orderRepo.findAll();
		if (orders.size()>0) {
			return orders;
			
		}else {
			return new ArrayList<OrdersDTO>();
		}
		
	}
	@Override
	public OrdersDTO getSingleOrder(String orderId) throws OrdersException {
		Optional<OrdersDTO> optionalOrders=orderRepo.findById(orderId);
		if (!optionalOrders.isPresent()) {
			throw new OrdersException(OrdersException.NotFoundException(orderId));
		}else {
			return optionalOrders.get();		}
	}
	
	@Override
	public void updateOrder(String orderId, OrdersDTO order) throws OrdersException{
		Optional<OrdersDTO> orderWithId=orderRepo.findById(orderId);
		Optional<OrdersDTO> orderSameName=orderRepo.findByOrder(order.getProductName());
		
		if (orderWithId.isPresent()) {
			
			if (orderSameName.isPresent() && orderSameName.get().getOrderId().equals(orderId)) {
				throw new OrdersException(OrdersException.OrderAlreadyExist());
			}
			
			
			
			OrdersDTO orderToUpdate= orderWithId.get();
			orderToUpdate.setProductName(order.getProductName());
			orderToUpdate.setStatus(order.getStatus());
			orderToUpdate.setOrderDate(order.getOrderDate());
			if (orderToUpdate.getStatus()==true) {
				orderToUpdate.setDeliveryDate(new Date(System.currentTimeMillis()));	
			}
			orderRepo.save(orderToUpdate);
		}else {
			throw new OrdersException(OrdersException.NotFoundException(orderId));
		}
	}
	@Override
	public void deleteOrderById(String orderId) throws OrdersException {
		Optional<OrdersDTO> orderOptional=orderRepo.findById(orderId);
		if (!orderOptional.isPresent()) {
			throw new OrdersException(OrdersException.NotFoundException(orderId));
		}else {
			orderRepo.deleteById(orderId);
		}
		
	}

}
