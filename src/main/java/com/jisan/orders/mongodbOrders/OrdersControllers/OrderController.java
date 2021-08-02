package com.jisan.orders.mongodbOrders.OrdersControllers;

import java.util.List;
import javax.validation.ConstraintViolationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.jisan.orders.mongodbOrders.Repository.ordersRepository;
import com.jisan.orders.mongodbOrders.Service.OrdersService;
import com.jisan.orders.mongodbOrders.exception.OrdersException;
import com.jisan.orders.mongodbOrders.ordersDTO.OrdersDTO;
@RestController
public class OrderController {
@Autowired
private ordersRepository ordersRepo;
@Autowired
private OrdersService ordersService;

@GetMapping("/orders")
public ResponseEntity<?> getAllOrders()
{
	List<OrdersDTO> orders =ordersService.getAllOrders();
	return new ResponseEntity<>(orders,orders.size()>0 ? HttpStatus.OK:HttpStatus.NOT_FOUND);
}




@GetMapping("/orders/{orderId}")
public ResponseEntity<?> getSingleOrder(@PathVariable("orderId") String orderId){
	try {
		return new ResponseEntity<>(ordersService.getSingleOrder(orderId),HttpStatus.OK);
	} catch (Exception e) {
		return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
	}
}




@PostMapping("/orders")
public ResponseEntity<?> createOrders(@RequestBody OrdersDTO orders)
{
	try {
			ordersService.createOrders(orders);
		return new ResponseEntity<OrdersDTO>(orders,HttpStatus.OK);
	} catch (ConstraintViolationException e) {
		
		return new ResponseEntity<>(e.getMessage(),HttpStatus.UNPROCESSABLE_ENTITY);
	}catch (OrdersException e) {
		return new ResponseEntity<>(e.getMessage(),HttpStatus.CONFLICT);
	}
}

@PutMapping("/orders/{orderId}")
public ResponseEntity<?> updateOrders(@PathVariable("orderId") String orderId,@RequestBody OrdersDTO orders)
{
	try {
		ordersService.updateOrder(orderId, orders);
		return new ResponseEntity<>("Update order with id "+orderId,HttpStatus.OK);
	} catch (ConstraintViolationException e) {
		return new ResponseEntity<>(e.getMessage(),HttpStatus.UNPROCESSABLE_ENTITY);
	}catch (OrdersException e) {
		// TODO: handle exception
		return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
	}
	
}

@DeleteMapping("/orders/{orderId}")
public ResponseEntity<?> deleteById(@PathVariable("orderId")String orderId){
	try {
		ordersService.deleteOrderById(orderId);
		return new ResponseEntity<>("Successfully deleted by id "+orderId,HttpStatus.OK);
	} catch (OrdersException e) {
		// TODO: handle exception
		return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
	}
}





}


