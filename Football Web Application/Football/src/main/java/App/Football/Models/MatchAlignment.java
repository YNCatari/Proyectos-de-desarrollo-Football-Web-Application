package App.Football.Models;

public class MatchAlignment {
    private int Id;
    private Match match;
    private Team team;
    private Player player;
    private Position position;
    private int Headline;

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public Match getMatch() {
        return match;
    }

    public void setMatch(Match match) {
        this.match = match;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public int getHeadline() {
        return Headline;
    }

    public void setHeadline(int headline) {
        Headline = headline;
    }
}
