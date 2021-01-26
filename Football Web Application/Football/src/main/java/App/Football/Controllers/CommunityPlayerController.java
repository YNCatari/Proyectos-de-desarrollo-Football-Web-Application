package App.Football.Controllers;

import App.Football.Factories.CommunityPlayerDAO;
import App.Football.Models.CommunityPlayer;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin/community-player")
public class CommunityPlayerController {
    @PostMapping("/store")
    public String Store(@ModelAttribute CommunityPlayer communityPlayer)
    {
        CommunityPlayerDAO dao = new CommunityPlayerDAO();
        if(dao.Save(communityPlayer))
        {
            return "redirect:/admin/player/" + communityPlayer.getPlayer().getId();
        }
        return "redirect:/admin/player/" + communityPlayer.getPlayer().getId();
    }

    @GetMapping("/{id}/{player}/delete")
    public String Delete(@PathVariable int id, @PathVariable int player)
    {
        CommunityPlayerDAO dao = new CommunityPlayerDAO();
        if(dao.Delete(id))
        {
            return "redirect:/admin/player/" + player;
        }
        return "redirect:/admin/player/" + player;
    }
}
