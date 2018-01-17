package com.waiterraterclient.controller;

import com.waiterraterclient.entity.Review;
import com.waiterraterclient.service.IReviewService;
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
public class ReviewController {
	@Autowired
	private IReviewService reviewService;

	//Get review by case ID and review attribute ID
	@SuppressWarnings("unchecked")
	@GetMapping("review/visit/{visit_id}/reviewattribute/{review_attribute_id}")
	public ResponseEntity<Review> getReviewById(@PathVariable("visit_id") Long visitId, @PathVariable("review_attribute_id") Long reviewAttributeId) {
		Review review = reviewService.getReviewById(visitId, reviewAttributeId);
		return new ResponseEntity<Review>(review, HttpStatus.OK);
	}

	//Get all reviews
	@GetMapping("reviews")
	public ResponseEntity<List<Review>> getAllReviews() {
		List<Review> list = reviewService.getAllReviews();
		return new ResponseEntity<List<Review>>(list, HttpStatus.OK);
	}

	//Get all reviews for specific server
	@GetMapping("reviews/server/{server_id}")
	public ResponseEntity<List<Review>> getReviewsByServer(@PathVariable("server_id") Long serverId) {
		List<Review> list = reviewService.getReviewsByServer(serverId);
		return new ResponseEntity<List<Review>>(list, HttpStatus.OK);
	}

	//Get all reviews for specific visit
	@GetMapping("reviews/visit/{visit_id}")
	public ResponseEntity<List<Review>> getReviewsByVisit(@PathVariable("visit_id") Long visitId) {
		List<Review> list = reviewService.getReviewsByVisit(visitId);
		return new ResponseEntity<List<Review>>(list, HttpStatus.OK);
	}

	//Get all reviews for specific review attribute
	@GetMapping("reviews/reviewattribute/{review_attribute_id}")
	public ResponseEntity<List<Review>> getReviewsByAttribute(@PathVariable("review_attribute_id") Long reviewAttributeId) {
		List<Review> list = reviewService.getReviewsByAttribute(reviewAttributeId);
		return new ResponseEntity<List<Review>>(list, HttpStatus.OK);
	}

	//Get all reviews for specific restaurant
	@GetMapping("reviews/restaurant/{restaurant_id}")
	public ResponseEntity<List<Review>> getReviewsByRestaurant(@PathVariable("restaurant_id") Long restaurantId) {
		List<Review> list = reviewService.getReviewsByRestaurant(restaurantId);
		return new ResponseEntity<List<Review>>(list, HttpStatus.OK);
	}

	//Add review
	@PostMapping("review")
	public ResponseEntity<Void> addReview(@RequestBody Review review, UriComponentsBuilder builder) {
        boolean flag = reviewService.addReview(review);
        if (flag == false) {
			return new ResponseEntity<Void>(HttpStatus.CONFLICT);
        }
        HttpHeaders headers = new HttpHeaders();
		Map<String, String> vars = new HashMap<String, String>() {{
			put("visit_id", String.valueOf(review.getVisit().getVisitId()));
			put("review_attribute_id", String.valueOf(review.getReviewAttribute().getReviewAttributeId()));
		}};
        headers.setLocation(builder.path("/visit/{visit_id}/reviewattribute/{review_attribute_id}").buildAndExpand(vars).encode().toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	}

	//Update review
	@PutMapping("review")
	public ResponseEntity<Review> updateReview(@RequestBody Review review) {
		reviewService.updateReview(review);
		return new ResponseEntity<Review>(review, HttpStatus.OK);
	}

	//Delete review
	@DeleteMapping("review/visit/{visit_id}/reviewattribute/{review_attribute_id}")
	public ResponseEntity<Void> deleteReview(@PathVariable("visit_id") Long visitId, @PathVariable("review_attribute_id") Long reviewAttributeId) {
		reviewService.deleteReview(visitId, reviewAttributeId);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}
}  