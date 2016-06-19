/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.pawww.hurtowniasecond.util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author R
 */
public class DBManager {

    private static DBManager instance;
    private EntityManagerFactory emf;

    private DBManager() {
    }

    public synchronized static DBManager getManager() {
        if (instance == null) {
            instance = new DBManager();
        }
        return instance;
    }

    public EntityManagerFactory createEntityManagerFactory() {
        if (emf == null) {
            emf = Persistence.createEntityManagerFactory("pl.pawww_HurtowniaSecond_war_1.0-SNAPSHOTPU");
        }
        return emf;
    }

    public EntityManager createEntityManager() {
        return this.createEntityManagerFactory().createEntityManager();
    }

    public void closeEntityManagerFactory() {
        if (emf != null) {
            emf.close();
        }

    }
}
