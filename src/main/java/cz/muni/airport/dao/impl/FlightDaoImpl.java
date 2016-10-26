package cz.muni.airport.dao.impl;

import cz.muni.airport.dao.FlightDAO;
import cz.muni.airport.model.Flight;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author rba on 23.10.2016.
 */

@Repository("flightDAO")
public class FlightDAOImpl implements FlightDAO {
    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    @Override public Flight addFlight(Flight flight) {
        Session session = this.sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        session.persist(flight);
        tx.commit();
        session.close();
        return flight;
    }

    @Override public Flight getFlight(Long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override public Flight updateFlight(Flight flight) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override public void removeFlight(Long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.

    }

    @Override public List<Flight> getFlightsByName(String name) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override public List<Flight> getAllFlights() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override public Flight updatePassengersByConst(Flight flight, Integer passengerConst) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
