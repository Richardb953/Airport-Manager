package cz.muni.airport.services.impl;

import cz.muni.airport.dao.StewardDAO;
import cz.muni.airport.model.Flight;
import cz.muni.airport.model.Steward;
import cz.muni.airport.services.StewardService;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Andrea Navratilova
 */
@Service("stewardService")
public class StewardServiceImpl implements StewardService {
    @Autowired
    private StewardDAO stewardDAO;

    @Override
	@Transactional
	public Steward addSteward(Steward steward) {
        return stewardDAO.addSteward(steward);
    }

	@Override
	@Transactional
	public void removeSteward(Steward steward) {
        stewardDAO.removeSteward(steward);
    }

	@Override
	@Transactional
	public Steward updateSteward(Steward steward) {
        return stewardDAO.updateSteward(steward);
    }

	@Override
	public Steward getSteward(Long stewardId) {
        return stewardDAO.getStewardById(stewardId);
    }

	@Override
	public List<Steward> getAllStewards() {
        return stewardDAO.getAllStewards();
    }

	@Override
	public List<Steward> getStewardByName(String firstName, String lastName) {
        return stewardDAO.getStewardByName(firstName, lastName);
    }


	public boolean isAvailable(Steward steward, Flight flight) {
		if(flight.getDeparture() == null || flight.getArrival() == null || flight.getSourcePort() == null || flight.getDestinationPort() == null) {
			throw new IllegalArgumentException("Some required arguments are null");
		}

		if(steward.getFlights().isEmpty()) {
			return true;
		}

		List<Flight> stewardFlights = steward.getFlights();
		Collections.sort(stewardFlights, (Flight flight1, Flight flight2) -> flight1.getArrival().compareTo(flight2.getArrival()));

		return isCompatible(flight, stewardFlights);
	}

	private boolean isCompatible(Flight flight, List<Flight> flights) {
		Flight prev, next;
		for(int i = 0; i <= flights.size(); i++) {
			boolean departure = true, arrival = true;
			// if there is previous flight in list, check if its arrival is before the departure of flight to add
			if(i != 0) {
				prev = flights.get(i-1);
				departure = prev.getArrival().before(flight.getDeparture()) && prev.getDestinationPort() == flight.getSourcePort();
			}
			// if there is next flight in list, check if its departure is after the arrival of flight to add
			if(i != flights.size()) {
				next = flights.get(i);
				arrival = next.getDeparture().after(flight.getArrival()) && next.getSourcePort() == flight.getDestinationPort();
			}

			if(departure && arrival) {
				return true;
			}
		}
		return false;
	}

	@Override
    public List<Steward> getAvailableStewards(Flight flight){
		List<Steward> stewards = new ArrayList<>();
		for (Steward steward : getAllStewards()) {
			if (isAvailable(steward, flight)) {
				stewards.add(steward);
			} 
		}
		return stewards;
    }
}
