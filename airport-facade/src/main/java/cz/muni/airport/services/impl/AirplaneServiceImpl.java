package cz.muni.airport.services.impl;

import cz.muni.airport.dao.AirplaneDAO;
import cz.muni.airport.model.Airplane;
import cz.muni.airport.services.AirplaneService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Implementation of AirplaneServiceInterface.
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
        try {
            return airplaneDAO.addAirplane(airplane);
        } catch (Exception e) {
            throw new DataAccessException(e.getMessage(), e) {};
        }        
    }

    @Override
    @Transactional
    public Airplane updateAirplane(Airplane airplane) {
        try {
            return airplaneDAO.updateAirplane(airplane);
        } catch (Exception e) {
            throw new DataAccessException(e.getMessage(), e) {};
        } 
    }

    @Override
    @Transactional
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

    
    
}
