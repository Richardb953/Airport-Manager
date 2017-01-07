package cz.muni.airport.dao.impl;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;

import cz.muni.airport.dao.AirportDAO;
import cz.muni.airport.model.Airport;

/**
 * Implementation of Airport DAO interface
 *
 * @author Jiri Krejci, github name: xkrejci7
 */

@Repository
@Transactional
public class AirportDAOImpl implements AirportDAO {
    @PersistenceContext(name= "airportUnit",type = PersistenceContextType.TRANSACTION)
    private EntityManager entityManager;

    @Override
    public Airport addAirport(Airport airport) {
        entityManager.persist(airport);
        return airport;
    }

    @Override
    public Airport updateAirport(Airport airport) {
        entityManager.merge(airport);
        return airport;
    }

    @Override
    public void removeAirport(Airport airport) {
        entityManager.remove(airport);
    }

    @Override
    public List<Airport> getAllAirports() {
        return entityManager.createNamedQuery("Airport.findAll", Airport.class).getResultList();
    }

    @Override
    public Airport getAirportById(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("Id can't be null");
        }
        return entityManager.find(Airport.class, id);
    }

    @Override
    public List<Airport> getAirportsByCity(String city) {
        if (city == null) {
            throw new IllegalArgumentException("City can't be null");
        }
        return entityManager.createNamedQuery("Airport.findByCity", Airport.class).setParameter("city", city).getResultList();
    }

    @Override
    public List<Airport> getAirportsByName(String name) {
        if (name == null) {
            throw new IllegalArgumentException("Name can't be null");
        }
        return entityManager.createNamedQuery("Airport.findByName", Airport.class).setParameter("name", name).getResultList();
    }

    @Override
    public List<Airport> getAirportsByCountry(String country) {
        if (country == null) {
            throw new IllegalArgumentException("Country can't be null");
        }
        return entityManager.createNamedQuery("Airport.findByCountry", Airport.class).setParameter("country", country).getResultList();
    }

    @Override
    public List<Airport> getAirportsByIata(String iata) {
        if (iata == null) {
            throw new IllegalArgumentException("Iata can't be null");
        }
        return entityManager.createNamedQuery("Airport.findByIata", Airport.class).setParameter("iata", iata).getResultList();
    }

}
