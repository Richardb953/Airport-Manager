package cz.muni.airport.data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import java.io.IOException;

import javax.annotation.PostConstruct;

import cz.muni.airport.config.ServiceConfiguration;

/**
 * Created by Richard Bariny on 10.12.2016.
 *
 * @author Richard Bariny, github name:Richardb953
 */

@Configuration
@Import(ServiceConfiguration.class)
@ComponentScan(basePackageClasses = {LoadFacadeImpl.class})
public class DataConfig {

    @Autowired
    LoadFacade loadFacade;

    @PostConstruct
    public void dataLoading() throws IOException {
        loadFacade.loadData();
    }
}
