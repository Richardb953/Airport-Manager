package cz.muni.airport.dto;

import java.util.Objects;

import cz.muni.airport.enums.FlightState;

/**
 * Created by Richard Bariny on 22.11.2016.
 *
 * @author Richard Bariny, github name:Richardb953
 */
public class FlightCreateDTO {

    private String name;
    private Long arrival;
    private Long departure;
    private int passagers;
    private FlightState flightState = FlightState.OPEN;
    private AirportDTO destinationport;
    private AirportDTO sourceport;

    public FlightCreateDTO() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getArrival() {
        return arrival;
    }

    public void setArrival(Long arrival) {
        this.arrival = arrival;
    }

    public Long getDeparture() {
        return departure;
    }

    public void setDeparture(Long departure) {
        this.departure = departure;
    }

    public int getPassagers() {
        return passagers;
    }

    public void setPassagers(int passagers) {
        this.passagers = passagers;
    }

    public AirportDTO getDestinationport() {
        return destinationport;
    }

    public void setDestinationport(AirportDTO destinationport) {
        this.destinationport = destinationport;
    }

    public AirportDTO getSourceport() {
        return sourceport;
    }

    public void setSourceport(AirportDTO sourceport) {
        this.sourceport = sourceport;
    }

    public FlightState getFlightState() {
        return flightState;
    }

    public void setFlightState(FlightState flightState) {
        this.flightState = flightState;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        FlightCreateDTO that = (FlightCreateDTO) o;

        if (!Objects.equals(arrival, that.arrival)) return false;
        if (!Objects.equals(departure, that.departure)) return false;
        if (destinationport != that.destinationport) return false;
        if (sourceport != that.sourceport) return false;
        return name != null ? name.equals(that.name) : that.name == null;

    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + ((name == null) ? 0 : name.hashCode());
        result = 31 * result + destinationport.hashCode();
        result = 31 * result + sourceport.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "FlightCreateDTO{" +
                "name='" + name + '\'' +
                ", arrival=" + arrival +
                ", departure=" + departure +
                ", passagers=" + passagers +
                '}';
    }

}
