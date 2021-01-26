package App.Football.Security;

import App.Football.Factories.UserDAO;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class UserService implements UserDetailsService {

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDAO userDAO = new UserDAO();
        UserInfo userInfo = userDAO.FindByUsername(username);
        //List<GrantedAuthority> roles = new ArrayList<>();
        //roles.add(new SimpleGrantedAuthority("ADMIN"));
        //return new User(userInfo.getUsername(),userInfo.getPassword(), roles);

        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
        grantedAuthorities.add(new SimpleGrantedAuthority(getRoleName(userInfo.getRole())));
        return new User(userInfo.getUsername(),userInfo.getPassword(), grantedAuthorities);
    }

    private String getRoleName(int role)
    {
        String nameRole = "ANONYMOUS";
        switch (role)
        {
            case 1: nameRole = "ADMIN"; break;
            case 2: nameRole = "TEAM"; break;
        }
        return nameRole;
    }
}
