package cz.muni.airport.dao.impl;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;

import cz.muni.airport.dao.AirplaneDAO;
import cz.muni.airport.model.Airplane;

/**
 * Implementation of AirplaneDAO. This Data Access Object provides access 
 * to Airplane antries ant Airplane related data.
 * 
 * @author Karolína Božková, github name: Kayeeec 
 * @see AirplaneDAO documentation.
 */
@Repository
@Transactional
public class AirplaneDAOImpl implements AirplaneDAO {
//
    @PersistenceContext(name= "airplaneUnit",type = PersistenceContextType.TRANSACTION)
    private EntityManager entityManager;

    @Override
    public List<Airplane> getAllAirplanes() {

        return entityManager.createQuery("from Airplane", Airplane.class).getResultList();
    }
    
    @Override
    public Airplane getAirplaneById(Long id) {
        if(id == null) throw new IllegalArgumentException("id is null");
        return entityManager.find(Airplane.class, id);
    }
    
    @Override
    public List<Airplane> getAirplaneByName(String name) {
        if(name == null) throw new IllegalArgumentException("name is null");
        return entityManager.createQuery("from Airplane where name = '" +name+"'", Airplane.class).getResultList();
    }

    @Override
    public Airplane addAirplane(Airplane airplane) {
        entityManager.persist(airplane);
        return airplane;
    }

    @Override
    public Airplane updateAirplane(Airplane airplane) {
        entityManager.merge(airplane);
        return airplane;
    }

    @Override
    public void removeAirplane(Airplane airplane) {
      entityManager.remove(airplane);
    }
    
}
