package App.Football.Controllers;

import App.Football.Factories.*;
import App.Football.Helpers.AppUtil;
import App.Football.Models.Competition;
import App.Football.Models.Season;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.sql.Date;

@Controller
@RequestMapping("/admin/season")
public class SeasonController {
    @GetMapping("")
    public String List(Model model)
    {
        SeasonDAO dao = new SeasonDAO();
        model.addAttribute("Seasons", dao.All());
        return "admin/pages/season/list";
    }

    @GetMapping("/add")
    public String Add(Model model)
    {
        CompetitionDAO dao = new CompetitionDAO();
        model.addAttribute("Competitions", dao.All());
        return "admin/pages/season/add";
    }

    @GetMapping("/{id}")
    public String View(@PathVariable int id, Model model)
    {
        SeasonDAO dao = new SeasonDAO();
        TeamDAO teamDAO = new TeamDAO();
        SeasonTeamDAO seasonTeamDAO = new SeasonTeamDAO();
        StadiumDAO stadiumDAO = new StadiumDAO();
        MatchDAO matchDAO = new MatchDAO();
        ClassificationDAO classificationDAO = new ClassificationDAO();
        model.addAttribute("Season", dao.Find(id));
        model.addAttribute("Teams", teamDAO.All());
        model.addAttribute("SeasonTeams", seasonTeamDAO.ToListSeason(id));
        model.addAttribute("Stadiums", stadiumDAO.All());
        model.addAttribute("SeasonMatches", matchDAO.ToListSeason(id));
        model.addAttribute("SeasonClassifications", classificationDAO.ToListSeason(id));
        return "admin/pages/season/view";
    }

    @GetMapping("/{id}/edit")
    public String Edit(@PathVariable int id, Model model)
    {
        SeasonDAO dao = new SeasonDAO();
        CompetitionDAO cdao = new CompetitionDAO();
        model.addAttribute("Season", dao.Find(id));
        model.addAttribute("Competitions", cdao.All());
        return "admin/pages/season/edit";
    }

    @PostMapping("/store")
    public String Store(HttpServletRequest request)
    {
        Date startDate = new Date(AppUtil.convertStringToDate(request.getParameter("StartDate")).getTime());
        Date endDate = new Date(AppUtil.convertStringToDate(request.getParameter("EndDate")).getTime());
        //Model
        Season season = new Season();
        Competition competition = new Competition();
        competition.setId(Integer.parseInt(request.getParameter("competition")));
        season.setCompetition(competition);
        season.setName(AppUtil.YearString(startDate)+"-"+AppUtil.YearString(endDate));
        if(AppUtil.YearString(startDate).equals(AppUtil.YearString(endDate))) season.setName(AppUtil.YearString(startDate));
        season.setStartDate(startDate);
        season.setEndDate(endDate);
        season.setState(0);
        //DAO
        SeasonDAO dao = new SeasonDAO();
        if(dao.Save(season))
        {
            return "redirect:/admin/season";
        }
        return "redirect:/admin/season";
    }

    @PostMapping("/update")
    public String Update(@ModelAttribute Season season)
    {
        season.setName(AppUtil.YearString(season.getStartDate())+"-"+AppUtil.YearString(season.getEndDate()));
        if(AppUtil.YearString(season.getStartDate()).equals(AppUtil.YearString(season.getEndDate()))) season.setName(AppUtil.YearString(season.getStartDate()));
        SeasonDAO dao = new SeasonDAO();
        if(dao.Update(season))
        {
            return "redirect:/admin/season";
        }
        return "redirect:/admin/season";
    }

    @PostMapping("/{id}/delete")
    @ResponseBody
    public int Delete(@PathVariable int id)
    {
        SeasonDAO dao = new SeasonDAO();
        if(dao.Delete(id))
        {
            return 1;
        }
        return 0;
    }
}
