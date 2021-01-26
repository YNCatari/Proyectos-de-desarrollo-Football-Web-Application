package App.Football.Factories;

import App.Football.Models.Community;
import App.Football.Models.Team;
import App.Football.Providers.MySQLConnection;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class TeamDAO implements IBusiness{
    @Override
    public boolean Save(Object obj) {
        boolean response = false;
        Team team = (Team) obj;
        try
        {
            CallableStatement cs = MySQLConnection.getInstance().connection.prepareCall("{ CALL SP_Team_Add(?,?,?,?,?,?) }");
            cs.setString(1, team.getFullName());
            cs.setString(2, team.getNickName());
            cs.setString(3, team.getInitials());
            cs.setString(4, team.getLogotype());
            cs.setString(5, team.getEmail());
            cs.setInt(6, team.getCommunity().getId());
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
        Team team = (Team) obj;
        try
        {
            CallableStatement cs = MySQLConnection.getInstance().connection.prepareCall("{ CALL SP_Team_ToUpdate(?,?,?,?,?,?) }");
            cs.setInt(1, team.getId());
            cs.setString(2, team.getFullName());
            cs.setString(3, team.getNickName());
            cs.setString(4, team.getInitials());
            cs.setString(5, team.getLogotype());
            cs.setInt(6, team.getCommunity().getId());
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
            CallableStatement cs = MySQLConnection.getInstance().connection.prepareCall("{ CALL SP_Team_Remove(?) }");
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
    public List<Team> All() {
        ArrayList<Team> List = new ArrayList<>();
        try
        {
            CallableStatement cs = MySQLConnection.getInstance().connection.prepareCall("{ CALL SP_Team_ToList() }");
            ResultSet rs = cs.executeQuery();
            while (rs.next())
            {
                Team obj = new Team();
                obj.setId(rs.getInt("id"));
                obj.setFullName(rs.getString("fullname"));
                obj.setNickName(rs.getString("nickname"));
                obj.setInitials(rs.getString("initials"));
                obj.setLogotype(rs.getString("logotype"));
                obj.setEmail(rs.getString("email"));
                Community community = new Community();
                community.setId(rs.getInt("id_community"));
                community.setName(rs.getString("name"));
                obj.setCommunity(community);
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
    public Team Find(int id) {
        Team obj = new Team();
        try
        {
            CallableStatement cs = MySQLConnection.getInstance().connection.prepareCall("{ CALL SP_Team_Select(?) }");
            cs.setInt(1, id);
            ResultSet rs = cs.executeQuery();
            if(rs.next())
            {
                obj.setId(rs.getInt("id"));
                obj.setFullName(rs.getString("fullname"));
                obj.setNickName(rs.getString("nickname"));
                obj.setInitials(rs.getString("initials"));
                obj.setLogotype(rs.getString("logotype"));
                obj.setEmail(rs.getString("email"));
                Community community = new Community();
                community.setId(rs.getInt("id_community"));
                community.setName(rs.getString("name"));
                obj.setCommunity(community);
            }
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
        return obj;
    }

    public static int getId(String email) {
        Team obj = new Team();
        try
        {
            PreparedStatement ps = MySQLConnection.getInstance().connection.prepareStatement("SELECT id FROM tbl_team WHERE email=?");
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();
            if(rs.next())
            {
                obj.setId(rs.getInt("id"));
            }
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
        return obj.getId();
    }
}
