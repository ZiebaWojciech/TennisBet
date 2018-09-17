package pl.coderslab.tennis_bet.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class AppConfig implements WebMvcConfigurer {
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
//        registry.addViewController("/homepage").setViewName("panel");
//        registry.addViewController("/403").setViewName("403");
//        registry.addViewController("/login").setViewName("login");
    }
}
