package App.Football.Services;

import App.Football.Factories.ClassificationDAO;
import App.Football.Factories.MatchDAO;
import App.Football.Factories.SeasonDAO;
import App.Football.Helpers.AppUtil;
import App.Football.Models.Match;
import App.Football.Models.Season;
import App.Football.Models.Classification;
import App.Football.Services.Models.*;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST})
@RequestMapping("/api/season")
public class SeasonService {

    @GetMapping("")
    public List<Season> List()
    {
        SeasonDAO seasonDAO = new SeasonDAO();
        return seasonDAO.All();
    }

    @GetMapping("/{id}")
    public Season View(@PathVariable int id)
    {
        SeasonDAO seasonDAO = new SeasonDAO();
        return seasonDAO.Find(id);
    }

    @GetMapping("/last")
    public List<Season> ListLast()
    {
        SeasonDAO seasonDAO = new SeasonDAO();
        return seasonDAO.ToListLast(1);
    }

    @GetMapping("/of-last")
    public Season FindLast()
    {
        SeasonDAO seasonDAO = new SeasonDAO();
        return seasonDAO.FindLast(1);
    }

    @GetMapping("/{id}/matches")
    public SeasonMatches Matches(@PathVariable int id)
    {
        SeasonDAO seasonDAO = new SeasonDAO();
        MatchDAO matchDAO = new MatchDAO();
        Season season = seasonDAO.Find(id);
        String seasonName = season.getName() + " " + season.getCompetition().getTitle();
        List<Integer> rounds = matchDAO.ToListRoundsSeason(id);
        List<Match> matches = matchDAO.ToListSeason(id);
        //ArrayList
        ArrayList<RoundModel> listRounds = new ArrayList<>();
        for(Integer round : rounds)
        {
            RoundModel roundModel = new RoundModel();
            roundModel.name = "Jornada de " + round + " de " + rounds.size();
            ArrayList<MatchModel> listMatches = new ArrayList<>();
            for(Match match : matches)
            {
                if(round == match.getMatchRound())
                {
                    TeamModel teamLocal = new TeamModel();
                    TeamModel teamVisitor = new TeamModel();
                    MatchModel matchModel = new MatchModel();
                    matchModel.id = match.getId();
                    matchModel.date = match.getMatchDate() != null ? match.getMatchDate().toString() : "";
                    matchModel.hour = match.getMatchHour() != null ? match.getMatchHour().toString() : "";
                    //TeamLocal
                    teamLocal.initials = match.getTeamLocal().getInitials();
                    teamLocal.name = match.getTeamLocal().getNickName();
                    teamLocal.fullname = match.getTeamLocal().getFullName();
                    teamLocal.logotype = AppUtil.PathUpload + match.getTeamLocal().getLogotype();
                    //TeamVisitor
                    teamVisitor.initials = match.getTeamVisitor().getInitials();
                    teamVisitor.name = match.getTeamVisitor().getNickName();
                    teamVisitor.fullname = match.getTeamVisitor().getFullName();
                    teamVisitor.logotype = AppUtil.PathUpload + match.getTeamVisitor().getLogotype();
                    matchModel.teamLocal = teamLocal;
                    matchModel.teamVisitor = teamVisitor;
                    matchModel.goalLocal = match.getGoalLocal();
                    matchModel.goalVisitor = match.getGoalVisitor();
                    matchModel.stadium = match.getStadium().getName();
                    matchModel.state = match.getState();
                    listMatches.add(matchModel);
                }
            }
            roundModel.matches = listMatches;
            listRounds.add(roundModel);
        }
        SeasonMatches obj = new SeasonMatches();
        obj.name = seasonName;
        obj.rounds = listRounds;
        return obj;
    }

    @GetMapping("/{id}/classification")
    public SeasonClassification Classification(@PathVariable int id)
    {
        SeasonDAO seasonDAO = new SeasonDAO();
        ClassificationDAO classificationDAO = new ClassificationDAO();
        Season season = seasonDAO.Find(id);
        List<Classification> classifications = classificationDAO.ToListSeason(id);
        String seasonName = season.getName() + " " + season.getCompetition().getTitle();
        ArrayList<ClassificationModel> listPositions = new ArrayList<>();
        for(Classification classification : classifications)
        {
            ClassificationModel cm = new ClassificationModel();
            TeamModel team = new TeamModel();
            team.initials = classification.getTeam().getInitials();
            team.name = classification.getTeam().getNickName();
            team.fullname = classification.getTeam().getFullName();
            team.logotype = AppUtil.PathUpload + classification.getTeam().getLogotype();
            cm.team = team;
            cm.points = classification.getPoints();
            cm.matchesPlayed = classification.getMatchesPlayed();
            cm.matchesWin = classification.getMatchesWin();
            cm.matchesTied = classification.getMatchesTied();
            cm.matchesLost = classification.getMatchesLost();
            cm.goalsFavor = classification.getGoalsFavor();
            cm.goalsAgainst = classification.getGoalsAgainst();
            cm.goalsDifference = classification.getGoalsDifference();
            listPositions.add(cm);
        }
        SeasonClassification obj = new SeasonClassification();
        obj.name = seasonName;
        obj.positions = listPositions;
        return obj;
    }
}
