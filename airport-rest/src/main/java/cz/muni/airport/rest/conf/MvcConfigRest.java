package cz.muni.airport.rest.conf;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.Locale;

import cz.muni.airport.config.ServiceConfiguration;

/**
 *
 * @author Richard Bariny, github: Richardb953
 */
@EnableWebMvc
@SpringBootApplication(scanBasePackages = {"cz.muni.airport"})
@Import(ServiceConfiguration.class)
public class MvcConfigRest extends WebMvcConfigurerAdapter {

    public static void main(String[] args) {
        Locale.setDefault(Locale.ENGLISH);
        SpringApplication.run(
                MvcConfigRest.class, args);
    }

}
