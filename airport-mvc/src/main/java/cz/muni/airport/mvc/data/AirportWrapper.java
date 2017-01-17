package cz.muni.airport.mvc.data;

import java.util.ArrayList;

/**
 * Created by Richard Bariny on 15.1.2017.
 *
 * @author Richard Bariny, github name:Richardb953
 */
public class AirportWrapper {
    private ArrayList<AirportSelection> clientList;

    public ArrayList<AirportSelection> getClientList() {
        return clientList;
    }
    public void setClientList(ArrayList<AirportSelection> clients) {
        this.clientList = clients;
    }
}
