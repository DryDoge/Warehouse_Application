package db.dao;

import db.e.Dodavatel;

import javax.persistence.*;
import java.util.Comparator;
import java.util.List;


public class daoDodavatel {

    /**
     * Gets all suppliers from database.
     *
     * @return List of suppliers.
     */
    public List<Dodavatel> getAllSuppliers(){
        EntityManagerFactory emf =
                Persistence.createEntityManagerFactory("AppPU");
        EntityManager em = emf.createEntityManager();


        TypedQuery<Dodavatel> q2 = em.createQuery(
                "SELECT d FROM Dodavatel AS d", Dodavatel.class
        );
        List<Dodavatel> l = q2.getResultList();

        l.sort(Comparator.comparing(Dodavatel::getIddod));
        for (Dodavatel d :l) {
            em.refresh(d);
        }
        em.close();
        emf.close();

        return l;
    }

    /**
     * Gets the supplier from database by its id.
     *
     * @param id Id of the supplier.
     * @return Supplier.
     */
    public Dodavatel getSupplierById(int id){
        EntityManagerFactory emf =
                Persistence.createEntityManagerFactory("AppPU");
        EntityManager em = emf.createEntityManager();

        Dodavatel d = em.find(Dodavatel.class, id);

        em.close();
        emf.close();
        return d;
    }

    /**
     * Gets the supplier from database by its name.
     *
     * @param name Name of the supplier.
     * @return Supplier.
     */
    public Dodavatel getSupplierByName(String name){
        EntityManagerFactory emf =
                Persistence.createEntityManagerFactory("AppPU");
        EntityManager em = emf.createEntityManager();

        TypedQuery<Dodavatel> q2 = em.createQuery(
                "SELECT d FROM Dodavatel AS d WHERE (d.nazov = :Name)" ,
                Dodavatel.class
        );
        q2.setParameter("Name", name);

        Dodavatel d = q2.getSingleResult();
        em.close();
        emf.close();
        return d;
    }

    /**
     * Delete the supplier from database.
     *
     * @param idSupplier Id of the supplier who is going to be deleted.
     * */
    public void deleteSupplier(int idSupplier){

        EntityManagerFactory emf =
                Persistence.createEntityManagerFactory("AppPU");
        EntityManager em = emf.createEntityManager();

        Dodavatel s = em.find(Dodavatel.class, idSupplier);

        EntityTransaction et = em.getTransaction();

        et.begin();
        em.remove(s);
        et.commit();

        em.close();
        emf.close();
    }

    /**
     * Update the supplier in database.
     *
     * @param d The supplier who is going to be updated.
     */
    public void updateSupplier(Dodavatel d){
        EntityManagerFactory emf =
                Persistence.createEntityManagerFactory("AppPU");
        EntityManager em = emf.createEntityManager();

        Dodavatel supplier = em.find(Dodavatel.class, d.getIddod());
        EntityTransaction et = em.getTransaction();

        et.begin();
        supplier.setTel(d.getTel());
        supplier.setNazov(d.getNazov());
        supplier.setEmail(d.getEmail());
        supplier.setWeb(d.getWeb());
        em.merge(supplier);
        et.commit();

        em.close();
        emf.close();
    }
}
