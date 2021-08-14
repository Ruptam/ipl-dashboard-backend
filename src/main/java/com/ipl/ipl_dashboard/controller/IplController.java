package com.ipl.ipl_dashboard.controller;

import java.util.List;

import com.ipl.ipl_dashboard.entity.IplData;
import com.ipl.ipl_dashboard.service.CSVService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IplController {

    @Autowired
    private CSVService csvService;

    @PostMapping(value = "/upload")
    public void loadData() {
        Resource resource = new ClassPathResource("IPL_Matches_2008_2020.csv");
        csvService.save(resource);
    }

    @GetMapping(value = "/year")
    public ResponseEntity<?> getAllDistinctYears() {
        List<Integer> allYears = csvService.getAllYear();
        return new ResponseEntity<>(allYears, HttpStatus.OK);
    }

    @GetMapping(value = "/detail")
    public ResponseEntity<?> getTeamDetails(
        @RequestParam("team") String teamName, @RequestParam("year") int year) {
       List<IplData> iplDatas = csvService.teamDetailsBasedOnYear(teamName, year);
       return new ResponseEntity<List<IplData>>(iplDatas, HttpStatus.OK);
    }
    
    @GetMapping(value = "/team")
    public ResponseEntity<?> getAllTeams() {
       List<String> allTeams = csvService.getDistinctTeam();
       return new ResponseEntity<List<String>>(allTeams, HttpStatus.OK);
    }
}
