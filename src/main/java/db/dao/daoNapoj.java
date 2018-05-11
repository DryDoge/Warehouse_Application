package db.dao;

import db.e.Napoj;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class daoNapoj {

    public List<Napoj> getAllBeverages(){
        List<Napoj> allList = new ArrayList<>();
        List<Napoj> alkoList = getAllAlcoBeverages();
        List<Napoj>nealkoList = getAllNonAlcoBeverages();
        allList.addAll(alkoList);
        allList.addAll(nealkoList);
        allList.sort(Comparator.comparing(Napoj::getIdnap));
        return allList;
    }


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

    public Napoj getBeverageByID(int id) {
        EntityManagerFactory emf =
                    Persistence.createEntityManagerFactory("AppPU");
            EntityManager em = emf.createEntityManager();

            Napoj n = em.find(Napoj.class, id);

            em.close();
            emf.close();
            return n;
    }


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

    public void updateBeverage(Napoj s){
        EntityManagerFactory emf =
                Persistence.createEntityManagerFactory("AppPU");
        EntityManager em = emf.createEntityManager();

        Napoj beverage = em.find(Napoj.class, s.getIdnap());
        EntityTransaction et = em.getTransaction();

        et.begin();
        beverage.setPrichut(s.getPrichut());
        beverage.setDruh(s.getDruh());
        beverage.setZnacka(s.getZnacka());
        beverage.setCena(s.getCena());
        beverage.setTyp(s.getTyp());
        beverage.setDodavatel(s.getDodavatel());
        em.merge(beverage);
        et.commit();

        em.close();
        emf.close();
    }
}
