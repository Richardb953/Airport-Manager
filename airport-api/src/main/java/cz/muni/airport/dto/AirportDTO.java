package cz.muni.airport.dto;

/**
 *
 * @author Jiri Krejci, github name: xkrejci7
 */
public class AirportDTO extends AirportCreateDTO {

    private Long id;
    private String iata;
    private String name;
    private String country;
    private String city;

    public AirportDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIata() {
        return iata;
    }

    public void setIata(String iata) {
        this.iata = iata;
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
        if (!(obj instanceof AirportDTO)) {
            return false;
        }

        AirportDTO otherDTO = (AirportDTO) obj;

        if (getId() == null) {
            if (otherDTO.getId() != null) {
                return false;
            }
            if (!getId().equals(otherDTO.getId())) {
                return false;
            }
        }

        if (getIata() == null) {
            if (otherDTO.getIata() != null) {
                return false;
            }
            if (!getIata().equals(otherDTO.getIata())) {
                return false;
            }
        }

        if (getName() == null) {
            if (otherDTO.getName() != null) {
                return false;
            }
            if (!getName().equals(otherDTO.getName())) {
                return false;
            }
        }

        if (getCountry() == null) {
            if (otherDTO.getCountry() != null) {
                return false;
            }
            if (!getCountry().equals(otherDTO.getCountry())) {
                return false;
            }
        }

        if (getCity() == null) {
            if (otherDTO.getCity() != null) {
                return false;
            }
            if (!getCity().equals(otherDTO.getCity())) {
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
