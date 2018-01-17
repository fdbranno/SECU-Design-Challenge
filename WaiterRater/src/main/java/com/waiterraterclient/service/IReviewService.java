package com.waiterraterclient.service;

import com.waiterraterclient.entity.Review;

import java.util.List;

public interface IReviewService {

	List<Review> getAllReviews();

	Review getReviewById(Long visitId, Long reviewAttributeId);

	List<Review> getReviewsByServer(Long serverId);

	List<Review> getReviewsByAttribute(Long reviewAttributeId);

	List<Review> getReviewsByRestaurant(Long restaurantId);

	List<Review> getReviewsByVisit(Long visitId);

	boolean addReview(Review review);

	void updateReview(Review review);

	void deleteReview(Long visitId, Long reviewAttributeId);

	boolean reviewExists(Long visitId, Long reviewAttributeId);
}