package db.dao;

import db.entity.Contain;

import javax.persistence.*;


/*
Class for communicating with database - table contain and for queries using this table.
*/
public class DaoContain {
    /**
     * Gets amount of the beverage from the warehouse.
     * @param storage The id of the warehouse in which beverage is stored.
     * @param beverage The id of the Beverage which amount we need.
     * @return Amount of the selected beverages in the warehouse.
     */
    public int getAmount(int storage, int beverage){

        Contain c  = getContentInfo(storage, beverage);
        return c.getAmount();
    }

    /**
     * Deletes the chosen beverage from selected warehouse.
     *  @param storage The id of the warehouse in which beverage is stored.
     *  @param beverage The id of the Beverage we want to delete from the warehouse.
     */
    public void deleteContentOfItem(int storage, int beverage){

        EntityManagerFactory emf =
                Persistence.createEntityManagerFactory("AppPU");
        EntityManager em = emf.createEntityManager();

        Contain c  = getContentInfo(storage, beverage);

        EntityTransaction et = em.getTransaction();
        if (!em.contains(c)) {
            c = em.merge(c);
        }
        
        et.begin();
        em.remove(c);
        et.commit();

        em.close();
        emf.close();
    }

    /**
     * Update the chosen content.
     *  @param c The content which is going to be updated.
     */
    public void updateAmount(Contain c){

        EntityManagerFactory emf =
                Persistence.createEntityManagerFactory("AppPU");
        EntityManager em = emf.createEntityManager();

        Contain o = getContentInfo(c.getStorageid(), c.getBeverageid());

        EntityTransaction et = em.getTransaction();

        et.begin();
        o.setAmount(c.getAmount());
        em.merge(o);
        et.commit();

        em.close();
        emf.close();
    }

    /**
     * Gets Content of the beverage from the warehouse.     *
     * @param storage The id of the warehouse in which beverage is stored.
     * @param beverage The id of the Beverage we want to delete from the warehouse.
     * @return Content.
     */
    public Contain getContentInfo(int storage, int beverage){

        EntityManagerFactory emf =
                Persistence.createEntityManagerFactory("AppPU");
        EntityManager em = emf.createEntityManager();


        TypedQuery<Contain> q3 = em.createQuery(
                "SELECT c FROM Contain AS c WHERE (c.storageid = :StorageID) AND (c.beverageid = :BeverageID)",
                Contain.class
        );

        q3.setParameter("StorageID", storage);
        q3.setParameter("BeverageID", beverage);
        Contain c  = q3.getSingleResult();

        em.close();
        emf.close();

        return c;
    }
}
