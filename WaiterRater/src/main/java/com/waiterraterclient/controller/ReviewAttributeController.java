package com.waiterraterclient.controller;

import com.waiterraterclient.entity.ReviewAttribute;
import com.waiterraterclient.service.IReviewAttributeService;
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
public class ReviewAttributeController {
	@Autowired
	private IReviewAttributeService reviewAttributeService;

	//Get review attribute by ID
	@SuppressWarnings("unchecked")
	@GetMapping("reviewattribute/{review_attribute_id}")
	public ResponseEntity<ReviewAttribute> getReviewAttributeById(@PathVariable("review_attribute_id") Long reviewAttributeId) {
		ReviewAttribute reviewAttribute = reviewAttributeService.getReviewAttributeById(reviewAttributeId);
		return new ResponseEntity<ReviewAttribute>(reviewAttribute, HttpStatus.OK);
	}

	//Get all review attributes
	@GetMapping("reviewattributes")
	public ResponseEntity<List<ReviewAttribute>> getAllReviewAttributes() {
		List<ReviewAttribute> list = reviewAttributeService.getAllReviewAttributes();
		return new ResponseEntity<List<ReviewAttribute>>(list, HttpStatus.OK);
	}

	//Add review attribute
	@PostMapping("reviewattribute")
	public ResponseEntity<Void> addReviewAttribute(@RequestBody ReviewAttribute reviewAttribute, UriComponentsBuilder builder) {
        boolean flag = reviewAttributeService.addReviewAttribute(reviewAttribute);
        if (flag == false) {
            return new ResponseEntity<Void>(HttpStatus.CONFLICT);
        }
        HttpHeaders headers = new HttpHeaders();
		Map<String, String> vars = new HashMap<String, String>() {{
			put("review_attribute_id", String.valueOf(reviewAttribute.getReviewAttributeId()));
		}};
        headers.setLocation(builder.path("/reviewattribute/{review_attribute_id}").buildAndExpand(vars).encode().toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	}

	//Update review attribute
	@PutMapping("reviewattribute")
	public ResponseEntity<ReviewAttribute> updateReviewAttribute(@RequestBody ReviewAttribute reviewAttribute) {
		reviewAttributeService.updateReviewAttribute(reviewAttribute);
		return new ResponseEntity<ReviewAttribute>(reviewAttribute, HttpStatus.OK);
	}

	//Delete review attribute
	@DeleteMapping("reviewattribute/{review_attribute_id}")
	public ResponseEntity<Void> deleteReviewAttribute(@PathVariable("review_attribute_id") Long reviewAttributeId) {
		reviewAttributeService.deleteReviewAttribute(reviewAttributeId);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}	
}  