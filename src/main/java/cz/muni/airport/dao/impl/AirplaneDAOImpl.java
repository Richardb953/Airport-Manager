package cz.muni.airport.dao.impl;

import cz.muni.airport.model.Airplane;
import java.util.List;
import cz.muni.airport.dao.AirplaneDAO;
import org.springframework.stereotype.Repository;
import cz.muni.airport.database.Connection;
import org.springframework.transaction.annotation.Transactional;

/**
 * Implementation of AirplaneDAO. This Data Access Object provides access 
 * to Airplane antries ant Airplane related data.
 * 
 * @author Karolína Božková 
 * @see AirplaneDAO documentation.
 */
@Transactional
@Repository("airplaneDAO")
public class AirplaneDAOImpl extends Connection implements AirplaneDAO {

    @Override
    public List<Airplane> getAllAirplanes() {
        return (List<Airplane>) getHibernateTemplate().find("from Airplane ");
    }
    
    @Override
    public Airplane getAirplaneById(Long id) {
        return getHibernateTemplate().get(Airplane.class, id);
    }
    
    @Override
    public List<Airplane> getAirplaneByName(String name) {
        return (List<Airplane>) getHibernateTemplate().find("from Airplane where name = '" +name+"'");
    }

    @Override
    public Airplane addAirplane(Airplane airplane) {
        getHibernateTemplate().save(airplane);
        return airplane;
    }

    @Override
    public Airplane updateAirplane(Airplane airplane) {
        getHibernateTemplate().update(airplane);
        return airplane;
    }

    @Override
    public void removeAirplane(Airplane airplane) {
        getHibernateTemplate().delete(airplane);
    }
    
}
