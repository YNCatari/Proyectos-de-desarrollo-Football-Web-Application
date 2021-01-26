package App.Football.Controllers;

import App.Football.Factories.CommunityDAO;
import App.Football.Factories.CommunityPlayerDAO;
import App.Football.Factories.PlayerDAO;
import App.Football.Factories.PositionDAO;
import App.Football.Models.Player;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin/player")
public class PlayerController {
    @GetMapping("")
    public String List(Model model)
    {
        PlayerDAO dao = new PlayerDAO();
        model.addAttribute("Players", dao.All());
        return "admin/pages/player/list";
    }

    @GetMapping("/add")
    public String Add(Model model)
    {
        PositionDAO dao = new PositionDAO();
        model.addAttribute("Positions", dao.All());
        return "admin/pages/player/add";
    }

    @GetMapping("/{id}")
    public String View(@PathVariable int id, Model model)
    {
        PlayerDAO playerDAO = new PlayerDAO();
        CommunityDAO communityDAO = new CommunityDAO();
        CommunityPlayerDAO communityPlayerDAO = new CommunityPlayerDAO();
        model.addAttribute("Player", playerDAO.Find(id));
        model.addAttribute("Communities", communityDAO.All());
        model.addAttribute("CommunitiesPlayer", communityPlayerDAO.ToListPlayer(id));
        return "admin/pages/player/view";
    }

    @GetMapping("/{id}/edit")
    public String Edit(@PathVariable int id, Model model)
    {
        PlayerDAO dao = new PlayerDAO();
        PositionDAO pdao = new PositionDAO();
        model.addAttribute("Player", dao.Find(id));
        model.addAttribute("Positions", pdao.All());
        return "admin/pages/player/edit";
    }

    @PostMapping("/store")
    public String Store(@ModelAttribute Player player)
    {
        PlayerDAO dao = new PlayerDAO();
        if(dao.Save(player))
        {
            return "redirect:/admin/player";
        }
        return "redirect:/admin/player";
    }

    @PostMapping("/update")
    public String Update(@ModelAttribute Player player)
    {
        PlayerDAO dao = new PlayerDAO();
        if(dao.Update(player))
        {
            return "redirect:/admin/player";
        }
        return "redirect:/admin/player";
    }
}
