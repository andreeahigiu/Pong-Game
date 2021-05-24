package dataBase;

import dataBase.entityClasses.Users;
import dataBase.entityManager.EntityManagement;
import dataBase.repository.UsersRepository;
import javax.persistence.EntityManager;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;

public class Application {

    public static void main(String[] args) {
    List<Users> list = new ArrayList<>();
        EntityManager em = EntityManagement.getInstance().getEntityManagerFactory().createEntityManager();
//
//        Users user = new Users("Ionela", "12345");
//        UsersRepository.create(user);

       UsersRepository.updateUserScore("Ionela", 1);

        list = UsersRepository.findTopUsers();
        System.out.println(list);

    }


}
