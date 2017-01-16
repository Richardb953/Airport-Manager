package cz.muni.airport.mvc.conf;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
            .authorizeRequests().anyRequest().authenticated()
                .antMatchers("/app/login/*").permitAll()
                .antMatchers("/", "/home", "/flight/**").access("hasAnyRole('ROLE_MANAGER', 'ROLE_CASHIER')")
                .antMatchers("/airplane/**", "/airport/**", "/steward/**").access("hasAnyRole('ROLE_MANAGER')")
                .and().httpBasic().and()
            .formLogin()
                .loginPage("/app/login")
                .loginProcessingUrl("/appLogin")
                .usernameParameter("username")
                .passwordParameter("password")
                .defaultSuccessUrl("/")
                .and()
            .logout()
                .logoutUrl("/appLogout")
                .logoutSuccessUrl("/app/login")
                .and()
                .exceptionHandling().accessDeniedPage("/app/accessDenied");
        
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication().withUser("manager").password("manager").roles("MANAGER");
        auth.inMemoryAuthentication().withUser("cashier").password("cashier").roles("CASHIER");
    }
    
    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
	
}
