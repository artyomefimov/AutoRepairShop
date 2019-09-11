package config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("1").password("{noop}1").roles("user");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .httpBasic()
                .and()
                .authorizeRequests()
                .antMatchers(HttpMethod.GET, "/**").hasRole("user")
                .antMatchers(HttpMethod.POST, "/**").hasRole("user")
                .antMatchers(HttpMethod.PUT, "/**").hasRole("user")
                .antMatchers(HttpMethod.PATCH, "/**").hasRole("user")
                .antMatchers(HttpMethod.DELETE, "/**").hasRole("user")
                .and()
                .csrf().disable()
                .formLogin().disable();
    }
}
