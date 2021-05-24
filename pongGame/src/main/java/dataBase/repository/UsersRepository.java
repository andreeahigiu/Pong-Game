package dataBase.repository;

import dataBase.entityClasses.Users;
import dataBase.entityManager.EntityManagement;

import javax.persistence.EntityManager;
import java.util.List;

/**
 * Class UsersRepository:
 *  contine metodele pentru crearea unei noi linii in tabela User, pentru identificarea unui utilizator in functie de nume,
 *  pentru updatarea scorului unui jucator din bd si pentru obtinerea tuturor jucatorilor din baza de date si sortarea acestora in functie de scor.
 *
 * @author Andreea Higiu
 * @version 1.0
 */

public class UsersRepository {
    public static Users create(Users user){
        EntityManager em = EntityManagement.getInstance().getEntityManagerFactory().createEntityManager();
        em.getTransaction().begin();

        em.persist(user);

        em.getTransaction().commit();
        em.close();

        return user;
    }

    public static Users findUserByID(Integer id){
        EntityManager em = EntityManagement.getInstance().getEntityManagerFactory().createEntityManager();
        em.getTransaction().begin();

        Users user = em.find(Users.class, id);

        em.getTransaction().commit();
        em.close();

        return user;
    }

    public static Users findUserByName(String username){
        EntityManager em = EntityManagement.getInstance().getEntityManagerFactory().createEntityManager();
        em.getTransaction().begin();
        Users user = (Users) em.createNamedQuery("User.findByName")
                .setParameter("username", username)
                .getSingleResult();

        em.getTransaction().commit();
        em.close();

        return user;
    }

    public static void updateUserScore(String username, int score){

        EntityManager em = EntityManagement.getInstance().getEntityManagerFactory().createEntityManager();
        em.getTransaction().begin();

        Users user = UsersRepository.findUserByName(username);
        user.setScore(score);
        em.merge(user);

        em.getTransaction().commit();
        em.close();


    }

    public static List<Users> findTopUsers() {
//        return session.createQuery("SELECT a FROM Student a", Users.class).getResultList();
        EntityManager em = EntityManagement.getInstance().getEntityManagerFactory().createEntityManager();
        em.getTransaction().begin();

        List<Users> result = em.createQuery("SELECT u FROM Users u", Users.class).getResultList();
        result.sort(Users::compareTo);

        em.getTransaction().commit();
        em.close();
        return result;
    }
}
