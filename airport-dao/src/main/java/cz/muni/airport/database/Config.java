package cz.muni.airport.database;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Created by Richard Bariny on 15.12.2016.
 *
 * @author Richard Bariny, github name:Richardb953
 */

@EntityScan(basePackages = {"cz.muni.airport.model"})
@PropertySource("classpath:/application.properties")
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = "cz.muni.airport.dao.impl")
public class Config {

    final static Logger log = LoggerFactory.getLogger(Config.class);

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
