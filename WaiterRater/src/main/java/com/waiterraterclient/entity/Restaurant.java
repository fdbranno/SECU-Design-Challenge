package com.waiterraterclient.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "restaurants")
public class Restaurant implements Serializable {
	
	private static final Long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "restaurant_id")
	private Long restaurantId;
	
	@Column(name = "restaurant_status")
	private boolean status;

	@Column(name = "restaurant_name")
	private String restaurantName;
	
	public Long getRestaurantId() {
		return restaurantId;
	}

	public boolean getStatus() {
		return status;
	}

	public String getRestaurantName() {
		return restaurantName;
	}
	
	public void setStatus(boolean status) {
		this.status = status;
	}

	public void setRestaurantName(String name) {
		restaurantName = name;
	}
}
