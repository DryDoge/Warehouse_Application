package db.dao;


import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class daoBasics {

    /**
     * Add object to database
     *
     * @param t The object which is going to be inserted.
     * @param <T> Class of the inserting object.
     */
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
