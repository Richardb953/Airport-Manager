package cz.muni.airport.facadeApi;

import cz.muni.airport.dto.AirportDTO;
import java.util.List;

/**
 *
 * @author Jiri Krejci, github name: xkrejci7
 */
public interface AirportFacade {

    AirportDTO createAirport(AirportDTO airportDTO);

    List<AirportDTO> getAllAirports();

    AirportDTO getAirportById(Long id);

    List<AirportDTO> getAirportsByIata(String iata);

    List<AirportDTO> getAirportsByCity(String city);

    List<AirportDTO> getAirportsByName(String name);

    List<AirportDTO> getAirportsByCountry(String country);

    void removeAirport(Long id);

    AirportDTO updateAirport(AirportDTO airportDTO);

}
