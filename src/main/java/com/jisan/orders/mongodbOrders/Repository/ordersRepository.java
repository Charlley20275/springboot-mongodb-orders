package com.jisan.orders.mongodbOrders.Repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.jisan.orders.mongodbOrders.ordersDTO.OrdersDTO;
@Repository
public interface ordersRepository extends MongoRepository<OrdersDTO, String>
{
	@Query("{productName:?0}")
	Optional<OrdersDTO> findByOrder(String orders);
}
