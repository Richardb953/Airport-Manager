package cz.muni.airport.data;

import java.io.IOException;

/**
 * Created by Richard Bariny on 10.12.2016.
 * Load Data to the database at the application start
 *
 * @author Richard Bariny, github name:Richardb953
 */
public interface LoadFacade {

    /**
     * Load Data to database
     * loadData @throws IOException
     */
    void loadData() throws IOException;
}
