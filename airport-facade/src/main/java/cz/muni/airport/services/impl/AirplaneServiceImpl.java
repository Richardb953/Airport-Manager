package cz.muni.airport.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import cz.muni.airport.dao.AirplaneDAO;
import cz.muni.airport.model.Airplane;
import cz.muni.airport.model.Flight;
import cz.muni.airport.services.AirplaneService;

/**
 * Implementation of AirplaneService interface.
 * @author Karolína Božková, github name: Kayeeec 
 * @see AirplaneService documentation.
 */

@Service
public class AirplaneServiceImpl implements AirplaneService {

    @Autowired
    private AirplaneDAO airplaneDAO;

    @Override
    public Airplane saveAirplane(Airplane airplane) {
        try {
            return airplaneDAO.addAirplane(airplane);
        } catch (Exception e) {
            throw new DataAccessException(e.getMessage(), e) {};
        }        
    }

    @Override
    public Airplane updateAirplane(Airplane airplane) {
        try {
            return airplaneDAO.updateAirplane(airplane);
        } catch (Exception e) {
            throw new DataAccessException(e.getMessage(), e) {};
        } 
    }

    @Override
    public void removeAirplane(Airplane airplane) {
        try {
            airplaneDAO.removeAirplane(airplane);
        } catch (Exception e) {
            throw new DataAccessException(e.getMessage(), e) {};
        } 
    }

    @Override
    public Airplane getAirplaneById(Long id) {
        try { 
            return airplaneDAO.getAirplaneById(id); 
        }
        catch (Exception e) {
            throw new DataAccessException(e.getMessage(), e) {};
        }
    }

    @Override
    public List<Airplane> getAirplaneByName(String name) {
        try { 
            return airplaneDAO.getAirplaneByName(name); 
        }
        catch (Exception e){
            throw new DataAccessException(e.getMessage(), e) {};
        }
    }

    @Override
    public List<Airplane> getAllAirplanes() {
        try {
            return airplaneDAO.getAllAirplanes();
        } 
        catch (Exception e) {
            throw new DataAccessException(e.getMessage(), e) {};
        }
    }
    
    /*
        projde seřazený seznam letů a snaží se najít místo kam by let časově zapadl
        vrací: 
            - dvojici letů - předcházející a následující let
                    * předcházející je null -> náš let může být první   result[0]
                    * následující je null -> náš let může být poslední  result[1]
            - null pokud se let nikam nevejde
    */    
    private List<Flight> findTimeSlot(List<Flight> flights, Flight flight){
        List<Flight> result = new ArrayList<Flight>(3);
        Flight prev = null, next = null, candidate = null;
        int i = 0;
        while (i < flights.size()) { //solves first by design
            candidate = flights.get(i);
            if(flight.getArrival().before(candidate.getDeparture())){
                next = candidate;
                break;
            }
            i++;           
        }
        if ( next == null && candidate.getArrival().before(flight.getDeparture()) ){ //could be last
            result.set(0, candidate);
            result.set(1, null);
            return result;
        }
        prev = flights.get(i-1); //prev for readability
        if(prev.getArrival().before(flight.getDeparture())){
            result.set(0, prev);
            result.set(0, next);
            return result;
        }
        return null;
    }
    
    @Override
    public boolean isAvailable(Airplane airplane, Flight flight) {
        if(flight.getSourcePort() == null || flight.getDestinationPort() == null 
            || flight.getDeparture() == null || flight.getArrival() == null){
            throw new IllegalArgumentException("One of sourcePort, destinationPort, departure, arrival in given flight is null.");
        }
        
        List<Flight> theseFlights = airplane.getFlights();
        //when no flights
        if (theseFlights.isEmpty()) return true;

        //sort flights
        List<Flight> modifiableList = new ArrayList<Flight>(theseFlights);
        Collections.sort(modifiableList, (Flight o1, Flight o2) -> o1.getArrival().compareTo(o2.getArrival()));

        //find time slot
        List<Flight> slot = findTimeSlot(theseFlights, flight);
        if (slot == null) return false;
        
        //check destination and arrival locations
        Flight prev = slot.get(0), next = slot.get(1); //readability
        if (prev == null){ //could be first
            if( flight.getDestinationPort().equals(next.getSourcePort()) ) return true;
        }
        if (next == null){ //could be last
            if( prev.getDestinationPort().equals(flight.getSourcePort()) ) return true;
        }
        if( prev.getDestinationPort().equals(flight.getSourcePort()) 
                && flight.getDestinationPort().equals(next.getSourcePort()) ){
            return true;
        }
        return false;
    }
    
    @Override
    public List<Airplane> getAvailableAirplanes(Flight flight){
        List<Airplane> airplanes = new ArrayList<>();
        for ( Airplane airplane : getAllAirplanes() ) {
            if ( isAvailable(airplane, flight) ) airplanes.add(airplane); 
        }
        return airplanes;
    }

    
    
}
