/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
     * 
     * @param name
     * @return 
     */
    List<AirplaneDTO> getAirplaneByName(String name);

    AirplaneDTO getAirplaneById(Long id);

    public boolean isAvailable(AirplaneDTO airplaneDTO, FlightDTO flightDTO);

    public List<AirplaneDTO> getAvailableAirplanes(FlightDTO flightDTO);

}
