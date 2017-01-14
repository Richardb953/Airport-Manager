package cz.muni.airport.database;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Created by Richard Bariny on 15.12.2016.
 *
 * @author Richard Bariny, github name:Richardb953
 */

@SpringBootApplication
@EntityScan(basePackages = {"cz.muni.airport.model"})
@PropertySource("classpath:/application.properties")
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = "cz.muni.airport.dao.impl")
public class Config {

    public static void main(String[] args) {
        SpringApplication.run(Config.class, args);
    }

//    spring.jpa.database=POSTGRESQL
//    spring.jpa.show-sql=true
//    spring.jpa.hibernate.ddl-auto=create
//    spring.database.driverClassName=org.hsqldb.jdbcDriver
//    spring.datasource.url=jdbc:hsqldb:mem:butterfly;create=true
//    spring.jpa.properties.hibernate.current_session_context_class=org.springframework.orm.hibernate4.SpringSessionContext
//    spring.data.jpa.repositories.enabled=true
//    spring.jpa.generate-ddl=true
//    spring.jpa.open-in-view=true
//    spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.HSQLDialect

}
