package bean.entity;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public interface EntityMan {
    String UNIT_NAME = "Galeria";

    static <R> R execute(util.returnable.Callback<EntityManager, R> callback){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory(UNIT_NAME);
        EntityManager em = emf.createEntityManager();
        R r = callback.call(em);
        em.close();
        emf.close();
        return r;
    }

    static <R> R executeTransaction(util.returnable.Callback<EntityManager, R> callback){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory(UNIT_NAME);
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        R r = callback.call(em);
        em.getTransaction().commit();
        em.close();
        emf.close();
        return r;
    }

    static void exec(util.noreturn.Callback<EntityManager> callback){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory(UNIT_NAME);
        EntityManager em = emf.createEntityManager();
        callback.call(em);
        em.close();
        emf.close();
    }

    static void execTransaction(util.noreturn.Callback<EntityManager> callback){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory(UNIT_NAME);
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        callback.call(em);
        em.getTransaction().commit();
        em.close();
        emf.close();
    }
}
