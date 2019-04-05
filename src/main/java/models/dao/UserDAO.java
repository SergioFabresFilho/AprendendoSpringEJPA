package main.java.models.dao;

import main.java.models.bean.User;
import main.java.rest.models.UserRest;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class UserDAO {

    private EntityManager getEntityManager() {
        EntityManagerFactory factory = null;
        EntityManager manager;

        try {
            factory = Persistence.createEntityManagerFactory("User");
            manager = factory.createEntityManager();
        } finally {
            if (factory != null) {
                factory.close();
            }
        }

        return manager;
    }

    public User save(User user) {
        EntityManager manager = getEntityManager();

        try {
            EntityTransaction transaction = manager.getTransaction();
            transaction.begin();
            System.out.println("Saving user");

            if(user.getId() == null) {
                manager.persist(user);
            } else {
                user = manager.merge(user);
            }

            transaction.commit();
        } finally {
            manager.close();
        }

        return user;
    }

    public void delete(Long id) {
        EntityManager manager = getEntityManager();

        try {
            EntityTransaction transaction = manager.getTransaction();
            transaction.begin();

            User user = manager.find(User.class, id);
            System.out.println("Deleting");

            manager.remove(user);
            transaction.commit();
        } finally {
            manager.close();
        }
    }

    public User getById(Long id) {
        EntityManager manager = getEntityManager();
        User user;

        try {
            user = manager.find(User.class, id);
        } finally {
            manager.close();
        }

        return user;
    }
}
