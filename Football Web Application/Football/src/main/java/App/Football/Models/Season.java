package App.Football.Models;

import App.Football.Factories.MatchDAO;

import java.sql.Date;

public class Season {
    private int Id;
    private Competition competition;
    private String Name;
    private Date StartDate;
    private Date EndDate;
    private int State;

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public Competition getCompetition() {
        return competition;
    }

    public void setCompetition(Competition competition) {
        this.competition = competition;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public Date getStartDate() {
        return StartDate;
    }

    public void setStartDate(Date startDate) {
        StartDate = startDate;
    }

    public Date getEndDate() {
        return EndDate;
    }

    public void setEndDate(Date endDate) {
        EndDate = endDate;
    }

    public int getState() {
        return State;
    }

    public void setState(int state) {
        State = state;
    }
}
