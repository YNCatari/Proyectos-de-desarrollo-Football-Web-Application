package App.Football.Controllers;

import App.Football.Factories.*;
import App.Football.Models.Match;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletRequest;

@Controller
@RequestMapping("/admin/match")
public class MatchController {

    @GetMapping("/{id}")
    public String View(@PathVariable int id, Model model)
    {
        MatchDAO matchDAO = new MatchDAO();
        Match match = matchDAO.Find(id);
        StadiumDAO stadiumDAO = new StadiumDAO();
        //MatchAlignment
        MatchAlignmentDAO matchAlignmentDAO = new MatchAlignmentDAO();
        //Type Result
        TypeResultDAO typeResultDAO = new TypeResultDAO();
        //MatchResult
        MatchResultDAO matchResultDAO = new MatchResultDAO();

        //Reports
        int matchesWinLocal = 0;
        int matchesWinVisitor = 0;
        int matchesAgainst = 0;
        for(Match matches : matchDAO.ToListMatchTeams(match.getTeamLocal().getId(), match.getTeamVisitor().getId()))
        {
            if(matches.getGoalLocal() > matches.getGoalVisitor())
            {
                matchesWinLocal += 1;
            }
            else if(matches.getGoalLocal() == matches.getGoalVisitor())
            {
                matchesAgainst += 1;
            }
            else if(matches.getGoalLocal() < matches.getGoalVisitor())
            {
                matchesWinVisitor += 1;
            }
        }

        //model
        model.addAttribute("Match", match);
        model.addAttribute("Stadiums", stadiumDAO.All());
        model.addAttribute("teamLocalAlignment", matchAlignmentDAO.Players(id, match.getTeamLocal().getId()));
        model.addAttribute("teamVisitorAlignment", matchAlignmentDAO.Players(id, match.getTeamVisitor().getId()));
        model.addAttribute("TypeResults", typeResultDAO.All());
        model.addAttribute("MatchResults", matchResultDAO.ToListMatch(id));

        //Reports
        model.addAttribute("MatchesWinLocal", matchesWinLocal);
        model.addAttribute("MatchesWinVisitor", matchesWinVisitor);
        model.addAttribute("MatchesAgainst", matchesAgainst);

        return "admin/pages/match/view";
    }

    @PostMapping("/update")
    public String Update(@ModelAttribute Match match)
    {
        MatchDAO dao = new MatchDAO();
        if(dao.Update(match))
        {
            return "redirect:/admin/match/" + match.getId();
        }
        return "redirect:/admin/match/" + match.getId();
    }

    @PostMapping("/generate-fixture")
    public String GenerateFixture(@RequestParam int id_season, @RequestParam int id_stadium)
    {
        MatchDAO dao = new MatchDAO();
        if(dao.GenerateMatches(id_season, id_stadium))
        {
            return "redirect:/admin/season/" + id_season;
        }
        return "redirect:/admin/season/" + id_season;
    }

    @GetMapping("/{id}/state-update")
    public String StateUpdate(@PathVariable int id)
    {
        MatchDAO matchDAO = new MatchDAO();
        if(matchDAO.StateUpdate(id, 1))
        {
            Match match = matchDAO.Find(id);
            int season_id = match.getSeason().getId();
            int teamLocal_id = match.getTeamLocal().getId();
            int teamVisitor_id = match.getTeamVisitor().getId();
            int goalLocal = match.getGoalLocal();
            int goalVisitor = match.getGoalVisitor();
            ClassificationDAO classificationDAO = new ClassificationDAO();
            classificationDAO.UpdateMatch(season_id,teamLocal_id,teamVisitor_id,goalLocal,goalVisitor);
            return "redirect:/admin/match/" + id;
        }
        return "redirect:/admin/match/" + id;
    }
}
