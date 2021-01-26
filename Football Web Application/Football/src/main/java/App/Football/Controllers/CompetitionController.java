package App.Football.Controllers;

import App.Football.Factories.CompetitionDAO;
import App.Football.Models.Competition;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin/competition")
public class CompetitionController {

    @GetMapping("")
    public String List(Model model)
    {
        CompetitionDAO dao = new CompetitionDAO();
        model.addAttribute("Competitions", dao.All());
        return "admin/pages/competition/list";
    }

    @GetMapping("/add")
    public String Add()
    {
        return "admin/pages/competition/add";
    }

    @GetMapping("/{id}/edit")
    public String Edit(@PathVariable int id, Model model)
    {
        CompetitionDAO dao = new CompetitionDAO();
        model.addAttribute("Competition", dao.Find(id));
        return "admin/pages/competition/edit";
    }

    @PostMapping("/store")
    public String Store(@ModelAttribute Competition competition)
    {
        CompetitionDAO dao = new CompetitionDAO();
        if(dao.Save(competition))
        {
            return "redirect:/admin/competition";
        }
        return "redirect:/admin/competition";
    }

    @PostMapping("/update")
    public String Update(@ModelAttribute Competition competition)
    {
        CompetitionDAO dao = new CompetitionDAO();
        if(dao.Update(competition))
        {
            return "redirect:/admin/competition";
        }
        return "redirect:/admin/competition";
    }
}
