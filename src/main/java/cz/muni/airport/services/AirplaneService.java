/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.airport.services;

import cz.muni.airport.model.Airplane;
import java.util.List;

/**
 *
 * @author Karolína Božková
 */
public interface AirplaneService {
    Airplane saveAirplane(Airplane airplane);
    Airplane updateAirplane(Airplane airplane);
    void removeAirplane(Airplane airplane);
    
    List<Airplane> getAllAirplanes(); 
    Airplane getAirplaneById(Long id);
    List<Airplane> getAirplaneByName(String name);
    
        
            
    
}
