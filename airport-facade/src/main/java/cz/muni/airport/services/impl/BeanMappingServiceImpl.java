package cz.muni.airport.services.impl;

import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.inject.Inject;

import cz.muni.airport.services.BeanMappingService;

/**
 * Created by Richard Bariny on 22.11.2016.
 *
 * @author Richard Bariny, github name:Richardb953
 */

@Service
class BeanMappingServiceImpl implements BeanMappingService{
    private final Mapper dozer;

    @Inject
    public BeanMappingServiceImpl(Mapper dozer) {
        this.dozer = dozer;
    }

    public  <T> List<T> mapTo(Collection<?> objects, Class<T> mapToClass) {
        List<T> mappedCollection = new ArrayList<>();
        for (Object object : objects) {
            mappedCollection.add(dozer.map(object, mapToClass));
        }
        return mappedCollection;
    }

    public  <T> T mapTo(Object u, Class<T> mapToClass)
    {
        return dozer.map(u,mapToClass);
    }

    public Mapper getMapper(){
        return dozer;
    }
}
