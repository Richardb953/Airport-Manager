package cz.muni.airport.mvc.conf;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import javax.validation.Validator;

import cz.muni.airport.config.ServiceConfiguration;
import cz.muni.airport.dao.AirplaneDAO;
import cz.muni.airport.dao.AirportDAO;
import cz.muni.airport.dao.FlightDAO;
import cz.muni.airport.dao.StewardDAO;
import cz.muni.airport.facadeApi.AirplaneFacade;
import cz.muni.airport.facadeApi.FlightFacade;
import cz.muni.airport.facadeApi.StewardFacade;
import cz.muni.airport.model.Steward;
import cz.muni.airport.mvc.controller.FlightController;
import cz.muni.airport.services.AirplaneService;
import cz.muni.airport.services.AirportService;
import cz.muni.airport.services.FlightService;
import cz.muni.airport.services.StewardService;

/**
 * The central Spring context and Spring MVC configuration.
 * The @Configuration annotation declares it as Spring configuration.
 * The @EnableWebMvc enables default  MVC config for using @Controller, @RequestMapping and so on,
 * see http://docs.spring.io/spring/docs/current/spring-framework-reference/html/mvc.html#mvc-config-enable
 *
 * @author Martin Kuba makub@ics.muni.cz
 */

@EnableWebMvc
@Configuration
@Import({ServiceConfiguration.class})
@EnableTransactionManagement
public class MvcConfig extends WebMvcConfigurerAdapter {

    private final static Logger log = LoggerFactory.getLogger(MvcConfig.class);

    private static final String TEXTS = "Translations";

    /**
     * Maps the main page to a specific view.
     */
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        log.debug("mapping URL / to home view");
        registry.addViewController("/").setViewName("home");
    }


    /**
     * Enables default Tomcat servlet that serves static files.
     */
    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        log.debug("enabling default servlet for static files");
        configurer.enable();
    }

    /**
     * Provides mapping from view names to JSP pages in WEB-INF/jsp directory.
     */
    @Bean
    public ViewResolver viewResolver() {
        log.debug("registering JSP in /WEB-INF/jsp/ as views");
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setPrefix("/WEB-INF/jsp/");
        viewResolver.setSuffix(".jsp");
        return viewResolver;
    }
    /**
     * Provides localized messages.
     */
    @Bean
    public MessageSource messageSource() {
        log.debug("registering ResourceBundle 'Texts' for messages");
        ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
        messageSource.setBasename(TEXTS);
        return messageSource;
    }

    /**
     * Provides JSR-303 Validator.
     */
    @Bean
    public Validator validator() {
        log.debug("registering JSR-303 validator");
        return new LocalValidatorFactoryBean();
    }


}