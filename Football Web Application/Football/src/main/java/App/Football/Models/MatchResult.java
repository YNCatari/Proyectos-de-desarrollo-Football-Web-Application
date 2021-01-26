package App.Football.Models;

public class MatchResult {
    private int Id;
    private Match match;
    private Team team;
    private Player player;
    private TypeResult typeResult;
    private int MatchMinute;

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

    public TypeResult getTypeResult() {
        return typeResult;
    }

    public void setTypeResult(TypeResult typeResult) {
        this.typeResult = typeResult;
    }

    public int getMatchMinute() {
        return MatchMinute;
    }

    public void setMatchMinute(int matchMinute) {
        MatchMinute = matchMinute;
    }
}
