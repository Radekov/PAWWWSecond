/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.pawww.hurtowniasecond.beans;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import pl.pawww.hurtowniasecond.entity.Produkt;
import pl.pawww.hurtowniasecond.entity.Uzytkownik;
import pl.pawww.hurtowniasecond.entity.Zamowienie;
import pl.pawww.hurtowniasecond.entity.ZamowienieProdukt;
import pl.pawww.hurtowniasecond.entity.ZamowienieProduktPK;
import pl.pawww.hurtowniasecond.util.DBManager;

/**
 *
 * @author R
 */
@ManagedBean(name = "zamowienieProduktManagedBean")
@RequestScoped
public class ZamowienieProduktManagedBean implements Serializable {

    ZamowienieProdukt zamowienieProdukt = new ZamowienieProdukt();
    Zamowienie koszyk;
    Produkt produkt;

    public ZamowienieProduktManagedBean() {
    }

    public ZamowienieProdukt getZamowienieProdukt() {
        return zamowienieProdukt;
    }

    public void setZamowienieProdukt(ZamowienieProdukt zamowienieProdukt) {
        this.zamowienieProdukt = zamowienieProdukt;
    }

    public void dodajDoKoszyka() {
        System.out.println("Tyle produktow " + zamowienieProdukt.getIlosc());
        BigDecimal cena = BigDecimal.ZERO;
        cena = produkt.getCena().multiply(new BigDecimal(zamowienieProdukt.getIlosc()));
        System.out.println("Cena prodktu: " + produkt.getCena());
        System.out.println(cena);
        zamowienieProdukt.setCena(cena);
        zamowienieProdukt.setZamowienieProduktPK(new ZamowienieProduktPK(produkt.getId(), koszyk.getId()));
        EntityManager em = DBManager.getManager().createEntityManager();
        em.getTransaction().begin();
        em.persist(zamowienieProdukt);
        em.getTransaction().commit();
        em.close();
        addMessage("Dodano do koszyka");
    }

    public void buttonAction(ActionEvent actionEvent) {
        addMessage("Dodano do koszyka");
    }

    public void addMessage(String summary) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary, null);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    public void produktKoszykListener() {
        String ids = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("produktID");
        int id = Integer.parseInt(ids);
        EntityManager em = DBManager.getManager().createEntityManager();
        this.produkt = em.find(Produkt.class, id);
        System.out.println(this.produkt.getCena());
        ids = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("koszykID");
        id = Integer.parseInt(ids);
        this.koszyk = em.find(Zamowienie.class, id);
        em.close();
    }

    public void usun() {
        EntityManager em = DBManager.getManager().createEntityManager();
        TypedQuery<ZamowienieProdukt> query = em.createNamedQuery("ZamowienieProdukt.findByBoth", ZamowienieProdukt.class);
        query.setParameter("idProdukt", produkt.getId());
        query.setParameter("idZamowienie", koszyk.getId());
        try {
            zamowienieProdukt = (ZamowienieProdukt) query.getSingleResult();
            em.getTransaction().begin();
            //zamowienieProdukt = em.merge(zamowienieProdukt);
            em.remove(zamowienieProdukt);
            em.getTransaction().commit();

        } catch (NoResultException e) {

        }
        em.close();
    }

}
