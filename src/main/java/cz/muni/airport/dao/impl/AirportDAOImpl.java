package cz.muni.airport.dao.impl;

import cz.muni.airport.model.Airport;
import java.util.List;
import cz.muni.airport.dao.AirportDAO;
import cz.muni.airport.database.Connection;
import org.springframework.stereotype.Repository;

/**
 * Implementation of Airport DAO interface
 * 
 * @author Jiri Krejci
 */
@Repository("airportDAO")
public class AirportDAOImpl extends Connection implements AirportDAO {

    @Override
    public Airport addAirport(Airport airport) {
        getHibernateTemplate().save(airport);
        return airport;
    }

    @Override
    public Airport updateAirport(Airport airport) {
        getHibernateTemplate().update(airport);
        return airport;
    }

    @Override
    public void removeAirport(Airport airport) {
        getHibernateTemplate().delete(airport);
    }

    @Override
    public List<Airport> getAllAirports() {
        return (List<Airport>) getHibernateTemplate().findByNamedQuery("Airport.findAll");
    }

    @Override
    public Airport getAirportById(Long id)  {
        return getHibernateTemplate().get(Airport.class, id);
    }

    @Override
    public List<Airport> getAirportsByCity(String city) {
        return (List<Airport>) getHibernateTemplate().findByNamedQuery("Airport.findByCity","city", city);
    }

    @Override
    public List<Airport> getAirportsByName(String name) {
        return (List<Airport>) getHibernateTemplate().findByNamedQuery("Airport.findByName","name", name);
    }

    @Override
    public List<Airport> getAirportsByCountry(String country) {
        return (List<Airport>) getHibernateTemplate().findByNamedQuery("Airport.findByCountry","country", country   );
    }

}
