package cz.muni.airport.mvc.conf;


import com.github.dandelion.datatables.thymeleaf.dialect.DataTablesDialect;
import com.github.dandelion.thymeleaf.dialect.DandelionDialect;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.thymeleaf.extras.springsecurity4.dialect.SpringSecurityDialect;
import org.thymeleaf.spring4.SpringTemplateEngine;
import org.thymeleaf.spring4.view.ThymeleafViewResolver;
import org.thymeleaf.templateresolver.ServletContextTemplateResolver;

import javax.validation.Validator;

import cz.muni.airport.database.Config;
import nz.net.ultraq.thymeleaf.LayoutDialect;

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
@Import(Config.class)
public class MvcConfig extends WebMvcConfigurerAdapter {

    private final static Logger log = LoggerFactory.getLogger(MvcConfig.class);

    private static final String TEXTS = "translations";

    /**
     * Maps the main page to a specific view.
     */
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        log.debug("mapping URL / to home view");
        registry.addViewController("/").setViewName("home");
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/WEB-INF/assets/**").addResourceLocations("/WEB-INF/assets/");
    }

    /**
     * Enables default Tomcat servlet that serves static files.
     */
    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        log.debug("enabling default servlet for static files");
        configurer.enable();
    }

    @Bean
    public LayoutDialect getLayoutDialect() {
        LayoutDialect dialect = new LayoutDialect();
        return dialect;
    }

    @Bean
    public DataTablesDialect getDataTablesDialect() {
        DataTablesDialect dialect = new DataTablesDialect();
        return dialect;
    }

    @Bean
    public DandelionDialect getDandelionDialect() {
        DandelionDialect dialect = new DandelionDialect();
        return dialect;
    }

    @Bean
    public SpringSecurityDialect getSpringSecurityDialect() {
        SpringSecurityDialect dialect = new SpringSecurityDialect();
        return dialect;
    }

    @Bean
    public SpringTemplateEngine getSpringTemplateEngine() {
        SpringTemplateEngine templateEngine = new SpringTemplateEngine();
        templateEngine.setTemplateResolver(getServletContextTtemplateResolver());
        templateEngine.addDialect(getLayoutDialect());
        templateEngine.addDialect(getDandelionDialect());
        templateEngine.addDialect(getDataTablesDialect());
        templateEngine.addDialect(getSpringSecurityDialect());
        return templateEngine;
    }

    @Bean
    public ServletContextTemplateResolver getServletContextTtemplateResolver() {
        ServletContextTemplateResolver templateResolver = new ServletContextTemplateResolver();
        templateResolver.setPrefix("/WEB-INF/templates/");
        templateResolver.setSuffix(".html");
        templateResolver.setCharacterEncoding("UTF-8");
        templateResolver.setCacheable(false);
        return templateResolver;
    }

    /**
     * Setup prefix and sufix (destination) of views JSP files.
     */
    @Bean
    public ThymeleafViewResolver getInternalResourceViewResolver() {
        ThymeleafViewResolver resolver = new ThymeleafViewResolver();
        resolver.setTemplateEngine(getSpringTemplateEngine());
        resolver.setCharacterEncoding("UTF-8");
        return resolver;
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