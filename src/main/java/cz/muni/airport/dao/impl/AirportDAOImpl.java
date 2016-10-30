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
    public Airport getAirportById(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("Id can't be null");
        }
        return getHibernateTemplate().get(Airport.class, id);
    }

    @Override
    public List<Airport> getAirportsByCity(String city) {
        if (city == null) {
            throw new IllegalArgumentException("City can't be null");
        }
        return (List<Airport>) getHibernateTemplate().findByNamedQueryAndNamedParam("Airport.findByCity", "city", city);
    }

    @Override
    public List<Airport> getAirportsByName(String name) {
        if (name == null) {
            throw new IllegalArgumentException("Name can't be null");
        }
        return (List<Airport>) getHibernateTemplate().findByNamedQueryAndNamedParam("Airport.findByName", "name", name);
    }

    @Override
    public List<Airport> getAirportsByCountry(String country) {
        if (country == null) {
            throw new IllegalArgumentException("Country can't be null");
        }
        return (List<Airport>) getHibernateTemplate().findByNamedQueryAndNamedParam("Airport.findByCountry", "country", country);
    }

}
