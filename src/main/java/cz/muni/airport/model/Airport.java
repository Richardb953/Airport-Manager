package cz.muni.airport.model;

import java.util.Locale;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * @author Jiri Krejci
 */
@Entity
public class Airport {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(nullable = false)
    private Locale country;

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

    public Locale getCountry() {
        return country;
    }

    /**
     * https://en.wikipedia.org/wiki/ISO_3166-1_alpha-2
     *
     * @param country
     */
    public void setCountry(Locale country) {
        this.country = country;
    }

    /**
     *
     * @param language
     * @param country
     */
    public void setCountry(String language, String country) {
        this.setCountry(new Locale(language, country));
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
                + ", country='" + country.getDisplayCountry() + " (" + country.getCountry() + ")'"
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
