package App.Football.Factories;

import App.Football.Models.User;
import App.Football.Providers.MySQLConnection;
import App.Football.Security.UserInfo;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class UserDAO implements IBusiness {
    @Override
    public boolean Save(Object obj) {
        boolean response = false;
        User user = (User) obj;
        try
        {
            PreparedStatement ps = MySQLConnection.getInstance().connection.prepareStatement("INSERT INTO tbl_user(username,password,state,role) VALUES(?,?,?,?)");
            ps.setString(1, user.getUsername());
            ps.setString(2, user.getPassword());
            ps.setInt(3, user.getState());
            ps.setInt(4, user.getRole());
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
        User user = (User) obj;
        try
        {
            PreparedStatement ps = MySQLConnection.getInstance().connection.prepareStatement("UPDATE tbl_user SET state=? WHERE id=?");
            ps.setInt(1, user.getState());
            ps.setInt(2, user.getId());
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
        return false;
    }

    @Override
    public List<User> All() {
        ArrayList<User> List = new ArrayList<>();
        try
        {
            PreparedStatement ps = MySQLConnection.getInstance().connection.prepareStatement("SELECT * FROM tbl_user");
            ResultSet rs = ps.executeQuery();
            while (rs.next())
            {
                User obj = new User();
                obj.setId(rs.getInt("id"));
                obj.setUsername(rs.getString("username"));
                obj.setPassword(rs.getString("password"));
                obj.setState(rs.getInt("state"));
                obj.setRole(rs.getInt("role"));
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
    public User Find(int id) {
        User obj = new User();
        try
        {
            PreparedStatement ps = MySQLConnection.getInstance().connection.prepareStatement("SELECT * FROM tbl_user WHERE id=?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if(rs.next())
            {
                obj.setId(rs.getInt("id"));
                obj.setUsername(rs.getString("username"));
                obj.setPassword(rs.getString("password"));
                obj.setState(rs.getInt("state"));
                obj.setRole(rs.getInt("role"));
            }
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
        return obj;
    }

    public User Login(String username, String password) {
        User obj = new User();
        try
        {
            CallableStatement cs = MySQLConnection.getInstance().connection.prepareCall("{ CALL SP_User_Login(?,?) }");
            cs.setString(1, username);
            cs.setString(2, password);
            ResultSet rs = cs.executeQuery();
            if(rs.next())
            {
                obj.setId(rs.getInt("id"));
                obj.setUsername(rs.getString("username"));
                obj.setState(rs.getInt("state"));
                obj.setRole(rs.getInt("role"));
            }
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
        return obj;
    }

    public UserInfo FindByUsername(String username) {
        UserInfo obj = new UserInfo();
        try
        {
            PreparedStatement ps = MySQLConnection.getInstance().connection.prepareStatement("SELECT id, username, password, state, role FROM tbl_user WHERE username=? AND state=1");
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();
            if(rs.next())
            {
                obj.setId(rs.getInt("id"));
                obj.setUsername(rs.getString("username"));
                obj.setPassword("{noop}" + rs.getString("password"));
                obj.setState(rs.getInt("state"));
                obj.setRole(rs.getInt("role"));
            }
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
        return obj;
    }
}
