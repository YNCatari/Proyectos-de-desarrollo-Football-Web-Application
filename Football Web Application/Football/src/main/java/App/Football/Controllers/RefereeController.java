package App.Football.Controllers;

import App.Football.Factories.RefereeDAO;
import App.Football.Models.Referee;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin/referee")
public class RefereeController {

    @GetMapping("")
    public String List(Model model)
    {
        RefereeDAO dao = new RefereeDAO();
        model.addAttribute("Referees", dao.All());
        return "admin/pages/referee/list";
    }

    @GetMapping("/add")
    public String Add()
    {
        return "admin/pages/referee/add";
    }

    @GetMapping("/{id}/edit")
    public String Edit(@PathVariable int id, Model model)
    {
        RefereeDAO dao = new RefereeDAO();
        model.addAttribute("Referee", dao.Find(id));
        return "admin/pages/referee/edit";
    }

    @PostMapping("/store")
    public String Store(@ModelAttribute Referee referee)
    {
        RefereeDAO dao = new RefereeDAO();
        if(dao.Save(referee))
        {
            return "redirect:/admin/referee";
        }
        return "redirect:/admin/referee";
    }

    @PostMapping("/update")
    public String Update(@ModelAttribute Referee referee)
    {
        RefereeDAO dao = new RefereeDAO();
        if(dao.Update(referee))
        {
            return "redirect:/admin/referee";
        }
        return "redirect:/admin/referee";
    }
}
