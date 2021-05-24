package dataBase;

import dataBase.entityClasses.Users;
import dataBase.entityManager.EntityManagement;
import dataBase.repository.UsersRepository;
import javax.persistence.EntityManager;

import javax.persistence.EntityManager;

public class Application {

    public static void main(String[] args) {

        EntityManager em = EntityManagement.getInstance().getEntityManagerFactory().createEntityManager();
//
//        Users user = new Users("Ionela", "12345");
//        UsersRepository.create(user);

        //UsersRepository.updateUserScore("Ioana", 9);
        UsersRepository.updateUserScore("Ionela", 9);

    }


}
