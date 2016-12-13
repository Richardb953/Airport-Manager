package cz.muni.airport.facade.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import cz.muni.airport.dto.FlightDTO;
import cz.muni.airport.dto.StewardDTO;
import cz.muni.airport.facadeApi.StewardFacade;
import cz.muni.airport.model.Flight;
import cz.muni.airport.model.Steward;
import cz.muni.airport.services.BeanMappingService;
import cz.muni.airport.services.StewardService;

/**
 * Steward facade implementation
 * 
 * @author Andrea Navratilova, github name: andrea-n
 */

@Service
@Transactional
class StewardFacadeImpl implements StewardFacade {
    @Autowired
    private StewardService stewardService;

    @Autowired
    private BeanMappingService beanMappingService;
    
    @Override
    public StewardDTO createSteward(StewardDTO stewardDTO) {
        Steward steward = beanMappingService.mapTo(stewardDTO, Steward.class);
        Steward createdSteward = stewardService.addSteward(steward);
        return beanMappingService.mapTo(createdSteward, StewardDTO.class);
    }
    

    @Override
    public StewardDTO updateSteward(StewardDTO stewardDTO) {
        Steward steward = beanMappingService.mapTo(stewardDTO, Steward.class);
        Steward updatedSteward = stewardService.updateSteward(steward);
        return beanMappingService.mapTo(updatedSteward, StewardDTO.class);
    }

    @Override
    public void deleteSteward(StewardDTO stewardDTO) {
        Steward steward = beanMappingService.mapTo(stewardDTO, Steward.class);
        stewardService.removeSteward(steward);
    }
    
    @Override
    public StewardDTO getSteward(Long id) {
        Steward steward = stewardService.getSteward(id);
        return (steward == null) ? null : beanMappingService.mapTo(steward, StewardDTO.class);
    }
    
    @Override
    public List<StewardDTO> getStewardByName(String firstName, String lastName) {
        List<Steward> stewards = stewardService.getStewardByName(firstName, lastName);
        return (stewards == null) ? null : beanMappingService.mapTo(stewards, StewardDTO.class);
    }

    @Override
    public List<StewardDTO> getAllStewards() {
        return beanMappingService.mapTo(stewardService.getAllStewards(), StewardDTO.class);
    }
	
	@Override
    public List<StewardDTO> getAvailableStewards(FlightDTO flightDTO) {
		Flight flight = beanMappingService.mapTo(flightDTO, Flight.class);
		List<Steward> stewards = stewardService.getAvailableStewards(flight);
		return beanMappingService.mapTo(stewards, StewardDTO.class);
    }
}
