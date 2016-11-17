package cz.muni.airport.model;

import java.util.Collections;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;
import java.util.Objects;

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

    @Override
    public boolean equals(Object obj) {
        if (obj == null) return false;
        if (!(obj instanceof Airplane)) return false; 
        
        final Airplane other = (Airplane) obj;
        
        if (this.getCapacity() != other.getCapacity()) return false;
        if (!Objects.equals(this.getName(), other.getName())) return false;
        if (this.getType() != other.getType()) return false;
        if (!(this.getFlights().equals(other.getFlights()))) return false;
        
        return true;
    }

    @Override
    public String toString() {
        return "Airplane{" + "id=" + id + ", name=" + name + ", capacity=" + capacity + ", type=" + type + '}';
    }

}
