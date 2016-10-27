package cz.muni.airport.dao;

import cz.muni.airport.model.Steward;
import java.util.List;

/**
 *
 * DAO for Steward entity
 * @author Andrea Navratilova
 */
public interface StewardDao {

	/**
	 *
	 * @param steward the steward object to add
	 * @return added steward object
	 */
	public Steward addSteward(Steward steward);

	/**
	 *
	 * @param steward the steward object to remove
	 */
	public void removeSteward(Steward steward);

	/**
	 *
	 * @param steward the steward object to update
	 * @return updated steward object
	 */
	public Steward updateSteward(Steward steward);

	/**
	 *
	 * @param id the id of steward object
	 * @return steward object by id
	 */
	public Steward getSteward(Long id);

	/**
	 *
	 * @return list of all stewards
	 */
	public List<Steward> getAllStewards();

	/**
	 *
	 * @param firstName the first name of steward
	 * @param lastName the last name of steward
	 * @return list of steward objects with specified first and last name
	 */
	public List<Steward> getStewardByName(String firstName, String lastName);
}
