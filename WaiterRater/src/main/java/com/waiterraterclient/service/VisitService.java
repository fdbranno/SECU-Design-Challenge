package com.waiterraterclient.service;

import com.waiterraterclient.dao.IVisitDAO;
import com.waiterraterclient.entity.Visit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VisitService implements IVisitService {
    @Autowired
    private IVisitDAO visitDAO;

    @Override
    public Visit getVisitById(Long visitId) {
        Visit obj = visitDAO.getVisitById(visitId);
        return obj;
    }

    @Override
    public List<Visit> getAllVisits(){
        return visitDAO.getAllVisits();
    }

    @Override
    public synchronized boolean addVisit(Visit visit){
        if (visitDAO.visitExists(visit.getVisitId())) {
            return false;
        } else {
            visitDAO.addVisit(visit);
            return true;
        }

    }

    @Override
    public void updateVisit(Visit visit) {
        visitDAO.updateVisit(visit);
    }

    @Override
    public void deleteVisit(Long visitId) {
        visitDAO.deleteVisit(visitId);
    }

    @Override
    public boolean visitExists(Long visitId) {
        return visitDAO.visitExists(visitId);
    }
}