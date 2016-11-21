package cz.muni.airport.database;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;

/**
 * Connection class handling session
 *
 * Created by Richard Bariny on 25.10.2016
 * @author github:Richardb953
 */


public abstract class Connection extends HibernateDaoSupport
{
    @Autowired
    public void anyMethodName(SessionFactory sessionFactory)
    {
        setSessionFactory(sessionFactory);
    }
}