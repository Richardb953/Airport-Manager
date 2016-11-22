package cz.muni.airport.dto;

/**
 * Created by Richard Bariny on 22.11.2016.
 *
 * @author Richard Bariny, github name:Richardb953
 */
public class FlightCreateDTO {

    private String name;
    private int arrival;
    private int departure;
    private int passagers;
    private int airplane;
    private int destinationport;
    private int sourceport;

    public FlightCreateDTO() {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        FlightCreateDTO that = (FlightCreateDTO) o;

        if (arrival != that.arrival) return false;
        if (departure != that.departure) return false;
        if (destinationport != that.destinationport) return false;
        if (sourceport != that.sourceport) return false;
        return name != null ? name.equals(that.name) : that.name == null;

    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + arrival;
        result = 31 * result + departure;
        result = 31 * result + destinationport;
        result = 31 * result + sourceport;
        return result;
    }

    @Override
    public String toString() {
        return "FlightCreateDTO{" +
                "name='" + name + '\'' +
                ", arrival=" + arrival +
                ", departure=" + departure +
                ", passagers=" + passagers +
                ", airplane=" + airplane +
                ", destinationport=" + destinationport +
                ", sourceport=" + sourceport +
                '}';
    }
}
