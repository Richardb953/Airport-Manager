package cz.muni.airport.rest.conf;

import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import java.util.Locale;

import cz.muni.airport.config.ServiceConfiguration;
import cz.muni.airport.rest.controller.AirplaneRestController;
import cz.muni.airport.rest.controller.AirportRestController;
import cz.muni.airport.rest.controller.FlightRestController;
import cz.muni.airport.rest.controller.StewardRestController;

/**
 *
 * @author Richard Bariny, github: Richardb953
 */

@Configuration
@Import(ServiceConfiguration.class)
@ComponentScan(basePackageClasses = {AirplaneRestController.class, AirportRestController.class,
        FlightRestController.class, StewardRestController.class})
public class RestConfig {

    public static void main(String[] args) {
        Locale.setDefault(Locale.ENGLISH);
        SpringApplication.run(
                RestConfig.class, args);
    }

}
