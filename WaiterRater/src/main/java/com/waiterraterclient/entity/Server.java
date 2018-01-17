package com.waiterraterclient.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "servers")
public class Server implements Serializable {
	
	private static final Long serialVersionUID = 1L;
	
    @Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "server_id")
    private Long serverId;
	
	@Column(name = "server_first_name")
	private String firstName;

	@Column(name = "server_last_name")
	private String lastName;

	@ManyToOne
	@JoinColumn(name = "server_restaurant", referencedColumnName = "restaurant_id")
	private Restaurant restaurant;
	
	@Column(name = "server_status")
	private boolean status;
	
	public Long getServerId() {
		return serverId;
	}
	
	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public Restaurant getRestaurant() { return restaurant; }
	
	public boolean getStatus() {
		return status;
	}

	public void setFirstName(String first) {
		firstName = first;
	}

	public void setLastName(String last) {
		lastName = last;
	}
	
	public void setRestaurant(Restaurant restaurant) {
		this.restaurant = restaurant;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}
}
