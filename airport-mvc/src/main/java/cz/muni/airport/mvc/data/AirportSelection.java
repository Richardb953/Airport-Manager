package cz.muni.airport.mvc.data;

/**
 * Created by Richard Bariny on 15.1.2017.
 *
 * @author Richard Bariny, github name:Richardb953
 */
public class AirportSelection {

    private Boolean selected;
    private String airportID;
    private String iata;
    private String name;
    private String city;
    private String country;

    public Boolean getSelected() {
        return selected;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setSelected(Boolean selected) {
        this.selected = selected;
    }

    public String getAirportID() {
        return airportID;
    }

    public void setAirportID(String airportID) {
        this.airportID = airportID;
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

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public AirportSelection() {
    }

    public AirportSelection(Boolean selected, String airportID, String iata, String name, String city, String country) {
        this.selected = selected;
        this.airportID = airportID;
        this.iata = iata;
        this.name = name;
        this.city = city;
        this.country = country;
    }
}
