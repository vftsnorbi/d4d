package hu.krisztn.interfaces.common;

import hu.krisztn.entity.base.IEntity;

import javax.ejb.Local;
import javax.persistence.EntityManager;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;

/**
 * Created by Norbert Kriszt (norbert.kriszt@gmail.com) on 2018. 08. 03..
 */
@Local
public interface CommonService extends Serializable {

    <E extends IEntity> List<E> getAll(Class<E> entityClass);

    <E extends IEntity> E save(E entity);

    <E extends IEntity> List<E> saveAll(Collection<E> entities);

    <E extends IEntity> void remove(E entity);

    <E extends IEntity> void removeAll(Collection<E> entities);

    EntityManager getEntityManager();
}
