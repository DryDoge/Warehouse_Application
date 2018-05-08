package db.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class daoBasics {

    public<T> void addAnything(T t){
        EntityManagerFactory emf =
                Persistence.createEntityManagerFactory("AppPU");

        EntityManager em = emf.createEntityManager();
        EntityTransaction et = em.getTransaction();
        et.begin();
        em.persist(t);
        et.commit();
    }
}
