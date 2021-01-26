package App.Football.Factories;

import App.Football.Models.Player;
import App.Football.Models.Position;
import App.Football.Providers.MySQLConnection;

import java.sql.CallableStatement;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class PlayerDAO implements IBusiness {
    @Override
    public boolean Save(Object obj) {
        boolean response = false;
        Player player = (Player) obj;
        try
        {
            CallableStatement cs = MySQLConnection.getInstance().connection.prepareCall("{ CALL SP_Player_Add(?,?,?,?,?,?,?,?,?,?,?,?) }");
            cs.setString(1, player.getDni());
            cs.setString(2, player.getName());
            cs.setString(3, player.getLastName());
            cs.setDate(4, (Date) player.getDateBirth());
            cs.setString(5, player.getPhoto());
            cs.setString(6, player.getPhone());
            cs.setString(7, player.getEmail());
            cs.setDouble(8, player.getHeight());
            cs.setDouble(9, player.getWeight());
            cs.setString(10, player.getFoot());
            cs.setInt(11, player.getPosition().getId());
            cs.setInt(12, player.getState());
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
        Player player = (Player) obj;
        try
        {
            CallableStatement cs = MySQLConnection.getInstance().connection.prepareCall("{ CALL SP_Player_ToUpdate(?,?,?,?,?,?,?,?,?,?,?,?,?) }");
            cs.setInt(1, player.getId());
            cs.setString(2, player.getDni());
            cs.setString(3, player.getName());
            cs.setString(4, player.getLastName());
            cs.setDate(5, (Date) player.getDateBirth());
            cs.setString(6, player.getPhoto());
            cs.setString(7, player.getPhone());
            cs.setString(8, player.getEmail());
            cs.setDouble(9, player.getHeight());
            cs.setDouble(10, player.getWeight());
            cs.setString(11, player.getFoot());
            cs.setInt(12, player.getPosition().getId());
            cs.setInt(13, player.getState());
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
        return false;
    }

    @Override
    public List<Player> All() {
        ArrayList<Player> List = new ArrayList<>();
        try
        {
            CallableStatement cs = MySQLConnection.getInstance().connection.prepareCall("{ CALL SP_Player_ToList() }");
            ResultSet rs = cs.executeQuery();
            while (rs.next())
            {
                Player obj = new Player();
                obj.setId(rs.getInt("id"));
                obj.setDni(rs.getString("dni"));
                obj.setName(rs.getString("name"));
                obj.setLastName(rs.getString("lastname"));
                obj.setDateBirth(rs.getDate("date_birth"));
                obj.setPhoto(rs.getString("photo"));
                obj.setPhone(rs.getString("phone"));
                obj.setEmail(rs.getString("email"));
                obj.setHeight(rs.getDouble("height"));
                obj.setWeight(rs.getDouble("weight"));
                obj.setFoot(rs.getString("foot"));
                obj.setState(rs.getInt("state"));
                Position position = new Position();
                position.setId(rs.getInt("position"));
                position.setDescription(rs.getString("description"));
                obj.setPosition(position);
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
    public Player Find(int id) {
        Player obj = new Player();
        try
        {
            CallableStatement cs = MySQLConnection.getInstance().connection.prepareCall("{ CALL SP_Player_Select(?) }");
            cs.setInt(1, id);
            ResultSet rs = cs.executeQuery();
            if(rs.next())
            {
                obj.setId(rs.getInt("id"));
                obj.setDni(rs.getString("dni"));
                obj.setName(rs.getString("name"));
                obj.setLastName(rs.getString("lastname"));
                obj.setDateBirth(rs.getDate("date_birth"));
                obj.setPhoto(rs.getString("photo"));
                obj.setPhone(rs.getString("phone"));
                obj.setEmail(rs.getString("email"));
                obj.setHeight(rs.getDouble("height"));
                obj.setWeight(rs.getDouble("weight"));
                obj.setFoot(rs.getString("foot"));
                obj.setState(rs.getInt("state"));
                Position position = new Position();
                position.setId(rs.getInt("position"));
                position.setDescription(rs.getString("description"));
                obj.setPosition(position);
            }
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
        return obj;
    }

    public List<Player> ToListCommunity(int id_community) {
        ArrayList<Player> List = new ArrayList<>();
        try
        {
            PreparedStatement ps = MySQLConnection.getInstance().connection.prepareStatement("SELECT py.id, py.dni, py.name, py.lastname, py.date_birth, py.photo, \n" +
                    "py.phone, py.email, py.height, py.weight, py.foot, py.position, ps.description, py.state \n" +
                    "FROM tbl_player py INNER JOIN tbl_position ps ON py.position = ps.id \n" +
                    "INNER JOIN tbl_communityplayer cp ON py.id = cp.id_player WHERE cp.id_community = ?");
            ps.setInt(1, id_community);
            ResultSet rs = ps.executeQuery();
            while (rs.next())
            {
                Player obj = new Player();
                obj.setId(rs.getInt("id"));
                obj.setDni(rs.getString("dni"));
                obj.setName(rs.getString("name"));
                obj.setLastName(rs.getString("lastname"));
                obj.setDateBirth(rs.getDate("date_birth"));
                obj.setPhoto(rs.getString("photo"));
                obj.setPhone(rs.getString("phone"));
                obj.setEmail(rs.getString("email"));
                obj.setHeight(rs.getDouble("height"));
                obj.setWeight(rs.getDouble("weight"));
                obj.setFoot(rs.getString("foot"));
                obj.setState(rs.getInt("state"));
                Position position = new Position();
                position.setId(rs.getInt("position"));
                position.setDescription(rs.getString("description"));
                obj.setPosition(position);
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
