package cz.muni.airport.dto;

import java.util.ArrayList;
import java.util.List;

/**
 * Data transfer object between flights layers
 * Created by Richard Bariny on 9.11.2016.
 * @author github:Richardb953
 */
public class FlightDTO {
    private int id;
    private String name;
    private int arrival;
    private int departure;
    private int passagers;
    private int airplane;
    private int destinationport;
    private int sourceport;
    private List<StewardDTO> stewards = new ArrayList<StewardDTO>();

    public FlightDTO() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getArrival() {
        return arrival;
    }

    public void setArrival(int arrival) {
        this.arrival = arrival;
    }

    public int getDeparture() {
        return departure;
    }

    public void setDeparture(int departure) {
        this.departure = departure;
    }

    public int getPassagers() {
        return passagers;
    }

    public void setPassagers(int passagers) {
        this.passagers = passagers;
    }

    public int getAirplane() {
        return airplane;
    }

    public void setAirplane(int airplane) {
        this.airplane = airplane;
    }

    public int getDestinationport() {
        return destinationport;
    }

    public void setDestinationport(int destinationport) {
        this.destinationport = destinationport;
    }

    public int getSourceport() {
        return sourceport;
    }

    public void setSourceport(int sourceport) {
        this.sourceport = sourceport;
    }

    public List<StewardDTO> getStewards() {
        return stewards;
    }

    public void setStewards(List<StewardDTO> stewards) {
        this.stewards = stewards;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        FlightDTO flightDTO = (FlightDTO) o;

        if (id != flightDTO.id) return false;
        if (arrival != flightDTO.arrival) return false;
        if (departure != flightDTO.departure) return false;
        if (destinationport != flightDTO.destinationport) return false;
        if (sourceport != flightDTO.sourceport) return false;
        return name != null ? name.equals(flightDTO.name) : flightDTO.name == null;

    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + arrival;
        result = 31 * result + departure;
        result = 31 * result + destinationport;
        result = 31 * result + sourceport;
        return result;
    }

    @Override
    public String toString() {
        return "FlightDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", arrival=" + arrival +
                ", departure=" + departure +
                ", passagers=" + passagers +
                ", airplane=" + airplane +
                ", destinationport=" + destinationport +
                ", sourceport=" + sourceport +
                '}';
    }
}
