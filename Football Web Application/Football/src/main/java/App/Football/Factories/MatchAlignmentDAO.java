package App.Football.Factories;

import App.Football.Models.*;
import App.Football.Providers.MySQLConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class MatchAlignmentDAO {
    public boolean Save(MatchAlignment obj) {
        boolean response = false;
        try
        {
            PreparedStatement ps = MySQLConnection.getInstance().connection.prepareStatement("INSERT INTO tbl_matchalignment(id_match,id_team,id_player,id_position,headline) VALUES(?,?,?,?,?)");
            ps.setInt(1, obj.getMatch().getId());
            ps.setInt(2, obj.getTeam().getId());
            ps.setInt(3, obj.getPlayer().getId());
            ps.setInt(4, obj.getPosition().getId());
            ps.setInt(5, obj.getHeadline());
            ps.executeUpdate();
            response = true;
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
        return response;
    }

    public List<MatchAlignment> Players(int id_match, int id_team) {
        ArrayList<MatchAlignment> List = new ArrayList<>();
        try
        {
            PreparedStatement ps = MySQLConnection.getInstance().connection.prepareStatement("SELECT ma.id, ma.id AS 'match_id', ma.id_team AS 'team_id', ma.id_player AS 'player_id', ma.id_position AS 'position_id', \n" +
                    "py.dni, py.name, py.lastname, py.date_birth, py.photo, \n" +
                    "py.phone, py.email, py.height, py.weight, py.foot, py.state, \n" +
                    "ma.headline, ps.description AS 'position_description' \n" +
                    "FROM tbl_matchalignment ma \n" +
                    "INNER JOIN tbl_player py ON py.id = ma.id_player\n" +
                    "INNER JOIN tbl_position ps ON ps.id = ma.id_position \n" +
                    "WHERE ma.id_match = ? AND ma.id_team = ?");
            ps.setInt(1, id_match);
            ps.setInt(2, id_team);
            ResultSet rs = ps.executeQuery();
            while (rs.next())
            {
                MatchAlignment obj = new MatchAlignment();
                obj.setId(rs.getInt("id"));

                Match match = new Match();
                match.setId(rs.getInt("match_id"));
                obj.setMatch(match);

                Team team = new Team();
                team.setId(rs.getInt("team_id"));
                obj.setTeam(team);

                Player player = new Player();
                player.setId(rs.getInt("player_id"));
                player.setDni(rs.getString("dni"));
                player.setName(rs.getString("name"));
                player.setLastName(rs.getString("lastname"));
                player.setDateBirth(rs.getDate("date_birth"));
                player.setPhoto(rs.getString("photo"));
                player.setPhone(rs.getString("phone"));
                player.setEmail(rs.getString("email"));
                player.setHeight(rs.getDouble("height"));
                player.setWeight(rs.getDouble("weight"));
                player.setFoot(rs.getString("foot"));
                player.setState(rs.getInt("state"));
                obj.setPlayer(player);

                Position position = new Position();
                position.setId(rs.getInt("position_id"));
                position.setDescription(rs.getString("position_description"));
                obj.setPosition(position);

                obj.setHeadline(rs.getInt("headline"));

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
