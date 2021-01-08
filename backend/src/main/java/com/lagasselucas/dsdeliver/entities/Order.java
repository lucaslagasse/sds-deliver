package com.lagasselucas.dsdeliver.entities;

import java.time.Instant;
import java.util.Set;

public class Order {
	
	private Long id;
	private String address;
	private Double latitude;
	private Double longitude;
	private Instant moment;
	private OrderStatus status;
	
	private Set<Product> products;

}
