package App.Football.Factories;

import App.Football.Models.Referee;
import App.Football.Providers.MySQLConnection;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class RefereeDAO implements IBusiness {
    @Override
    public boolean Save(Object obj) {
        boolean response = false;
        Referee referee = (Referee) obj;
        try
        {
            CallableStatement cs = MySQLConnection.getInstance().connection.prepareCall("{ CALL SP_Referee_Add(?,?,?,?) }");
            cs.setString(1, referee.getName());
            cs.setString(2, referee.getLastName());
            cs.setString(3, referee.getPhone());
            cs.setString(4, referee.getEmail());
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
        Referee referee = (Referee) obj;
        try
        {
            CallableStatement cs = MySQLConnection.getInstance().connection.prepareCall("{ CALL SP_Referee_ToUpdate(?,?,?,?,?) }");
            cs.setInt(1, referee.getId());
            cs.setString(2, referee.getName());
            cs.setString(3, referee.getLastName());
            cs.setString(4, referee.getPhone());
            cs.setString(5, referee.getEmail());
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
            CallableStatement cs = MySQLConnection.getInstance().connection.prepareCall("{ CALL SP_Referee_Remove(?) }");
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
    public List<Referee> All() {
        ArrayList<Referee> List = new ArrayList<>();
        try
        {
            CallableStatement cs = MySQLConnection.getInstance().connection.prepareCall("{ CALL SP_Referee_ToList() }");
            ResultSet rs = cs.executeQuery();
            while (rs.next())
            {
                Referee obj = new Referee();
                obj.setId(rs.getInt("id"));
                obj.setName(rs.getString("name"));
                obj.setLastName(rs.getString("lastname"));
                obj.setPhone(rs.getString("phone"));
                obj.setEmail(rs.getString("email"));
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
    public Referee Find(int id) {
        Referee obj = new Referee();
        try
        {
            CallableStatement cs = MySQLConnection.getInstance().connection.prepareCall("{ CALL SP_Referee_Select(?) }");
            cs.setInt(1, id);
            ResultSet rs = cs.executeQuery();
            if(rs.next())
            {
                obj.setId(rs.getInt("id"));
                obj.setName(rs.getString("name"));
                obj.setLastName(rs.getString("lastname"));
                obj.setPhone(rs.getString("phone"));
                obj.setEmail(rs.getString("email"));
            }
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
        return obj;
    }
}
