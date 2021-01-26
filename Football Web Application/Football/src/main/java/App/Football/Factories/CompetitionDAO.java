package App.Football.Factories;

import App.Football.Models.Competition;
import App.Football.Providers.MySQLConnection;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class CompetitionDAO implements IBusiness {
    @Override
    public boolean Save(Object obj) {
        boolean response = false;
        Competition competition = (Competition) obj;
        try
        {
            CallableStatement cs = MySQLConnection.getInstance().connection.prepareCall("{ CALL SP_Competition_Add(?) }");
            cs.setString(1, competition.getTitle());
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
        Competition competition = (Competition) obj;
        try
        {
            CallableStatement cs = MySQLConnection.getInstance().connection.prepareCall("{ CALL SP_Competition_ToUpdate(?,?) }");
            cs.setInt(1, competition.getId());
            cs.setString(2, competition.getTitle());
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
            CallableStatement cs = MySQLConnection.getInstance().connection.prepareCall("{ CALL SP_Competition_Remove(?) }");
            cs.setInt(1, id);
            response = cs.execute();
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
        return response;
    }

    @Override
    public List<Competition> All() {
        ArrayList<Competition> List = new ArrayList<>();
        try
        {
            CallableStatement cs = MySQLConnection.getInstance().connection.prepareCall("{ CALL SP_Competition_ToList() }");
            ResultSet rs = cs.executeQuery();
            while (rs.next())
            {
                Competition obj = new Competition();
                obj.setId(rs.getInt("id"));
                obj.setTitle(rs.getString("title"));
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
    public Competition Find(int id) {
        Competition obj = new Competition();
        try
        {
            CallableStatement cs = MySQLConnection.getInstance().connection.prepareCall("{ CALL SP_Competition_Select(?) }");
            cs.setInt(1, id);
            ResultSet rs = cs.executeQuery();
            if(rs.next())
            {
                obj.setId(rs.getInt("id"));
                obj.setTitle(rs.getString("title"));
            }
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
        return obj;
    }
}
