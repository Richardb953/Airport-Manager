package cz.muni.airport.database;

import cz.muni.airport.model.Airplane;
import cz.muni.airport.model.Airport;
import cz.muni.airport.model.Flight;
import cz.muni.airport.model.PlaneType;
import cz.muni.airport.model.Steward;
import cz.muni.airport.services.AirplaneService;
import cz.muni.airport.services.AirportService;
import cz.muni.airport.services.FlightService;
import cz.muni.airport.services.StewardService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Calendar;
import java.util.List;

/**
 * Autogenerated file for hibernate connection
 *
 * @author rba on 15.10.2016.
 */
public class Main {

    public static void main(final String[] args) throws Exception {
        // create and configure beans
        ApplicationContext context
                = new ClassPathXmlApplicationContext("WEB-INF/applicationContext.xml");

//         createPlane(context);
//        createSteward(context);
//         createFlight(context);
        createAirport(context);

    }

    private static void createPlane(ApplicationContext context) {
        // retrieve configured instance
        AirplaneService airplaneService = (AirplaneService) context.getBean("airplaneService");

        Airplane airplane = new Airplane();
        airplane.setCapacity(200);
        airplane.setName("Dumbo");
        airplane.setType(PlaneType.AIRLINER);

        Airplane airplane2 = new Airplane();
        airplane2.setCapacity(355);
        airplane2.setName("Carrier");
        airplane2.setType(PlaneType.CARGO);

        airplaneService.saveAirplane(airplane);
        airplaneService.saveAirplane(airplane2);

        System.out.println("ALL AIRPLANES.........................");
        List<Airplane> planes = airplaneService.getAllAirplanes();
        for (Airplane plane : planes) {
            System.out.println(plane.toString());
        }

        String name = "Dumbo";;
        System.out.println("BY NAME: " + name + "..............................");
        System.out.println(airplaneService.getAirplaneByName(name).toString());
        List<Airplane> found = airplaneService.getAirplaneByName(name);
        for (Airplane plane : found) {
            System.out.println("found plane is > " + plane.toString());
        }
    }

    private static void createFlight(ApplicationContext context) {
        FlightService flightService = context.getBean("flightServiceImpl", FlightService.class);

        Flight flight = new Flight();
        flight.setName("LetCislo42");

        Calendar arrival = Calendar.getInstance();
        arrival.set(2016, Calendar.DECEMBER, 20, 10, 22);

        Calendar departure = Calendar.getInstance();
        departure.set(2016, Calendar.DECEMBER, 20, 11, 22);

        flight.setArrival(arrival.getTime());
        flight.setDeparture(departure.getTime());
        flight.setPassagers(50);

        flightService.saveFlight(flight);

        Flight flight1 = flightService.getFlight(1L);
        System.out.println("GET: " + flight1.toString());

        Flight flight2 = new Flight();
        arrival.add(Calendar.HOUR, 2);
        flight2.setArrival(arrival.getTime());
        departure.add(Calendar.HOUR, 5);
        flight2.setDeparture(departure.getTime());
        flight2.setName("leto cislo 26681861866");
        flightService.saveFlight(flight2);

        System.out.println("Vsetky:");
        for (Flight flight3 : flightService.findAllFlights()) {
            System.out.println(flight3.getName());
        }

        System.out.println("let 266..:");

        for (Flight flight3 : flightService.findFlightByName("leto cislo 26681861866")) {
            System.out.println(flight3.getName());
        }
    }

    private static void createSteward(ApplicationContext context) {
        StewardService stewardService = context.getBean("stewardService", StewardService.class);

        Steward steward = new Steward();
        steward.setFirstName("Jano");
        steward.setLastName("Mrkva");

        stewardService.addSteward(steward);
        stewardService.addSteward(steward);

        List<Steward> stewards = stewardService.getStewardByName("Jano", "Mrkva");
        for (Steward s : stewards) {
            System.out.println(s.toString());
        }
    }

    private static void createAirport(ApplicationContext context) {
        AirportService airportService = (AirportService) context.getBean("airportService");

        Airport a = new Airport();
        a.setName("Letisti V. Havla");
        a.setCity("Brno");
        a.setCountry("CZ");

        airportService.addAirport(a);

        List<Airport> ports = airportService.getAllAirports();
        for (Airport plane : ports) {
            System.out.println(plane.toString());
        }
    }
}
