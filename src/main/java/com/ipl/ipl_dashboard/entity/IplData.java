package com.ipl.ipl_dashboard.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "IPLDATA")
@Getter
@Setter
@NoArgsConstructor
public class IplData {

    @Id
    @Column(name = "id")
    private long id;

    @Column(name = "city")
    private String city;

    @Column(name = "date")
    private LocalDate date;

    @Column(name = "player_of_match")
    private String playerOfMatch;

    @Column(name = "venue")
    private String venue;

    @Column(name = "team1")
    private String team1;

    @Column(name = "team2")
    private String team2;

    @Column(name = "toss_winner")
    private String tossWinner;

    @Column(name = "toss_decision")
    private String tossDecision;

    @Column(name = "winner")
    private String winner;

    @Column(name = "winning_result")
    private String winningResult;

    @Column(name = "result_margin")
    private String resultMargin;

    @Column(name = "eliminator")
    private String eliminator;

    @Column(name = "umpire1")
    private String umpire1;

    @Column(name = "umpire2")
    private String umpire2;

}
