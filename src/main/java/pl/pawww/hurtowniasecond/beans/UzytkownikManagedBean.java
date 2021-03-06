/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.pawww.hurtowniasecond.beans;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import javax.servlet.http.HttpSession;
import org.primefaces.context.RequestContext;
import pl.pawww.hurtowniasecond.checknip.checkNIP;
import pl.pawww.hurtowniasecond.entity.Produkt;
import pl.pawww.hurtowniasecond.entity.Uzytkownik;
import pl.pawww.hurtowniasecond.entity.Zamowienie;
import pl.pawww.hurtowniasecond.entity.ZamowienieProdukt;
import pl.pawww.hurtowniasecond.hash.HashPassword;
import pl.pawww.hurtowniasecond.util.DBManager;

/**
 *
 * @author R
 */
@ManagedBean(name = "uzytkownikManagedBean")
@SessionScoped
public class UzytkownikManagedBean implements Serializable {

    /**
     * Creates a new instance of UzytkownikManagedBean
     */
    Uzytkownik uzytkownik = new Uzytkownik();

    public Uzytkownik getUzytkownik() {
        return uzytkownik;
    }

    public void setUzytkownik(Uzytkownik uzytkownik) {
        this.uzytkownik = uzytkownik;
    }

    public UzytkownikManagedBean() {
    }

    public String login() {
        EntityManager em = DBManager.getManager().createEntityManager();
        TypedQuery<Uzytkownik> query = em.createNamedQuery("Uzytkownik.findByLogin", Uzytkownik.class);
        query.setParameter("login", uzytkownik.getLogin());
        Uzytkownik findu = null;;
        try{
            findu = (Uzytkownik) query.getSingleResult();
        }
        catch(NoResultException e){
            
        }
        em.close();
        String haslo = null;
        if (findu != null) {
            try {
                haslo = HashPassword.hash(uzytkownik.getHaslo());
            } catch (NoSuchAlgorithmException ex) {
                System.out.println("Cos nie ma SHA-256");
                Logger.getLogger(UzytkownikManagedBean.class.getName()).log(Level.SEVERE, null, ex);
            } catch (UnsupportedEncodingException ex) {
                System.out.println("Cos nie moze na UTF-8 zrobic");
                Logger.getLogger(UzytkownikManagedBean.class.getName()).log(Level.SEVERE, null, ex);
            }
            if (haslo != null && haslo.equals(findu.getHaslo())) {
                RequestContext context = RequestContext.getCurrentInstance();
                boolean loggedIn = true;
                uzytkownik = findu;
                System.out.println(uzytkownik.getLogin());
                System.out.println(uzytkownik.getNazwaSpolki());
                System.out.println(uzytkownik.getIdOstatnie()+"  yedg");
                context.addCallbackParam("loggedIn", loggedIn);
                FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("uzytkownik", uzytkownik);
                this.dodajKomunikat("Zalogowałeś się");
                return "restricted/mojekonto.xhtml";
            }
        }
        if (findu == null) {
            this.dodajKomunikat("Taki login nie istnieje");
        }
        if (findu != null) {
            this.dodajKomunikat("Złe hasło");
        }
        return null;
    }

    public String zarejestruj() {
        try {
            uzytkownik.setHaslo(HashPassword.hash(uzytkownik.getHaslo()));
        } catch (NoSuchAlgorithmException ex) {
            System.out.println("Nie znalazło SHA-256");
            Logger.getLogger(UzytkownikManagedBean.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedEncodingException ex) {
            System.out.println("Problem z konwertowaniem na UTF-8 ");
            Logger.getLogger(UzytkownikManagedBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (checkNIP.validNIP(uzytkownik.getNip())) {
            EntityManager em = DBManager.getManager().createEntityManager();
            Zamowienie zamowienie = new Zamowienie();
            zamowienie.setDataZamowienia(new Date());
            em.getTransaction().begin();
            em.persist(zamowienie);
            uzytkownik.setIdOstatnie(zamowienie.getId());
            em.persist(uzytkownik);
            em.getTransaction().commit();
            this.dodajKomunikat("Pomyślnie zarejestrowano");
            em.close();

            FacesContext fc = FacesContext.getCurrentInstance();
            HttpSession httpSession = (HttpSession) fc.getExternalContext().getSession(true);
            httpSession.setAttribute("logg", uzytkownik);
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("uzytkownik", uzytkownik);
            return "restricted/mojekonto.xhtml";
        }
        this.dodajKomunikat("Nie zarejestrowano");
        return null;
    }

    public void dodajKomunikat(String kom) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, kom, ""));
    }
    public Zamowienie getKoszyk(){
        if(uzytkownik.getIdOstatnie() == null)
            return null;
        EntityManager em = DBManager.getManager().createEntityManager();
        Zamowienie koszyk = em.find(Zamowienie.class, uzytkownik.getIdOstatnie());
        em.close();
        return koszyk;
    }
    public List<ZamowienieProdukt> getProdukty(){
        Zamowienie koszyk = getKoszyk();
        Integer idZamowienie = koszyk.getId();
        EntityManager em = DBManager.getManager().createEntityManager();
        TypedQuery<ZamowienieProdukt> query = em.createNamedQuery("ZamowienieProdukt.findByIdZamowienie", ZamowienieProdukt.class);
        query.setParameter("idZamowienie", idZamowienie);
        List<ZamowienieProdukt> findKoszyk = null;
        findKoszyk = query.getResultList();
        em.close();
        return findKoszyk;
    }
    public String getSumaKoszyk(){
        List<ZamowienieProdukt> kosz = getProdukty();
        BigDecimal suma = BigDecimal.ZERO;
        for(ZamowienieProdukt zp:kosz){
            suma = suma.add(zp.getCena());
        }
        return suma.toString();
    }
}
