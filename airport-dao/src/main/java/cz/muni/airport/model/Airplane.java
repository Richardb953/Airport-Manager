package cz.muni.airport.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
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
    
    /*
        projde seřazený seznam letů a snaží se najít místo kam by let časově zapadl
            - dalo by se to udělat efektivněji 
    */
    private List<Flight> findTimeSlot(List<Flight> flights, Flight flight){
        List<Flight> result = new ArrayList<>(2);
        //test zda se vejde před hned první let
        Flight first = flights.get(0);
        if(first.getDeparture().after(flight.getArrival())){
            result.set(0, null);
            result.set(1, first);
            return result;
        }
        //test ostatních letů
        Flight prev, next;
        int i = 1;
        while(i < flights.size()){
            prev = flights.get(i-1);
            next = flights.get(i);
            if(prev.getArrival().before(flight.getDeparture()) && next.getDeparture().after(flight.getArrival())){
                result.set(0, prev);
                result.set(1, next);
                return result;
            }
            i++;
        }
        //zda se vejde za poslední let
        Flight last = flights.get(i);
        if(last.getArrival().before(flight.getDeparture())){
            result.set(0, last);
            result.set(1, null);
            return result;
        }
        //nevejde se nikam -> vrací null
        return null;
    }
    
//    private List<Flight> findTimeSlot(List<Flight> flights, Flight flight){
//        List<Flight> result = new ArrayList<>(2);
//        Flight prev, next, candidate = null;
//        int i = 0;
//        while (i < flights.size()) {
//            candidate = flights.get(i);
//            if(flight.getArrival().before(candidate.getDeparture())){
//                next = candidate;
//                break;
//            }
//            i++;           
//        }
//        if (i == 0){
//            result.set(0, null);
//            result.set(1, next);
//            return result;
//        }
//        if (next == null){ 
//            result.set(0, next);
//            result.set(1, null);
//            return result;
//        }
//        
//        
//        
//    }
    
    public boolean checkIfAvailable(Flight flight){
        if(flight.getSourcePort() == null 
                || flight.getDestinationPort() == null 
                || flight.getDeparture() == null 
                || flight.getArrival() == null){
            throw new IllegalArgumentException("One of sourcePort, destinationPort, departure, arrival in given flight is null.");
        }
        
        List<Flight> theseFlights = this.getFlights();
        //when no flights
        if (theseFlights.isEmpty()) return true;
        
        //sort flights
        Collections.sort(theseFlights, (Flight o1, Flight o2) -> o1.getArrival().compareTo(o2.getArrival()));
        
        //find time slot
        List<Flight> slot = findTimeSlot(theseFlights, flight);
        if (slot == null) return false;
        
        //check destination and arrival locations
        Flight prev = slot.get(0);
        Flight next = slot.get(1);
        
        if (prev == null){ //could be first
            if(flight.getDestinationPort().equals(next.getSourcePort())) return true;
        }
        if (next == null){ //could be last
            if(flight.getSourcePort().equals(prev.getDestinationPort())) return true;
        }
        if(prev.getDestinationPort().equals(flight.getSourcePort()) 
                && flight.getDestinationPort().equals(next.getSourcePort())){
            return true;
        }
        return false;
        
    }



    
    

}
