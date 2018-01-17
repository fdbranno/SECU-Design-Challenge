package com.waiterraterclient.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "reviews")
public class Review implements Serializable {
	
	private static final Long serialVersionUID = 1L;
	
	@Id
	@ManyToOne
	@JoinColumn(name = "review_review_attribute", referencedColumnName = "review_attribute_id")
	private ReviewAttribute reviewAttribute;
	
	@Id
	@ManyToOne
	@JoinColumn(name = "review_visit", referencedColumnName = "visit_id")
	private Visit visit;
	
	@Column(name = "review_score")
	private Long score;
	
	public ReviewAttribute getReviewAttribute() {
		return reviewAttribute;
	}
	
	public Visit getVisit() {
		return visit;
	}
	
	public Long getScore() {
		return score;
	}
	
	public void setScore(Long score) {
		this.score = score;
	}
	
	public void setReviewAttribute(ReviewAttribute reviewAttribute) {
		this.reviewAttribute = reviewAttribute;
	}
	
	public void setVisit(Visit visit) {
		this.visit = visit;
	}
}
