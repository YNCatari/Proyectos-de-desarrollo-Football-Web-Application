package App.Football.Controllers;

import App.Football.Factories.TemplateDAO;
import App.Football.Models.Template;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class TemplateController {
    @PostMapping("/team/template/store")
    public String Store(@ModelAttribute Template template)
    {
        TemplateDAO templateDAO = new TemplateDAO();
        if(templateDAO.Save(template))
        {
            return "redirect:/team/template/" + template.getSeason().getId();
        }
        return "redirect:/team/template/" + template.getSeason().getId();
    }

    @PostMapping("/team/template/update")
    public String Update(@ModelAttribute Template template)
    {
        TemplateDAO templateDAO = new TemplateDAO();
        if(templateDAO.Update(template))
        {
            return "redirect:/team/template/" + template.getSeason().getId();
        }
        return "redirect:/team/template/" + template.getSeason().getId();
    }

    @GetMapping("/team/template/{id}/edit")
    public String Edit(@PathVariable int id, Model model)
    {
        TemplateDAO templateDAO = new TemplateDAO();
        model.addAttribute("Template", templateDAO.Find(id));
        return "team/pages/template/edit";
    }

    @GetMapping("/team/template/{id}/delete/{season}")
    public String Delete(@PathVariable int id, @PathVariable int season)
    {
        TemplateDAO templateDAO = new TemplateDAO();
        if(templateDAO.Delete(id))
        {
            return "redirect:/team/template/" + season;
        }
        return "redirect:/team/template/" + season;
    }
}
