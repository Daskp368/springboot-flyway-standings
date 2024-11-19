package springboot_flyway_standings.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "matches")
public class Match {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "match_id")
    private int id;

    @Column(name = "season")
    private String season; //String

    @Column(name = "match_date")
    private LocalDate date; //LocalDate

    @ManyToOne
    @JoinColumn(name = "home_team_id",referencedColumnName = "team_id")
    private Team homeTeam;

    @ManyToOne
    @JoinColumn(name = "away_team_id", referencedColumnName = "team_id")
    private Team awayTeam;

    @Column(name = "home_score")
    private Integer homeScore;

    @Column(name = "away_score")
    private Integer awayScore;

    public Match(){ }

    public Match(int id, String season, LocalDate date, Team homeTeam,Team awayTeam, Integer homeScore, Integer awayScore) {
        this.id = id;
        this.season = season;
        this.date = (LocalDate) date;
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
        this.homeScore = homeScore;
        this.awayScore = awayScore;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSeason() {
        return season;
    }

    public void setSeason(String season) {
        this.season = season;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = (LocalDate)date;
    }

    public Team getAwayTeam() {
        return awayTeam;
    }

    public void setAwayTeam(Team awayTeam) {
        this.awayTeam = awayTeam;
    }

    public Team getHomeTeam() {
        return homeTeam;
    }

    public void setHomeTeam(Team homeTeam) {
        this.homeTeam = homeTeam;
    }

    public Integer getHomeScore() {
        return homeScore;
    }

    public void setHomeScore(Integer homeScore) {
        this.homeScore = homeScore;
    }

    public Integer getAwayScore() {
        return awayScore;
    }

    public void setAwayScore(Integer awayScore) {
        this.awayScore = awayScore;
    }
}
