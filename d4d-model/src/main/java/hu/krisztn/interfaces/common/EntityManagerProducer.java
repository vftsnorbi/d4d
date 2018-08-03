package hu.krisztn.interfaces.common;

import javax.ejb.Local;
import javax.persistence.EntityManager;
import java.io.Serializable;

/**
 * Created by Norbert Kriszt (norbert.kriszt@gmail.com) on 2018. 08. 03..
 */
@Local
public interface EntityManagerProducer extends Serializable {

    EntityManager getEntityManager();
}
