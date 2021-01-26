package App.Football.Controllers;

import App.Football.Factories.CommunityDAO;
import App.Football.Factories.TeamDAO;
import App.Football.Factories.UserDAO;
import App.Football.Helpers.AppFileUpload;
import App.Football.Models.Team;
import App.Football.Models.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Controller
@RequestMapping("/admin/team")
public class TeamController {

    @GetMapping("")
    public String List(Model model)
    {
        TeamDAO dao = new TeamDAO();
        model.addAttribute("Teams", dao.All());
        return "admin/pages/team/list";
    }

    @GetMapping("/add")
    public String Add(Model model)
    {
        CommunityDAO dao = new CommunityDAO();
        model.addAttribute("Communities", dao.All());
        return "admin/pages/team/add";
    }

    @GetMapping("/{id}/edit")
    public String Edit(@PathVariable int id, Model model)
    {
        TeamDAO dao = new TeamDAO();
        CommunityDAO cdao = new CommunityDAO();
        model.addAttribute("Team", dao.Find(id));
        model.addAttribute("Communities", cdao.All());
        return "admin/pages/team/edit";
    }

    @PostMapping("/store")
    public String Store(@ModelAttribute Team team, @RequestParam("file") MultipartFile file, @RequestParam("name_file") String name_file) throws IOException {
        if(!name_file.equals("default"))
        {
            String fileName = AppFileUpload.Upload(file, "team", team.getFullName());
            team.setLogotype(fileName);
        }
        TeamDAO dao = new TeamDAO();
        if(dao.Save(team))
        {
            User user = new User();
            user.setUsername(team.getEmail());
            user.setPassword("football");
            user.setState(1);
            user.setRole(2);
            UserDAO userDAO = new UserDAO();
            userDAO.Save(user);
            return "redirect:/admin/team";
        }
        return "redirect:/admin/team";
    }

    @PostMapping("/update")
    public String Update(@ModelAttribute Team team, @RequestParam("file") MultipartFile file, @RequestParam("name_file") String name_file) throws IOException {
        team.setLogotype(name_file);
        if (!file.getOriginalFilename().isEmpty())
        {
            String fileName = AppFileUpload.Upload(file, "team", team.getFullName());
            team.setLogotype(fileName);
        }
        TeamDAO dao = new TeamDAO();
        if(dao.Update(team))
        {
            return "redirect:/admin/team";
        }
        return "redirect:/admin/team";
    }
}
