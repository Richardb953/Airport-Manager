//package cz.muni.airport.mvc.conf;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.Import;
//import org.springframework.security.authentication.AuthenticationProvider;
//import org.springframework.security.authentication.ProviderManager;
//import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//
//import java.util.ArrayList;
//import java.util.List;
//
//@Configuration
//@EnableWebSecurity
//@EnableGlobalMethodSecurity(prePostEnabled = true)
//@Import(MvcConfig.class)
//public class SecurityConfig extends WebSecurityConfigurerAdapter {
//
//	/**
//	 * minimal strength is 4.
//	 * http://en.wikipedia.org/wiki/Bcrypt
//	 */
//	public static final int BCRYPT_STRENGTH = 8;
//
//
//	@Bean
//	public PasswordEncoder getPasswordEncoder() {
//		return new BCryptPasswordEncoder(BCRYPT_STRENGTH);
//	}
//
//	@Bean
//	public DaoAuthenticationProvider daoAuthenticationProvider() {
//		DaoAuthenticationProvider dao = new DaoAuthenticationProvider();
//		dao.setPasswordEncoder(getPasswordEncoder());
//		return dao;
//	}
//
//	@Bean
//	public ProviderManager providerManager() {
//		List<AuthenticationProvider> list = new ArrayList<AuthenticationProvider>();
//		list.add(daoAuthenticationProvider());
//        return new ProviderManager(list);
//	}
//
//
//	@Override
//	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//		super.configure(auth);
//		auth.authenticationProvider(daoAuthenticationProvider());
//	}
//
//	/**
//	 * Replace spring-security XML configuration
//	 */
//	@Override
//	protected void configure(HttpSecurity http) throws Exception {
//
//		/**
//		 * dolpnit
//		 */
//
//
//	}
//}
