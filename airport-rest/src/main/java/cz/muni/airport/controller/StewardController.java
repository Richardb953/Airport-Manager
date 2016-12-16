package cz.muni.airport.controller;

import cz.muni.airport.dto.StewardDTO;
import cz.muni.airport.facadeApi.StewardFacade;
import cz.muni.airport.ApiUris;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping(ApiUris.ROOT_URI_STEWARD)
public class StewardController {

    @Autowired
    private StewardFacade stewardFacade;

    @CrossOrigin(origins = "*")
    @RequestMapping(method = RequestMethod.GET)
    public Collection<StewardDTO> getAll() {
        return stewardFacade.getAllStewards();
    }

    @CrossOrigin(origins = "*")
    @RequestMapping(value = "/name/{name}", method = RequestMethod.GET)
    public Collection<StewardDTO> getByName(@PathVariable String name) {
        return stewardFacade.getStewardByName(name, name);
    }

    @CrossOrigin(origins = "*")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public StewardDTO getSteward(@PathVariable long id) {
        return stewardFacade.getSteward(id);
    }

}
