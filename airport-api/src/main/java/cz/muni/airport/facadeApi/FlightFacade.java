package cz.muni.airport.facadeApi;

import java.util.List;

import cz.muni.airport.dto.FlightCreateDTO;
import cz.muni.airport.dto.FlightDTO;
import cz.muni.airport.dto.StewardDTO;
import cz.muni.airport.enums.FlightState;

/**
 * Created by Richard Bariny on 9.11.2016.
 * Facade Interface of Flight entity
 * @author Richard Bariny, github name: Richardb953
 */

public interface FlightFacade {

    /**
     * Create Flight
     *
     * @param flightDTO Flight object as DTO
     * @return id of created object
     */
    Long createFlight(FlightDTO flightDTO);

    /**
     * Create new FlightCreate with opne state without stewards and Airplane
     *
     * @param flightCreateDTO Flight Create object as DTO
     * @return id of created object
     */
    Long createNewFlight(FlightCreateDTO flightCreateDTO);

    /**
     * Get All Flights
     *
     * @return list of all Flights as DTO objects
     */
    List<FlightDTO> getAllFlights();

    /**
     * Get Existing flight by id
     *
     * @param id Flight object id
     * @return FlightDto
     */
    FlightDTO getFlightById(Long id);

    /**
     * Delete Flight by id
     *
     * @param id Flight object id
     */
    void removeFlight(Long id);

    /**
     * Update Flight object
     *
     * @param flightDTO Flight object as DTO
     * @return updated Flight DTO
     */
    FlightDTO updateFlight(FlightDTO flightDTO);

    /**
     * Validate Flight - times, airports and airplane availibilitz The system should also check that
     * the plane does not have another flight scheduled during the time of the this flight. It
     * should also checking for the steward's availability.
     *
     * @param flightDTO Flight object as DTO
     * @return boolean validated or not
     */
    boolean validateFlight(FlightDTO flightDTO);

    /**
     * Add steward to Flight
     *
     * @param flightDTO  Flight object as DTO
     * @param stewardDTO steward object as DTO
     * @return updated Flight DTO
     */
    FlightDTO addStewardToFlight(FlightDTO flightDTO, StewardDTO stewardDTO);

    /**
     * Add steward to Flight
     *
     * @param flightDTO  Flight object as DTO
     * @param stewardDTO steward object as DTO
     * @return updated Flight DTO
     */
    FlightDTO removeStewardToFlight(FlightDTO flightDTO, StewardDTO stewardDTO);


    /**
     * Change state of flight
     *
     * @param newFlightState new State
     * @param flightDTO      flight object
     * @return updated Flight object
     */
    FlightDTO changeFlightState(FlightDTO flightDTO, FlightState newFlightState);

}