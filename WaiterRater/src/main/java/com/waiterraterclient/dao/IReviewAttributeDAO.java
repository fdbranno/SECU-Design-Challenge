package com.waiterraterclient.dao;

import com.waiterraterclient.entity.ReviewAttribute;

import java.util.List;

public interface IReviewAttributeDAO {

    List<ReviewAttribute> getAllReviewAttributes();
    
	ReviewAttribute getReviewAttributeById(Long reviewAttributeId);
    
	void addReviewAttribute(ReviewAttribute reviewAttribute);
    
	void updateReviewAttribute(ReviewAttribute reviewAttribute);
    
	void deleteReviewAttribute(Long reviewAttributeId);
    
	boolean reviewAttributeExists(Long reviewAttributeAttributeId);
} 