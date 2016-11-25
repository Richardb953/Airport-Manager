package cz.muni.airport.facade.impl;

import cz.muni.airport.dto.StewardDTO;
import cz.muni.airport.facadeApi.StewardFacade;
import cz.muni.airport.model.Steward;
import cz.muni.airport.services.StewardService;
import cz.muni.airport.services.BeanMappingService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Steward facade implementation
 * 
 * @author Andrea Navratilova, github name: andrea-n
 */
public class StewardFacadeImpl implements StewardFacade {
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
}