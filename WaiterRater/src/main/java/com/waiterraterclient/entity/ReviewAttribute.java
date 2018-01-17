package com.waiterraterclient.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "review_attributes")
public class ReviewAttribute implements Serializable {
	
	private static final Long serialVersionUID = 1L;
	
    @Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "review_attribute_id")
    private Long reviewAttributeId;
	
	@Column(name = "review_attribute_attribute")
	private String attribute;
	
	@Column(name = "review_attribute_status")
	private boolean status;
	
	public Long getReviewAttributeId() {
		return reviewAttributeId;
	}
	
	public String getAttribute() {
		return attribute;
	}
	
	public boolean getStatus() {
		return status;
	}
	
	public void setAttribute(String attribute) {
		this.attribute = attribute;
	}
	
	public void setStatus(boolean status) {
		this.status = status;
	}
}
