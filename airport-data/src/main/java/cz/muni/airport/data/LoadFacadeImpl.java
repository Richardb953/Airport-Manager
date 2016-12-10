package cz.muni.airport.data;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;

/**
 * Created by Richard Bariny on 10.12.2016.
 *
 * Load Data to the database at the application start
 * @author Richard Bariny, github name:Richardb953
 */
@Component
@Transactional
public class LoadFacadeImpl implements LoadFacade {
    @Override
    public void loadData() throws IOException {

    }
}
