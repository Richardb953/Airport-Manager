package cz.muni.airport.facadeApi;

import java.util.List;

import cz.muni.airport.dto.AirplaneDTO;
import cz.muni.airport.dto.FlightDTO;

/**
 *
 * @author Karolína Božková, github name: Kayeeec
 */
public interface AirplaneFacade {

    /**
     * Create new airplane
     * @param airplaneDTO airplane DTO to create
     * @return created airplane
     */
    AirplaneDTO createAirplane(AirplaneDTO airplaneDTO);

    /**
     * Update given airplane
     * @param airplaneDTO airplane DTO to update
     * @return updated airplane
     */
    AirplaneDTO updateAirplane(AirplaneDTO airplaneDTO);

    /**
     * Delete airplane
     * @param airplaneDTO airplane DTO to remove
     */
    void deleteAirplane(AirplaneDTO airplaneDTO);

    /**
     * Delete airplane
     * @param id airplane id to remove
     */
    public void deleteAirplane(long id);

    /**
     * Get list of all airplanes
     * @return list of all airplanes
     */
    List<AirplaneDTO> getAllAirplanes();

    /**
     * Get list of airples specified by id
     * @param name name to search for
     * @return  list of airplanes
     */
    List<AirplaneDTO> getAirplaneByName(String name);

    /**
     * Get airplane specified by id
     * @param id Airplane object id
     * @return AirplaneDTO
     */
    AirplaneDTO getAirplaneById(Long id);

    /**
     * Checks availibility of airplane for given flight.
     * 
     * @param airplaneDTO Airplane object as DTO
     * @param flightDTO Flight object as DTO
     * @return true if airplane is available for flight, otherwise false
     */
    public boolean isAvailable(AirplaneDTO airplaneDTO, FlightDTO flightDTO);

    /**
     * Get list of all available airplanes for given flight
     *
     * @param flightDTO the flight to find available airplanes
     * @return available airplanes for given flight
     */
    public List<AirplaneDTO> getAvailableAirplanes(FlightDTO flightDTO);

}
