package com.ipl.ipl_dashboard.repository;

import java.util.List;

import com.ipl.ipl_dashboard.entity.IplData;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IplRepository extends CrudRepository<IplData, Long> {
    
    @Query(value = "SELECT DISTINCT YEAR(DATE) FROM IPLDATA", nativeQuery = true)
    List<Integer> getAllDistinctYear();

    @Query(value = "select * from ipldata where (team1=?1 or team2=?1) and year(date)=?2", nativeQuery = true)
    List<IplData> getAllMatchByYearAndTeamName(String teamName, int year);

    @Query(value = "SELECT DISTINCT(TEAM2) FROM IPLDATA", nativeQuery = true)
    List<String> getAllDistinctTeam();
}
