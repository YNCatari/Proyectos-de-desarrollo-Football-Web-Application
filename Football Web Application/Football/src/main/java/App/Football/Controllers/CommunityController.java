package App.Football.Controllers;

import App.Football.Factories.CommunityDAO;
import App.Football.Models.Community;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin/community")
public class CommunityController {
    @GetMapping("")
    public String List(Model model)
    {
        CommunityDAO dao = new CommunityDAO();
        model.addAttribute("Communities", dao.All());
        return "admin/pages/community/list";
    }

    @GetMapping("/add")
    public String Add()
    {
        return "admin/pages/community/add";
    }

    @GetMapping("/{id}/edit")
    public String Edit(@PathVariable int id, Model model)
    {
        CommunityDAO dao = new CommunityDAO();
        model.addAttribute("Community", dao.Find(id));
        return "admin/pages/community/edit";
    }

    @PostMapping("/store")
    public String Store(@ModelAttribute Community community)
    {
        CommunityDAO dao = new CommunityDAO();
        if(dao.Save(community))
        {
            return "redirect:/admin/community";
        }
        return "redirect:/admin/community";
    }

    @PostMapping("/update")
    public String Update(@ModelAttribute Community community)
    {
        CommunityDAO dao = new CommunityDAO();
        if(dao.Update(community))
        {
            return "redirect:/admin/community";
        }
        return "redirect:/admin/community";
    }
}
