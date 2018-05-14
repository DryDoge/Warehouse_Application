package db.dao;

import db.e.Obsahuje;

import javax.persistence.*;

public class daoObsahuje {
    /**
     * Gets amount of the beverage from the warehouse.
     * @param storage The id of the warehouse in which beverage is stored.
     * @param beverage The id of the Beverage which amount we need.
     * @return Amount of the selected beverages in the warehouse.
     */
    public int getAmount(int storage, int beverage){
        Obsahuje o  = getContentInfo(storage, beverage);
        return o.getMnozstvo();
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

        Obsahuje o = getContentInfo(storage, beverage);

        EntityTransaction et = em.getTransaction();
        if (!em.contains(o)) {
            o = em.merge(o);
        }
        
        et.begin();
        em.remove(o);
        et.commit();

        em.close();
        emf.close();
    }

    /**
     * Update the chosen content.
     *  @param ob The content which is going to be updated.
     */
    public void updateAmount(Obsahuje ob){
        EntityManagerFactory emf =
                Persistence.createEntityManagerFactory("AppPU");
        EntityManager em = emf.createEntityManager();

        Obsahuje o = getContentInfo(ob.getIdsklad(), ob.getIdnap());

        EntityTransaction et = em.getTransaction();

        et.begin();
        o.setMnozstvo(ob.getMnozstvo());
        em.merge(o);
        et.commit();

        em.close();
        emf.close();
    }

    /**
     * Gets Content of the beverage from the warehouse.
     *
     * @param storage The id of the warehouse in which beverage is stored.
     * @param beverage The id of the Beverage we want to delete from the warehouse.
     * @return Content.
     */
    public Obsahuje getContentInfo(int storage, int beverage){

        EntityManagerFactory emf =
                Persistence.createEntityManagerFactory("AppPU");
        EntityManager em = emf.createEntityManager();


        TypedQuery<Obsahuje> q3 = em.createQuery(
                "SELECT o FROM Obsahuje AS o WHERE (o.idsklad = :StorageID) AND (o.idnap = :BeverageID)",
                Obsahuje.class
        );

        q3.setParameter("StorageID", storage);
        q3.setParameter("BeverageID", beverage);
        Obsahuje o  = q3.getSingleResult();

        em.close();
        emf.close();

        return o;

    }

}
