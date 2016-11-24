package cz.muni.airport.facadeApi;

import cz.muni.airport.dto.StewardDTO;
import java.util.List;

/**
 *
 * @author Andrea Navratilova, github name: andrea-n
 */
public interface StewardFacade {
    
    /**
     * Create new steward
     * @param stewardDTO steward DTO to create
     * @return created steward
     */
    StewardDTO createSteward(StewardDTO stewardDTO);
    
    /**
     * Update steward
     * @param stewardDTO steward DTO to update
     * @return updated steward
     */
    StewardDTO updateSteward(StewardDTO stewardDTO);

    /**
     * Delete steward
     * @param stewardDTO steward DTO to remove
     */
    void deleteSteward(StewardDTO stewardDTO);
    
    /**
     * Get steward by id
     * @param id steward id
     * @return steward with given id
     */
    StewardDTO getSteward(Long id);
    
    /**
     * Get steward by first and last name
     * @param firstName steward first name
     * @param lastName steward last name
     * @return steward with given first and last name
     */
    List<StewardDTO> getStewardByName(String firstName, String lastName);

    /**
     * Get list of all stewards
     * @return list of all stewards
     */
    List<StewardDTO> getAllStewards();
}
