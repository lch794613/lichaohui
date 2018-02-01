package lch.dy.controller.configure;

import lch.dy.controller.serivce.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
@Configuration
@EnableWebSecurity
public class security extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserService userService;

        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http
                    .authorizeRequests()
                    .antMatchers("/","/register","/email/ajax").permitAll()
                    .antMatchers("/css/**", "/js/**","/images/**", "/json/**","/Template/**").permitAll()
                    .anyRequest().authenticated()
                    .and()
                    .formLogin()
                    .loginPage("/login")
                    .permitAll()
                    .and()
                    .logout()
                    .permitAll();
        }

        @Autowired
        public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
            auth//userDetailsService(userService);
                    .inMemoryAuthentication()
                    .withUser("user").password("password").roles("USER");
        }

    }


