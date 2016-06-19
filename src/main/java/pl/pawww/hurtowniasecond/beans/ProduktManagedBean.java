/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.pawww.hurtowniasecond.beans;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import pl.pawww.hurtowniasecond.entity.Produkt;
import pl.pawww.hurtowniasecond.util.DBManager;

/**
 *
 * @author R
 */
public class ProduktManagedBean {

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
}
