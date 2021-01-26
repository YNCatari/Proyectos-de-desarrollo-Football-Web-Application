package App.Football.Factories;

import App.Football.Models.*;
import App.Football.Providers.MySQLConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class TemplateDAO implements IBusiness {
    @Override
    public boolean Save(Object obj) {
        boolean response = false;
        Template template = (Template) obj;
        try
        {
            PreparedStatement ps = MySQLConnection.getInstance().connection.prepareStatement("INSERT INTO tbl_template(id_season,id_team,id_player,dorsal,nickname) VALUES(?,?,?,?,?)");
            ps.setInt(1, template.getSeason().getId());
            ps.setInt(2, template.getTeam().getId());
            ps.setInt(3, template.getPlayer().getId());
            ps.setString(4, template.getDorsal());
            ps.setString(5, template.getNickname());
            ps.executeUpdate();
            response = true;
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
        return response;
    }

    @Override
    public boolean Update(Object obj) {
        boolean response = false;
        Template template = (Template) obj;
        try
        {
            PreparedStatement ps = MySQLConnection.getInstance().connection.prepareStatement("UPDATE tbl_template SET dorsal=?, nickname=? WHERE id=?");
            ps.setString(1, template.getDorsal());
            ps.setString(2, template.getNickname());
            ps.setInt(3, template.getId());
            ps.executeUpdate();
            response = true;
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
        return response;
    }

    @Override
    public boolean Delete(int id) {
        boolean response = false;
        try
        {
            PreparedStatement ps = MySQLConnection.getInstance().connection.prepareStatement("DELETE FROM tbl_template WHERE id=?");
            ps.setInt(1, id);
            ps.execute();
            response = true;
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
        return response;
    }

    @Override
    public List All() {
        return null;
    }

    @Override
    public Template Find(int id) {
        Template obj = new Template();
        try
        {
            PreparedStatement ps = MySQLConnection.getInstance().connection.prepareStatement("SELECT tm.id, s.id AS 'season_id', t.id AS 'team_id', p.id AS 'player_id',\n" +
                    "p.dni AS 'player_dni', p.name AS 'player_name', p.lastname AS 'player_lastname', pos.description AS 'player_position', \n" +
                    "tm.dorsal, tm.nickname \n" +
                    "FROM tbl_template tm\n" +
                    "INNER JOIN tbl_season s ON tm.id_season = s.id \n" +
                    "INNER JOIN tbl_team t ON tm.id_team = t.id \n" +
                    "INNER JOIN tbl_player p ON tm.id_player = p.id \n" +
                    "INNER JOIN tbl_position pos ON p.position = pos.id \n" +
                    "WHERE tm.id = ?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if(rs.next())
            {
                obj.setId(rs.getInt("id"));
                //Season
                Season season = new Season();
                season.setId(rs.getInt("season_id"));
                obj.setSeason(season);
                //Team
                Team team = new Team();
                team.setId(rs.getInt("team_id"));
                obj.setTeam(team);
                //Player
                Player player = new Player();
                player.setId(rs.getInt("player_id"));
                player.setDni(rs.getString("player_dni"));
                player.setName(rs.getString("player_name"));
                player.setLastName(rs.getString("player_lastname"));
                //Position
                Position position = new Position();
                position.setDescription(rs.getString("player_position"));
                player.setPosition(position);
                obj.setPlayer(player);
                obj.setDorsal(rs.getString("dorsal"));
                obj.setNickname(rs.getString("nickname"));
            }
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
        return obj;
    }

    public List<Template> ToListSeasonTeam(int SeasonId, int TeamId) {
        ArrayList<Template> List = new ArrayList<>();
        try
        {
            PreparedStatement ps = MySQLConnection.getInstance().connection.prepareStatement("SELECT tm.id, s.id AS 'season_id', t.id AS 'team_id', p.id AS 'player_id',\n" +
                    "p.dni AS 'player_dni', p.name AS 'player_name', p.lastname AS 'player_lastname', pos.description AS 'player_position', \n" +
                    "tm.dorsal, tm.nickname \n" +
                    "FROM tbl_template tm\n" +
                    "INNER JOIN tbl_season s ON tm.id_season = s.id \n" +
                    "INNER JOIN tbl_team t ON tm.id_team = t.id \n" +
                    "INNER JOIN tbl_player p ON tm.id_player = p.id \n" +
                    "INNER JOIN tbl_position pos ON p.position = pos.id \n" +
                    "WHERE tm.id_season = ? AND tm.id_team = ?");
            ps.setInt(1, SeasonId);
            ps.setInt(2, TeamId);
            ResultSet rs = ps.executeQuery();
            while (rs.next())
            {
                Template obj = new Template();
                obj.setId(rs.getInt("id"));
                //Season
                Season season = new Season();
                season.setId(rs.getInt("season_id"));
                obj.setSeason(season);
                //Team
                Team team = new Team();
                team.setId(rs.getInt("team_id"));
                obj.setTeam(team);
                //Player
                Player player = new Player();
                player.setId(rs.getInt("player_id"));
                player.setDni(rs.getString("player_dni"));
                player.setName(rs.getString("player_name"));
                player.setLastName(rs.getString("player_lastname"));
                //Position
                Position position = new Position();
                position.setDescription(rs.getString("player_position"));
                player.setPosition(position);
                obj.setPlayer(player);
                obj.setDorsal(rs.getString("dorsal"));
                obj.setNickname(rs.getString("nickname"));
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
