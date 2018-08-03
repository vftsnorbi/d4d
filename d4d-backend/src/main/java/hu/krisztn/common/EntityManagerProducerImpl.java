package hu.krisztn.common;


import hu.krisztn.interfaces.common.EntityManagerProducer;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Default;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;

/**
 * Created by Norbert Kriszt (norbert.kriszt@gmail.com) on 2018. 04. 03..
 */
@RequestScoped
public class EntityManagerProducerImpl implements EntityManagerProducer {

    @PersistenceUnit(unitName = "dummyUnit")
    private EntityManagerFactory entityManagerFactory;

    @Produces
    @RequestScoped
    @Default
    @Override
    public EntityManager getEntityManager() {
        return entityManagerFactory.createEntityManager();
    }
}
