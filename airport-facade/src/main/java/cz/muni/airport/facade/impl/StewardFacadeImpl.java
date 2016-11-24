package cz.muni.airport.facade.impl;

import cz.muni.airport.services.StewardService;
import cz.muni.airport.services.BeanMappingService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author Andrea Navratilova, github name: andrea-n
 */
public class StewardFacadeImpl {
    @Autowired
    private StewardService stewardService;

    @Autowired
    private BeanMappingService beanMappingService;
    
    
}
