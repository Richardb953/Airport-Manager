package cz.muni.airport.services;

import org.dozer.Mapper;

import java.util.Collection;
import java.util.List;

/**
 * Created by Richard Bariny on 22.11.2016.
 *
 * @author Richard Bariny, github name:Richardb953
 */
public interface BeanMappingService {
    public  <T> List<T> mapTo(Collection<?> objects, Class<T> mapToClass);

    public  <T> T mapTo(Object u, Class<T> mapToClass);
    public Mapper getMapper();
}
