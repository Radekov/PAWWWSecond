/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.pawww.hurtowniasecond.beans;

import java.io.Serializable;
import java.util.Date;
import javax.enterprise.context.SessionScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import pl.pawww.hurtowniasecond.entity.Uzytkownik;
import pl.pawww.hurtowniasecond.entity.Zamowienie;
import pl.pawww.hurtowniasecond.util.DBManager;

/**
 *
 * @author R
 */
@ManagedBean(name = "zamowienieManagedBean")
@SessionScoped
public class ZamowienieManagedBean implements Serializable{
    private Zamowienie zamowienie = new Zamowienie();
    private Uzytkownik u = new Uzytkownik();
    public ZamowienieManagedBean() {
    }

    public Zamowienie getZamowienie() {
        return zamowienie;
    }

    public void setZamowienie(Zamowienie zamowienie) {
        this.zamowienie = zamowienie;
    }
    public void zamowienieListener(){
        String ids = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("uzytkownikID");
        int id = Integer.parseInt(ids);
        EntityManager em = DBManager.getManager().createEntityManager();
        this.u = em.find(Uzytkownik.class, id);
        ids = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("koszykID");
        id = Integer.parseInt(ids);
        this.zamowienie = em.find(Zamowienie.class, id);
        em.close();
    }
    public String zamow(){
        Zamowienie z = new Zamowienie();
        z.setDataZamowienia(new Date());
        z.setIdUzytkownik(u);
        System.out.println(u.getId());
        EntityManager em = DBManager.getManager().createEntityManager();
        em.getTransaction().begin();
        z.setZamowienieProduktCollection(zamowienie.getZamowienieProduktCollection());
        em.persist(z);
        em.getTransaction().commit();
        em.close();
        return null;
    }
}
