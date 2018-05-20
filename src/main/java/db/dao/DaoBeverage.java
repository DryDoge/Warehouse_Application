package db.dao;

import db.entity.Beverage;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;



/*
Class for communicating with database - table beverage and for queries using this table.
*/
public class DaoBeverage {

    /**
     * Gets all beverages from database.
     * @return List of beverages.
     */
    public List<Beverage> getAllBeverages(){
        List<Beverage> allList = new ArrayList<>();
        List<Beverage> alkoList = getAllAlcoBeverages();
        List<Beverage>nealkoList = getAllNonAlcoBeverages();
        allList.addAll(alkoList);
        allList.addAll(nealkoList);
        allList.sort(Comparator.comparing(Beverage::getId));
        return allList;
    }

    /**
     * Gets all nonalcoholic beverages from database.
     * @return List of nonalcoholic beverages.
     */
    public List<Beverage> getAllNonAlcoBeverages(){
        EntityManagerFactory emf =
                Persistence.createEntityManagerFactory("AppPU");
        EntityManager em = emf.createEntityManager();
        TypedQuery<Beverage> q2 = em.createQuery(
                "SELECT na FROM Beverage AS na WHERE (na.type = :type)" ,
                Beverage.class
        );
        q2.setParameter("type", "Nealko");

        List<Beverage> l = q2.getResultList();

        l.sort(Comparator.comparing(Beverage::getId));
        for (Beverage b :l) {
            em.refresh(b);
        }
        em.close();
        emf.close();

        return l;
    }

    /**
     * Gets all alcoholic beverages from database.
     * @return List of alcoholic beverages.
     */
    public List<Beverage>getAllAlcoBeverages(){
        EntityManagerFactory emf =
                Persistence.createEntityManagerFactory("AppPU");
        EntityManager em = emf.createEntityManager();
        TypedQuery<Beverage> q2 = em.createQuery(
                "SELECT a FROM Beverage AS a WHERE (a.type = :type)" ,
                Beverage.class
        );
        q2.setParameter("type", "Alko");

        List<Beverage> l = q2.getResultList();

        l.sort(Comparator.comparing(Beverage::getId));
        for (Beverage b :l) {
            em.refresh(b);
        }
        em.close();
        emf.close();

        return l;

    }
    /**
     * Gets the beverage from database by its id.
     * @param id Id of the beverage.
     * @return Beverage.
     */
    public Beverage getBeverageByID(int id) {
        EntityManagerFactory emf =
                    Persistence.createEntityManagerFactory("AppPU");
            EntityManager em = emf.createEntityManager();

            Beverage n = em.find(Beverage.class, id);

            em.close();
            emf.close();
            return n;
    }

    /**
     * Delete the beverage from database.
     * @param idBeverage Id of the beverage which is going to be deleted.
     */
    public void deleteBeverage(int idBeverage){

        EntityManagerFactory emf =
                Persistence.createEntityManagerFactory("AppPU");
        EntityManager em = emf.createEntityManager();

        Beverage b = em.find(Beverage.class, idBeverage);

        EntityTransaction et = em.getTransaction();

        et.begin();
        em.remove(b);
        et.commit();

        em.close();
        emf.close();
    }

    /**
     * Update the beverage in database.
     * @param b The beverage which is going to be updated.
     */
    public void updateBeverage(Beverage b){
        EntityManagerFactory emf =
                Persistence.createEntityManagerFactory("AppPU");
        EntityManager em = emf.createEntityManager();

        Beverage beverage = em.find(Beverage.class, b.getId());
        EntityTransaction et = em.getTransaction();

        et.begin();
        beverage.setFlavor(b.getFlavor());
        beverage.setCategory(b.getCategory());
        beverage.setBrand(b.getBrand());
        beverage.setPrice(b.getPrice());
        beverage.setType(b.getType());
        beverage.setSupplier(b.getSupplier());
        em.merge(beverage);
        et.commit();

        em.close();
        emf.close();
    }
}
