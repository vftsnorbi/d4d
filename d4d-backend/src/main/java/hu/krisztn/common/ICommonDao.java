package hu.krisztn.common;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Created by Norbert Kriszt (norbert.kriszt@gmail.com) on 2018. 08. 03..
 */

public class ICommonDao {

    @PersistenceContext(unitName = "dummyUnit")
    private EntityManager entityManager;
}
