package App.Football.Factories;

import App.Football.Models.Stadium;
import App.Football.Providers.MySQLConnection;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class StadiumDAO implements IBusiness{
    @Override
    public boolean Save(Object obj) {
        boolean response = false;
        Stadium stadium = (Stadium) obj;
        try
        {
            CallableStatement cs = MySQLConnection.getInstance().connection.prepareCall("{ CALL SP_Stadium_Add(?,?) }");
            cs.setString(1,stadium.getName());
            cs.setString(2,stadium.getAddress());
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
        Stadium stadium = (Stadium) obj;
        try
        {
            CallableStatement cs = MySQLConnection.getInstance().connection.prepareCall("{ CALL SP_Stadium_ToUpdate(?,?,?) }");
            cs.setInt(1,stadium.getId());
            cs.setString(2,stadium.getName());
            cs.setString(3,stadium.getAddress());
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
            CallableStatement cs = MySQLConnection.getInstance().connection.prepareCall("{ CALL SP_Stadium_Remove(?) }");
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
    public List<Stadium> All() {
        ArrayList<Stadium> List = new ArrayList<>();
        try
        {
            CallableStatement cs = MySQLConnection.getInstance().connection.prepareCall("{ CALL SP_Stadium_ToList() }");
            ResultSet rs = cs.executeQuery();
            while (rs.next())
            {
                Stadium obj = new Stadium();
                obj.setId(rs.getInt("id"));
                obj.setName(rs.getString("name"));
                obj.setAddress(rs.getString("address"));
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
    public Stadium Find(int id) {
        Stadium obj = new Stadium();
        try
        {
            CallableStatement cs = MySQLConnection.getInstance().connection.prepareCall("{ CALL SP_Stadium_Select(?) }");
            cs.setInt(1, id);
            ResultSet rs = cs.executeQuery();
            if(rs.next())
            {
                obj.setId(rs.getInt("id"));
                obj.setName(rs.getString("name"));
                obj.setAddress(rs.getString("address"));
            }
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
        return obj;
    }
}
