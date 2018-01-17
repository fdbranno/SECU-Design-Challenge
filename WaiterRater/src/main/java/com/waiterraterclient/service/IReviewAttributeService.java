package com.waiterraterclient.service;

import com.waiterraterclient.entity.ReviewAttribute;

import java.util.List;

public interface IReviewAttributeService {
    List<ReviewAttribute> getAllReviewAttributes();
    
	ReviewAttribute getReviewAttributeById(Long reviewAttributeId);
    
	boolean addReviewAttribute(ReviewAttribute reviewAttribute);
    
	void updateReviewAttribute(ReviewAttribute reviewAttribute);
    
	void deleteReviewAttribute(Long reviewAttributeId);
}