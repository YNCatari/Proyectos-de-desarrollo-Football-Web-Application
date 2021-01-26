package App.Football.Controllers;

import App.Football.Factories.UserDAO;
import App.Football.Models.Position;
import App.Football.Models.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin/user")
public class UserController {
    @GetMapping("")
    public String List(Model model)
    {
        UserDAO dao = new UserDAO();
        model.addAttribute("Users", dao.All());
        return "admin/pages/user/list";
    }

    @GetMapping("/add")
    public String Add()
    {
        return "admin/pages/user/add";
    }

    @GetMapping("/{id}/edit")
    public String Edit(@PathVariable int id, Model model)
    {
        UserDAO dao = new UserDAO();
        model.addAttribute("User", dao.Find(id));
        return "admin/pages/user/edit";
    }

    @PostMapping("/update")
    public String Update(@ModelAttribute User user)
    {
        UserDAO dao = new UserDAO();
        if(dao.Update(user))
        {
            return "redirect:/admin/user";
        }
        return "redirect:/admin/user";
    }
}
