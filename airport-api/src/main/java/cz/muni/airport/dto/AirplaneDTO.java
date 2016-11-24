/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.airport.dto;

import java.util.List;

import cz.muni.airport.enums.PlaneType;

/**
 *
 * @author Karolína Božková, github name: Kayeeec
 */
public class AirplaneDTO {
    private Long id;
    private String name;
    private int capacity;
    private PlaneType type;
    private List<FlightDTO> flights;

    public AirplaneDTO() {
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

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public PlaneType getType() {
        return type;
    }

    public void setType(PlaneType type) {
        this.type = type;
    }

    public List<FlightDTO> getFlights() {
        return flights;
    }

    public void setFlights(List<FlightDTO> flights) {
        this.flights = flights;
    }
    
    
    
    
    
}
