package cz.muni.airport.database;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Created by Richard Bariny on 11.1.2017.
 * Test Configuration file
 * @author Richard Bariny, github name:Richardb953
 */
@Configuration
@PropertySource("classpath:/application.properties")
@EnableAutoConfiguration
@EntityScan(basePackages = {"cz.muni.airport.model"})
@EnableTransactionManagement
@ComponentScan(basePackages = "cz.muni.airport.dao")
public class testConfig {
    public static void main(String[] args){
        System.out.print("Running Tests");
    }
}
