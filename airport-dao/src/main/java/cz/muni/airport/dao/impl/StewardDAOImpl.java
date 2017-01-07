package cz.muni.airport.dao.impl;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;

import cz.muni.airport.dao.StewardDAO;
import cz.muni.airport.model.Steward;

/**
 *
 * @author Andrea Navratilova, github name: andrea-n
 */

@Repository
@Transactional
public class StewardDAOImpl implements StewardDAO {
	@PersistenceContext(name= "stewardUnit", type = PersistenceContextType.TRANSACTION)
	private EntityManager entityManager;

    @Override
	public Steward addSteward(Steward steward) {
		entityManager.persist(steward);
		return steward;
	}

	@Override
	public void removeSteward(Steward steward) {
		entityManager.remove(steward);
	}

	@Override
	public Steward updateSteward(Steward steward) {
		entityManager.merge(steward);
		return steward;
	}

	@Override
	public Steward getStewardById(Long stewardId) {
		return entityManager.find(Steward.class, stewardId);
	}

	@Override
	public List<Steward> getAllStewards() {
		return entityManager.createQuery("from Steward", Steward.class).getResultList();
	}

	@Override
	public List<Steward> getStewardByName(String firstName, String lastName) {
		if(firstName == null || lastName == null) throw new IllegalArgumentException("Firstname and lastname params can't be null");
		return entityManager.createQuery("from Steward where firstName = '" + firstName +"' and lastName = '" + lastName + "'", Steward.class).getResultList();
	}
}
