package cz.muni.airport.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;

import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * Created by Richard Bariny on 23.10.2016.
 *
 * Entity of Flight
 * Contains 2 Airports as Destination and source, airplane which is going to be used and basic parameters
 * about fligth as Times
 *
 * @author Richard Bariny, github name: Richardb953
 */

@Entity
@NamedQueries({
        @NamedQuery(name = "Flight.findAll", query = "from Flight"),
        @NamedQuery(name = "Flight.findByName", query = "FROM Flight WHERE name = :name")
})
public class Flight {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column
    private Date arrival;

    @Column
    private Date departure;

    @Column
    private Integer passagers;

    @ManyToOne()
    private Airplane airplane;

    @OneToOne()
    private Airport destinationPort;

    @OneToOne()
    private Airport sourcePort;

    @ManyToMany(mappedBy = "flights")
    private List<Steward> stewards;


    public Flight() {
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

    public Airplane getAirplane() {
        return airplane;
    }

    public void setAirplane(Airplane airplane) {
        this.airplane = airplane;
    }

    public Airport getDestinationPort() {
        return destinationPort;
    }

    public void setDestinationPort(Airport destinationPort) {
        this.destinationPort = destinationPort;
    }

    public Airport getSourcePort() {
        return sourcePort;
    }

    public void setSourcePort(Airport sourcePort) {
        this.sourcePort = sourcePort;
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

    public Integer getPassagers() {
        return passagers;
    }

    public void setPassagers(Integer passagers) {
        this.passagers = passagers;
    }

    public List<Steward> getStewards() {
        return Collections.unmodifiableList(stewards);
    }

    public void setStewards(List<Steward> stewards) {
        this.stewards = stewards;
    }

    public void addSteward(Steward steward){
        this.stewards.add(steward);
    }

    @Override public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof Flight))
            return false;

        Flight flight = (Flight) o;

        return getId() != null ? getId().equals(flight.getId()) : flight.getId() == null;

    }

    @Override public int hashCode() {
        return getId() != null ? getId().hashCode() : 0;
    }

    @Override
    public String toString() {
        return "Flight{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", arrival=" + arrival +
                ", departure=" + departure +
                ", passagers=" + passagers +
                ", airplane=" + airplane +
                ", destinationPort=" + destinationPort +
                ", sourcePort=" + sourcePort +
                '}';
    }
}
