package cz.muni.airport.services.impl;

import cz.muni.airport.services.AirportService;
import org.springframework.beans.factory.annotation.Autowired;
import cz.muni.airport.dao.AirportDAO;
import cz.muni.airport.dao.FlightDAO;
import cz.muni.airport.model.Airplane;
import cz.muni.airport.model.Airport;
import cz.muni.airport.model.Flight;
import cz.muni.airport.model.Steward;
import cz.muni.airport.services.AirplaneService;
import cz.muni.airport.services.StewardService;
import java.util.ArrayList;
import java.util.List;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Implementation of Airport Service
 *
 * @author Jiri Krejci, github name: xkrejci7
 */
@Service("airportService")
public class AirportServiceImpl implements AirportService {

    @Autowired
    private AirportDAO airportDAO;

    @Autowired
    private StewardService stewardService;

    @Autowired
    private AirplaneService airplaneService;

    @Autowired
    private FlightDAO flightDAO;

    public void setAirportDAO(AirportDAO airportDAO) {
        this.airportDAO = airportDAO;
    }

    public void setStewardService(StewardService stewardService) {
        this.stewardService = stewardService;
    }

    public void setAirplaneService(AirplaneService airplaneService) {
        this.airplaneService = airplaneService;
    }

    public void setFlightDAO(FlightDAO flightDAO) {
        this.flightDAO = flightDAO;
    }

    @Override
    @Transactional
    public Airport saveAirport(Airport airport) {
        try {
            airportDAO.addAirport(airport);
            return airport;
        } catch (Exception e) {
            throw new DataAccessException(e.getMessage(), e) {
            };
        }
    }

    @Override
    @Transactional
    public Airport updateAirport(Airport airport) {
        try {
            airportDAO.updateAirport(airport);
            return airport;
        } catch (Exception e) {
            throw new DataAccessException(e.getMessage(), e) {
            };
        }
    }

    @Override
    @Transactional
    public void removeAirport(Airport airport) {
        try {
            airportDAO.removeAirport(airport);
        } catch (Exception e) {
            throw new DataAccessException(e.getMessage(), e) {
            };
        }
    }

    @Override
    public List<Airport> findAllAirports() {
        try {
            return airportDAO.getAllAirports();
        } catch (Exception e) {
            throw new DataAccessException(e.getMessage(), e) {
            };
        }
    }

    @Override
    public Airport findAirportById(Long id) {
        try {
            return airportDAO.getAirportById(id);
        } catch (Exception e) {
            throw new DataAccessException(e.getMessage(), e) {
            };
        }
    }

    @Override
    public List<Airport> findAirportsByCity(String city) {
        try {
            return airportDAO.getAirportsByCity(city);
        } catch (Exception e) {
            throw new DataAccessException(e.getMessage(), e) {
            };
        }
    }

    @Override
    public List<Airport> findAirportsByName(String name) {
        try {
            return airportDAO.getAirportsByName(name);
        } catch (Exception e) {
            throw new DataAccessException(e.getMessage(), e) {
            };
        }
    }

    @Override
    public List<Airport> findAirportsByCountry(String country) {
        try {
            return airportDAO.getAirportsByCountry(country);
        } catch (Exception e) {
            throw new DataAccessException(e.getMessage(), e) {
            };
        }
    }

    @Override
    public List<Airport> findAirportByIata(String iata) {
        try {
            return airportDAO.getAirportsByIata(iata);
        } catch (Exception e) {
            throw new DataAccessException(e.getMessage(), e) {
            };
        }
    }

    @Override
    public List<Steward> getAvailableStewards(Flight flight) {

        List<Steward> stewards = new ArrayList<>();

        for (Steward steward : stewardService.getAllStewards()) {

//            if (stewardService.isAvalible(steward, flight)) {
            if (true) {
                stewards.add(steward);
            }

        }

        return stewards;
    }

    @Override
    public List<Airplane> getAvailableAirplanes(Flight flight) {

        List<Airplane> airplanes = new ArrayList<>();

        for (Airplane airplane : airplaneService.getAllAirplanes()) {

            if (airplaneService.isAvailable(airplane, flight)) {
                airplanes.add(airplane);
            }
        }

        return airplanes;

    }

}
