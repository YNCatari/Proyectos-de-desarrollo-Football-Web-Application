package App.Football.Controllers;

import App.Football.Factories.TypeResultDAO;
import App.Football.Models.TypeResult;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin/type-result")
public class TypeResultController {

    @GetMapping("")
    public String List(Model model)
    {
        TypeResultDAO dao = new TypeResultDAO();
        model.addAttribute("TypeResults", dao.All());
        return "admin/pages/type-result/list";
    }

    @GetMapping("/add")
    public String Add()
    {
        return "admin/pages/type-result/add";
    }

    @GetMapping("/{id}/edit")
    public String Edit(@PathVariable int id, Model model)
    {
        TypeResultDAO dao = new TypeResultDAO();
        model.addAttribute("TypeResult", dao.Find(id));
        return "admin/pages/type-result/edit";
    }

    @PostMapping("/store")
    public String Store(@ModelAttribute TypeResult typeResult)
    {
        TypeResultDAO dao = new TypeResultDAO();
        if(dao.Save(typeResult))
        {
            return "redirect:/admin/type-result";
        }
        return "redirect:/admin/type-result";
    }

    @PostMapping("/update")
    public String Update(@ModelAttribute TypeResult typeResult)
    {
        TypeResultDAO dao = new TypeResultDAO();
        if(dao.Update(typeResult))
        {
            return "redirect:/admin/type-result";
        }
        return "redirect:/admin/type-result";
    }
}
