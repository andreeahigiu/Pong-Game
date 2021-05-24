package dataBase.repository;

import dataBase.entityClasses.Users;
import dataBase.entityManager.EntityManagement;

import javax.persistence.EntityManager;
import java.util.List;

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
