package cz.muni.airport.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

/**
 * Airport entity class
 * 
 * @author Jiri Krejci
 */
@Entity
@NamedQueries({
    @NamedQuery(name = "Airport.findAll", query = "FROM Airport"),
    @NamedQuery(name = "Airport.findByName", query = "FROM Airport WHERE name = :name"),
    @NamedQuery(name = "Airport.findByCity", query = "FROM Airport WHERE city = :city"),
    @NamedQuery(name = "Airport.findByCountry", query = "FROM Airport WHERE country = :country"),
})
public class Airport {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(nullable = false)
    private String country;

    @Column(nullable = false)
    private String city;

    public Airport() {
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

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }


    @Override
    public String toString() {
        return "Airport {"
                + "id=" + getId()
                + ", name='" + name + "'"
                + ", country='" + country + "'"
                + ", city='" + city + "'"
                + "}";
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof Airport)) {
            return false;
        }

        Airport other = (Airport) obj;
        if (id == null) {
            if (other.id != null) {
                return false;
            }
        } else if (!id.equals(other.getId())) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
