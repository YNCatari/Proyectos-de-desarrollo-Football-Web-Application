package App.Football.Models;

public class Template {
    private int Id;
    private Season season;
    private Team team;
    private Player player;
    private String Dorsal;
    private String Nickname;

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

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public String getDorsal() {
        return Dorsal;
    }

    public void setDorsal(String dorsal) {
        Dorsal = dorsal;
    }

    public String getNickname() {
        return Nickname;
    }

    public void setNickname(String nickname) {
        Nickname = nickname;
    }
}
