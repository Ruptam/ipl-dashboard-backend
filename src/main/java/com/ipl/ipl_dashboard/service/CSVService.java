package com.ipl.ipl_dashboard.service;

import java.io.IOException;
import java.util.List;

import com.ipl.ipl_dashboard.entity.IplData;
import com.ipl.ipl_dashboard.repository.IplRepository;
import com.ipl.ipl_dashboard.service.helper.CSVHelper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class CSVService {

    @Autowired
    private IplRepository iplRepository;

    public void save(Resource resource) {
        try {
            List<IplData> iplDatas = CSVHelper.csvToIplData(resource.getInputStream());
            iplRepository.saveAll(iplDatas);
        } catch (IOException e) {
            e.getMessage();
        }
    }

    public List<Integer> getAllYear() {
        List<Integer> allYears = iplRepository.getAllDistinctYear();
        return allYears;
    }

    public List<IplData> teamDetailsBasedOnYear(String teamName, int year) {
        List<IplData> iplDatas = iplRepository.getAllMatchByYearAndTeamName(teamName, year);
        return iplDatas;
    }

    public List<String> getDistinctTeam() {
       List<String> allTeams = iplRepository.getAllDistinctTeam();
       return allTeams;
    }
    
}
