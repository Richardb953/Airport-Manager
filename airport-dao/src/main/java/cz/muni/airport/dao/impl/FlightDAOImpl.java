package cz.muni.airport.dao.impl;

import cz.muni.airport.dao.FlightDAO;
import cz.muni.airport.database.Connection;
import cz.muni.airport.model.Flight;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * DAO Flight object
 * created by Richard Bariny on 25.10.2016
 * @author Richard Bariny, github name: Richardb953
 */

@Transactional
@Repository("flightDAO")
public class FlightDAOImpl extends Connection implements FlightDAO {

    @Override public Flight addFlight(Flight flight) {
        getHibernateTemplate().save(flight);
        return flight;
    }

    @Override public Flight getFlightById(Long id) {
        return getHibernateTemplate().get(Flight.class, id);
    }

    @Override public Flight updateFlight(Flight flight) {
        getHibernateTemplate().update(flight);
        return flight;
    }

    @Override public void removeFlight(Flight flight) {
        getHibernateTemplate().delete(flight);
    }

    @Override public List<Flight> getFlightsByName(String name) {
        if(name == null) throw new IllegalArgumentException("Name can not be null");
        return (List<Flight>) getHibernateTemplate().findByNamedQueryAndNamedParam("Flight.findByName", "name", name);
    }

    @Override public List<Flight> getAllFlights() {
        return (List<Flight>) getHibernateTemplate().findByNamedQuery("Flight.findAll");
    }
}
