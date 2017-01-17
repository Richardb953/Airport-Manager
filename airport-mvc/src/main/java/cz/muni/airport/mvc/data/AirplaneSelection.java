package cz.muni.airport.mvc.data;

/**
 * Created by Richard Bariny on 15.1.2017.
 *
 * @author Richard Bariny, github name:Richardb953
 */
public class AirplaneSelection {

    private Boolean selected;
    private String airplaneID;
    private String name;
    private String capacity;
    private String type;

    public AirplaneSelection(Boolean selected, String airplaneID, String name, String capacity, String type) {
        this.selected = selected;
        this.airplaneID = airplaneID;
        this.name = name;
        this.capacity = capacity;
        this.type = type;
    }

    public AirplaneSelection() {
    }

    public Boolean getSelected() {
        return selected;
    }

    public void setSelected(Boolean selected) {
        this.selected = selected;
    }

    public String getAirplaneID() {
        return airplaneID;
    }

    public void setAirplaneID(String airplaneID) {
        this.airplaneID = airplaneID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCapacity() {
        return capacity;
    }

    public void setCapacity(String capacity) {
        this.capacity = capacity;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
