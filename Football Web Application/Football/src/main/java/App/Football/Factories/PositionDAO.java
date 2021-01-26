package App.Football.Factories;

import App.Football.Models.Position;
import App.Football.Providers.MySQLConnection;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class PositionDAO implements IBusiness {
    @Override
    public boolean Save(Object obj) {
        boolean response = false;
        Position position = (Position) obj;
        try
        {
            CallableStatement cs = MySQLConnection.getInstance().connection.prepareCall("{ CALL SP_Position_Add(?) }");
            cs.setString(1, position.getDescription());
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
        Position position = (Position) obj;
        try
        {
            CallableStatement cs = MySQLConnection.getInstance().connection.prepareCall("{ CALL SP_Position_ToUpdate(?,?) }");
            cs.setInt(1, position.getId());
            cs.setString(2, position.getDescription());
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
            CallableStatement cs = MySQLConnection.getInstance().connection.prepareCall("{ CALL SP_Position_Remove(?) }");
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
    public List<Position> All() {
        ArrayList<Position> List = new ArrayList<>();
        try
        {
            CallableStatement cs = MySQLConnection.getInstance().connection.prepareCall("{ CALL SP_Position_ToList() }");
            ResultSet rs = cs.executeQuery();
            while (rs.next())
            {
                Position obj = new Position();
                obj.setId(rs.getInt("id"));
                obj.setDescription(rs.getString("description"));
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
    public Position Find(int id) {
        Position obj = new Position();
        try
        {
            CallableStatement cs = MySQLConnection.getInstance().connection.prepareCall("{ CALL SP_Position_Select(?) }");
            cs.setInt(1, id);
            ResultSet rs = cs.executeQuery();
            if(rs.next())
            {
                obj.setId(rs.getInt("id"));
                obj.setDescription(rs.getString("description"));
            }
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
        return obj;
    }
}
