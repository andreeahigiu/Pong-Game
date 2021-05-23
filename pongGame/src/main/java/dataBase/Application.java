package dataBase;

import dataBase.entityClasses.Users;
import dataBase.entityManager.EntityManagement;
import dataBase.repository.UsersRepository;
import javax.persistence.EntityManager;

import javax.persistence.EntityManager;

public class Application {

    public static void main(String[] args) {

        EntityManager em = EntityManagement.getInstance().getEntityManagerFactory().createEntityManager();

        Users user = new Users("Ioana", "1234i");
        UsersRepository.create(user);

    }


}
