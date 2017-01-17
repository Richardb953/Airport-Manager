package cz.muni.airport.mvc.data;

/**
 * Created by Richard Bariny on 15.1.2017.
 *
 * @author Richard Bariny, github name:Richardb953
 */
public class StewardSelection {
    private Boolean selected;
    private String stewardID;
    private String firstName;
    private String secondName;


    public StewardSelection() {
    }

    public StewardSelection(Boolean selected, String stewardID, String firstName, String secondName) {
        super();
        this.selected = selected;
        this.firstName = firstName;
        this.secondName = secondName;
        this.stewardID = stewardID;

    }

    public Boolean getSelected() {
        return selected;
    }

    public void setSelected(Boolean selected) {
        this.selected = selected;
    }

    public String getStewardID() {
        return stewardID;
    }

    public void setStewardID(String stewardID) {
        this.stewardID = stewardID;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }
}