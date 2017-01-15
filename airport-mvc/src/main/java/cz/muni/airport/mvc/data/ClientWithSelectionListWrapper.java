package cz.muni.airport.mvc.data;

import java.util.ArrayList;

/**
 * Created by Richard Bariny on 15.1.2017.
 *
 * @author Richard Bariny, github name:Richardb953
 */
public class ClientWithSelectionListWrapper {

    private ArrayList<StewardSelection> clientList;

    public ArrayList<StewardSelection> getClientList() {
        return clientList;
    }
    public void setClientList(ArrayList<StewardSelection> clients) {
        this.clientList = clients;
    }
}
