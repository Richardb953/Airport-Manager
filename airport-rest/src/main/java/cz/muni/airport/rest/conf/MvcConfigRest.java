package cz.muni.airport.rest.conf;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.Locale;

import cz.muni.airport.config.ServiceConfiguration;

//import cz.muni.airport.mvc.conf.MvcConfig;
/**
 * Application MAIN configuration file, icnludes other modules configs and set
 * MVC controllers
 *
 * @author Richard Bariny, github: Richardb953
 */
@EnableWebMvc
@SpringBootApplication(scanBasePackages = {"cz.muni.airport"})
@Import(ServiceConfiguration.class)
public class MvcConfigRest extends WebMvcConfigurerAdapter {

//    private final static Logger log = LoggerFactory.getLogger(MvcConfig.class);
//    private static final String TEXTS = "properties";
    public static void main(String[] args) {
        Locale.setDefault(Locale.ENGLISH);
        SpringApplication.run(
                MvcConfigRest.class, args);
    }

//    /**
//     * Maps the main page to a specific view.
//     */
//    @Override
//    public void addViewControllers(ViewControllerRegistry registry) {
//        log.debug("mapping URL / to home view");
//        registry.addViewController("/").setViewName("home");
//    }
//
//    @Override
//    public void addResourceHandlers(ResourceHandlerRegistry registry) {
//        registry.addResourceHandler("/assets/**").addResourceLocations("/WEB-INF/assets/");
//        registry.addResourceHandler("/static/**").addResourceLocations("/WEB-INF/templates/static/");
//
//    }
//
//    @Override
//    public void addFormatters(final FormatterRegistry registry) {
//        super.addFormatters(registry);
//       // registry.addFormatter(dateFormatter());
////        registry.addFormatter(dateFormatterr());
//    }
//    /**
//     * Enables default Tomcat servlet that serves static files.
//     */
//    @Override
//    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
//        log.debug("enabling default servlet for static files");
//        configurer.enable();
//    }
//
//    @Bean
//    public LayoutDialect getLayoutDialect() {
//        LayoutDialect dialect = new LayoutDialect();
//        return dialect;
//    }
//
//    @Bean
//    public DataTablesDialect getDataTablesDialect() {
//        DataTablesDialect dialect = new DataTablesDialect();
//        return dialect;
//    }
//
//    @Bean
//    public DandelionDialect getDandelionDialect() {
//        DandelionDialect dialect = new DandelionDialect();
//        return dialect;
//    }
//
//    @Bean
//    public SpringSecurityDialect getSpringSecurityDialect() {
//        SpringSecurityDialect dialect = new SpringSecurityDialect();
//        return dialect;
//    }
//    @Bean
//    public DateFormatter dateFormatter() {
//        return new DateFormatter();
//    }
//
//    @Bean
//    public cz.muni.airport.mvc.conf.DateFormatter dateFormatterr() {
//        return new cz.muni.airport.mvc.conf.DateFormatter();
//    }
//    @Bean
//    public SpringTemplateEngine getSpringTemplateEngine() {
//        SpringTemplateEngine templateEngine = new SpringTemplateEngine();
//        templateEngine.setTemplateResolver(getServletContextTtemplateResolver());
//        templateEngine.addDialect(getLayoutDialect());
//        templateEngine.addDialect(getDandelionDialect());
//        templateEngine.addDialect(getDataTablesDialect());
//        templateEngine.addDialect(getSpringSecurityDialect());
//        templateEngine.addDialect(new Java8TimeDialect());
//
//        return templateEngine;
//    }
//    @Bean
//    public ServletContextTemplateResolver getServletContextTtemplateResolver() {
//        ServletContextTemplateResolver templateResolver = new ServletContextTemplateResolver();
//        templateResolver.setPrefix("/WEB-INF/templates/");
//        templateResolver.setSuffix(".html");
//        templateResolver.setCharacterEncoding("UTF-8");
//        templateResolver.setCacheable(false);
//        return templateResolver;
//    }
//    /**
//     * Setup prefix and sufix (destination) of views JSP files.
//     */
//    @Bean
//    public ThymeleafViewResolver getInternalResourceViewResolver() {
//        ThymeleafViewResolver resolver = new ThymeleafViewResolver();
//        resolver.setTemplateEngine(getSpringTemplateEngine());
//        resolver.setCharacterEncoding("UTF-8");
//        return resolver;
//    }
//
//    /**
//     * Provides localized messages.
//     */
//    @Bean
//    public ResourceBundleMessageSource messageSource() {
//        log.debug("registering ResourceBundle 'Texts' for messages");
//
//        ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
//        messageSource.setBasenames("WEB-INF/i18n/properties");
//        messageSource.setDefaultEncoding("UTF-8");
//
//        return messageSource;
//    }
//    /**
//     * New interceptor to read parameter from URL (lang) and change localization of page.
//     *
//     * @return
//     */
//    @Bean
//    public LocaleChangeInterceptor localeChangeInterceptor() {
//        LocaleChangeInterceptor localeChangeInterceptor = new LocaleChangeInterceptor();
//        localeChangeInterceptor.setParamName("lang");
//        return localeChangeInterceptor;
//    }
//    /**
//     * Register locale change interceptor.
//     */
//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor(localeChangeInterceptor());
//    }
    /**
     * Replace default spring AcceptHeaderLocaleResolver with
     * SessionLocaleResolver. Explanation: default locale resolver dont allow
     * change locale.
     * {@link //www.mkyong.com/spring-mvc/cannot-change-http-accept-header-use-a-different-locale-resolution-strategy/}
     *
     * Bean name has to be "localResolver" !
     *
     * @return solver
     */
//    @Bean
//    public SessionLocaleResolver sessionLocaleResolver() {
//        SessionLocaleResolver localeResolver = new SessionLocaleResolver();
//        localeResolver.setDefaultLocale(Locale.ENGLISH);
//        return localeResolver;
//    }
//
//    @Bean
//    public CommonsMultipartResolver commonsMultipartResolver() {
//        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
//        multipartResolver.setDefaultEncoding("UTF-8");
//        //5MB
//        multipartResolver.setMaxUploadSize(5242880L);
//        return multipartResolver;
//    }
//    /**
//     * Provides JSR-303 Validator.
//     */
//    @Bean
//    public Validator validator() {
//        log.debug("registering JSR-303 validator");
//        return new LocalValidatorFactoryBean();
//    }
//
//    @Bean
//    public LocaleResolver localeResolver(){
//        SessionLocaleResolver  resolver = new SessionLocaleResolver ();
//        resolver.setDefaultLocale(new Locale("en"));
//        return resolver;
//    }
}
