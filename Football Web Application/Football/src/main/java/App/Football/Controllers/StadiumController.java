package App.Football.Controllers;

import App.Football.Factories.StadiumDAO;
import App.Football.Models.Stadium;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin/stadium")
public class StadiumController {

    @GetMapping("")
    public String List(Model model)
    {
        StadiumDAO dao = new StadiumDAO();
        model.addAttribute("Stadiums", dao.All());
        return "admin/pages/stadium/list";
    }

    @GetMapping("/add")
    public String Add()
    {
        return "admin/pages/stadium/add";
    }

    @GetMapping("/{id}/edit")
    public String Edit(@PathVariable int id, Model model)
    {
        StadiumDAO dao = new StadiumDAO();
        model.addAttribute("Stadium", dao.Find(id));
        return "admin/pages/stadium/edit";
    }

    @PostMapping("/store")
    public String Store(@ModelAttribute Stadium stadium)
    {
        StadiumDAO dao = new StadiumDAO();
        if(dao.Save(stadium))
        {
            return "redirect:/admin/stadium";
        }
        return "redirect:/admin/stadium";
    }

    @PostMapping("/update")
    public String Update(@ModelAttribute Stadium stadium)
    {
        StadiumDAO dao = new StadiumDAO();
        if(dao.Update(stadium))
        {
            return "redirect:/admin/stadium";
        }
        return "redirect:/admin/stadium";
    }
}
