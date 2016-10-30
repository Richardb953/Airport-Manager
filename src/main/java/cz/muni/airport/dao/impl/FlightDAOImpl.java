package cz.muni.airport.dao.impl;

import cz.muni.airport.dao.FlightDAO;
import cz.muni.airport.database.Connection;
import cz.muni.airport.model.Flight;
import org.springframework.stereotype.Repository;

import java.util.List;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author rba on 23.10.2016.
 */
@Repository("flightDAO")
public class FlightDAOImpl extends Connection implements FlightDAO {

    @Override
    @Transactional
    public Flight addFlight(Flight flight) {
        getHibernateTemplate().save(flight);
        return flight;
    }

    @Override
    @Transactional
    public Flight getFlight(Long id) {
        return getHibernateTemplate().get(Flight.class, id);
    }

    @Override
    @Transactional
    public Flight updateFlight(Flight flight) {
        getHibernateTemplate().update(flight);
        return flight;
    }

    @Override
    @Transactional
    public void removeFlight(Flight flight) {
        getHibernateTemplate().delete(flight);
    }

    @Override
    @Transactional
    public List<Flight> getFlightsByName(String name) {
        return (List<Flight>) getHibernateTemplate().findByNamedQueryAndNamedParam("Flight.findByName", "name", name);
    }

    @Override
    @Transactional
    public List<Flight> getAllFlights() {
        return (List<Flight>) getHibernateTemplate().findByNamedQuery("Flight.findAll");
    }
}
