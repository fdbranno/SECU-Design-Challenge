package com.waiterraterclient.dao;

import com.waiterraterclient.entity.Visit;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Transactional
@Repository
public class VisitDAO implements IVisitDAO {
    @PersistenceContext
    private EntityManager entityManager;

    @SuppressWarnings("unchecked")
    @Override
    public Visit getVisitById(Long visitId) {
        String hql = "FROM Visit as v WHERE v.visitId = :vid";
        List<Visit> obj = entityManager.createQuery(hql).setParameter("vid", visitId)
                .getResultList();
        return obj.get(0);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Visit> getAllVisits() {
        String hql = "FROM Visit as v ORDER BY v.visitId";
        return (List<Visit>) entityManager.createQuery(hql).getResultList();
    }

    @Override
    public void addVisit(Visit visit) {
        entityManager.persist(visit);
    }

    @Override
    public void updateVisit(Visit visit) {
        Visit v = getVisitById(visit.getVisitId());
        v.setServer(visit.getServer());
        v.setComment(visit.getComment());
        entityManager.flush();
    }

    @Override
    public void deleteVisit(Long visitId) {
        entityManager.remove(getVisitById(visitId));
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean visitExists(Long visitId) {
        String hql = "FROM Visit as v WHERE v.visitId = :vid";
        List<Visit> obj = entityManager.createQuery(hql).setParameter("vid", visitId).getResultList();
        return obj.size() > 0;
    }
}