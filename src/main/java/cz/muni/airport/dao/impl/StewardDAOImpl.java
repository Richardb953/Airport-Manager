package cz.muni.airport.dao.impl;

import cz.muni.airport.dao.StewardDAO;
import cz.muni.airport.database.Connection;
import cz.muni.airport.model.Steward;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 *
 * @author Andrea Navratilova
 */
@Repository("stewardDAO")
public class StewardDAOImpl extends Connection implements StewardDAO {

	@Override
	public Steward addSteward(Steward steward) {
		getHibernateTemplate().save(steward);
		return steward;
	}

	@Override
	public void removeSteward(Steward steward) {
		getHibernateTemplate().delete(steward);
	}

	@Override
	public Steward updateSteward(Steward steward) {
		getHibernateTemplate().update(steward);
		return steward;
	}

	@Override
	public Steward getSteward(Long stewardId) {
		return getHibernateTemplate().get(Steward.class, stewardId);
	}

	@Override
	public List<Steward> getAllStewards() {
		return (List<Steward>) getHibernateTemplate().find("from Steward");
	}

	@Override
	public List<Steward> getStewardByName(String firstName, String lastName) {
		return (List<Steward>) getHibernateTemplate().find("from Steward where firstName = '" + firstName +"' and lastName = '" + lastName + "'");
	}
}
