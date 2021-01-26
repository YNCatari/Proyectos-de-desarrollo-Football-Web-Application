package App.Football.Factories;

import App.Football.Models.TypeResult;
import App.Football.Providers.MySQLConnection;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class TypeResultDAO implements IBusiness {
    @Override
    public boolean Save(Object obj) {
        boolean response = false;
        TypeResult typeResult = (TypeResult) obj;
        try
        {
            CallableStatement cs = MySQLConnection.getInstance().connection.prepareCall("{ CALL SP_TypeResult_Add(?) }");
            cs.setString(1, typeResult.getDescription());
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
        TypeResult typeResult = (TypeResult) obj;
        try
        {
            CallableStatement cs = MySQLConnection.getInstance().connection.prepareCall("{ CALL SP_TypeResult_ToUpdate(?,?) }");
            cs.setInt(1, typeResult.getId());
            cs.setString(2, typeResult.getDescription());
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
            CallableStatement cs = MySQLConnection.getInstance().connection.prepareCall("{ CALL SP_TypeResult_Remove(?) }");
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
    public List<TypeResult> All() {
        ArrayList<TypeResult> List = new ArrayList<>();
        try
        {
            CallableStatement cs = MySQLConnection.getInstance().connection.prepareCall("{ CALL SP_TypeResult_ToList() }");
            ResultSet rs = cs.executeQuery();
            while (rs.next())
            {
                TypeResult obj = new TypeResult();
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
    public TypeResult Find(int id) {
        TypeResult obj = new TypeResult();
        try
        {
            CallableStatement cs = MySQLConnection.getInstance().connection.prepareCall("{ CALL SP_TypeResult_Select(?) }");
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
