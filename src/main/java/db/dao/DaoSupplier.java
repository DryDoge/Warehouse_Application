package db.dao;

import db.entity.Supplier;

import javax.persistence.*;
import java.util.Comparator;
import java.util.List;

/*
Class for communicating with database - table supplier and for queries using this table.
*/
public class DaoSupplier {

    /**
     * Gets all suppliers from database.
     * @return List of suppliers.
     */
    public List<Supplier> getAllSuppliers(){
        EntityManagerFactory emf =
                Persistence.createEntityManagerFactory("AppPU");
        EntityManager em = emf.createEntityManager();


        TypedQuery<Supplier> q2 = em.createQuery(
                "SELECT s FROM Supplier AS s", Supplier.class
        );
        List<Supplier> l = q2.getResultList();

        l.sort(Comparator.comparing(Supplier::getId));
        for (Supplier s :l) {
            em.refresh(s);
        }
        em.close();
        emf.close();

        return l;
    }

    /**
     * Gets the supplier from database by its id.
     * @param id Id of the supplier.
     * @return Supplier.
     */
    public Supplier getSupplierById(int id){
        EntityManagerFactory emf =
                Persistence.createEntityManagerFactory("AppPU");
        EntityManager em = emf.createEntityManager();

        Supplier s = em.find(Supplier.class, id);

        em.close();
        emf.close();
        return s;
    }

    /**
     * Gets the supplier from database by its name.
     * @param name Name of the supplier.
     * @return Supplier.
     */
    public Supplier getSupplierByName(String name){
        EntityManagerFactory emf =
                Persistence.createEntityManagerFactory("AppPU");
        EntityManager em = emf.createEntityManager();

        TypedQuery<Supplier> q2 = em.createQuery(
                "SELECT s FROM Supplier AS s WHERE (s.name = :Name)" ,
                Supplier.class
        );
        q2.setParameter("Name", name);

        Supplier s = q2.getSingleResult();
        em.close();
        emf.close();
        return s;
    }

    /**
     * Delete the supplier from database.
     * @param idSupplier Id of the supplier who is going to be deleted.
     * */
    public void deleteSupplier(int idSupplier){

        EntityManagerFactory emf =
                Persistence.createEntityManagerFactory("AppPU");
        EntityManager em = emf.createEntityManager();

        Supplier s = em.find(Supplier.class, idSupplier);

        EntityTransaction et = em.getTransaction();

        et.begin();
        em.remove(s);
        et.commit();

        em.close();
        emf.close();
    }

    /**
     * Update the supplier in database.
     * @param s The supplier who is going to be updated.
     */
    public void updateSupplier(Supplier s){
        EntityManagerFactory emf =
                Persistence.createEntityManagerFactory("AppPU");
        EntityManager em = emf.createEntityManager();

        Supplier supplier = em.find(Supplier.class, s.getId());
        EntityTransaction et = em.getTransaction();

        et.begin();
        supplier.setTel(s.getTel());
        supplier.setName(s.getName());
        supplier.setEmail(s.getEmail());
        supplier.setWeb(s.getWeb());
        em.merge(supplier);
        et.commit();

        em.close();
        emf.close();
    }
}
