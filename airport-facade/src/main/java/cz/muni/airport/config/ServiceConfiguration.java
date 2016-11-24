package cz.muni.airport.config;

import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.dozer.loader.api.BeanMappingBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import cz.muni.airport.dto.FlightDTO;
import cz.muni.airport.model.Flight;
import cz.muni.airport.services.impl.FlightServiceImpl;

/**
 * Created by Richard Bariny on 22.11.2016.
 *
 * @author Richard Bariny, github name:Richardb953
 */

@Configuration
@ComponentScan(basePackageClasses={FlightServiceImpl.class})
public class ServiceConfiguration {


    @Bean
    public Mapper dozer(){
        DozerBeanMapper dozer = new DozerBeanMapper();
        dozer.addMapping(new DozerCustomConfig());
        return dozer;
    }

    /**
     * Custom config for Dozer if needed
     * @author nguyen
     *
     */
    public class DozerCustomConfig extends BeanMappingBuilder {
        @Override
        protected void configure() {
            mapping(Flight.class, FlightDTO.class);
        }
    }
}
