package cz.muni.airport.services;

import cz.muni.airport.model.Flight;
import cz.muni.airport.model.Steward;
import java.util.List;

/**
 *
 * @author Andrea Navratilova
 */
public interface StewardService {
	
	/**
     * Add new steward
     * @param stewardDTO steward DTO to create
     * @return created steward
     */
	public Steward addSteward(Steward steward);
	
	/**
     * Remove steward
     * @param stewardDTO steward DTO to remove
     */
	public void removeSteward(Steward steward);
	
	/**
     * Update steward
     * @param stewardDTO steward DTO to update
     * @return updated steward
     */
	public Steward updateSteward(Steward steward);

	/**
     * Get steward by id
     * @param id steward id
     * @return steward with given id
     */
	public Steward getSteward(Long id);

	/**
     * Get list of all stewards
     * @return list of all stewards
     */
	public List<Steward> getAllStewards();

	/**
     * Get steward by first and last name
     * @param firstName steward first name
     * @param lastName steward last name
     * @return steward with given first and last name
     */
	public List<Steward> getStewardByName(String firstName, String lastName);

	/**
	 * Check if steward is available for given flight
	 * Steward is available if the flight can be inserted in his flight list
	 * @param flight to check - departure, arrival, source port and destination port are required
	 * @return boolean if available
	 */
	public boolean isAvailable(Steward steward, Flight flight);

	/**
	 * Get list of all available stewards for given flight
	 * @param flightDTO the flight to find available stewards
	 * @return available stewards for given flight
	 */
    public List<Steward> getAvailableStewards(Flight flight);
}
