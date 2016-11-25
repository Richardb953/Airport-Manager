package cz.muni.airport.facadeApi;

import cz.muni.airport.dto.AirportDTO;
import java.util.List;

/**
 *
 * @author Jiri Krejci, github name: xkrejci7
 */
public interface AirportFacade {

    /**
     * Create Airport
     *
     * @param airportDTO Airport object as DTO
     * @return id of created object
     */
    AirportDTO createAirport(AirportDTO airportDTO);

    /**
     * Get All Airports
     *
     * @return list of all Airports as DTO objects
     */
    List<AirportDTO> getAllAirports();

    /**
     * Get Airport specified by id
     *
     * @param id Airport object id
     * @return AirportDto
     */
    AirportDTO getAirportById(Long id);

    /**
     * Get All Airports specified by iata code
     *
     * @param iata iata to search for
     * @return list of all Airports as DTO objects
     */
    List<AirportDTO> getAirportsByIata(String iata);

    /**
     * Get All Airports specified by city
     *
     * @param city city to search for
     * @return list of all Airports as DTO objects
     */
    List<AirportDTO> getAirportsByCity(String city);

    /**
     * Get All Airports specified by name
     *
     * @param name name to search for
     * @return list of all Airports as DTO objects
     */
    List<AirportDTO> getAirportsByName(String name);

    /**
     * Get All Airports specified by country
     *
     * @param country country to search for
     * @return list of all Airports as DTO objects
     */
    List<AirportDTO> getAirportsByCountry(String country);

    /**
     * Delete Airport by id
     *
     * @param id Airport object id
     */
    void removeAirport(Long id);

    /**
     * Update Airport object
     *
     * @param airportDTO Airport object as DTO
     * @return updated Airport DTO
     */
    AirportDTO updateAirport(AirportDTO airportDTO);

    /**
     * Chcecks if airport has valid IATA code IATA code is valid if it has form
     * of three-capital-letter code
     *
     * @param airportDTO
     * @return true if airport has valid IATA code
     */
    boolean hasValidIAta(AirportDTO airportDTO);

}
