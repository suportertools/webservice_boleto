/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sindical.factory;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Claudemir Rtools
 */
public class ConnectionDB {

    private EntityManager entityManager;

    protected EntityManager getEntityManager() {
        try {
            if (entityManager == null) {
                EntityManagerFactory emf = Persistence.createEntityManagerFactory("persistence");
                entityManager = emf.createEntityManager();
            }
        } catch (Exception e) {
            e.getMessage();
            System.err.println(e.getMessage() + "\n" + e.getCause().getMessage());
        }
        return entityManager;
    }

    protected void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
}
