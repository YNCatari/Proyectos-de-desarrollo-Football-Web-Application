package App.Football.Controllers;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Collection;

@Controller
public class HomeController {

    @GetMapping("")
    public String Home()
    {
        return "redirect:/login";
    }

    @GetMapping("/login")
    public String Login()
    {
        return "admin/login";
    }

    @PostMapping("/login")
    public String ValidateLogin()
    {
        return "redirect:/admin";
    }

    @GetMapping("/authority")
    public String Authority()
    {
        Collection<? extends GrantedAuthority> authorities;
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        authorities = auth.getAuthorities();
        String userName = auth.getName();
        String myRole = authorities.toArray()[0].toString();
        String admin = "ADMIN";
        if (myRole.equals(admin)) {
            return "redirect:/admin";
        }
        return "redirect:/team";
    }
}
