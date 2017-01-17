package cz.muni.airport.dao;

import org.springframework.stereotype.Repository;

import java.util.List;

import cz.muni.airport.model.Steward;

/**
 *
 * DAO for Steward entity
 * @author Andrea Navratilova, github name: andrea-n
 */
@Repository
public interface StewardDAO {

	/**
	 * Create Steward
	 * @param steward the steward object to add
	 * @return added steward object
         * @throws IllegalArgumentException if steward is null
	 */
	public Steward addSteward(Steward steward) throws IllegalArgumentException;

	/**
	 * Remove Steward
	 * @param steward the steward object to remove
         * @throws IllegalArgumentException if steward is null
	 */
	public void removeSteward(Steward steward) throws IllegalArgumentException;

	/**
	 * Update Steward
	 * @param steward the steward object to update
	 * @return updated steward object
         * @throws IllegalArgumentException if steward is null
	 */
	public Steward updateSteward(Steward steward) throws IllegalArgumentException;

	/**
	 * Find Steward by id
	 * @param id the id of steward object
	 * @return steward object by id
         * @throws IllegalArgumentException if steward is null
	 */
	public Steward getStewardById(Long id) throws IllegalArgumentException;

	/**
	 * Find all Stewards
	 * @return list of all stewards
         * @throws IllegalArgumentException if steward is null
	 */
	public List<Steward> getAllStewards();

	/**
	 * Find Steward by name
	 * @param firstName the first name of steward
	 * @param lastName the last name of steward
	 * @return list of steward objects with specified first and last name
         * @throws IllegalArgumentException if steward is null
	 */
	public List<Steward> getStewardByName(String firstName, String lastName) throws IllegalArgumentException;
}
