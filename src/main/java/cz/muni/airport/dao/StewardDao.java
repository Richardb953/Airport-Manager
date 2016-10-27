package cz.muni.airport.dao;

import cz.muni.airport.model.Steward;
import java.util.List;

/**
 *
 * @author Andrea Navr�tilov�
 */
public interface StewardDao {

	public Steward addSteward(Steward steward);
	public void removeSteward(Steward steward);
	public Steward updateSteward(Steward steward);
	public Steward getSteward(Long id);
	public List<Steward> getAllStewards();
	public List<Steward> getStewardsByFirstName(String firstName);
	public List<Steward> getStewardsByLastName(String lastName);
	public List<Steward> getStewardsByName(String firstName, String lastName);
}
