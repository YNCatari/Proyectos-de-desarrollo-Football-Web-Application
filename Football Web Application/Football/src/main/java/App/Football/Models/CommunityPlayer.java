package App.Football.Models;

public class CommunityPlayer {
    private int Id;
    private Player player;
    private Community community;

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Community getCommunity() {
        return community;
    }

    public void setCommunity(Community community) {
        this.community = community;
    }
}
