package pl.coderslab.tennis_bet.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import pl.coderslab.tennis_bet.entity.Role;
import pl.coderslab.tennis_bet.entity.User;
import pl.coderslab.tennis_bet.entity.transitionModel.CurrentUser;
import pl.coderslab.tennis_bet.service.UserService;

import java.util.HashSet;
import java.util.Set;

public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        User user = userService.getByLogin(login);
        if (user == null) throw new UsernameNotFoundException(login);
        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
        for (Role role : user.getRoles()) {
            grantedAuthorities.add(new SimpleGrantedAuthority(role.getName().name()));
        }
        return new CurrentUser(user.getLogin(), user.getPassword(),grantedAuthorities,user);
    }
}
