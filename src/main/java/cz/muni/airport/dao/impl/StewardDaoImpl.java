package cz.muni.airport.dao.impl;

import cz.muni.airport.dao.StewardDao;
import cz.muni.airport.database.Connection;
import cz.muni.airport.model.Steward;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 *
 * @author Andrea Navr�tilov�
 */
@Repository("stewardDAO")
public class StewardDaoImpl extends Connection implements StewardDao {

	@Override
	public Steward addSteward(Steward steward) {
		HibernateTemplate session;
		session = getHibernateTemplate();
		session.setCheckWriteOperations(false);
		session.save(steward);
		return steward;
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
