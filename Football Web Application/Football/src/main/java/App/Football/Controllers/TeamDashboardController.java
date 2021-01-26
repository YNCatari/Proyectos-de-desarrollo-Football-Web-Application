package App.Football.Controllers;

import App.Football.Factories.*;
import App.Football.Models.Match;
import App.Football.Models.Team;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/team")
public class TeamDashboardController {

    @GetMapping("/seasons")
    public String Seasons(Model model)
    {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        int teamID = TeamDAO.getId(auth.getName());
        SeasonTeamDAO seasonTeamDAO = new SeasonTeamDAO();
        model.addAttribute("Seasons", seasonTeamDAO.ToListTeam(teamID));
        return "team/pages/season/list";
    }

    @GetMapping("/season/{id}")
    public String Seasons(@PathVariable int id, Model model)
    {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        int teamID = TeamDAO.getId(auth.getName());
        //DAO
        SeasonDAO seasonDAO = new SeasonDAO();
        MatchDAO matchDAO = new MatchDAO();
        ClassificationDAO classificationDAO = new ClassificationDAO();
        TeamDAO teamDAO = new TeamDAO();
        TemplateDAO templateDAO = new TemplateDAO();
        model.addAttribute("Season", seasonDAO.Find(id));
        model.addAttribute("SeasonMatches", matchDAO.ToListSeasonTeam(id, teamID));
        model.addAttribute("SeasonClassifications", classificationDAO.ToListSeason(id));
        model.addAttribute("Team", teamDAO.Find(teamID));
        model.addAttribute("Templates", templateDAO.ToListSeasonTeam(id, teamID));
        return "team/pages/season/view";
    }

    @GetMapping("/competitions")
    public String Competitions(Model model)
    {
        SeasonDAO seasonDAO = new SeasonDAO();
        model.addAttribute("Seasons", seasonDAO.ToListSeasonState(0));
        return "team/pages/competition/list";
    }

    //Template
    @GetMapping("/template/{season}")
    public String Template(@PathVariable int season, Model model)
    {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        int teamID = TeamDAO.getId(auth.getName());
        //DAO
        SeasonDAO seasonDAO = new SeasonDAO();
        TeamDAO teamDAO = new TeamDAO();
        PlayerDAO playerDAO = new PlayerDAO();
        TemplateDAO templateDAO = new TemplateDAO();
        Team team = teamDAO.Find(teamID);
        model.addAttribute("Season", seasonDAO.Find(season));
        model.addAttribute("Team", team);
        model.addAttribute("Players", playerDAO.ToListCommunity(team.getCommunity().getId()));
        model.addAttribute("TemplatePlayers", templateDAO.ToListSeasonTeam(season, teamID));
        return "team/pages/template/list";
    }

    //Match
    @GetMapping("/match/{id}")
    public String Match(@PathVariable int id, Model model)
    {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        int teamID = TeamDAO.getId(auth.getName());
        //DAO
        MatchDAO matchDAO = new MatchDAO();
        PositionDAO positionDAO = new PositionDAO();
        TemplateDAO templateDAO = new TemplateDAO();
        TeamDAO teamDAO = new TeamDAO();
        Match match = matchDAO.Find(id);
        Team team = teamDAO.Find(teamID);
        //MatchAlignment
        MatchAlignmentDAO matchAlignmentDAO = new MatchAlignmentDAO();

        //model
        model.addAttribute("Match", match);
        model.addAttribute("TemplatePlayers", templateDAO.ToListSeasonTeam(match.getSeason().getId(), teamID));
        model.addAttribute("Positions", positionDAO.All());
        model.addAttribute("Team", team);
        model.addAttribute("teamLocalAlignment", matchAlignmentDAO.Players(id, match.getTeamLocal().getId()));
        model.addAttribute("teamVisitorAlignment", matchAlignmentDAO.Players(id, match.getTeamVisitor().getId()));
        return "team/pages/match/view";
    }
}
