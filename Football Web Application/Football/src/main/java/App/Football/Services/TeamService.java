package App.Football.Services;

import App.Football.Factories.TeamDAO;
import App.Football.Models.Team;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/team")
public class TeamService {
    @GetMapping("")
    public List<Team> List()
    {
        TeamDAO teamDAO = new TeamDAO();
        return teamDAO.All();
    }

    @GetMapping("/{id}")
    public Team View(@PathVariable int id)
    {
        TeamDAO teamDAO = new TeamDAO();
        return teamDAO.Find(id);
    }
}
