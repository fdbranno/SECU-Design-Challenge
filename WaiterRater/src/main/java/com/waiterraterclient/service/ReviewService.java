package com.waiterraterclient.service;

import com.waiterraterclient.dao.IReviewDAO;
import com.waiterraterclient.entity.Review;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewService implements IReviewService {
	@Autowired
	private IReviewDAO reviewDAO;
	
	@Override
	public Review getReviewById(Long visitId, Long reviewAttributeId) {
		Review obj = reviewDAO.getReviewById(visitId, reviewAttributeId);
		return obj;
	}	
	
	@Override
	public List<Review> getAllReviews(){
		return reviewDAO.getAllReviews();
	}
	
	@Override
	public synchronized boolean addReview(Review review){
        if (reviewDAO.reviewExists(review.getVisit().getVisitId(), review.getReviewAttribute().getReviewAttributeId())) {
    	    return false;
        } else {
			reviewDAO.addReview(review);
    	    return true;
        }
	}

	@Override
	public List<Review> getReviewsByRestaurant(Long restaurantId) {
		return reviewDAO.getReviewsByRestaurant(restaurantId);
	}

	@Override
	public List<Review> getReviewsByAttribute(Long reviewAttributeId) {
		return reviewDAO.getReviewsByAttribute(reviewAttributeId);
	}

	@Override
	public List<Review> getReviewsByServer(Long serverId) {
		return reviewDAO.getReviewsByServer(serverId);
	}

	@Override
	public List<Review> getReviewsByVisit(Long visitId) {
		return reviewDAO.getReviewsByVisit(visitId);
	}
	
	@Override
	public void updateReview(Review review) {
		reviewDAO.updateReview(review);
	}
	
	@Override
	public void deleteReview(Long visitId, Long reviewAttributeId) {
		reviewDAO.deleteReview(visitId, reviewAttributeId);
	}

	@Override
	public boolean reviewExists(Long visitId, Long reviewAttributeId) {
		return reviewDAO.reviewExists(visitId, reviewAttributeId);
	}
}