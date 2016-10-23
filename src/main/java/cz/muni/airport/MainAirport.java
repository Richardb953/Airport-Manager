/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.airport;

import cz.muni.airport.model.Airport;

/**
 *
 * @author Jirka
 */
public class MainAirport {

//    private static EntityManagerFactory emf;
    public static void main(String[] args) {

//        new AnnotationConfigApplicationContext(InMemoryDatabaseSpring.class);
//        emf = Persistence.createEntityManagerFactory("default");
        Airport a = new Airport();
        a.setId(new Long(1));
        a.setCity("Brno");
        a.setName("Letištì Václava Havla");
        a.setCountry("cs", "CZ");

        System.out.println(a);

    }

}
