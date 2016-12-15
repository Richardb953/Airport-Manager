package cz.muni.airport.database;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.orm.jpa.vendor.HibernateJpaSessionFactoryBean;

/**
 * Created by Richard Bariny on 15.12.2016.
 *
 * @author Richard Bariny, github name:Richardb953
 */
@SpringBootApplication(scanBasePackages = {"cz.muni.airport"} )
@EntityScan(basePackages = {"cz.muni.airport.model"})
public class Config {
    @Bean
    public HibernateJpaSessionFactoryBean sessionFactory() {
        return new HibernateJpaSessionFactoryBean();
    }
}
