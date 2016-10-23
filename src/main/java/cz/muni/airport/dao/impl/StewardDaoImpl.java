package cz.muni.airport.dao.impl;

import cz.muni.airport.dao.StewardDao;
import cz.muni.airport.model.Steward;
import java.util.List;

/**
 *
 * @author Andrea Navrátilová
 */
public class StewardDaoImpl implements StewardDao {

	@Override
	public Steward addSteward(Steward steward) {
		throw new UnsupportedOperationException("Not supported yet."); 
	}

	@Override
	public void removeSteward(Steward steward) {
		throw new UnsupportedOperationException("Not supported yet.");
	}

	@Override
	public Steward updateSteward(Steward steward) {
		throw new UnsupportedOperationException("Not supported yet.");
	}

	@Override
	public Steward getSteward(Long id) {
		throw new UnsupportedOperationException("Not supported yet.");
	}

	@Override
	public List<Steward> getAllStewards() {
		throw new UnsupportedOperationException("Not supported yet.");
	}

	@Override
	public List<Steward> getStewardsByFirstName(String firstName) {
		throw new UnsupportedOperationException("Not supported yet.");
	}

	@Override
	public List<Steward> getStewardsByLastName(String lastName) {
		throw new UnsupportedOperationException("Not supported yet.");
	}

	@Override
	public List<Steward> getStewardsByName(String firstName, String lastName) {
		throw new UnsupportedOperationException("Not supported yet.");
	}
}
