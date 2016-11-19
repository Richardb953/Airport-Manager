package cz.muni.airport.services.impl;

import cz.muni.airport.dao.AirplaneDAO;
import cz.muni.airport.model.Airplane;
import cz.muni.airport.services.AirplaneService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Implementation of AirplaneServiceInterface.
 * @author Karolína Božková 
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

    
    
}
