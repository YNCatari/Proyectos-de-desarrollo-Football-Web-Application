package App.Football.Factories;

import App.Football.Models.Community;
import App.Football.Models.CommunityPlayer;
import App.Football.Models.Player;
import App.Football.Providers.MySQLConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class CommunityPlayerDAO {
    public boolean Save(CommunityPlayer obj) {
        boolean response = false;
        try
        {
            PreparedStatement ps = MySQLConnection.getInstance().connection.prepareStatement("INSERT INTO tbl_communityplayer(id_player,id_community) VALUES(?,?)");
            ps.setInt(1, obj.getPlayer().getId());
            ps.setInt(2, obj.getCommunity().getId());
            ps.executeUpdate();
            response = true;
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
        return response;
    }

    public boolean Delete(int id) {
        boolean response = false;
        try
        {
            PreparedStatement ps = MySQLConnection.getInstance().connection.prepareStatement("DELETE FROM tbl_communityplayer WHERE id = ?");
            ps.setInt(1, id);
            ps.executeUpdate();
            response = true;
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
        return response;
    }

    public List<CommunityPlayer> ToListPlayer(int playerId) {
        ArrayList<CommunityPlayer> List = new ArrayList<>();
        try
        {
            PreparedStatement ps = MySQLConnection.getInstance().connection.prepareStatement("SELECT cp.id, cp.id_player, cp.id_community, \n" +
                    "c.id AS 'community_id', c.name AS 'community_name'\n" +
                    "FROM tbl_communityplayer cp\n" +
                    "INNER JOIN tbl_player p ON cp.id_player = p.id\n" +
                    "INNER JOIN tbl_community c ON cp.id_community = c.id\n" +
                    "WHERE cp.id_player = ?");
            ps.setInt(1, playerId);
            ResultSet rs = ps.executeQuery();
            while (rs.next())
            {
                CommunityPlayer obj = new CommunityPlayer();
                obj.setId(rs.getInt("id"));
                Player player = new Player();
                player.setId(rs.getInt("id_player"));
                obj.setPlayer(player);
                Community community = new Community();
                community.setId(rs.getInt("community_id"));
                community.setName(rs.getString("community_name"));
                obj.setCommunity(community);
                List.add(obj);
            }
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
        return List;
    }
}
