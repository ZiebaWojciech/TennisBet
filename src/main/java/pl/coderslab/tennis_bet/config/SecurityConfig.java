package pl.coderslab.tennis_bet.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
//                .antMatchers("/home").permitAll()
//                .antMatchers("/user/**").permitAll()
//                .anyRequest().authenticated()
                .anyRequest().permitAll();
//                .and().formLogin().defaultSuccessUrl("/home")
//                .and().logout().logoutSuccessUrl("/home").permitAll();
    }
}

