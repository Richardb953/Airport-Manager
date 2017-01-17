package cz.muni.airport.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import cz.muni.airport.enums.FlightState;

/**
 * Data transfer object between flights layers
 * Created by Richard Bariny on 9.11.2016.
 * @author github:Richardb953
 */
public class FlightDTO extends FlightCreateDTO {
    private Long id;
    private String name;
    private Date arrival;
    private Date departure;
    private int passagers;
    private FlightState flightState;
    private AirplaneDTO airplane;
    private AirportDTO destinationport;
    private AirportDTO sourceport;
    private List<StewardDTO> stewards = new ArrayList<StewardDTO>();

    public FlightDTO() {
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

    public Date getArrival() {
        return arrival;
    }

    public void setArrival(Date arrival) {
        this.arrival = arrival;
    }

    public Date getDeparture() {
        return departure;
    }

    public void setDeparture(Date departure) {
        this.departure = departure;
    }

    public int getPassagers() {
        return passagers;
    }

    public void setPassagers(int passagers) {
        this.passagers = passagers;
    }

    public AirplaneDTO getAirplane() {
        return airplane;
    }

    public void setAirplane(AirplaneDTO airplane) {
        this.airplane = airplane;
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

    public List<StewardDTO> getStewards() {
        return stewards;
    }

    public void addSteward(StewardDTO stewardDTO){
        this.stewards.add(stewardDTO);
    }

    public void setStewards(List<StewardDTO> stewards) {
        this.stewards = stewards;
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

        FlightDTO flightDTO = (FlightDTO) o;

        if (!Objects.equals(id, flightDTO.id)) return false;
        if (!Objects.equals(arrival, flightDTO.arrival)) return false;
        if (!Objects.equals(departure, flightDTO.departure)) return false;
        if (destinationport != flightDTO.destinationport) return false;
        if (sourceport != flightDTO.sourceport) return false;
        return name != null ? name.equals(flightDTO.name) : flightDTO.name == null;

    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((name == null) ? 0 : name.hashCode())
                + ((destinationport == null) ? 0 : destinationport.hashCode())
                + ((sourceport == null) ? 0 : sourceport.hashCode())
                + ((id == null) ? 0 : id.hashCode());
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
                '}';
    }

}
