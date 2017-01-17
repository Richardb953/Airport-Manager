package cz.muni.airport.mvc.conf;


import com.github.dandelion.datatables.thymeleaf.dialect.DataTablesDialect;
import com.github.dandelion.thymeleaf.dialect.DandelionDialect;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.format.FormatterRegistry;
import org.springframework.format.datetime.DateFormatter;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;
import org.thymeleaf.extras.java8time.dialect.Java8TimeDialect;
import org.thymeleaf.extras.springsecurity4.dialect.SpringSecurityDialect;
import org.thymeleaf.spring4.SpringTemplateEngine;
import org.thymeleaf.spring4.view.ThymeleafViewResolver;
import org.thymeleaf.templateresolver.ServletContextTemplateResolver;

import java.util.Locale;

import javax.validation.Validator;

import cz.muni.airport.config.ServiceConfiguration;
import cz.muni.airport.rest.conf.RestConfig;
import nz.net.ultraq.thymeleaf.LayoutDialect;


/**
 * Application MAIN configuration file, icnludes other modules configs and set MVC controllers
 *
 * @author Richard Bariny, github: Richardb953
 */
@EnableWebMvc
@SpringBootApplication(scanBasePackages = {"cz.muni.airport"} )
@Import({ServiceConfiguration.class, RestConfig.class})
public class MvcConfig extends WebMvcConfigurerAdapter {

    private final static Logger log = LoggerFactory.getLogger(MvcConfig.class);

    private static final String TEXTS = "properties";

    public static void main(String[] args) {
        Locale.setDefault(Locale.ENGLISH);
        SpringApplication.run(
                MvcConfig.class, args);
    }

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
        registry.addResourceHandler("/assets/**").addResourceLocations("/WEB-INF/assets/");
        registry.addResourceHandler("/static/**").addResourceLocations("/WEB-INF/templates/static/");

    }

    @Override
    public void addFormatters(final FormatterRegistry registry) {
        super.addFormatters(registry);
       // registry.addFormatter(dateFormatter());
        registry.addFormatter(dateFormatterr());
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
    public DateFormatter dateFormatter() {
        return new DateFormatter();
    }

    @Bean
    public cz.muni.airport.mvc.conf.DateFormatter dateFormatterr() {
        return new cz.muni.airport.mvc.conf.DateFormatter();
    }
    @Bean
    public SpringTemplateEngine getSpringTemplateEngine() {
        SpringTemplateEngine templateEngine = new SpringTemplateEngine();
        templateEngine.setTemplateResolver(getServletContextTtemplateResolver());
        templateEngine.addDialect(getLayoutDialect());
        templateEngine.addDialect(getDandelionDialect());
        templateEngine.addDialect(getDataTablesDialect());
        templateEngine.addDialect(getSpringSecurityDialect());
        templateEngine.addDialect(new Java8TimeDialect());

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
    public ResourceBundleMessageSource messageSource() {
        log.debug("registering ResourceBundle 'Texts' for messages");

        ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
        messageSource.setBasenames("WEB-INF/i18n/properties");
        messageSource.setDefaultEncoding("UTF-8");

        return messageSource;
    }
    /**
     * New interceptor to read parameter from URL (lang) and change localization of page.
     *
     * @return
     */
    @Bean
    public LocaleChangeInterceptor localeChangeInterceptor() {
        LocaleChangeInterceptor localeChangeInterceptor = new LocaleChangeInterceptor();
        localeChangeInterceptor.setParamName("lang");
        return localeChangeInterceptor;
    }
    /**
     * Register locale change interceptor.
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(localeChangeInterceptor());
    }


    /**
     * Replace default spring AcceptHeaderLocaleResolver with SessionLocaleResolver.
     * Explanation: default locale resolver dont allow change locale.
     * {@link //www.mkyong.com/spring-mvc/cannot-change-http-accept-header-use-a-different-locale-resolution-strategy/}
     *
     * Bean name has to be "localResolver" !
     * @return solver
     */
    @Bean
    public SessionLocaleResolver sessionLocaleResolver() {
        SessionLocaleResolver localeResolver = new SessionLocaleResolver();
        localeResolver.setDefaultLocale(Locale.ENGLISH);
        return localeResolver;
    }

    @Bean
    public CommonsMultipartResolver commonsMultipartResolver() {
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
        multipartResolver.setDefaultEncoding("UTF-8");
        //5MB
        multipartResolver.setMaxUploadSize(5242880L);
        return multipartResolver;
    }
    /**
     * Provides JSR-303 Validator.
     */
    @Bean
    public Validator validator() {
        log.debug("registering JSR-303 validator");
        return new LocalValidatorFactoryBean();
    }

    @Bean
    public LocaleResolver localeResolver(){
        SessionLocaleResolver  resolver = new SessionLocaleResolver ();
        resolver.setDefaultLocale(new Locale("en"));
        return resolver;
    }
}