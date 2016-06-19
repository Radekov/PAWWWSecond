/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.pawww.hurtowniasecond.beans;

import java.util.ArrayList;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ManagedBean;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import pl.pawww.hurtowniasecond.entity.Jednostka;
import pl.pawww.hurtowniasecond.util.DBManager;

/**
 *
 * @author R
 */
@ManagedBean(name = "jednostkaManagedBean")
@RequestScoped
public class JednostkaManagedBean {
    ArrayList<Jednostka> jednostka = new ArrayList<>();
    public JednostkaManagedBean() {
    }
    public List<Jednostka> getJednostka() {
        EntityManager em = DBManager.getManager().createEntityManager();
        TypedQuery<Jednostka>  query = em.createNamedQuery("Jednostka.findAll", Jednostka.class);
        List<Jednostka>result = query.getResultList();
        em.close();
        return result;
    }
}
