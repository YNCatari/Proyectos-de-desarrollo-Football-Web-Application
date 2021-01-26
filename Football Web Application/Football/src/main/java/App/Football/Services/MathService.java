package App.Football.Services;

import App.Football.Factories.MatchDAO;
import App.Football.Factories.SeasonDAO;
import App.Football.Helpers.AppUtil;
import App.Football.Models.Match;
import App.Football.Models.Season;
import App.Football.Services.Models.MatchDetails;
import App.Football.Services.Models.TeamDetail;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST})
@RequestMapping("/api/match")
public class MathService {
    @GetMapping("")
    public List<Match> List()
    {
        MatchDAO matchDAO = new MatchDAO();
        return matchDAO.All();
    }

    @GetMapping("/{id}")
    public MatchDetails View(@PathVariable int id)
    {
        MatchDAO matchDAO = new MatchDAO();
        Match match = matchDAO.Find(id);
        SeasonDAO seasonDAO = new SeasonDAO();
        Season season = seasonDAO.Find(match.getSeason().getId());
        //TeamLocal
        TeamDetail teamDetailLocal = new TeamDetail();
        teamDetailLocal.id = match.getTeamLocal().getId();
        teamDetailLocal.name = match.getTeamLocal().getNickName();
        teamDetailLocal.fullname = match.getTeamLocal().getFullName();
        teamDetailLocal.initials = match.getTeamLocal().getInitials();
        teamDetailLocal.logotype = AppUtil.PathUpload + match.getTeamLocal().getLogotype();
        //TeamVisitor
        TeamDetail teamDetailVisitor = new TeamDetail();
        teamDetailVisitor.id = match.getTeamVisitor().getId();
        teamDetailVisitor.name = match.getTeamVisitor().getNickName();
        teamDetailVisitor.fullname = match.getTeamVisitor().getFullName();
        teamDetailVisitor.initials = match.getTeamVisitor().getInitials();
        teamDetailVisitor.logotype = AppUtil.PathUpload + match.getTeamVisitor().getLogotype();
        //Match Details
        MatchDetails matchDetails = new MatchDetails();
        matchDetails.id = match.getId();
        matchDetails.date = match.getMatchDate() != null ? match.getMatchDate().toString() : "";
        matchDetails.hour = match.getMatchHour() != null ? match.getMatchHour().toString() : "";
        matchDetails.matchRound = match.getMatchRound();
        matchDetails.teamLocal = teamDetailLocal;
        matchDetails.teamVisitor = teamDetailVisitor;
        matchDetails.goalLocal = match.getGoalLocal();
        matchDetails.goalVisitor = match.getGoalVisitor();
        matchDetails.stadium = match.getStadium().getName();
        matchDetails.season = season.getCompetition().getTitle() + " " + season.getName();
        matchDetails.state = match.getState();
        return matchDetails;
    }
}
