package cz.muni.airport.facade.impl;

import cz.muni.airport.dto.AirplaneDTO;
import cz.muni.airport.facadeApi.AirplaneFacade;
import cz.muni.airport.model.Airplane;
import cz.muni.airport.services.AirplaneService;
import cz.muni.airport.services.BeanMappingService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author Karolína Božková, github name: Kayeeec
 */
public class AirplaneFacadeImpl implements AirplaneFacade{
    @Autowired
    private AirplaneService airplaneService;

    @Autowired
    private BeanMappingService beanMappingService;

    @Override
    public AirplaneDTO createAirplane(AirplaneDTO airplaneDTO) {
        Airplane airplane = beanMappingService.mapTo(airplaneDTO, Airplane.class);
        Airplane created = airplaneService.saveAirplane(airplane);
        return beanMappingService.mapTo(created, AirplaneDTO.class);
    }
    

    @Override
    public AirplaneDTO updateAirplane(AirplaneDTO airplaneDTO) {
        Airplane airplane = beanMappingService.mapTo(airplaneDTO, Airplane.class);
        Airplane updated = airplaneService.updateAirplane(airplane);
        return beanMappingService.mapTo(updated, AirplaneDTO.class);
    }

    @Override
    public void deleteAirplane(AirplaneDTO airplaneDTO) {
        Airplane airplane = beanMappingService.mapTo(airplaneDTO, Airplane.class);
        airplaneService.removeAirplane(airplane);
    }

    @Override
    public List<AirplaneDTO> getAllAirplanes() {
        return beanMappingService.mapTo(airplaneService.getAllAirplanes(), AirplaneDTO.class);
    }

    @Override
    public List<AirplaneDTO> getAirplaneByName(String name) {
        List<Airplane> airplanes = airplaneService.getAirplaneByName(name);
        return (airplanes == null) ? null : beanMappingService.mapTo(airplanes, AirplaneDTO.class);
    }

    @Override
    public AirplaneDTO getAirplaneById(Long id) {
        Airplane airplane = airplaneService.getAirplaneById(id);
        return (airplane == null) ? null : beanMappingService.mapTo(airplane, AirplaneDTO.class);
    }
    
}
