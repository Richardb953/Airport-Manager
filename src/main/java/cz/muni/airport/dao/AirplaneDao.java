/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.airport.dao;

import cz.muni.airport.model.Airplane;
import java.util.List;

/**
 *
 * @author Karolína Božková
 */
public interface AirplaneDao {
    public List<Airplane> getAllAirplanes();
    public Airplane getAirplaneById(Long id);
    public Airplane getAirplaneByName(String name);
    
    public void addAirplane(Airplane airplane);
    public void updateAirplane(Airplane airplane);
    public void removeAirplane(Airplane airplane);
    
}
