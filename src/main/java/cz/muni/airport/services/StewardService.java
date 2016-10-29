package cz.muni.airport.services;

import cz.muni.airport.model.Steward;
import java.util.List;

/**
 *
 * @author Andrea Navratilova
 */
public interface StewardService {

	public Steward addSteward(Steward steward);
	public void removeSteward(Steward steward);
	public Steward updateSteward(Steward steward);
	public Steward getSteward(Long id);
	public List<Steward> getAllStewards();
	public List<Steward> getStewardByName(String firstName, String lastName);
}
