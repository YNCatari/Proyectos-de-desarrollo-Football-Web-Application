package App.Football.Models;

public class Classification {
    private int Id;
    private Season season;
    private Team team;
    private int MatchesPlayed;
    private int MatchesWin;
    private int MatchesTied;
    private int MatchesLost;
    private int GoalsFavor;
    private int GoalsAgainst;
    private int GoalsDifference;
    private int Points;

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

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public int getMatchesPlayed() {
        return MatchesPlayed;
    }

    public void setMatchesPlayed(int matchesPlayed) {
        MatchesPlayed = matchesPlayed;
    }

    public int getMatchesWin() {
        return MatchesWin;
    }

    public void setMatchesWin(int matchesWin) {
        MatchesWin = matchesWin;
    }

    public int getMatchesTied() {
        return MatchesTied;
    }

    public void setMatchesTied(int matchesTied) {
        MatchesTied = matchesTied;
    }

    public int getMatchesLost() {
        return MatchesLost;
    }

    public void setMatchesLost(int matchesLost) {
        MatchesLost = matchesLost;
    }

    public int getGoalsFavor() {
        return GoalsFavor;
    }

    public void setGoalsFavor(int goalsFavor) {
        GoalsFavor = goalsFavor;
    }

    public int getGoalsAgainst() {
        return GoalsAgainst;
    }

    public void setGoalsAgainst(int goalsAgainst) {
        GoalsAgainst = goalsAgainst;
    }

    public int getGoalsDifference() {
        return GoalsDifference;
    }

    public void setGoalsDifference(int goalsDifference) {
        GoalsDifference = goalsDifference;
    }

    public int getPoints() {
        return Points;
    }

    public void setPoints(int points) {
        Points = points;
    }
}
