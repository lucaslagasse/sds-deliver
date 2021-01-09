package com.lagasselucas.dsdeliver.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lagasselucas.dsdeliver.entities.Order;

public interface OrderRepository extends JpaRepository<Order, Long>{

}
