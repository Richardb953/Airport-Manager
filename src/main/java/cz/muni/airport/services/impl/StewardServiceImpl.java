package cz.muni.airport.services.impl;

import cz.muni.airport.dao.StewardDao;
import cz.muni.airport.model.Steward;
import cz.muni.airport.services.StewardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Andrea Navr�tilov�
 */
@Service("stewardService")
public class StewardServiceImpl implements StewardService {
    @Autowired
    StewardDao stewardDao;

    public void setStewardDao(StewardDao stewardDao){
        this.stewardDao = stewardDao;
    }

    @Override public void save(Steward steward) {
        stewardDao.addSteward(steward);
    }
}
