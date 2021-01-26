package App.Football.Controllers;

import App.Football.Factories.MatchAlignmentDAO;
import App.Football.Models.MatchAlignment;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class MatchAlignmentController {

    @PostMapping("/team/match-alignment/store")
    public String Store(@ModelAttribute MatchAlignment matchAlignment)
    {
        MatchAlignmentDAO matchAlignmentDAO = new MatchAlignmentDAO();
        if(matchAlignmentDAO.Save(matchAlignment))
        {
            return "redirect:/team/match/" + matchAlignment.getMatch().getId();
        }
        return "redirect:/team/match/" + matchAlignment.getMatch().getId();
    }

}
