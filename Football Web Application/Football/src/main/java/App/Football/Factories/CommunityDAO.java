package App.Football.Factories;

import App.Football.Models.Community;
import App.Football.Models.Position;
import App.Football.Providers.MySQLConnection;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class CommunityDAO implements IBusiness {
    @Override
    public boolean Save(Object obj) {
        boolean response = false;
        Community community = (Community) obj;
        try
        {
            CallableStatement cs = MySQLConnection.getInstance().connection.prepareCall("{ CALL SP_Community_Add(?) }");
            cs.setString(1, community.getName());
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
        Community community = (Community) obj;
        try
        {
            CallableStatement cs = MySQLConnection.getInstance().connection.prepareCall("{ CALL SP_Community_ToUpdate(?,?) }");
            cs.setInt(1, community.getId());
            cs.setString(2, community.getName());
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
            CallableStatement cs = MySQLConnection.getInstance().connection.prepareCall("{ CALL SP_Community_Remove(?) }");
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
    public List<Community> All() {
        ArrayList<Community> List = new ArrayList<>();
        try
        {
            CallableStatement cs = MySQLConnection.getInstance().connection.prepareCall("{ CALL SP_Community_ToList() }");
            ResultSet rs = cs.executeQuery();
            while (rs.next())
            {
                Community obj = new Community();
                obj.setId(rs.getInt("id"));
                obj.setName(rs.getString("name"));
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
    public Community Find(int id) {
        Community obj = new Community();
        try
        {
            CallableStatement cs = MySQLConnection.getInstance().connection.prepareCall("{ CALL SP_Community_Select(?) }");
            cs.setInt(1, id);
            ResultSet rs = cs.executeQuery();
            if(rs.next())
            {
                obj.setId(rs.getInt("id"));
                obj.setName(rs.getString("name"));
            }
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
        return obj;
    }
}
