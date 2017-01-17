package cz.muni.airport.data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import cz.muni.airport.model.Airplane;
import cz.muni.airport.model.Airport;
import cz.muni.airport.model.Flight;
import cz.muni.airport.model.Steward;
import cz.muni.airport.model.enums.FlightState;
import cz.muni.airport.model.enums.PlaneType;
import cz.muni.airport.services.AirplaneService;
import cz.muni.airport.services.AirportService;
import cz.muni.airport.services.FlightService;
import cz.muni.airport.services.StewardService;

/**
 * Created by Richard Bariny on 10.12.2016.
 *
 * Load Data to the database at the application start
 * @author Richard Bariny, github name:Richardb953
 */


@Component
@Transactional
public class LoadFacadeImpl implements LoadFacade {

    @Autowired
    AirplaneService airplaneService;

    @Autowired
    FlightService flightService;

    @Autowired
    AirportService airportService;

    @Autowired
    StewardService stewardService;


    @Override
    public void loadData() throws IOException {
        Airplane airplane = createAirplane("BOEING-474", PlaneType.AIRLINER, 110);
        Airplane airplane1 = createAirplane("BOEING-989", PlaneType.AIRLINER, 500);
        Airplane airplane2 = createAirplane("Helicopter", PlaneType.HELICOPTER, 10);
        Airplane airplane3 = createAirplane("MAC-555", PlaneType.TWIN_PISTON, 2);
        Airplane airplane4 = createAirplane("CARGO-96", PlaneType.CARGO, 12);

        Steward steward = createSteward("Anna", "Hlucha", new ArrayList<>());
        Steward steward1 = createSteward("Zuzka", "Presibana", new ArrayList<>());
        Steward steward2 = createSteward("Mirko", "Supko", new ArrayList<>());
        Steward steward3 = createSteward("Lojzo", "Mrtvy", new ArrayList<>());
        Steward steward4 = createSteward("Jana", "Vyhladavana", new ArrayList<>());
        Steward steward5 = createSteward("Richard", "Mojzo", new ArrayList<>());
        Steward steward6 = createSteward("Lucia", "Meliskova", new ArrayList<>());
        Steward steward7 = createSteward("Maria", "Polna", new ArrayList<>());
        Steward steward8 = createSteward("Janko", "Hrasko", new ArrayList<>());
        Steward steward9 = createSteward("Peter", "Marek", new ArrayList<>());
        Steward steward10 = createSteward("Marek", "Petrovic", new ArrayList<>());
        Steward steward11 = createSteward("Karolina", "Svetoznama", new ArrayList<>());

        Airport airport = createAirport("Dublin", "DBL", "Irsko", "Dublin");
        Airport airport1 = createAirport("Bratislava - Stefanik", "BRA", "Slovensko", "Bratislava");
        Airport airport2 = createAirport("Kosicke hlavne", "KOS", "Slovensko", "Kosice");
        Airport airport3 = createAirport("Londyn Heatway", "LON", "Anglicko", "Londyn");
        Airport airport4 = createAirport("Berlin neuer", "BER", "Nemecko", "Berlin");
        Airport airport5 = createAirport("Vieden BaufHaufen", "VIE", "Rakusko", "Vieden");
        Airport airport6 = createAirport("New York city", "NYC", "USA", "New York");
        Airport airport7 = createAirport("Rio", "RIO", "Brazilia", "Rio");
//        Airport airport = createAirport("Dublin", "Dublin-888-aa-87", "Irsko", "Dublin");
//        Airport airport1 = createAirport("Bratislava - Stefanik", "Bratislava-8sf-a-2001", "Slovensko", "Bratislava");
//        Airport airport2 = createAirport("Kosicke hlavne", "Kosice-7k8-KK-17", "Slovensko", "Kosice");
//        Airport airport3 = createAirport("Londyn Heatway", "London-ENG-HH-87", "Anglicko", "Londyn");
//        Airport airport4 = createAirport("Berlin neuer", "Berlin-NN8-7a-82", "Nemecko", "Berlin");
//        Airport airport5 = createAirport("Vieden BaufHaufen", "Vien-VV11-b-81", "Rakusko", "Vieden");
//        Airport airport6 = createAirport("New York city", "NC-8cc-New-2017", "USA", "New York");
//        Airport airport7 = createAirport("Rio", "DeJanEro-188-SA-87", "Brazilia", "Rio");

        List<Steward> stewardList = new ArrayList<>();
        stewardList.add(steward);
        stewardList.add(steward1);
        stewardList.add(steward2);
        stewardList.add(steward3);

        List<Steward> stewardList1 = new ArrayList<>();
        stewardList.add(steward4);
        stewardList.add(steward5);
        stewardList.add(steward6);
        stewardList.add(steward7);

        List<Steward> stewardList2 = new ArrayList<>();
        stewardList.add(steward8);
        stewardList.add(steward9);
        stewardList.add(steward10);
        stewardList.add(steward10);

        List<Steward> stewardList3 = new ArrayList<>();
        stewardList.add(steward);
        stewardList.add(steward5);
        stewardList.add(steward8);
        stewardList.add(steward10);

        Flight flight = createFlight(toDate(2017, 11, 31, 22, 0),toDate(2018, 0, 1, 1, 10), "Vianocny utocny Let Nemecko", airport, airport3, airplane3, 1, stewardList, FlightState.ACCEPTED);
        Flight flight1 = createFlight(toDate(2017, 1, 1, 10, 20),toDate(2017, 1, 2, 12, 30), "Cestovny Let Vieden", airport1, airport5, airplane, 69, stewardList1, FlightState.IN_VERIFY);
        Flight flight2 = createFlight(toDate(2017, 1, 1, 1, 30),toDate(2017, 1, 1, 23, 10), "normalny Let USA", airport4, airport6, airplane4, 50, stewardList2, FlightState.DECLINED);
        Flight flight3 = createFlight(toDate(2017, 1, 5, 3, 50),toDate(2017, 1, 7, 7, 45), "dialkovy Let Rio", airport2, airport7, airplane1, 478, stewardList3, FlightState.OPEN);
        Flight flight4 = createFlight(toDate(2017, 1, 5, 5, 55),toDate(2017, 1, 5, 6, 55), "kratky Let Slovensko", airport1, airport2, airplane2, 8, null, FlightState.ACCEPTED);

    }
    private static Date toDate(int year, int month, int day, int hours, int minutes) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month, day, hours, minutes);
        return calendar.getTime();
    }


    private Airplane createAirplane(String name, PlaneType planeType, int capacity){
        Airplane airplane = new Airplane();
        airplane.setName(name);
        airplane.setType(planeType);
        airplane.setCapacity(capacity);
        return airplaneService.saveAirplane(airplane);
    }

    private Steward createSteward(String name, String last, ArrayList flights){
        Steward steward = new Steward();
        steward.setFirstName(name);
        steward.setLastName(last);
        if(flights == null){
            flights = new ArrayList();
        }
        steward.setFlights(flights);
        return stewardService.addSteward(steward);
    }

    private Airport createAirport(String name, String iata, String country, String city){
        Airport airport = new Airport();
        airport.setName(name);
        airport.setIata(iata);
        airport.setCountry(country);
        airport.setCity(city);
        return airportService.saveAirport(airport);
    }

    private Flight createFlight(Date arrival, Date departure, String name, Airport source, Airport destin, Airplane airplane, int passCount, List<Steward> stewardList, FlightState state){
        Flight flight = new Flight();
        flight.setArrival(arrival);
        flight.setDeparture(departure);
        flight.setName(name);
        flight.setSourcePort(source);
        flight.setDestinationPort(destin);
        flight.setAirplane(airplane);
        flight.setPassagers(passCount);
        flight.setStewards(stewardList);
        flight.setFlightState(state);
        return flightService.saveFlight(flight);
    }
}
