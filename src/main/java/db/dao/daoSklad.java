package db.dao;

import db.e.*;

import javax.persistence.*;
import java.util.Comparator;
import java.util.List;

public class daoSklad {

    /**
     * Gets all warehouses from database.
     *
     * @return List of warehouses.
     */
    public List<Sklad> getAllWarehouses(){
        EntityManagerFactory emf =
                Persistence.createEntityManagerFactory("AppPU");
        EntityManager em = emf.createEntityManager();


        TypedQuery<Sklad> q2 = em.createQuery(
                "SELECT s FROM Sklad AS s", Sklad.class
        );
        List<Sklad> l = q2.getResultList();

        l.sort(Comparator.comparing(Sklad::getIdsklad));
        for (Sklad s :l) {
            em.refresh(s);
        }
        em.close();
        emf.close();

        return l;
    }

    /**
     * Gets the warehouse from database by its id.
     *
     * @param id Id of the warehouse.
     * @return Warehouse.
     */
    public Sklad getWarehouseById(int id){
        EntityManagerFactory emf =
                Persistence.createEntityManagerFactory("AppPU");
        EntityManager em = emf.createEntityManager();

        Sklad s = em.find(Sklad.class, id);

        em.close();
        emf.close();
        return s;
    }

    /**
     * Delete the warehouse from database.
     *
     * @param idWarehouse Id of the warehouse which is going to be deleted.
     */
    public void deleteWarehouse(int idWarehouse){

        EntityManagerFactory emf =
                Persistence.createEntityManagerFactory("AppPU");
        EntityManager em = emf.createEntityManager();

        Sklad s = em.find(Sklad.class, idWarehouse);

        EntityTransaction et = em.getTransaction();

        et.begin();
        em.remove(s);
        et.commit();

        em.close();
        emf.close();
    }


    /**
     * Update the warehouse in database.
     *
     * @param s The warehouse which is going to be updated.
     */
    public void updateWarehouse(Sklad s){
        EntityManagerFactory emf =
                Persistence.createEntityManagerFactory("AppPU");
        EntityManager em = emf.createEntityManager();

        Sklad storage = em.find(Sklad.class, s.getIdsklad());
        EntityTransaction et = em.getTransaction();

        et.begin();
        storage.setTel(s.getTel());
        storage.setUlica(s.getUlica());
        storage.setMesto(s.getMesto());
        storage.setPsc(s.getPsc());
        storage.setWeb(s.getWeb());
        em.merge(storage);
        et.commit();

        em.close();
        emf.close();
    }

}
