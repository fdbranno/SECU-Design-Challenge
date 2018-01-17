package com.waiterraterclient.controller;

import com.waiterraterclient.entity.Restaurant;
import com.waiterraterclient.service.IRestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api")
public class RestaurantController {
	@Autowired
	private IRestaurantService restaurantService;

	//Get restaurant by ID
	@GetMapping("restaurant/{restaurant_id}")
	public ResponseEntity<Restaurant> getRestaurantById(@PathVariable("restaurant_id") Long restaurantId) {
		Restaurant restaurant = restaurantService.getRestaurantById(restaurantId);
		return new ResponseEntity<Restaurant>(restaurant, HttpStatus.OK);
	}

	//Get all restaurants
	@GetMapping("restaurants")
	public ResponseEntity<List<Restaurant>> getAllRestaurants() {
		List<Restaurant> list = restaurantService.getAllRestaurants();
		return new ResponseEntity<List<Restaurant>>(list, HttpStatus.OK);
	}

	//Add restaurant
	@PostMapping("restaurant")
	public ResponseEntity<Void> addRestaurant(@RequestBody Restaurant restaurant, UriComponentsBuilder builder) {
        boolean flag = restaurantService.addRestaurant(restaurant);
        if (flag == false) {
            return new ResponseEntity<Void>(HttpStatus.CONFLICT);
        }
        HttpHeaders headers = new HttpHeaders();
        Map<String, String> vars = new HashMap<String, String>() {{
			put("restaurant_id", String.valueOf(restaurant.getRestaurantId()));
		}};
		headers.setLocation(builder.path("/api/restaurant/{restaurant_id}").buildAndExpand(vars).encode().toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	}

	//Update restaurant
	@PutMapping("restaurant")
	public ResponseEntity<Restaurant> updateRestaurant(@RequestBody Restaurant restaurant) {
		restaurantService.updateRestaurant(restaurant);
		return new ResponseEntity<Restaurant>(restaurant, HttpStatus.OK);
	}

	//Delete restaurant
	@DeleteMapping("restaurant/{restaurant_id}")
	public ResponseEntity<Void> deleteClient(@PathVariable("restaurant_id") Long restaurantId) {
		restaurantService.deleteRestaurant(restaurantId);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}
}  