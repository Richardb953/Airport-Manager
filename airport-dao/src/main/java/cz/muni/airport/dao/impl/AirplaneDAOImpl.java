package cz.muni.airport.dao.impl;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import cz.muni.airport.dao.AirplaneDAO;
import cz.muni.airport.model.Airplane;

/**
 * Implementation of AirplaneDAO. This Data Access Object provides access 
 * to Airplane antries ant Airplane related data.
 * 
 * @author Karolína Božková, github name: Kayeeec 
 * @see AirplaneDAO documentation.
 */
@Transactional
@Repository("airplaneDAO")
public class AirplaneDAOImpl extends HibernateDaoSupport implements AirplaneDAO {
//
    @Autowired
    private SessionFactory sessionFactory;


    @Override
    public List<Airplane> getAllAirplanes() {
        return (List<Airplane>) getHibernateTemplate().find("from Airplane ");
    }
    
    @Override
    public Airplane getAirplaneById(Long id) {
        if(id == null) throw new IllegalArgumentException("id is null");
        return getHibernateTemplate().get(Airplane.class, id);
    }
    
    @Override
    public List<Airplane> getAirplaneByName(String name) {
        if(name == null) throw new IllegalArgumentException("name is null");
        return (List<Airplane>) getHibernateTemplate().find("from Airplane where name = '" +name+"'");
    }

    @Override
    public Airplane addAirplane(Airplane airplane) {
        getHibernateTemplate().save(airplane);
        return airplane;
    }

    @Override
    public Airplane updateAirplane(Airplane airplane) {
        getHibernateTemplate().update(airplane);
        return airplane;
    }

    @Override
    public void removeAirplane(Airplane airplane) {
        getHibernateTemplate().delete(airplane);
    }
    
}
