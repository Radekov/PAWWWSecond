/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.pawww.hurtowniasecond.listeners;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import pl.pawww.hurtowniasecond.util.DBManager;

/**
 * Web application lifecycle listener.
 *
 * @author R
 */
public class ContextListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        DBManager.getManager().createEntityManagerFactory();
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        DBManager.getManager().closeEntityManagerFactory();
    }
}
