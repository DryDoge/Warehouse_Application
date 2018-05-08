package db.dao;

import db.e.Napoj;

import javax.persistence.*;
import java.util.Comparator;
import java.util.List;

public class daoNapoj {

    public List<Napoj> getAllBeverages(){
        EntityManagerFactory emf =
                Persistence.createEntityManagerFactory("AppPU");
        EntityManager em = emf.createEntityManager();


        TypedQuery<Napoj> q2 = em.createQuery(
                "SELECT s FROM Napoj AS s", Napoj.class
        );
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
}
