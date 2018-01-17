package com.waiterraterclient.service;

import com.waiterraterclient.entity.Visit;

import java.util.List;

public interface IVisitService {
    List<Visit> getAllVisits();

    Visit getVisitById(Long visitId);

    boolean addVisit(Visit visit);

    void updateVisit(Visit visit);

    void deleteVisit(Long visitId);

    boolean visitExists(Long visitId);
}