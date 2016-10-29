package cz.muni.airport.services.impl;

import cz.muni.airport.dao.StewardDAO;
import cz.muni.airport.model.Steward;
import cz.muni.airport.services.StewardService;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Andrea Navratilova
 */
@Service("stewardService")
public class StewardServiceImpl implements StewardService {
    @Autowired
    private StewardDAO stewardDAO;

    @Override
	@Transactional
	public Steward addSteward(Steward steward) {
        return stewardDAO.addSteward(steward);
    }

	@Override
	@Transactional
	public void removeSteward(Steward steward) {
        stewardDAO.removeSteward(steward);
    }
	
	@Override
	@Transactional
	public Steward updateSteward(Steward steward) {
        return stewardDAO.updateSteward(steward);
    }
	
	@Override
	public Steward getSteward(Long stewardId) {
        return stewardDAO.getSteward(stewardId);
    }
	
	@Override
	public List<Steward> getAllStewards() {
        return stewardDAO.getAllStewards();
    }
	
	@Override
	public List<Steward> getStewardByName(String firstName, String lastName) {
        return stewardDAO.getStewardByName(firstName, lastName);
    }
}
