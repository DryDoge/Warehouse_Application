package app;

import db.*;
import gui.LoginView;

import javax.persistence.*;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        LoginView l = new LoginView();
        l.actionLogin();
        EntityManagerFactory emf =
                Persistence.createEntityManagerFactory("AppPU");
        EntityManager em = emf.createEntityManager();
//
//        Book b1 = em.find(Book.class, 2);
//        System.out.println(
//                (b1 == null) ? "NULL" : "Id: " + b1.getId() + " | title: " + b1.getTitle()
//        );
//        System.out.println();
//
//        TypedQuery<Book> q2 = em.createQuery(
//                "SELECT b FROM Book AS b",
//                Book.class
//        );
//
//        List<Book> l = q2.getResultList();
//        for (Book b : l) {
//            System.out.println("Id: " + b.getId() + " | title: " + b.getTitle());
//        }
//        System.out.println();
//
//        TypedQuery<Book> q3 = em.createQuery(
//                "SELECT b FROM Book AS b WHERE (b.id = :BookId)",
//                Book.class
//        );
//        q3.setParameter("BookId", 2);
//        Book b3 = q3.getSingleResult();
//        System.out.println(
//                b3.getId()+" and title: "+b3.getTitle()
//        );
//
        EntityTransaction et = em.getTransaction();
//
////        et.begin();
////        Book b4 = new Book();
////        b4.setTitle("Amerika");
////        em.persist(b4);
////        et.commit();
//
//
//        et.begin();
//        Book employee= (Book)em.find(Book.class , 1);
//        em
//                .createQuery("update Book set title = 'Lolovina' where title = 'Jebo'")
//                .executeUpdate();
//        et.commit();
//        em.refresh(employee);
//
//
//        TypedQuery<Book> q4 = em.createQuery(
//                "SELECT b FROM Book AS b WHERE (b.title = :BookTitle)",
//                Book.class
//        );
//
//        q4.setParameter("BookTitle", "Lolovina");
//        Book b4 = q4.getSingleResult();
//        System.out.println(b4.getId()+" "+ b4.getTitle());

//
//        em.close();
//        emf.close();
    }
}
