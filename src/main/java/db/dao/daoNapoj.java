package db.dao;

import db.e.Napoj;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class daoNapoj {

    /**
     * Gets all beverages from database.
     *
     * @return List of beverages.
     */
    public List<Napoj> getAllBeverages(){
        List<Napoj> allList = new ArrayList<>();
        List<Napoj> alkoList = getAllAlcoBeverages();
        List<Napoj>nealkoList = getAllNonAlcoBeverages();
        allList.addAll(alkoList);
        allList.addAll(nealkoList);
        allList.sort(Comparator.comparing(Napoj::getIdnap));
        return allList;
    }

    /**
     * Gets all nonalcoholic beverages from database.
     *
     * @return List of nonalcoholic beverages.
     */
    public List<Napoj> getAllNonAlcoBeverages(){
        EntityManagerFactory emf =
                Persistence.createEntityManagerFactory("AppPU");
        EntityManager em = emf.createEntityManager();
        TypedQuery<Napoj> q2 = em.createQuery(
                "SELECT na FROM Napoj AS na WHERE (na.typ = :typ)" ,
                Napoj.class
        );
        q2.setParameter("typ", "Nealko");

        List<Napoj> l = q2.getResultList();

        l.sort(Comparator.comparing(Napoj::getIdnap));
        for (Napoj n :l) {
            em.refresh(n);
        }
        em.close();
        emf.close();

        return l;
    }

    /**
     * Gets all alcoholic beverages from database.
     *
     * @return List of alcoholic beverages.
     */
    public List<Napoj>getAllAlcoBeverages(){
        EntityManagerFactory emf =
                Persistence.createEntityManagerFactory("AppPU");
        EntityManager em = emf.createEntityManager();
        TypedQuery<Napoj> q2 = em.createQuery(
                "SELECT na FROM Napoj AS na WHERE (na.typ = :typ)" ,
                Napoj.class
        );
        q2.setParameter("typ", "Alko");

        List<Napoj> l = q2.getResultList();

        l.sort(Comparator.comparing(Napoj::getIdnap));
        for (Napoj n :l) {
            em.refresh(n);
        }
        em.close();
        emf.close();

        return l;

    }
    /**
     * Gets the beverage from database by its id.
     *
     * @param id Id of the beverage.
     * @return Beverage.
     */
    public Napoj getBeverageByID(int id) {
        EntityManagerFactory emf =
                    Persistence.createEntityManagerFactory("AppPU");
            EntityManager em = emf.createEntityManager();

            Napoj n = em.find(Napoj.class, id);

            em.close();
            emf.close();
            return n;
    }

    /**
     * Delete the beverage from database.
     *
     * @param idBeverage Id of the beverage which is going to be deleted.
     */
    public void deleteBeverage(int idBeverage){

        EntityManagerFactory emf =
                Persistence.createEntityManagerFactory("AppPU");
        EntityManager em = emf.createEntityManager();

        Napoj n = em.find(Napoj.class, idBeverage);

        EntityTransaction et = em.getTransaction();

        et.begin();
        em.remove(n);
        et.commit();

        em.close();
        emf.close();
    }

    /**
     * Update the beverage in database.
     *
     * @param n The beverage which is going to be updated.
     */
    public void updateBeverage(Napoj n){
        EntityManagerFactory emf =
                Persistence.createEntityManagerFactory("AppPU");
        EntityManager em = emf.createEntityManager();

        Napoj beverage = em.find(Napoj.class, n.getIdnap());
        EntityTransaction et = em.getTransaction();

        et.begin();
        beverage.setPrichut(n.getPrichut());
        beverage.setDruh(n.getDruh());
        beverage.setZnacka(n.getZnacka());
        beverage.setCena(n.getCena());
        beverage.setTyp(n.getTyp());
        beverage.setDodavatel(n.getDodavatel());
        em.merge(beverage);
        et.commit();

        em.close();
        emf.close();
    }
}
