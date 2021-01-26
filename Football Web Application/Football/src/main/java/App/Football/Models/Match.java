package App.Football.Models;

import java.sql.Date;
import java.sql.Time;

public class Match {
    private int Id;
    private Season season;
    private Stadium stadium;
    private Team TeamLocal;
    private Team TeamVisitor;
    private Date MatchDate;
    private Time MatchHour;
    private int MatchRound;
    private int GoalLocal;
    private int GoalVisitor;
    private int State;

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public Season getSeason() {
        return season;
    }

    public void setSeason(Season season) {
        this.season = season;
    }

    public Stadium getStadium() {
        return stadium;
    }

    public void setStadium(Stadium stadium) {
        this.stadium = stadium;
    }

    public Team getTeamLocal() {
        return TeamLocal;
    }

    public void setTeamLocal(Team teamLocal) {
        TeamLocal = teamLocal;
    }

    public Team getTeamVisitor() {
        return TeamVisitor;
    }

    public void setTeamVisitor(Team teamVisitor) {
        TeamVisitor = teamVisitor;
    }

    public Date getMatchDate() {
        return MatchDate;
    }

    public void setMatchDate(Date matchDate) {
        MatchDate = matchDate;
    }

    public Time getMatchHour() {
        return MatchHour;
    }

    public void setMatchHour(Time matchHour) {
        MatchHour = matchHour;
    }

    public int getMatchRound() {
        return MatchRound;
    }

    public void setMatchRound(int matchRound) {
        MatchRound = matchRound;
    }

    public int getGoalLocal() {
        return GoalLocal;
    }

    public void setGoalLocal(int goalLocal) {
        GoalLocal = goalLocal;
    }

    public int getGoalVisitor() {
        return GoalVisitor;
    }

    public void setGoalVisitor(int goalVisitor) {
        GoalVisitor = goalVisitor;
    }

    public int getState() {
        return State;
    }

    public void setState(int state) {
        State = state;
    }
}
