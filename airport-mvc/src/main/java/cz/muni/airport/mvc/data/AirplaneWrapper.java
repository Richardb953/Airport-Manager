package cz.muni.airport.mvc.data;

import java.util.ArrayList;

/**
 * Created by Richard Bariny on 15.1.2017.
 *
 * @author Richard Bariny, github name:Richardb953
 */
public class AirplaneWrapper {
    private ArrayList<AirplaneSelection> clientList;

    public ArrayList<AirplaneSelection> getClientList() {
        return clientList;
    }
    public void setClientList(ArrayList<AirplaneSelection> clients) {
        this.clientList = clients;
    }
}
