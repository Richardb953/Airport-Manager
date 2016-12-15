package cz.muni.airport.dao.impl;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import cz.muni.airport.dao.AirportDAO;
import cz.muni.airport.model.Airport;

/**
 * Implementation of Airport DAO interface
 *
 * @author Jiri Krejci, github name: xkrejci7
 */
@Transactional
@Repository("airportDAO")
public class AirportDAOImpl extends HibernateDaoSupport implements AirportDAO {

    @Autowired
    private SessionFactory sessionFactory;

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

    @Override
    public List<Airport> getAirportsByIata(String iata) {
        if (iata == null) {
            throw new IllegalArgumentException("Iata can't be null");
        }
        return (List<Airport>) getHibernateTemplate().findByNamedQueryAndNamedParam("Airport.findByIata", "iata", iata);
    }

}
