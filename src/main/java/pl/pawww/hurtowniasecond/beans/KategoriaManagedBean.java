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
import pl.pawww.hurtowniasecond.entity.Kategoria;
import pl.pawww.hurtowniasecond.util.DBManager;

/**
 *
 * @author R
 */
@ManagedBean(name = "kategoriaManagedBean")
@RequestScoped
public class KategoriaManagedBean {

    /**
     * Creates a new instance of KategoriaManagedBean
     */
    ArrayList<Kategoria> kategoria = new ArrayList<>();

    public KategoriaManagedBean() {

    }

    public List<Kategoria> getKategoria() {
        EntityManager em = DBManager.getManager().createEntityManager();
        TypedQuery<Kategoria> query = em.createNamedQuery("Kategoria.findAll", Kategoria.class);
        List<Kategoria> result = query.getResultList();
        em.close();
        return result;
    }
}
