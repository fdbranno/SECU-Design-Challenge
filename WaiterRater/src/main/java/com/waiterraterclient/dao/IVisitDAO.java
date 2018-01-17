package com.waiterraterclient.dao;

import com.waiterraterclient.entity.Visit;

import java.util.List;

public interface IVisitDAO {

    List<Visit> getAllVisits();

    Visit getVisitById(Long visitId);

    void addVisit(Visit visit);

    void updateVisit(Visit visit);

    void deleteVisit(Long visitId);

    boolean visitExists(Long visitId);
}