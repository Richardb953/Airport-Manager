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
    AirplaneDTO createAirplane(AirplaneDTO airplaneDTO);
    AirplaneDTO updateAirplane(AirplaneDTO airplaneDTO);
    void deleteAirplane(AirplaneDTO airplaneDTO);
    List<AirplaneDTO> getAllAirplanes();
    List<AirplaneDTO> getAirplaneByName(String name);
    AirplaneDTO getAirplaneById(Long id);
    
    public boolean isAvailable(AirplaneDTO airplaneDTO, FlightDTO flightDTO);
    
    public List<AirplaneDTO> getAvailableAirplanes(FlightDTO flightDTO);
    
}
