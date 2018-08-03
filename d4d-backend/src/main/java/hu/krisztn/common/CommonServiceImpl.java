package hu.krisztn.common;

import hu.krisztn.entity.base.IEntity;
import hu.krisztn.interfaces.common.CommonService;
import hu.krisztn.interfaces.common.EntityManagerProducer;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by Norbert Kriszt (norbert.kriszt@gmail.com) on 2018. 03. 01..
 */
@Stateless
public class CommonServiceImpl implements CommonService {

    @Inject
    private EntityManagerProducer entityManagerProducer;

    @Override
    @SuppressWarnings("unchecked")
    public <E extends IEntity> List<E> getAll(Class<E> entityClass) {
        return getEntityManager().createQuery("SELECT entity FROM " + entityClass.getSimpleName() + " entity").getResultList();
    }

    @Override
    public <E extends IEntity> E save(E entity) {
        EntityManager em = this.getEntityManager();
        E result;
        if (entity.getId() == null) {
            em.persist(entity);
            result = entity;
        } else {
            result = em.merge(entity);
        }

        return result;
    }

    @Override
    public <E extends IEntity> List<E> saveAll(Collection<E> entities) {
        List<E> retList = new ArrayList<>();
        for (E entity : entities) {
            retList.add(this.save(entity));
        }
        return retList;
    }

    @Override
    public <E extends IEntity> void remove(E entity) {
        EntityManager em = this.getEntityManager();
        Object entityToRemove = em.find(entity.getClass(), entity.getId());
        if (entityToRemove != null) {
            em.remove(entityToRemove);
            em.flush();
        }
    }

    @Override
    public <E extends IEntity> void removeAll(Collection<E> entities) {
        if (entities != null) {
            EntityManager em = this.getEntityManager();
            for (E entity : entities) {
                em.remove(em.find(entity.getClass(), entity.getId()));
            }
            em.flush();
        }
    }

    @Override
    public EntityManager getEntityManager() {
        return entityManagerProducer.getEntityManager();
    }
}
