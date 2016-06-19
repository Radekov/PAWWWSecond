/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.pawww.hurtowniasecond.entity;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author R
 */
@Entity
@Table(name = "UZYTKOWNIK", catalog = "", schema = "APP")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Uzytkownik.findAll", query = "SELECT u FROM Uzytkownik u"),
    @NamedQuery(name = "Uzytkownik.findById", query = "SELECT u FROM Uzytkownik u WHERE u.id = :id"),
    @NamedQuery(name = "Uzytkownik.findByHaslo", query = "SELECT u FROM Uzytkownik u WHERE u.haslo = :haslo"),
    @NamedQuery(name = "Uzytkownik.findByIdOstatnie", query = "SELECT u FROM Uzytkownik u WHERE u.idOstatnie = :idOstatnie"),
    @NamedQuery(name = "Uzytkownik.findByLogin", query = "SELECT u FROM Uzytkownik u WHERE u.login = :login"),
    @NamedQuery(name = "Uzytkownik.findByNazwaSpolki", query = "SELECT u FROM Uzytkownik u WHERE u.nazwaSpolki = :nazwaSpolki"),
    @NamedQuery(name = "Uzytkownik.findByNip", query = "SELECT u FROM Uzytkownik u WHERE u.nip = :nip")})
public class Uzytkownik implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Size(max = 255)
    @Column(name = "HASLO")
    private String haslo;
    @Column(name = "ID_OSTATNIE")
    private Integer idOstatnie;
    @Size(max = 255)
    @Column(name = "LOGIN")
    private String login;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "NAZWA_SPOLKI")
    private String nazwaSpolki;
    @Size(max = 255)
    @Column(name = "NIP")
    private String nip;
    @OneToMany(mappedBy = "idUzytkownik")
    private Collection<Zamowienie> zamowienieCollection;

    public Uzytkownik() {
    }

    public Uzytkownik(Integer id) {
        this.id = id;
    }

    public Uzytkownik(Integer id, String nazwaSpolki) {
        this.id = id;
        this.nazwaSpolki = nazwaSpolki;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getHaslo() {
        return haslo;
    }

    public void setHaslo(String haslo) {
        this.haslo = haslo;
    }

    public Integer getIdOstatnie() {
        return idOstatnie;
    }

    public void setIdOstatnie(Integer idOstatnie) {
        this.idOstatnie = idOstatnie;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getNazwaSpolki() {
        return nazwaSpolki;
    }

    public void setNazwaSpolki(String nazwaSpolki) {
        this.nazwaSpolki = nazwaSpolki;
    }

    public String getNip() {
        return nip;
    }

    public void setNip(String nip) {
        this.nip = nip;
    }

    @XmlTransient
    public Collection<Zamowienie> getZamowienieCollection() {
        return zamowienieCollection;
    }

    public void setZamowienieCollection(Collection<Zamowienie> zamowienieCollection) {
        this.zamowienieCollection = zamowienieCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Uzytkownik)) {
            return false;
        }
        Uzytkownik other = (Uzytkownik) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "pl.pawww.hurtowniasecond.Uzytkownik[ id=" + id + " ]";
    }
    
}
