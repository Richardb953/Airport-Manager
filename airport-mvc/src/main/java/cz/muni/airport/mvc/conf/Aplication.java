package cz.muni.airport.mvc.conf;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

/**
 * Created by Richard Bariny on 16.12.2016.
 *
 * @author Richard Bariny, github name:Richardb953
 */
public class Aplication {
    public static void main(String[] args) throws Exception {
        SpringApplication.run(Aplication.class, args);
    }
    @Bean
    public ServletRegistrationBean foo() {
        DispatcherServlet dispatcherServlet = new DispatcherServlet();
        AnnotationConfigWebApplicationContext applicationContext = new AnnotationConfigWebApplicationContext();
        applicationContext.register(MvcConfig.class);

        dispatcherServlet.setApplicationContext(applicationContext);
        return new ServletRegistrationBean(dispatcherServlet, "/*");
    }
}
