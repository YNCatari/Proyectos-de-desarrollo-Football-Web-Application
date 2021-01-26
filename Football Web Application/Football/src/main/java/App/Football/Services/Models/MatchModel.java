package App.Football.Services.Models;


public class MatchModel {
    public int id;
    public String date;
    public String hour;
    public TeamModel teamLocal;
    public TeamModel teamVisitor;
    public int goalLocal;
    public int goalVisitor;
    public String stadium;
    public int state;
}
