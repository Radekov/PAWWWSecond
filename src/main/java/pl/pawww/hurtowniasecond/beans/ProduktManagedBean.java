/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.pawww.hurtowniasecond.beans;

import java.io.Serializable;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.faces.bean.ManagedBean;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import pl.pawww.hurtowniasecond.entity.Produkt;
import pl.pawww.hurtowniasecond.util.DBManager;

/**
 *
 * @author R
 */
@ManagedBean(name = "produktManagedBean")
@SessionScoped
public class ProduktManagedBean implements Serializable {

    Produkt produkt = new Produkt();

    public ProduktManagedBean() {
    }

    public Produkt getProdukt() {
        return produkt;
    }

    public void setProdukt(Produkt produkt) {
        this.produkt = produkt;
    }

    public List<Produkt> getLista() {
        EntityManager em = DBManager.getManager().createEntityManager();
        TypedQuery<Produkt> query = em.createNamedQuery("Produkt.findAll", Produkt.class);
        List<Produkt> result = query.getResultList();
        em.close();
        return result;
    }
    public String dodaj(){
        EntityManager em = DBManager.getManager().createEntityManager();
        em.getTransaction().begin();
        em.persist(produkt);
        em.getTransaction().commit();
        em.close();
        return "../mojekonto.xhtml";
    }
}
