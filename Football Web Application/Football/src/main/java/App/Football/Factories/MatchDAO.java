package App.Football.Factories;

import App.Football.Helpers.AppUtil;
import App.Football.Helpers.LeagueFixture;
import App.Football.Models.*;
import App.Football.Providers.MySQLConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class MatchDAO implements IBusiness {
    @Override
    public boolean Save(Object obj) {
        boolean response = false;
        Match match = (Match) obj;
        try
        {
            CallableStatement cs = MySQLConnection.getInstance().connection.prepareCall("{ CALL SP_Match_Add(?,?,?,?,?,?,?,?,?,?) }");
            cs.setInt(1, match.getSeason().getId());
            cs.setInt(2, match.getStadium().getId());
            cs.setInt(3, match.getTeamLocal().getId());
            cs.setInt(4, match.getTeamVisitor().getId());
            cs.setDate(5, (Date) match.getMatchDate());
            cs.setTime(6, match.getMatchHour());
            cs.setInt(7, match.getMatchRound());
            cs.setInt(8, match.getGoalLocal());
            cs.setInt(9, match.getGoalVisitor());
            cs.setInt(10, match.getState());
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
        Match match = (Match) obj;
        try
        {
            PreparedStatement ps = MySQLConnection.getInstance().connection.prepareStatement("UPDATE tbl_match SET id_stadium=?, match_date=?, match_hour=? WHERE id=?");
            ps.setInt(4, match.getId());
            ps.setInt(1, match.getStadium().getId());
            ps.setDate(2, (Date) match.getMatchDate());
            ps.setTime(3, match.getMatchHour());
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
    public List<Match> All() {
        return null;
    }

    @Override
    public Match Find(int id) {
        Match obj = new Match();
        try
        {
            CallableStatement cs = MySQLConnection.getInstance().connection.prepareCall("{ CALL SP_Match_Select(?) }");
            cs.setInt(1, id);
            ResultSet rs = cs.executeQuery();
            if(rs.next())
            {
                obj.setId(rs.getInt("id"));
                obj.setMatchDate(rs.getDate("match_date"));
                obj.setMatchHour(rs.getTime("match_hour"));
                obj.setMatchRound(rs.getInt("match_round"));
                obj.setGoalLocal(rs.getInt("goal_local"));
                obj.setGoalVisitor(rs.getInt("goal_visitor"));
                obj.setState(rs.getInt("state"));
                //Season
                Season season = new Season();
                season.setId(rs.getInt("id_season"));
                obj.setSeason(season);
                //Stadium
                Stadium stadium = new Stadium();
                stadium.setId(rs.getInt("id_stadium"));
                stadium.setName(rs.getString("stadium_name"));
                stadium.setAddress(rs.getString("stadium_address"));
                obj.setStadium(stadium);
                //Team Local
                Team teamLocal = new Team();
                teamLocal.setId(rs.getInt("id_team_local"));
                teamLocal.setFullName(rs.getString("local_fullname"));
                teamLocal.setNickName(rs.getString("local_nickname"));
                teamLocal.setInitials(rs.getString("local_initials"));
                teamLocal.setLogotype(rs.getString("local_logotype"));
                obj.setTeamLocal(teamLocal);
                //Team Visitor
                Team teamVisitor = new Team();
                teamVisitor.setId(rs.getInt("id_team_visitor"));
                teamVisitor.setFullName(rs.getString("visitor_fullname"));
                teamVisitor.setNickName(rs.getString("visitor_nickname"));
                teamVisitor.setInitials(rs.getString("visitor_initials"));
                teamVisitor.setLogotype(rs.getString("visitor_logotype"));
                obj.setTeamVisitor(teamVisitor);
            }
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
        return obj;
    }

    public List<Match> ToListSeason(int id_Season) {
        ArrayList<Match> List = new ArrayList<>();
        try
        {
            CallableStatement cs = MySQLConnection.getInstance().connection.prepareCall("{ CALL SP_Match_ToListSeason(?) }");
            cs.setInt(1, id_Season);
            ResultSet rs = cs.executeQuery();
            while (rs.next())
            {
                Match obj = new Match();
                obj.setId(rs.getInt("id"));
                obj.setMatchDate(rs.getDate("match_date"));
                obj.setMatchHour(rs.getTime("match_hour"));
                obj.setMatchRound(rs.getInt("match_round"));
                obj.setGoalLocal(rs.getInt("goal_local"));
                obj.setGoalVisitor(rs.getInt("goal_visitor"));
                obj.setState(rs.getInt("state"));
                //Season
                Season season = new Season();
                season.setId(rs.getInt("id_season"));
                obj.setSeason(season);
                //Stadium
                Stadium stadium = new Stadium();
                stadium.setId(rs.getInt("id_stadium"));
                obj.setStadium(stadium);
                //Team Local
                Team teamLocal = new Team();
                teamLocal.setId(rs.getInt("id_team_local"));
                teamLocal.setFullName(rs.getString("tl_fullname"));
                teamLocal.setNickName(rs.getString("tl_nickname"));
                teamLocal.setLogotype(rs.getString("tl_logotype"));
                obj.setTeamLocal(teamLocal);
                //Team Visitor
                Team teamVisitor = new Team();
                teamVisitor.setId(rs.getInt("id_team_visitor"));
                teamVisitor.setFullName(rs.getString("tv_fullname"));
                teamVisitor.setNickName(rs.getString("tv_nickname"));
                teamVisitor.setLogotype(rs.getString("tv_logotype"));
                obj.setTeamVisitor(teamVisitor);
                //add
                List.add(obj);
            }
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
        return List;
    }


    public List<Match> ToListSeasonTeam(int id_Season, int id_Team) {
        ArrayList<Match> List = new ArrayList<>();
        try
        {
            PreparedStatement ps = MySQLConnection.getInstance().connection.prepareStatement("SELECT \n" +
                    "mt.id, mt.id_season, mt.id_stadium, mt.id_team_local, mt.id_team_visitor, mt.match_date, mt.match_hour, mt.match_round, mt.goal_local, mt.goal_visitor, mt.state, \n" +
                    "tl.fullname AS 'tl_fullname', tl.nickname AS 'tl_nickname', tl.logotype AS 'tl_logotype', \n" +
                    "tv.fullname AS 'tv_fullname', tv.nickname AS 'tv_nickname', tv.logotype AS 'tv_logotype' \n" +
                    "FROM tbl_match mt \n" +
                    "INNER JOIN tbl_team tl ON mt.id_team_local = tl.id\n" +
                    "INNER JOIN tbl_team tv ON mt.id_team_visitor = tv.id\n" +
                    "WHERE mt.id_season = ? AND (mt.id_team_local = ? OR mt.id_team_visitor = ?)");
            ps.setInt(1, id_Season);
            ps.setInt(2, id_Team);
            ps.setInt(3, id_Team);
            ResultSet rs = ps.executeQuery();
            while (rs.next())
            {
                Match obj = new Match();
                obj.setId(rs.getInt("id"));
                obj.setMatchDate(rs.getDate("match_date"));
                obj.setMatchHour(rs.getTime("match_hour"));
                obj.setMatchRound(rs.getInt("match_round"));
                obj.setGoalLocal(rs.getInt("goal_local"));
                obj.setGoalVisitor(rs.getInt("goal_visitor"));
                obj.setState(rs.getInt("state"));
                //Season
                Season season = new Season();
                season.setId(rs.getInt("id_season"));
                obj.setSeason(season);
                //Stadium
                Stadium stadium = new Stadium();
                stadium.setId(rs.getInt("id_stadium"));
                obj.setStadium(stadium);
                //Team Local
                Team teamLocal = new Team();
                teamLocal.setId(rs.getInt("id_team_local"));
                teamLocal.setFullName(rs.getString("tl_fullname"));
                teamLocal.setNickName(rs.getString("tl_nickname"));
                teamLocal.setLogotype(rs.getString("tl_logotype"));
                obj.setTeamLocal(teamLocal);
                //Team Visitor
                Team teamVisitor = new Team();
                teamVisitor.setId(rs.getInt("id_team_visitor"));
                teamVisitor.setFullName(rs.getString("tv_fullname"));
                teamVisitor.setNickName(rs.getString("tv_nickname"));
                teamVisitor.setLogotype(rs.getString("tv_logotype"));
                obj.setTeamVisitor(teamVisitor);
                //add
                List.add(obj);
            }
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
        return List;
    }


    public List<Integer> ToListRoundsSeason(int id_Season) {
        ArrayList<Integer> List = new ArrayList<>();
        try
        {
            PreparedStatement ps = MySQLConnection.getInstance().connection.prepareStatement("SELECT match_round FROM tbl_match WHERE id_season = ?  GROUP BY match_round");
            ps.setInt(1, id_Season);
            ResultSet rs = ps.executeQuery();
            while (rs.next())
            {
                List.add(rs.getInt("match_round"));
            }
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
        return List;
    }

    public boolean GenerateMatches(int id_season, int id_stadium)
    {
        boolean response = false;
        List<Integer> teams = new ArrayList<>();
        SeasonTeamDAO dao = new SeasonTeamDAO();
        List<SeasonTeam> listSeasonTeam = dao.ToListSeason(id_season);
        for(SeasonTeam seasonTeam : listSeasonTeam)
        {
            teams.add(seasonTeam.getTeam().getId());
        }

        //Date
        SeasonDAO siDAO = new SeasonDAO();
        Season si = siDAO.Find(id_season);
        Date date = si.getStartDate();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.HOUR, 8);
        int days = 0;
        int hours = 0;
        Date newEndDate = null;

        //Algorithm Fixture
        LeagueFixture.Partido[][] rondas =  LeagueFixture.CalculateLeague(listSeasonTeam.size());
        //IDA
        System.out.println("IDA");
        for (int i = 0; i < rondas.length; i ++)
        {
            System.out.print("Ronda " + (i + 1) + ": ");


            calendar.add(Calendar.DAY_OF_YEAR, days);
            calendar.add(Calendar.HOUR, hours);
            java.util.Date dataMatch = calendar.getTime();
            Date newDate = new Date(AppUtil.convertToDate(dataMatch).getTime());
            String newHour = AppUtil.convertToTime(dataMatch);
            System.out.println(newDate);
            days = 7;
            newEndDate = newDate;


            for (int j = 0; j < rondas[i].length; j ++)
            {
                //Create Match
                Match obj = new Match();
                //Season
                Season season = new Season();
                season.setId(id_season);
                //Stadium
                Stadium stadium = new Stadium();
                stadium.setId(id_stadium);
                //Team Local
                Team teamLocal = new Team();
                teamLocal.setId(teams.get(rondas[i][j].local));
                //Team Visitor
                Team teamVisitor = new Team();
                teamVisitor.setId(teams.get(rondas[i][j].visitante));
                //set
                obj.setSeason(season);
                obj.setStadium(stadium);
                obj.setTeamLocal(teamLocal);
                obj.setTeamVisitor(teamVisitor);

                obj.setMatchDate(newDate);
                obj.setMatchHour(Time.valueOf(newHour));

                obj.setMatchRound(i + 1);
                obj.setGoalLocal(0);
                obj.setGoalVisitor(0);
                obj.setState(0);

                //Save Match
                if(this.Save(obj))
                {
                    System.out.print("  " + teams.get(rondas[i][j].local) + "-" + teams.get(rondas[i][j].visitante));
                    response = true;
                }
            }
            System.out.println();
        }
        //VUELTA
        System.out.println("VUELTA");
        if(response)
        {
            //Update Season
            SeasonDAO seasonDAO = new SeasonDAO();
            seasonDAO.ToUpdateState(id_season,1);
            seasonDAO.ToUpdateEndDate(id_season, newEndDate);
            //Create Classification
            ClassificationDAO classificationDAO = new ClassificationDAO();
            classificationDAO.CreateClassification(id_season,listSeasonTeam);
        }
        return response;
    }


    //Match State Update
    public boolean StateUpdate(int match_id, int state) {
        boolean response = false;
        try
        {
            PreparedStatement ps = MySQLConnection.getInstance().connection.prepareStatement("UPDATE tbl_match SET state=? WHERE id=?");
            ps.setInt(1, state);
            ps.setInt(2, match_id);
            ps.executeUpdate();
            response = true;
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
        return response;
    }

    //Match Goal Update
    public boolean GoalUpdate(int match_id, int goalLocal, int goalVisitor) {
        boolean response = false;
        try
        {
            PreparedStatement ps = MySQLConnection.getInstance().connection.prepareStatement("UPDATE tbl_match SET goal_local=?, goal_visitor=? WHERE id=?");
            ps.setInt(1, goalLocal);
            ps.setInt(2, goalVisitor);
            ps.setInt(3, match_id);
            ps.executeUpdate();
            response = true;
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
        return response;
    }

    public List<Match> ToListMatchTeams(int teamLocal_id, int teamVisitor_id) {
        ArrayList<Match> List = new ArrayList<>();
        try
        {
            PreparedStatement ps = MySQLConnection.getInstance().connection.prepareStatement("SELECT * FROM tbl_match WHERE (id_team_local=? AND id_team_visitor=?) OR (id_team_local=? AND id_team_visitor=?)");
            ps.setInt(1, teamLocal_id);
            ps.setInt(2, teamVisitor_id);
            ps.setInt(3, teamVisitor_id);
            ps.setInt(4, teamLocal_id);
            ResultSet rs = ps.executeQuery();
            while (rs.next())
            {
                Match obj = new Match();
                obj.setId(rs.getInt("id"));
                obj.setMatchDate(rs.getDate("match_date"));
                obj.setMatchHour(rs.getTime("match_hour"));
                obj.setMatchRound(rs.getInt("match_round"));
                obj.setGoalLocal(rs.getInt("goal_local"));
                obj.setGoalVisitor(rs.getInt("goal_visitor"));
                obj.setState(rs.getInt("state"));
                //Season
                Season season = new Season();
                season.setId(rs.getInt("id_season"));
                obj.setSeason(season);
                //Stadium
                Stadium stadium = new Stadium();
                stadium.setId(rs.getInt("id_stadium"));
                obj.setStadium(stadium);
                //Team Local
                Team teamLocal = new Team();
                teamLocal.setId(rs.getInt("id_team_local"));
                obj.setTeamLocal(teamLocal);
                //Team Visitor
                Team teamVisitor = new Team();
                teamVisitor.setId(rs.getInt("id_team_visitor"));
                obj.setTeamVisitor(teamVisitor);
                //add
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
