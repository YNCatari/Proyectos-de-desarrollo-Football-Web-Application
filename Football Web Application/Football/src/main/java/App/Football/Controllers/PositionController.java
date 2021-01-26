package App.Football.Controllers;

import App.Football.Factories.CompetitionDAO;
import App.Football.Factories.PositionDAO;
import App.Football.Models.Competition;
import App.Football.Models.Position;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin/position")
public class PositionController {

    @GetMapping("")
    public String List(Model model)
    {
        PositionDAO dao = new PositionDAO();
        model.addAttribute("Positions", dao.All());
        return "admin/pages/position/list";
    }

    @GetMapping("/add")
    public String Add()
    {
        return "admin/pages/position/add";
    }

    @GetMapping("/{id}")
    public String View(@PathVariable int id, Model model)
    {
        return "admin/pages/position/view";
    }

    @GetMapping("/{id}/edit")
    public String Edit(@PathVariable int id, Model model)
    {
        PositionDAO dao = new PositionDAO();
        model.addAttribute("Position", dao.Find(id));
        return "admin/pages/position/edit";
    }

    @PostMapping("/store")
    public String Store(@ModelAttribute Position position)
    {
        PositionDAO dao = new PositionDAO();
        if(dao.Save(position))
        {
            return "redirect:/admin/position";
        }
        return "redirect:/admin/position";
    }

    @PostMapping("/update")
    public String Update(@ModelAttribute Position position)
    {
        PositionDAO dao = new PositionDAO();
        if(dao.Update(position))
        {
            return "redirect:/admin/position";
        }
        return "redirect:/admin/position";
    }
}
