package cz.muni.airport.database;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;

/**
 * @author rba on 15.10.2016.
 */

public abstract class Connection extends HibernateDaoSupport
{
    @Autowired
    public void anyMethodName(SessionFactory sessionFactory)
    {
        setSessionFactory(sessionFactory);
    }
}