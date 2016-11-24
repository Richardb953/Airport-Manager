package cz.muni.airport.model;

import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;
import java.util.Objects;
import java.util.Set;

/**
 * Class representing the Airplane entity.
 * 
 * @author Karolína Božková, github name: Kayeeec
 */

@Entity
public class Airplane {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long id;
    
    @Column(nullable = false)
    private String name;
    
    /**
     * Maximum number of passangers it can take.
     */
    @Column
    private int capacity;
    
    /**
     * Specifies type of plane.
     * @see PlaneType
     */
    @Column
    private PlaneType type;

    /**
     * Airplane can have many noninterfering flights.
     */
    @OneToMany(mappedBy = "airplane")
    private List<Flight> flights;
    
    /**
     * Constructor of class Airplane. 
     * No parameters - everything set by setters.
     */
    public Airplane() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public PlaneType getType() {
        return type;
    }

    public void setType(PlaneType type) {
        this.type = type;
    }

    public List<Flight> getFlights() {
        return Collections.unmodifiableList(flights);
    }

    public void setFlights(List<Flight> flights) {
        this.flights = flights;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + getName().hashCode()
                + Objects.hashCode(getCapacity()) 
                + getType().toString().hashCode()
                + getFlights().hashCode();
        return hash;
    }
    
   private boolean listEqualsNoOrder(List<Flight> l1, List<Flight> l2) {
    final Set<Flight> s1 = new HashSet<>(l1);
    final Set<Flight> s2 = new HashSet<>(l2);

    return s1.equals(s2);
}

    @Override
    public boolean equals(Object obj) {
        if (obj == null) return false;
        if (!(obj instanceof Airplane)) return false; 
        
        final Airplane other = (Airplane) obj;
        
        if (!Objects.equals(this.getCapacity(), other.getCapacity())) return false;
        if (!Objects.equals(this.getName(), other.getName())) return false;
        if (!Objects.equals(this.getType(), other.getType())) return false;
//        if (!Objects.equals(this.getFlights(), other.getFlights())) return false;
        
        return true;
    }

    @Override
    public String toString() {
        return "Airplane{" + "id=" + id + ", name=" + name + ", capacity=" + capacity + ", type=" + type + '}';
    }
    
    private boolean dateIntervalsInterfere(Date oldDeparture, Date oldArrival, Date newDeparture, Date newArrival){
        //oldD..newD..newA..oldA
        //oldD..newD........oldA..newA
        if (oldDeparture.before(newDeparture) && newDeparture.before(oldArrival)) return true;
        //newD..oldD..oldA..newA
        //newD..oldD........newA..oldA
        if (newDeparture.before(oldDeparture) && oldDeparture.before(newArrival)) return true;
        //newD=oldD..........
        if (oldDeparture.equals(newDeparture) || oldArrival.equals(newArrival)) return true;
        //newD..newA==oldD...oldA OR oldD..oldA==newD...newA
        if (newArrival.equals(oldDeparture) || oldArrival.equals(newDeparture)) return true;
        
        return false;      
    }
    
    public boolean checkIfAvailable(Flight flight){
        if(flight.getSourcePort() == null 
                || flight.getDestinationPort() == null 
                || flight.getDeparture() == null 
                || flight.getArrival() == null){
            throw new IllegalArgumentException("One of sourcePort, destinationPort, departure, arrival in given flight is null.");
        }
        //sort
        List<Flight> theseFlights = this.getFlights();
        
        //find time slot
        
        //checkt destination and arrival locations
        
        
        
    }



    
    

}
