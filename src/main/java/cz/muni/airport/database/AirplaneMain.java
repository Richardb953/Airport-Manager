/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.airport.database;

import cz.muni.airport.dao.AirplaneDAO;
import cz.muni.airport.dao.impl.AirplaneDAOImpl;
import cz.muni.airport.model.Airplane;
import cz.muni.airport.model.PlaneType;
import cz.muni.airport.services.AirplaneService;
import cz.muni.airport.services.impl.AirplaneServiceImpl;
import java.util.List;
import javax.transaction.TransactionManager;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author Karolína Božková
 */
public class AirplaneMain {
    public static void main(String[] args) {
        // create and configure beans
        ApplicationContext context =
            new ClassPathXmlApplicationContext("WEB-INF/applicationContext.xml");

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
       for(Airplane plane : planes){
           System.out.println(plane.toString());
       }
       
       String name = "Dumbo";
       System.out.println("BY NAME: "+name+"..............................");
       System.out.println(airplaneService.getAirplaneByName(name).toString());
       List<Airplane> found = airplaneService.getAirplaneByName(name);
       for(Airplane plane : found){
           System.out.println("found plane is > " + plane.toString());
       }
       
    }
       
              
    
}
