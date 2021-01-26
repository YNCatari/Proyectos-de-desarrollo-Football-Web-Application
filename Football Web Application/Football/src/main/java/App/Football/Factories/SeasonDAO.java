package App.Football.Factories;

import App.Football.Models.Competition;
import App.Football.Models.Season;
import App.Football.Providers.MySQLConnection;

import java.sql.CallableStatement;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class SeasonDAO implements IBusiness {
    @Override
    public boolean Save(Object obj) {
        boolean response = false;
        Season season = (Season) obj;
        try
        {
            CallableStatement cs = MySQLConnection.getInstance().connection.prepareCall("{ CALL SP_Season_Add(?,?,?,?,?) }");
            cs.setInt(1, season.getCompetition().getId());
            cs.setString(2, season.getName());
            cs.setDate(3, (Date) season.getStartDate());
            cs.setDate(4, (Date) season.getEndDate());
            cs.setInt(5, season.getState());
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
        boolean response = false;
        Season season = (Season) obj;
        try
        {
            CallableStatement cs = MySQLConnection.getInstance().connection.prepareCall("{ CALL SP_Season_ToUpdate(?,?,?,?,?) }");
            cs.setInt(1, season.getId());
            cs.setInt(2, season.getCompetition().getId());
            cs.setString(3, season.getName());
            cs.setDate(4, (Date) season.getStartDate());
            cs.setDate(5, (Date) season.getEndDate());
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
    public boolean Delete(int id) {
        boolean response = false;
        try
        {
            CallableStatement cs = MySQLConnection.getInstance().connection.prepareCall("{ CALL SP_Season_Remove(?) }");
            cs.setInt(1, id);
            cs.execute();
            response = true;
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
        return response;
    }

    @Override
    public List<Season> All() {
        ArrayList<Season> List = new ArrayList<>();
        try
        {
            CallableStatement cs = MySQLConnection.getInstance().connection.prepareCall("{ CALL SP_Season_ToList() }");
            ResultSet rs = cs.executeQuery();
            while (rs.next())
            {
                Season obj = new Season();
                obj.setId(rs.getInt("id"));
                obj.setName(rs.getString("name"));
                obj.setStartDate(rs.getDate("start_date"));
                obj.setEndDate(rs.getDate("end_date"));
                obj.setState(rs.getInt("state"));
                //Competition
                Competition competition = new Competition();
                competition.setId(rs.getInt("id_competition"));
                competition.setTitle(rs.getString("title"));
                obj.setCompetition(competition);
                List.add(obj);
            }
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
        return List;
    }

    @Override
    public Season Find(int id) {
        Season obj = new Season();
        try
        {
            CallableStatement cs = MySQLConnection.getInstance().connection.prepareCall("{ CALL SP_Season_Select(?) }");
            cs.setInt(1, id);
            ResultSet rs = cs.executeQuery();
            if(rs.next())
            {
                obj.setId(rs.getInt("id"));
                obj.setName(rs.getString("name"));
                obj.setStartDate(rs.getDate("start_date"));
                obj.setEndDate(rs.getDate("end_date"));
                obj.setState(rs.getInt("state"));
                //Competition
                Competition competition = new Competition();
                competition.setId(rs.getInt("id_competition"));
                competition.setTitle(rs.getString("title"));
                obj.setCompetition(competition);
            }
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
        return obj;
    }

    public boolean ToUpdateState(int id, int state) {
        boolean response = false;
        try
        {
            CallableStatement cs = MySQLConnection.getInstance().connection.prepareCall("{ CALL SP_Season_ToUpdateState(?,?) }");
            cs.setInt(1, id);
            cs.setInt(2, state);
            cs.executeUpdate();
            response = true;
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
        return response;
    }


    public List<Season> ToListSeasonState(int state) {
        ArrayList<Season> List = new ArrayList<>();
        try
        {
            PreparedStatement ps = MySQLConnection.getInstance().connection.prepareStatement("SELECT ss.id, ss.name, ss.id_competition, ct.title, ss.start_date, ss.end_date, ss.state \n" +
                    "FROM tbl_season ss INNER JOIN tbl_competition ct ON ss.id_competition = ct.id \n" +
                    "WHERE ss.state = ? ORDER BY id DESC");
            ps.setInt(1, state);
            ResultSet rs = ps.executeQuery();
            while (rs.next())
            {
                Season obj = new Season();
                obj.setId(rs.getInt("id"));
                obj.setName(rs.getString("name"));
                obj.setStartDate(rs.getDate("start_date"));
                obj.setEndDate(rs.getDate("end_date"));
                obj.setState(rs.getInt("state"));
                //Competition
                Competition competition = new Competition();
                competition.setId(rs.getInt("id_competition"));
                competition.setTitle(rs.getString("title"));
                obj.setCompetition(competition);
                List.add(obj);
            }
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
        return List;
    }

    public boolean ToUpdateEndDate(int id, Date date) {
        boolean response = false;
        try
        {
            PreparedStatement cs = MySQLConnection.getInstance().connection.prepareStatement("UPDATE tbl_season SET end_date=? WHERE id=?");
            cs.setDate(1, date);
            cs.setInt(2, id);
            cs.executeUpdate();
            response = true;
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
        return response;
    }

    public List<Season> ToListLast(int state) {
        ArrayList<Season> List = new ArrayList<>();
        try
        {
            PreparedStatement ps = MySQLConnection.getInstance().connection.prepareStatement("SELECT s.id, s.id_competition, s.name, s.start_date, s.end_date, s.state, c.title  \n" +
                    "FROM tbl_season s\n" +
                    "INNER JOIN tbl_competition c ON s.id_competition = c.id \n" +
                    "WHERE s.state=? ORDER BY s.id DESC LIMIT 5");
            ps.setInt(1, state);
            ResultSet rs = ps.executeQuery();
            while(rs.next())
            {
                Season obj = new Season();
                obj.setId(rs.getInt("id"));
                obj.setName(rs.getString("name"));
                obj.setStartDate(rs.getDate("start_date"));
                obj.setEndDate(rs.getDate("end_date"));
                obj.setState(rs.getInt("state"));
                //Competition
                Competition competition = new Competition();
                competition.setId(rs.getInt("id_competition"));
                competition.setTitle(rs.getString("title"));
                obj.setCompetition(competition);
                List.add(obj);
            }
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
        return List;
    }

    public Season FindLast(int state) {
        Season obj = new Season();
        try
        {
            PreparedStatement ps = MySQLConnection.getInstance().connection.prepareStatement("SELECT s.id, s.id_competition, s.name, s.start_date, s.end_date, s.state, c.title  \n" +
                    "FROM tbl_season s\n" +
                    "INNER JOIN tbl_competition c ON s.id_competition = c.id \n" +
                    "WHERE s.state=? ORDER BY s.id DESC LIMIT 1");
            ps.setInt(1, state);
            ResultSet rs = ps.executeQuery();
            if(rs.next())
            {
                obj.setId(rs.getInt("id"));
                obj.setName(rs.getString("name"));
                obj.setStartDate(rs.getDate("start_date"));
                obj.setEndDate(rs.getDate("end_date"));
                obj.setState(rs.getInt("state"));
                //Competition
                Competition competition = new Competition();
                competition.setId(rs.getInt("id_competition"));
                competition.setTitle(rs.getString("title"));
                obj.setCompetition(competition);
            }
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
        return obj;
    }
}
