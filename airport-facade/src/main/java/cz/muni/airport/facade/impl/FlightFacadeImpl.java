package cz.muni.airport.facade.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import cz.muni.airport.dto.FlightCreateDTO;
import cz.muni.airport.dto.FlightDTO;
import cz.muni.airport.dto.StewardDTO;
import cz.muni.airport.facadeApi.FlightFacade;
import cz.muni.airport.model.Airport;
import cz.muni.airport.model.Flight;
import cz.muni.airport.model.Steward;
import cz.muni.airport.model.enums.FlightState;
import cz.muni.airport.services.AirplaneService;
import cz.muni.airport.services.AirportService;
import cz.muni.airport.services.BeanMappingService;
import cz.muni.airport.services.FlightService;
import cz.muni.airport.services.StewardService;


/**
 * Created by Richard Bariny on 9.11.2016.
 * Implementation of Facade interface
 *
 * @author Richard Bariny, github name: Richardb953
 */

@Service
public class FlightFacadeImpl implements FlightFacade {

    @Autowired
    private FlightService flightService;

    @Autowired
    private AirportService airportService;

    @Autowired
    private AirplaneService airplaneService;

    @Autowired
    private StewardService stewardService;

    @Autowired
    private BeanMappingService beanMappingService;

    @Override
    public Long createFlight(FlightDTO flightCreateDTO) {
        Flight flight = beanMappingService.mapTo(flightCreateDTO, Flight.class);

        FlightState flightState = convertFlightState(flightCreateDTO.getFlightState());

        Airport destAirport = flightCreateDTO.getDestinationport() == null ? null :  airportService.findAirportById(flightCreateDTO.getDestinationport().getId());
        Airport sourcAirport = flightCreateDTO.getSourceport() == null ? null :  airportService.findAirportById(flightCreateDTO.getSourceport().getId());

        flight.setDestinationPort(destAirport);
        flight.setSourcePort(sourcAirport);

        flight.setAirplane(
                airplaneService.getAirplaneById(flightCreateDTO.getAirplane().getId()));
        List<Steward> stewards = new ArrayList<>(flightCreateDTO.getStewards().size());
        for(StewardDTO stewardDTO : flightCreateDTO.getStewards()){
            stewards.add(stewardService.getSteward(stewardDTO.getId()));
        }

        flight.setStewards(stewards);

        Date departure =   (flightCreateDTO.getDeparture());
        Date arrival = (flightCreateDTO.getArrival());
        flight.setArrival(arrival);
        flight.setDeparture(departure);
        flight.setFlightState(flightState);

        Flight flight1 = flightService.saveFlight(flight);

        return flight1.getId();    }

    @Override
    public Long createNewFlight(FlightCreateDTO flightCreateDTO) {
        Flight flight = beanMappingService.mapTo(flightCreateDTO, Flight.class);

        FlightState flightState = convertFlightState(flightCreateDTO.getFlightState());

        Airport destAirport = flightCreateDTO.getDestinationport() == null ? null :  airportService.findAirportById(flightCreateDTO.getDestinationport().getId());
        Airport sourcAirport = flightCreateDTO.getSourceport() == null ? null :  airportService.findAirportById(flightCreateDTO.getSourceport().getId());

        flight.setDestinationPort(destAirport);
        flight.setSourcePort(sourcAirport);

        Date departure = flightCreateDTO.getDeparture();
        Date arrival = flightCreateDTO.getArrival();
        flight.setArrival(arrival);
        flight.setDeparture(departure);
        flight.setFlightState(flightState);

        Flight flight1 = flightService.saveFlight(flight);

        return flight1.getId();
    }

    @Override
    public List<FlightDTO> getAllFlights() {
        return beanMappingService.mapTo(flightService.findAllFlights(), FlightDTO.class);
    }

    @Override
    public FlightDTO getFlightById(Long id) {
        return beanMappingService.mapTo(flightService.getFlight(id), FlightDTO.class);
    }

    @Override
    public void removeFlight(Long id) {
        flightService.removeFlight(flightService.getFlight(id));
    }

    @Override
    public FlightDTO updateFlight(FlightDTO flightDTO) {
        Flight flight = beanMappingService.mapTo(flightDTO, Flight.class);
        return beanMappingService.mapTo(flightService.updateFlight(flight), FlightDTO.class);
    }

    @Override
    public boolean validateFlight(FlightDTO flightDTO) {
        Flight flight = beanMappingService.mapTo(flightDTO, Flight.class);
        return flightService.validateFlight(flight);
    }

    @Override
    public FlightDTO addStewardToFlight(FlightDTO flightDTO, StewardDTO stewardDTO) {
        Flight flight = beanMappingService.mapTo(flightDTO, Flight.class);
        Steward steward = beanMappingService.mapTo(stewardDTO, Steward.class);

        return beanMappingService.mapTo(flightService.addStewardToFlight(flight, steward), FlightDTO.class);
    }

    @Override
    public FlightDTO changeFlightState(FlightDTO flightDTO, cz.muni.airport.enums.FlightState newFlightState) {
        Flight flight = beanMappingService.mapTo(flightDTO, Flight.class);
        flight.setFlightState(convertFlightState(newFlightState));
        flight = flightService.updateFlight(flight);
        return beanMappingService.mapTo(flight, FlightDTO.class);
    }

    private FlightState convertFlightState(cz.muni.airport.enums.FlightState flightState){
        switch (flightState) {
            case OPEN:  return FlightState.OPEN;
            case ACCEPTED: return FlightState.ACCEPTED;
            case IN_VERIFY: return FlightState.IN_VERIFY;
            case DECLINED: return FlightState.DECLINED;
        }
        return FlightState.OPEN;
    }
}
