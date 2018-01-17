package com.waiterraterclient.service;

import com.waiterraterclient.entity.Restaurant;

import java.util.List;

public interface IRestaurantService {
    List<Restaurant> getAllRestaurants();
    
	Restaurant getRestaurantById(Long restaurantId);
    
	boolean addRestaurant(Restaurant restaurant);
    
	void updateRestaurant(Restaurant restaurant);
    
	void deleteRestaurant(Long restaurantId);

	boolean restaurantExists(Long restaurantId);
}