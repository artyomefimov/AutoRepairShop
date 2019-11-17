package com.artyomefimov.security;

import com.artyomefimov.web.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.web.cors.CorsConfiguration;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Configuration
@EnableWebSecurity
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {
    private UserService userService;
    private RedirectStrategy redirectStrategy;

    @Autowired
    public SpringSecurityConfig(UserService userService) {
        this.userService = userService;
        this.redirectStrategy = new DefaultRedirectStrategy();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.authenticationProvider(authenticationProvider());
        auth.userDetailsService(userService).passwordEncoder(passwordEncoder());
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .cors().configurationSource(request ->
                new CorsConfiguration().applyPermitDefaultValues())
                .and()
                .formLogin()
                .loginPage("/login")
                .permitAll()
                .successHandler((request, response, authentication) -> {
                    redirectStrategy.sendRedirect(request, response, "/workshops");
                })
                .and()
                .authorizeRequests()
                .antMatchers("/workshops").permitAll()
                .antMatchers("/workshops/workshop/**").authenticated()
                .antMatchers("/**/levels").permitAll()
                .antMatchers("/**/levels/level/**").authenticated()
                .antMatchers("/**/masters/**").authenticated()
                .antMatchers("/**/masters/master/**").authenticated()
                .antMatchers("/**/customers/**").authenticated()
                .antMatchers("/**/customers/customer/**").authenticated()
                .antMatchers("/**/cars/**").authenticated()
                .antMatchers("/**/cars/car/**").authenticated()
                .and()
                .httpBasic();
    }
    //    @Bean
//    DaoAuthenticationProvider authenticationProvider() {
//        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
//        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
//        daoAuthenticationProvider.setUserDetailsService(userService);
//
//        return daoAuthenticationProvider;
//    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
