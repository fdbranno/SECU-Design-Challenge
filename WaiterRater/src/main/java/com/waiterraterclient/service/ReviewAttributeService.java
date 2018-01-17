package com.waiterraterclient.service;

import com.waiterraterclient.dao.IReviewAttributeDAO;
import com.waiterraterclient.entity.ReviewAttribute;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewAttributeService implements IReviewAttributeService {
	@Autowired
	private IReviewAttributeDAO reviewAttributeDAO;
	
	@Override
	public ReviewAttribute getReviewAttributeById(Long reviewAttributeId) {
		ReviewAttribute obj = reviewAttributeDAO.getReviewAttributeById(reviewAttributeId);
		return obj;
	}	
	
	@Override
	public List<ReviewAttribute> getAllReviewAttributes(){
		return reviewAttributeDAO.getAllReviewAttributes();
	}
	
	@Override
	public synchronized boolean addReviewAttribute(ReviewAttribute reviewAttribute){
        if (reviewAttributeDAO.reviewAttributeExists(reviewAttribute.getReviewAttributeId())) {
    	    return false;
        } else {
    	    reviewAttributeDAO.addReviewAttribute(reviewAttribute);
    	    return true;
        }
	}
	
	@Override
	public void updateReviewAttribute(ReviewAttribute reviewAttribute) {
		reviewAttributeDAO.updateReviewAttribute(reviewAttribute);
	}
	
	@Override
	public void deleteReviewAttribute(Long reviewAttributeId) {
		reviewAttributeDAO.deleteReviewAttribute(reviewAttributeId);
	}
}