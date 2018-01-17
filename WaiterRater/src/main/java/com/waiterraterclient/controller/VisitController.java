package com.waiterraterclient.controller;

import com.waiterraterclient.entity.Visit;
import com.waiterraterclient.service.IVisitService;
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
public class VisitController {
    @Autowired
    private IVisitService visitService;

    //Get visit by ID
    @GetMapping("visit/{visit_id}")
    public ResponseEntity<Visit> getVisitById(@PathVariable("visit_id") Long visitId) {
        Visit visit = visitService.getVisitById(visitId);
        return new ResponseEntity<Visit>(visit, HttpStatus.OK);
    }

    //Get all visits
    @GetMapping("visits")
    public ResponseEntity<List<Visit>> getAllVisits() {
        List<Visit> list = visitService.getAllVisits();
        return new ResponseEntity<List<Visit>>(list, HttpStatus.OK);
    }

    //Add visit
    @PostMapping("visit")
    public ResponseEntity<Void> addVisit(@RequestBody Visit visit, UriComponentsBuilder builder) {
        boolean flag = visitService.addVisit(visit);
        if (flag == false) {
            return new ResponseEntity<Void>(HttpStatus.CONFLICT);
        }
        HttpHeaders headers = new HttpHeaders();
        Map<String, String> vars = new HashMap<String, String>() {{
            put("visit_id", String.valueOf(visit.getVisitId()));
        }};
        headers.setLocation(builder.path("/api/visit/{visit_id}").buildAndExpand(vars).encode().toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }

    //Update visit
    @PutMapping("visit")
    public ResponseEntity<Visit> updateVisit(@RequestBody Visit visit) {
        visitService.updateVisit(visit);
        return new ResponseEntity<Visit>(visit, HttpStatus.OK);
    }

    //Delete visit
    @DeleteMapping("visit/{visit_id}")
    public ResponseEntity<Void> deleteVisit(@PathVariable("visit_id") Long visitId) {
        visitService.deleteVisit(visitId);
        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    }
}