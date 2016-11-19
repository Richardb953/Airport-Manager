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
 * @author Jiri Krejci, github name: xkrejci7
 */
@Entity
@NamedQueries({
    @NamedQuery(name = "Airport.findAll", query = "FROM Airport"),
    @NamedQuery(name = "Airport.findByIata", query = "FROM Airport WHERE iata = :iata"),
    @NamedQuery(name = "Airport.findByName", query = "FROM Airport WHERE name = :name"),
    @NamedQuery(name = "Airport.findByCity", query = "FROM Airport WHERE city = :city"),
    @NamedQuery(name = "Airport.findByCountry", query = "FROM Airport WHERE country = :country"),})
public class Airport {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long id;

    @Column(nullable = false, unique = true)
    private String iata;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(nullable = false)
    private String country;

    @Column(nullable = false)
    private String city;

    public Airport() {
    }

    public String getIata() {
        return iata;
    }

    public void setIata(String iata) {
        this.iata = iata;
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
                + "id=" + id
                + ", IATA='" + iata + "'"
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

        if (getId() == null) {
            if (other.getId() != null) {
                return false;
            }
            if (!getId().equals(other.getId())) {
                return false;
            }
        }

        if (getIata() == null) {
            if (other.getIata() != null) {
                return false;
            }
            if (!getIata().equals(other.getIata())) {
                return false;
            }
        }

        if (getName() == null) {
            if (other.getName() != null) {
                return false;
            }
            if (!getName().equals(other.getName())) {
                return false;
            }
        }

        if (getCountry() == null) {
            if (other.getCountry() != null) {
                return false;
            }
            if (!getCountry().equals(other.getCountry())) {
                return false;
            }
        }

        if (getCity() == null) {
            if (other.getCity() != null) {
                return false;
            }
            if (!getCity().equals(other.getCity())) {
                return false;
            }
        }

        return true;
    }

    @Override
    public int hashCode() {
        final int primeNumber = 47;
        int hashCode = 13;
        hashCode = primeNumber * hashCode + (getName() != null ? getName().hashCode() : 0);
        hashCode = primeNumber * hashCode + (getIata() != null ? getIata().hashCode() : 0);
        return hashCode;
    }
}