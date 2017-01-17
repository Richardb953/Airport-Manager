package cz.muni.airport.mvc.conf;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.csrf.CsrfFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled=true)
public class SecurityConfig {
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http.csrf().disable()
//            .authorizeRequests().anyRequest().authenticated()
//                .antMatchers("/app/login/*").permitAll()
//                .antMatchers("/", "/home", "/flight/**").access("hasAnyRole('ROLE_MANAGER', 'ROLE_CASHIER')")
//                .antMatchers("/airplane/**", "/airport/**", "/steward/**").hasRole("MANAGER")
//                .and().httpBasic().and()
//            .formLogin()
//                .loginPage("/app/login")
//                .loginProcessingUrl("/appLogin")
//                .usernameParameter("username")
//                .passwordParameter("password")
//                .defaultSuccessUrl("/")
//                .and()
//            .logout()
//                .logoutUrl("/appLogout")
//                .logoutSuccessUrl("/app/login")
//                .and()
//                .exceptionHandling().accessDeniedPage("/app/accessDenied");
//        
//    }
    @Configuration
    @Order(1)
    public static class ApiWebSecurityConfigurationAdapter extends WebSecurityConfigurerAdapter {
            protected void configure(HttpSecurity http) throws Exception {
                    http
//                            .antMatcher("airplane/*").antMatcher("/steward/*").antMatcher("/airport/*")
//                            .antMatcher("airplane/update/*").antMatcher("/steward/update/*").antMatcher("/airport/update/*")
//                            .antMatcher("airplane/delete/*").antMatcher("/steward/delete/*").antMatcher("/airport/delete/*")
                            .authorizeRequests()
                                    .anyRequest().hasRole("MANAGER").antMatchers("airplane/**","/steward/*","/airport/*").access("hasAnyRole('ROLE_MANAGER')")
                                    .and();
//                            .httpBasic();
                    http
                            .antMatcher("/flight/*")
                            .authorizeRequests()
                                    .anyRequest().hasAnyRole("MANAGER","CASHIER")
                                    .and();
                            //.httpBasic();
            }
    }

    @Configuration
    public static class FormLoginWebSecurityConfigurerAdapter extends WebSecurityConfigurerAdapter {

            @Override
            protected void configure(HttpSecurity http) throws Exception {
                    http.csrf().disable()
                        .authorizeRequests()
                            .antMatchers("/app/login", "/static/**", "/layouts/**").permitAll()
                            .anyRequest().authenticated()
                            .and()
                        .formLogin()
                            .loginPage("/app/login")
                            .loginProcessingUrl("/appLogin")
                            .usernameParameter("username")
                            .passwordParameter("password")
                            .defaultSuccessUrl("/")
                            .and()
                        .logout()
                            .logoutUrl("/app/logout")
                            .logoutSuccessUrl("/app/login")
                            .and()
                            .exceptionHandling().accessDeniedPage("/app/accessDenied").and();
            }
            
            @Override
            public void configure(WebSecurity web) throws Exception {
                web.ignoring().antMatchers("/favicon.ico", "/static/**");
            }
            
            @Bean
            @Override
            public AuthenticationManager authenticationManagerBean() throws Exception {
                return super.authenticationManagerBean();
            }
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication().withUser("manager").password("manager").roles("MANAGER");
        auth.inMemoryAuthentication().withUser("cashier").password("cashier").roles("CASHIER");
    }
    
//    @Bean
//    @Override
//    public AuthenticationManager authenticationManagerBean() throws Exception {
//        return super.authenticationManagerBean();
//    }
	
}
