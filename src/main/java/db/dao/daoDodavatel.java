package db.dao;

import db.e.Dodavatel;

import javax.persistence.*;
import java.util.Comparator;
import java.util.List;

public class daoDodavatel {


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

    public Dodavatel getSupplierById(int id){
        EntityManagerFactory emf =
                Persistence.createEntityManagerFactory("AppPU");
        EntityManager em = emf.createEntityManager();

        Dodavatel d = em.find(Dodavatel.class, id);

        em.close();
        emf.close();
        return d;
    }

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
