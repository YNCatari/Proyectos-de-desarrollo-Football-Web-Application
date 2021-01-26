package App.Football.Factories;

import App.Football.Models.Classification;
import App.Football.Models.Season;
import App.Football.Models.SeasonTeam;
import App.Football.Models.Team;
import App.Football.Providers.MySQLConnection;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ClassificationDAO implements IBusiness {
    @Override
    public boolean Save(Object obj) {
        boolean response = false;
        Classification classification = (Classification) obj;
        try
        {
            CallableStatement cs = MySQLConnection.getInstance().connection.prepareCall("{ CALL SP_Classification_Add(?,?,?,?,?,?,?,?,?,?) }");
            cs.setInt(1, classification.getSeason().getId());
            cs.setInt(2, classification.getTeam().getId());
            cs.setInt(3, classification.getMatchesPlayed());
            cs.setInt(4, classification.getMatchesWin());
            cs.setInt(5, classification.getMatchesTied());
            cs.setInt(6,classification.getMatchesLost());
            cs.setInt(7, classification.getGoalsFavor());
            cs.setInt(8, classification.getGoalsAgainst());
            cs.setInt(9, classification.getGoalsDifference());
            cs.setInt(10, classification.getPoints());
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
        return false;
    }

    @Override
    public boolean Delete(int id) {
        return false;
    }

    @Override
    public List<Classification> All() {
        return null;
    }

    @Override
    public Classification Find(int id) {
        return null;
    }

    public boolean CreateClassification(int id_season, List<SeasonTeam> seasonTeams)
    {
        boolean response = false;
        for(SeasonTeam seasonTeam : seasonTeams)
        {
            Classification obj = new Classification();
            Season season = new Season();
            season.setId(id_season);
            Team team = new Team();
            team.setId(seasonTeam.getTeam().getId());
            obj.setSeason(season);
            obj.setTeam(team);
            obj.setMatchesPlayed(0);
            obj.setMatchesWin(0);
            obj.setMatchesTied(0);
            obj.setMatchesLost(0);
            obj.setGoalsFavor(0);
            obj.setGoalsAgainst(0);
            obj.setGoalsDifference(0);
            obj.setPoints(0);

            if(this.Save(obj))
            {
                response = true;
            }
        }
        return response;
    }

    public List<Classification> ToListSeason(int id_season) {
        ArrayList<Classification> List = new ArrayList<>();
        try
        {
            CallableStatement cs = MySQLConnection.getInstance().connection.prepareCall("{ CALL SP_Classification_ToListSeason(?) }");
            cs.setInt(1, id_season);
            ResultSet rs = cs.executeQuery();
            while (rs.next())
            {
                Classification obj = new Classification();
                obj.setId(rs.getInt("id"));
                obj.setMatchesPlayed(rs.getInt("matches_played"));
                obj.setMatchesWin(rs.getInt("matches_win"));
                obj.setMatchesTied(rs.getInt("matches_tied"));
                obj.setMatchesLost(rs.getInt("matches_lost"));
                obj.setGoalsFavor(rs.getInt("goals_favor"));
                obj.setGoalsAgainst(rs.getInt("goals_against"));
                obj.setGoalsDifference(rs.getInt("goals_difference"));
                obj.setPoints(rs.getInt("points"));
                //Season
                Season season = new Season();
                season.setId(rs.getInt("id_season"));
                obj.setSeason(season);
                //Team
                Team team = new Team();
                team.setId(rs.getInt("id_team"));
                team.setFullName(rs.getString("fullname"));
                team.setNickName(rs.getString("nickname"));
                team.setInitials(rs.getString("initials"));
                team.setLogotype(rs.getString("logotype"));
                obj.setTeam(team);
                List.add(obj);
            }
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
        return List;
    }

    //Select Team
    private Classification SelectTeam(int idSeason, int idTeam) {
        Classification obj = new Classification();
        try
        {
            CallableStatement cs = MySQLConnection.getInstance().connection.prepareCall("{ CALL SP_Classification_SelectTeam(?,?) }");
            cs.setInt(1, idSeason);
            cs.setInt(2, idTeam);
            ResultSet rs = cs.executeQuery();
            if(rs.next())
            {
                obj.setId(rs.getInt("id"));
                obj.setMatchesPlayed(rs.getInt("matches_played"));
                obj.setMatchesWin(rs.getInt("matches_win"));
                obj.setMatchesTied(rs.getInt("matches_tied"));
                obj.setMatchesLost(rs.getInt("matches_lost"));
                obj.setGoalsFavor(rs.getInt("goals_favor"));
                obj.setGoalsAgainst(rs.getInt("goals_against"));
                obj.setGoalsDifference(rs.getInt("goals_difference"));
                obj.setPoints(rs.getInt("points"));
                //Season
                Season season = new Season();
                season.setId(rs.getInt("id_season"));
                obj.setSeason(season);
                //Team
                Team team = new Team();
                team.setId(rs.getInt("id_team"));
                obj.setTeam(team);
            }
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
        return obj;
    }

    private void ToUpdatePoints(Classification classification) {
        boolean response = false;
        try
        {
            CallableStatement cs = MySQLConnection.getInstance().connection.prepareCall("{ CALL SP_Classification_ToUpdatePoints(?,?,?,?,?,?,?,?,?) }");
            cs.setInt(1, classification.getId());
            cs.setInt(2, classification.getMatchesPlayed());
            cs.setInt(3, classification.getMatchesWin());
            cs.setInt(4, classification.getMatchesTied());
            cs.setInt(5,classification.getMatchesLost());
            cs.setInt(6, classification.getGoalsFavor());
            cs.setInt(7, classification.getGoalsAgainst());
            cs.setInt(8, classification.getGoalsDifference());
            cs.setInt(9, classification.getPoints());
            cs.executeUpdate();
            response = true;
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
        //return response;
    }

    public void UpdateMatch(int season_id, int teamLocal_id, int teamVisitor_id, int goalLocal, int goalVisitor)
    {
        ClassificationDAO classificationDAO = new ClassificationDAO();
        Classification objLocal = classificationDAO.SelectTeam(season_id, teamLocal_id);
        Classification objVisitor = classificationDAO.SelectTeam(season_id, teamVisitor_id);

        //Points
        int pointLocal = objLocal.getPoints();
        int pointVisitor = objVisitor.getPoints();
        int matchesPlayedLocal = objLocal.getMatchesPlayed();
        int matchesPlayedVisitor = objVisitor.getMatchesPlayed();
        int matchesWinLocal = objLocal.getMatchesWin();
        int matchesWinVisitor = objVisitor.getMatchesWin();
        int matchesTiedLocal = objLocal.getMatchesTied();
        int matchesTiedVisitor = objVisitor.getMatchesTied();
        int matchesLostLocal = objLocal.getMatchesLost();
        int matchesLostVisitor = objVisitor.getMatchesLost();
        int goalsFavorLocal = objLocal.getGoalsFavor();
        int goalsFavorVisitor = objVisitor.getGoalsFavor();
        int goalsAgainstLocal = objLocal.getGoalsAgainst();
        int goalsAgainstVisitor = objVisitor.getGoalsAgainst();
        int goalsDifferenceLocal = objLocal.getGoalsDifference();
        int goalsDifferenceVisitor = objVisitor.getGoalsDifference();

        if(goalLocal > goalVisitor)
        {
            pointLocal = pointLocal + 3;
            matchesWinLocal = matchesWinLocal + 1;
            matchesLostVisitor = matchesLostVisitor + 1;
        }
        else if(goalLocal == goalVisitor)
        {
            pointLocal = pointLocal + 1;
            pointVisitor = pointVisitor + 1;
            matchesTiedLocal = matchesTiedLocal + 1;
            matchesTiedVisitor = matchesTiedVisitor + 1;
        }
        else {
            pointVisitor = pointVisitor + 3;
            matchesWinVisitor = matchesWinVisitor + 1;
            matchesLostLocal = matchesLostLocal + 1;
        }

        matchesPlayedLocal = matchesPlayedLocal + 1;
        matchesPlayedVisitor = matchesPlayedVisitor + 1;
        goalsFavorLocal = goalsFavorLocal + goalLocal;
        goalsFavorVisitor = goalsFavorVisitor + goalVisitor;
        goalsAgainstLocal = goalsAgainstLocal + goalVisitor;
        goalsAgainstVisitor = goalsAgainstVisitor + goalLocal;
        //Difference
        goalsDifferenceLocal = goalsFavorLocal - goalsAgainstLocal;
        goalsDifferenceVisitor = goalsFavorVisitor - goalsAgainstVisitor;

        //Update Classification Local
        Classification classificationLocal = new Classification();
        classificationLocal.setId(objLocal.getId());
        classificationLocal.setMatchesPlayed(matchesPlayedLocal);
        classificationLocal.setMatchesWin(matchesWinLocal);
        classificationLocal.setMatchesTied(matchesTiedLocal);
        classificationLocal.setMatchesLost(matchesLostLocal);
        classificationLocal.setGoalsFavor(goalsFavorLocal);
        classificationLocal.setGoalsAgainst(goalsAgainstLocal);
        classificationLocal.setGoalsDifference(goalsDifferenceLocal);
        classificationLocal.setPoints(pointLocal);

        ClassificationDAO daoClassificationLocal = new ClassificationDAO();
        daoClassificationLocal.ToUpdatePoints(classificationLocal);


        //Update Classification Visitor
        Classification classificationVisitor = new Classification();
        classificationVisitor.setId(objVisitor.getId());
        classificationVisitor.setMatchesPlayed(matchesPlayedVisitor);
        classificationVisitor.setMatchesWin(matchesWinVisitor);
        classificationVisitor.setMatchesTied(matchesTiedVisitor);
        classificationVisitor.setMatchesLost(matchesLostVisitor);
        classificationVisitor.setGoalsFavor(goalsFavorVisitor);
        classificationVisitor.setGoalsAgainst(goalsAgainstVisitor);
        classificationVisitor.setGoalsDifference(goalsDifferenceVisitor);
        classificationVisitor.setPoints(pointVisitor);

        ClassificationDAO daoClassificationVisitor = new ClassificationDAO();
        daoClassificationVisitor.ToUpdatePoints(classificationVisitor);
    }
}
