package com.lagasselucas.dsdeliver.services;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lagasselucas.dsdeliver.dto.OrderDTO;
import com.lagasselucas.dsdeliver.dto.ProductDTO;
import com.lagasselucas.dsdeliver.entities.Order;
import com.lagasselucas.dsdeliver.entities.OrderStatus;
import com.lagasselucas.dsdeliver.entities.Product;
import com.lagasselucas.dsdeliver.repositories.OrderRepository;
import com.lagasselucas.dsdeliver.repositories.ProductRepository;

@Service
public class OrderService {
	
	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	private ProductRepository productRepository;
	
	@Transactional(readOnly = true)
	public List<OrderDTO> findAll(){
		List<Order> listOrders = orderRepository.findAllOrdersWithProducts();
		return listOrders.stream().map(x -> new OrderDTO(x)).collect(Collectors.toList());
	}
	
	@Transactional
	public OrderDTO insert(OrderDTO dto){
		
		Order order = new Order(null, dto.getAddress(),dto.getLatitude(),dto.getLongitude(),
				Instant.now(), OrderStatus.PENDING);
		
		for (ProductDTO p : dto.getProducts()) {
			Product product = productRepository.getOne(p.getId());
			order.getProducts().add(product);
		}
		
		order = orderRepository.save(order);
		return new OrderDTO(order);
	}
	
	@Transactional
	public OrderDTO setDelivered(Long id){
		
		Order order = orderRepository.getOne(id); 
		
		order.setStatus(OrderStatus.DELIVERED);
		
		order = orderRepository.save(order);
		
		return new OrderDTO(order);
	}
	
	

}
