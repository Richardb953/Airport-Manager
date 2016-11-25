package cz.muni.airport.services.impl;

import cz.muni.airport.dao.AirplaneDAO;
import cz.muni.airport.model.Airplane;
import cz.muni.airport.model.Flight;
import cz.muni.airport.services.AirplaneService;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Implementation of AirplaneService interface.
 * @author Karolína Božková, github name: Kayeeec 
 * @see AirplaneService documentation.
 */
@Service("airplaneService")
public class AirplaneServiceImpl implements AirplaneService {
    @Autowired
    AirplaneDAO airplaneDAO;

    public void setAirplaneDAO(AirplaneDAO airplaneDAO) {
        this.airplaneDAO = airplaneDAO;
    }
   
    @Override
    @Transactional
    public Airplane saveAirplane(Airplane airplane) {
        airplaneDAO.addAirplane(airplane);
        return airplane;
    }

    @Override
    @Transactional
    public Airplane updateAirplane(Airplane airplane) {
        airplaneDAO.updateAirplane(airplane);
        return airplane;
    }

    @Override
    @Transactional
    public void removeAirplane(Airplane airplane) {
        airplaneDAO.removeAirplane(airplane);
    }

    @Override
    public Airplane getAirplaneById(Long id) {
        return airplaneDAO.getAirplaneById(id);
    }

    @Override
    public List<Airplane> getAirplaneByName(String name) {
        return airplaneDAO.getAirplaneByName(name);
    }

    @Override
    public List<Airplane> getAllAirplanes() {
        return airplaneDAO.getAllAirplanes();
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
        List<Flight> result = new ArrayList<>(2);
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
    public boolean isAvailable(Airplane airplane, Flight flight){
        if(flight.getSourcePort() == null || flight.getDestinationPort() == null 
            || flight.getDeparture() == null || flight.getArrival() == null){
            throw new IllegalArgumentException("One of sourcePort, destinationPort, departure, arrival in given flight is null.");
        }
        
        List<Flight> theseFlights = airplane.getFlights();
        //when no flights
        if (theseFlights.isEmpty()) return true;
        
        //sort flights
        Collections.sort(theseFlights, (Flight o1, Flight o2) -> o1.getArrival().compareTo(o2.getArrival()));
        
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

    
    
}
