package com.waiterraterclient.dao;

import com.waiterraterclient.entity.Review;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Transactional
@Repository
public class ReviewDAO implements IReviewDAO {
	@PersistenceContext	
	private EntityManager entityManager;	
	
	@Override
	public Review getReviewById(Long visitId, Long reviewAttributeId) {
		String hql = "FROM Review as r WHERE r.visit.visitId = :vid AND r.reviewAttribute.reviewAttributeId = :rid";
		List<Review> Reviews = entityManager.createQuery(hql).setParameter("vid", visitId).setParameter("rid", reviewAttributeId).getResultList();
		return Reviews.get(0);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Review> getAllReviews() {
		String hql = "FROM Review as r ORDER BY r.visit.visitId";
		return (List<Review>) entityManager.createQuery(hql).getResultList();
	}	
	
	@Override
	public List<Review> getReviewsByServer(Long serverId) {
		String hql = "FROM Review as r WHERE r.visit.server.serverId = :sid ORDER BY r.reviewAttribute.reviewAttributeId";
		return (List<Review>) entityManager.createQuery(hql).setParameter("sid", serverId).getResultList();
	}
	
	@Override
	public List<Review> getReviewsByAttribute(Long reviewAttributeId) {
		String hql = "FROM Review as r WHERE r.reviewAttribute.reviewAttributeId = :rid ORDER BY r.cs.caseId";
		return (List<Review>) entityManager.createQuery(hql).setParameter("rid", reviewAttributeId).getResultList();
	}

	@Override
	public List<Review> getReviewsByRestaurant(Long restaurantId) {
		String hql = "FROM Review as r WHERE r.visit.server.restaurant.restaurantId = :rid ORDER BY r.visit.server.restaurant.restaurantId";
		return (List<Review>) entityManager.createQuery(hql).setParameter("rid", restaurantId).getResultList();
	}

	@Override
	public List<Review> getReviewsByVisit(Long visitId) {
		String hql = "FROM Review as r WHERE r.visit.visitID = :vid ORDER BY r.reviewAttribute.reviewAttributeId";
		return (List<Review>) entityManager.createQuery(hql).setParameter("vid", visitId).getResultList();
	}
	
	@Override
	public void addReview(Review review) {
		entityManager.persist(review);
	}
	
	@Override
	public void updateReview(Review review) {
		Review r = getReviewById(review.getVisit().getVisitId(), review.getReviewAttribute().getReviewAttributeId());
		r.setReviewAttribute(review.getReviewAttribute());
		r.setVisit(review.getVisit());
		r.setScore(review.getScore());
		entityManager.flush();
	}
	
	@Override
	public void deleteReview(Long visitId, Long reviewAttributeId) {
		entityManager.remove(getReviewById(visitId, reviewAttributeId));
	}

	@Override
	public boolean reviewExists(Long visitId, Long reviewAttributeId) {
		String hql = "FROM Review as r WHERE r.visit.visitId = :vid AND r.reviewAttribute.reviewAttributeId = :rid";
		List<Review> Reviews = entityManager.createQuery(hql).setParameter("vid", visitId).setParameter("rid", reviewAttributeId).getResultList();
		return Reviews.size() > 0;
	}
}