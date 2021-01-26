package App.Football.Controllers;

import App.Football.Factories.SeasonTeamDAO;
import App.Football.Models.Season;
import App.Football.Models.SeasonTeam;
import App.Football.Models.Team;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/admin/season-team")
public class SeasonTeamController {
    @PostMapping("/store")
    public String Store(HttpServletRequest request)
    {
        Season season = new Season();
        season.setId(Integer.parseInt(request.getParameter("id_season")));
        Team team = new Team();
        team.setId(Integer.parseInt(request.getParameter("id_team")));
        SeasonTeam seasonTeam = new SeasonTeam();
        seasonTeam.setSeason(season);
        seasonTeam.setTeam(team);
        SeasonTeamDAO dao = new SeasonTeamDAO();
        if(dao.Save(seasonTeam))
        {
            return "redirect:/admin/season/" + request.getParameter("id_season");
        }
        return "redirect:/admin/season/" + request.getParameter("id_season");
    }
}
