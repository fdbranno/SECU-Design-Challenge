package com.waiterraterclient.dao;

import com.waiterraterclient.entity.ReviewAttribute;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Transactional
@Repository
public class ReviewAttributeDAO implements IReviewAttributeDAO {
	@PersistenceContext	
	private EntityManager entityManager;

	@SuppressWarnings("unchecked")
	@Override
	public ReviewAttribute getReviewAttributeById(Long reviewAttributeId) {
		String hql = "FROM ReviewAttribute as ra WHERE ra.reviewAttributeId = :rid";
		List<ReviewAttribute> reviewAttributes = entityManager.createQuery(hql).setParameter("rid", reviewAttributeId)
		              .getResultList();
		return reviewAttributes.get(0);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<ReviewAttribute> getAllReviewAttributes() {
		String hql = "FROM ReviewAttribute as ra ORDER BY ra.reviewAttributeId";
		return (List<ReviewAttribute>) entityManager.createQuery(hql).getResultList();
	}	
	
	@Override
	public void addReviewAttribute(ReviewAttribute reviewAttribute) {
		entityManager.persist(reviewAttribute);
	}
	
	@Override
	public void updateReviewAttribute(ReviewAttribute reviewAttribute) {
		ReviewAttribute ra = getReviewAttributeById(reviewAttribute.getReviewAttributeId());
		ra.setAttribute(reviewAttribute.getAttribute());
		ra.setStatus(reviewAttribute.getStatus());
		entityManager.flush();
	}
	
	@Override
	public void deleteReviewAttribute(Long reviewAttributeId) {
		entityManager.remove(getReviewAttributeById(reviewAttributeId));
	}
	
	@Override
	public boolean reviewAttributeExists(Long reviewAttributeId) {
		String hql = "FROM ReviewAttribute as ra WHERE ra.reviewAttributeId = :rid";
		int count = entityManager.createQuery(hql).setParameter("rid", reviewAttributeId)
		              .getResultList().size();
		return count > 0;
	}
}