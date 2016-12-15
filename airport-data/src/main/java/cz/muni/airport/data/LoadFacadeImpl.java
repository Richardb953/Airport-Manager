package cz.muni.airport.data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;

import cz.muni.airport.model.Steward;
import cz.muni.airport.services.StewardService;

/**
 * Created by Richard Bariny on 10.12.2016.
 *
 * Load Data to the database at the application start
 * @author Richard Bariny, github name:Richardb953
 */
@Component
@Transactional
public class LoadFacadeImpl implements LoadFacade {
    @Autowired
    StewardService stewardService;

    @Override
    public void loadData() throws IOException {
        Steward steward = new Steward();
        steward.setLastName("a");
        steward.setFirstName("v");
        stewardService.addSteward(steward);
    }
}