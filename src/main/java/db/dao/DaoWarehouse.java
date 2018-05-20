package db.dao;

import db.entity.*;

import javax.persistence.*;
import java.util.Comparator;
import java.util.List;


/*
Class for communicating with database - table warehouse and for queries using this table.
*/
public class DaoWarehouse {

    /**
     * Gets all warehouses from database.
     * @return List of warehouses.
     */
    public List<Warehouse> getAllWarehouses(){
        EntityManagerFactory emf =
                Persistence.createEntityManagerFactory("AppPU");
        EntityManager em = emf.createEntityManager();


        TypedQuery<Warehouse> q2 = em.createQuery(
                "SELECT w FROM Warehouse AS w", Warehouse.class
        );
        List<Warehouse> l = q2.getResultList();

        l.sort(Comparator.comparing(Warehouse::getId));
        for (Warehouse w :l) {
            em.refresh(w);
        }
        em.close();
        emf.close();

        return l;
    }

    /**
     * Gets the warehouse from database by its id.
     * @param id Id of the warehouse.
     * @return Warehouse.
     */
    public Warehouse getWarehouseById(int id){
        EntityManagerFactory emf =
                Persistence.createEntityManagerFactory("AppPU");
        EntityManager em = emf.createEntityManager();

        Warehouse w = em.find(Warehouse.class, id);

        em.close();
        emf.close();
        return w;
    }

    /**
     * Delete the warehouse from database.
     * @param idWarehouse Id of the warehouse which is going to be deleted.
     */
    public void deleteWarehouse(int idWarehouse){

        EntityManagerFactory emf =
                Persistence.createEntityManagerFactory("AppPU");
        EntityManager em = emf.createEntityManager();

        Warehouse w = em.find(Warehouse.class, idWarehouse);

        EntityTransaction et = em.getTransaction();

        et.begin();
        em.remove(w);
        et.commit();

        em.close();
        emf.close();
    }


    /**
     * Update the warehouse in database.
     * @param w The warehouse which is going to be updated.
     */
    public void updateWarehouse(Warehouse w){
        EntityManagerFactory emf =
                Persistence.createEntityManagerFactory("AppPU");
        EntityManager em = emf.createEntityManager();

        Warehouse storage = em.find(Warehouse.class, w.getId());
        EntityTransaction et = em.getTransaction();

        et.begin();
        storage.setTel(w.getTel());
        storage.setStreet(w.getStreet());
        storage.setCity(w.getCity());
        storage.setPostalcode(w.getPostalcode());
        storage.setWeb(w.getWeb());
        em.merge(storage);
        et.commit();

        em.close();
        emf.close();
    }

}
