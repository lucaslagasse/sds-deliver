package com.lagasselucas.dsdeliver.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lagasselucas.dsdeliver.dto.OrderDTO;
import com.lagasselucas.dsdeliver.entities.Order;
import com.lagasselucas.dsdeliver.repositories.OrderRepository;

@Service
public class OrderService {
	
	@Autowired
	private OrderRepository orderRepository;
	
	@Transactional(readOnly = true)
	public List<OrderDTO> findAll(){
		List<Order> listOrders = orderRepository.findAll();
		return listOrders.stream().map(x -> new OrderDTO(x)).collect(Collectors.toList());
	}

}
