package App.Football.Factories;

import App.Football.Models.Competition;
import App.Football.Models.Season;
import App.Football.Models.SeasonTeam;
import App.Football.Models.Team;
import App.Football.Providers.MySQLConnection;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class SeasonTeamDAO implements IBusiness {
    @Override
    public boolean Save(Object obj) {
        boolean response = false;
        SeasonTeam seasonTeam = (SeasonTeam) obj;
        try
        {
            CallableStatement cs = MySQLConnection.getInstance().connection.prepareCall("{ CALL SP_SeasonTeam_Add(?,?) }");
            cs.setInt(1, seasonTeam.getSeason().getId());
            cs.setInt(2, seasonTeam.getTeam().getId());
            cs.executeUpdate();
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
        return false;
    }

    @Override
    public boolean Delete(int id) {
        return false;
    }

    @Override
    public List<SeasonTeam> All() {
        return null;
    }

    @Override
    public SeasonTeam Find(int id) {
        return null;
    }

    public List<SeasonTeam> ToListSeason(int id_season) {
        ArrayList<SeasonTeam> List = new ArrayList<>();
        try
        {
            CallableStatement cs = MySQLConnection.getInstance().connection.prepareCall("{ CALL SP_SeasonTeam_ToListSeason(?) }");
            cs.setInt(1, id_season);
            ResultSet rs = cs.executeQuery();
            while (rs.next())
            {
                SeasonTeam obj = new SeasonTeam();
                obj.setId(rs.getInt("id"));
                Season season = new Season();
                season.setId(rs.getInt("id_season"));
                obj.setSeason(season);
                Team team = new Team();
                team.setId(rs.getInt("id_team"));
                team.setFullName(rs.getString("fullname"));
                team.setNickName(rs.getString("nickname"));
                team.setInitials(rs.getString("initials"));
                team.setLogotype(rs.getString("logotype"));
                team.setEmail(rs.getString("email"));
                obj.setTeam(team);
                List.add(obj);
            }
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
        return List;
    }

    public List<SeasonTeam> ToListTeam(int id_team) {
        ArrayList<SeasonTeam> List = new ArrayList<>();
        try
        {
            PreparedStatement ps = MySQLConnection.getInstance().connection.prepareStatement("SELECT st.id, st.id_season, st.id_team, \n" +
                    "s.id_competition, s.name, s.start_date, s.end_date, s.state, \n" +
                    "c.title \n" +
                    "FROM tbl_seasonteam st \n" +
                    "INNER JOIN tbl_season s ON st.id_season = s.id\n" +
                    "INNER JOIN tbl_competition c ON s.id_competition = c.id \n" +
                    "WHERE st.id_team = ? ORDER BY s.id DESC");
            ps.setInt(1, id_team);
            ResultSet rs = ps.executeQuery();
            while (rs.next())
            {
                SeasonTeam obj = new SeasonTeam();
                obj.setId(rs.getInt("id"));
                Season season = new Season();
                season.setId(rs.getInt("id_season"));
                season.setName(rs.getString("name"));
                season.setStartDate(rs.getDate("start_date"));
                season.setEndDate(rs.getDate("end_date"));
                season.setState(rs.getInt("state"));
                Competition competition = new Competition();
                competition.setId(rs.getInt("id_competition"));
                competition.setTitle(rs.getString("title"));
                season.setCompetition(competition);
                obj.setSeason(season);
                Team team = new Team();
                team.setId(rs.getInt("id_team"));
                obj.setTeam(team);
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
