package cz.muni.airport.model;

import java.util.Collections;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.util.List;
import java.util.Objects;

/**
 * Steward entity
 * @author Andrea Navratilova, github name: andrea-n
 */

@Entity
public class Steward {
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long id;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @ManyToMany()
    private List<Flight> flights;

    public Steward() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

	public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

	public List<Flight> getFlights() {
		return flights;
	}

	public void setFlights(List<Flight> flights) {
		this.flights = flights;
	}

	/**
	 * Check if steward is available for given flight
	 * Steward is available if the flight can be inserted in his flight list
	 * @param flight to check - departure, arrival, source port and destination port are required
	 * @return boolean if available
	 */
	public boolean isAvailable(Flight flight) {
		if(flight.getDeparture() == null || flight.getArrival() == null || flight.getSourcePort() == null || flight.getDestinationPort() == null) {
			throw new IllegalArgumentException("Some required arguments are null");
		}

		if(this.flights.isEmpty()) {
			return true;
		}

		List<Flight> stewardFlights = this.flights;
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
	public int hashCode() {
		int hash = 5;
		hash = 29 * hash + Objects.hashCode(this.id);
		hash = 29 * hash + Objects.hashCode(this.firstName);
		hash = 29 * hash + Objects.hashCode(this.lastName);
		return hash;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		final Steward other = (Steward) obj;
		if (!Objects.equals(this.firstName, other.firstName)) {
			return false;
		}
		if (!Objects.equals(this.lastName, other.lastName)) {
			return false;
		}
		if (!Objects.equals(this.id, other.id)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "Steward{" + "id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + '}';
	}
}
