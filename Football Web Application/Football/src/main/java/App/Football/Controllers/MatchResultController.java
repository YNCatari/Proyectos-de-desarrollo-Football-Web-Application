package App.Football.Controllers;

import App.Football.Factories.MatchDAO;
import App.Football.Factories.MatchResultDAO;
import App.Football.Models.Match;
import App.Football.Models.MatchResult;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin/match-result")
public class MatchResultController {

    @PostMapping("/store")
    public String Store(@ModelAttribute MatchResult matchResult)
    {
        if(matchResult.getTypeResult().getId() == 1)
        {
            MatchDAO matchDAO = new MatchDAO();
            Match match = matchDAO.Find(matchResult.getMatch().getId());
            int match_id = match.getId();
            int goalLocal = (match.getTeamLocal().getId() == matchResult.getTeam().getId()) ? (match.getGoalLocal() + 1) : match.getGoalLocal();
            int goalVisitor = (match.getTeamVisitor().getId() == matchResult.getTeam().getId()) ? (match.getGoalVisitor() + 1) : match.getGoalVisitor();
            matchDAO.GoalUpdate(match_id, goalLocal, goalVisitor);
        }
        MatchResultDAO matchResultDAO = new MatchResultDAO();
        if(matchResultDAO.Save(matchResult))
        {
            return "redirect:/admin/match/" + matchResult.getMatch().getId();
        }
        return "redirect:/admin/match/" + matchResult.getMatch().getId();
    }

}
