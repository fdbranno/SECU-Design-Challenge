package com.waiterraterclient.dao;

import com.waiterraterclient.entity.Restaurant;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Transactional
@Repository
public class RestaurantDAO implements IRestaurantDAO {
	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public Restaurant getRestaurantById(Long restaurantId) {
		String hql = "FROM Restaurant as r WHERE r.restaurantId = :rid";
		List<Restaurant> restaurants = entityManager.createQuery(hql).setParameter("rid", restaurantId).getResultList();
		return restaurants.get(0);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Restaurant> getAllRestaurants() {
		String hql = "FROM Restaurant as r ORDER BY r.restaurantId";
		return (List<Restaurant>) entityManager.createQuery(hql).getResultList();
	}

	@Override
	public void addRestaurant(Restaurant restaurant) {
		entityManager.persist(restaurant);
	}

	@Override
	public void updateRestaurant(Restaurant restaurant) {
		Restaurant r = getRestaurantById(restaurant.getRestaurantId());
		r.setRestaurantName(restaurant.getRestaurantName());
		r.setStatus(restaurant.getStatus());
		entityManager.flush();
	}

	@Override
	public void deleteRestaurant(Long restaurantId) {
		entityManager.remove(getRestaurantById(restaurantId));
	}

	@Override
	public boolean restaurantExists(Long restaurantId) {
		String hql = "FROM Restaurant as r WHERE r.restaurantId = :rid";
		List<Restaurant> restaurants = entityManager.createQuery(hql).setParameter("rid", restaurantId).getResultList();
		return restaurants.size() > 0;
	}
}