package com.waiterraterclient.service;

import com.waiterraterclient.dao.IRestaurantDAO;
import com.waiterraterclient.entity.Restaurant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RestaurantService implements IRestaurantService {
	@Autowired
	private IRestaurantDAO restaurantDAO;
	
	@Override
	public Restaurant getRestaurantById(Long restaurantId) {
		Restaurant obj = restaurantDAO.getRestaurantById(restaurantId);
		return obj;
	}
	
	@Override
	public List<Restaurant> getAllRestaurants(){
		return restaurantDAO.getAllRestaurants();
	}
	
	@Override
	public synchronized boolean addRestaurant(Restaurant restaurant){
        if (restaurantDAO.restaurantExists(restaurant.getRestaurantId())) {
    	    return false;
        } else {
			restaurantDAO.addRestaurant(restaurant);
    	    return true;
        }
	}
	
	@Override
	public void updateRestaurant(Restaurant restaurant) {
		restaurantDAO.updateRestaurant(restaurant);
	}
	
	@Override
	public void deleteRestaurant(Long restaurantId) {
		restaurantDAO.deleteRestaurant(restaurantId);
	}

	@Override
	public boolean restaurantExists(Long restaurantId) {
		return restaurantDAO.restaurantExists(restaurantId);
	}
}