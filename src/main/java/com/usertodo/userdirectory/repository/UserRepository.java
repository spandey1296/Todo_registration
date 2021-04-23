package com.usertodo.userdirectory.repository;

import com.usertodo.userdirectory.model.UserLogin;
import org.springframework.stereotype.Repository;

import javax.persistence.*;

@Repository
public class UserRepository {

    //Entity Manager Factory
    @PersistenceUnit(unitName = "phonedirectory")
    EntityManagerFactory entityManagerFactory;

    //Method to register new user
    public void registerUser(UserLogin newUser){
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        try{
            transaction.begin();
            entityManager.persist(newUser);
            transaction.commit();

        }catch (Exception e){
            System.out.println(e.getMessage());
            transaction.rollback();
        }
    }

    //Method to check credentials
    public UserLogin checkCredentials(String username, String password) {
        try{
            EntityManager entityManager = entityManagerFactory.createEntityManager();
            TypedQuery<UserLogin> query = entityManager.createQuery("SELECT u FROM UserLogin u WHERE u.username = :username AND u.password = :password",UserLogin.class);
            query.setParameter("username", username);
            query.setParameter("password", password);
            return query.getSingleResult();

        }catch(Exception e) {
            System.out.println(e);
            return null;
        }
    }

}
