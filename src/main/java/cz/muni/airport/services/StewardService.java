package cz.muni.airport.services;

import cz.muni.airport.model.Steward;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
 *
 * @author Andrea Navratilova
 */

@Transactional
@Service
public interface StewardService {

    void save(Steward steward);
	
}
