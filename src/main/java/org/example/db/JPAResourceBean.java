package org.example.db;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import jakarta.inject.Named;
@ApplicationScoped
@Named
public class JPAResourceBean {
    private EntityManagerFactory emf;
    public EntityManagerFactory getEMF (){
        if (emf == null){
            emf = Persistence.createEntityManagerFactory("default", new java.util.HashMap<>());
        }
        return emf;
    }
}
