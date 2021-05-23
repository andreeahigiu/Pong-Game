package dataBase.entityManager;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class EntityManagement {

    private static EntityManagement instance;
    private static EntityManagerFactory emf;

    private EntityManagement() {}

    public static EntityManagement getInstance() {
        if(instance == null) {
            instance = new EntityManagement();
        }
        return instance;
    }

    public EntityManagerFactory getEntityManagerFactory() {

        if (emf == null)
            createEntityManagerFactory();
        return emf;
    }


    protected void createEntityManagerFactory() {
        this.emf = Persistence.createEntityManagerFactory("Pong");
    }

    public void close(){
        if(emf != null){
            emf.close();
            emf = null;
        }
    }
}
