package App.Football.Factories;

import App.Football.Models.*;
import App.Football.Providers.MySQLConnection;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class MatchResultDAO {
    public boolean Save(MatchResult obj) {
        boolean response = false;
        try
        {
            PreparedStatement ps = MySQLConnection.getInstance().connection.prepareStatement("INSERT INTO tbl_matchresult(id_match,id_team,id_player,id_typeresult,match_minute) VALUES(?,?,?,?,?)");
            ps.setInt(1, obj.getMatch().getId());
            ps.setInt(2, obj.getTeam().getId());
            ps.setInt(3, obj.getPlayer().getId());
            ps.setInt(4, obj.getTypeResult().getId());
            ps.setInt(5, obj.getMatchMinute());
            ps.executeUpdate();
            response = true;
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
        return response;
    }

    public List<MatchResult> ToListMatch(int match_id) {
        ArrayList<MatchResult> List = new ArrayList<>();
        try
        {
            PreparedStatement ps = MySQLConnection.getInstance().connection.prepareStatement("SELECT mr.id, mr.id_match AS 'match_id', mr.id_team AS 'team_id', mr.id_player AS 'player_id', mr.id_typeresult AS 'typeresult_id', \n" +
                    "t.fullname AS 'team_fullname', t.nickname AS 'team_nickname', t.initials AS 'team_initials', t.logotype AS 'team_logotype', \n" +
                    "p.name AS 'player_name', p.lastname AS 'player_lastname', \n" +
                    "tr.description AS 'typeresult_description', mr.match_minute \n" +
                    "FROM tbl_matchresult mr \n" +
                    "INNER JOIN tbl_match m ON mr.id_match = m.id \n" +
                    "INNER JOIN tbl_team t ON mr.id_team = t.id \n" +
                    "INNER JOIN tbl_player p ON mr.id_player = p.id \n" +
                    "INNER JOIN tbl_typeresult tr ON mr.id_typeresult = tr.id \n" +
                    "WHERE mr.id_match = ?");
            ps.setInt(1, match_id);
            ResultSet rs = ps.executeQuery();
            while (rs.next())
            {
                //Match
                Match match = new Match();
                match.setId(rs.getInt("match_id"));

                //Team
                Team team = new Team();
                team.setId(rs.getInt("team_id"));
                team.setFullName(rs.getString("team_fullname"));
                team.setNickName(rs.getString("team_nickname"));
                team.setInitials(rs.getString("team_initials"));
                team.setLogotype(rs.getString("team_logotype"));

                //Player
                Player player = new Player();
                player.setId(rs.getInt("player_id"));
                player.setName(rs.getString("player_name"));
                player.setLastName(rs.getString("player_lastname"));

                //TypeResult
                TypeResult typeResult = new TypeResult();
                typeResult.setId(rs.getInt("typeresult_id"));
                typeResult.setDescription(rs.getString("typeresult_description"));

                //MatchResult
                MatchResult obj = new MatchResult();
                obj.setId(rs.getInt("id"));
                obj.setMatch(match);
                obj.setTeam(team);
                obj.setPlayer(player);
                obj.setTypeResult(typeResult);
                obj.setMatchMinute(rs.getInt("match_minute"));

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
