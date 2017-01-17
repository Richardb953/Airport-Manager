package cz.muni.airport.dao.impl;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;

import cz.muni.airport.dao.FlightDAO;
import cz.muni.airport.model.Flight;

/**
 * DAO Flight object created by Richard Bariny on 25.10.2016
 *
 * @author Richard Bariny, github name: Richardb953
 */
@Repository
@Transactional
public class FlightDAOImpl implements FlightDAO {

    @PersistenceContext(name = "flightUnit", type = PersistenceContextType.TRANSACTION)
    private EntityManager entityManager;

    @Override
    public Flight addFlight(Flight flight) {
        if (flight == null) {
            throw new IllegalArgumentException("flight can't be null");
        }
        entityManager.persist(flight);
        return flight;
    }

    @Override
    public Flight getFlightById(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("id is null");
        }

        return entityManager.find(Flight.class, id);
    }

    @Override
    public Flight updateFlight(Flight flight) {
        if (flight == null) {
            throw new IllegalArgumentException("flight can't be null");
        }
        entityManager.merge(flight);
        return flight;
    }

    @Override
    public void removeFlight(Flight flight) {
        if (flight == null) {
            throw new IllegalArgumentException("flight can't be null");
        }
        entityManager.remove(entityManager.merge(flight));
    }

    @Override
    public List<Flight> getFlightsByName(String name) {
        if (name == null) {
            throw new IllegalArgumentException("Name can not be null");
        }
        return entityManager.createNamedQuery("Flight.findByName", Flight.class).setParameter("name", name).getResultList();
    }

    @Override
    public List<Flight> getAllFlights() {
        return entityManager.createNamedQuery("Flight.findAll", Flight.class).getResultList();
    }
}
