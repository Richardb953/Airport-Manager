package cz.muni.airport.config;

import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.dozer.loader.api.BeanMappingBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import cz.muni.airport.dto.AirplaneDTO;
import cz.muni.airport.dto.AirportDTO;
import cz.muni.airport.dto.FlightCreateDTO;
import cz.muni.airport.dto.FlightDTO;
import cz.muni.airport.dto.StewardDTO;
import cz.muni.airport.facadeApi.AirplaneFacade;
import cz.muni.airport.facadeApi.FlightFacade;
import cz.muni.airport.facadeApi.StewardFacade;
import cz.muni.airport.model.Airplane;
import cz.muni.airport.model.Airport;
import cz.muni.airport.model.Flight;
import cz.muni.airport.model.Steward;
import cz.muni.airport.services.AirplaneService;
import cz.muni.airport.services.AirportService;
import cz.muni.airport.services.FlightService;
import cz.muni.airport.services.StewardService;

/**
 * Created by Richard Bariny on 22.11.2016.
 *
 * @author Richard Bariny, github name:Richardb953
 */

@Configuration
@ComponentScan(basePackageClasses={FlightService.class, AirportService.class, AirplaneService.class, StewardService.class,
        FlightFacade.class, AirplaneFacade.class, AirplaneFacade.class, StewardFacade.class
})
public class ServiceConfiguration {


    @Bean
    public Mapper dozer(){
        DozerBeanMapper dozer = new DozerBeanMapper();
        dozer.addMapping(new DozerCustomConfig());
        return dozer;
    }

    public class DozerCustomConfig extends BeanMappingBuilder {
        @Override
        protected void configure() {
            mapping(Flight.class, FlightDTO.class);
            mapping(Airplane.class, AirplaneDTO.class);
            mapping(Steward.class, StewardDTO.class);
            mapping(Airport.class, AirportDTO.class);
            mapping(Flight.class, FlightCreateDTO.class);
        }
    }
}
