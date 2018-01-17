package com.waiterraterclient.dao;

import com.waiterraterclient.entity.Restaurant;

import java.util.List;

public interface IRestaurantDAO {

    List<Restaurant> getAllRestaurants();

	Restaurant getRestaurantById(Long restaurantId);

	void addRestaurant(Restaurant restaurant);

	void updateRestaurant(Restaurant restaurant);

	void deleteRestaurant(Long restaurantId);

	boolean restaurantExists(Long restaurantId);
} 