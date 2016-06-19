/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.pawww.hurtowniasecond.entity;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author R
 */
@Entity
@Table(name = "ZAMOWIENIE", catalog = "", schema = "APP")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Zamowienie.findAll", query = "SELECT z FROM Zamowienie z"),
    @NamedQuery(name = "Zamowienie.findById", query = "SELECT z FROM Zamowienie z WHERE z.id = :id"),
    @NamedQuery(name = "Zamowienie.findByDataZamowienia", query = "SELECT z FROM Zamowienie z WHERE z.dataZamowienia = :dataZamowienia"),
    @NamedQuery(name = "Zamowienie.findByDataZrealizowania", query = "SELECT z FROM Zamowienie z WHERE z.dataZrealizowania = :dataZrealizowania")})
public class Zamowienie implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "DATA_ZAMOWIENIA")
    @Temporal(TemporalType.DATE)
    private Date dataZamowienia;
    @Column(name = "DATA_ZREALIZOWANIA")
    @Temporal(TemporalType.DATE)
    private Date dataZrealizowania;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "zamowienie")
    private Collection<ZamowienieProdukt> zamowienieProduktCollection;
    @JoinColumn(name = "ID_UZYTKOWNIK", referencedColumnName = "ID")
    @ManyToOne
    private Uzytkownik idUzytkownik;

    public Zamowienie() {
    }

    public Zamowienie(Integer id) {
        this.id = id;
    }

    public Zamowienie(Integer id, Date dataZamowienia) {
        this.id = id;
        this.dataZamowienia = dataZamowienia;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getDataZamowienia() {
        return dataZamowienia;
    }

    public void setDataZamowienia(Date dataZamowienia) {
        this.dataZamowienia = dataZamowienia;
    }

    public Date getDataZrealizowania() {
        return dataZrealizowania;
    }

    public void setDataZrealizowania(Date dataZrealizowania) {
        this.dataZrealizowania = dataZrealizowania;
    }

    @XmlTransient
    public Collection<ZamowienieProdukt> getZamowienieProduktCollection() {
        return zamowienieProduktCollection;
    }

    public void setZamowienieProduktCollection(Collection<ZamowienieProdukt> zamowienieProduktCollection) {
        this.zamowienieProduktCollection = zamowienieProduktCollection;
    }

    public Uzytkownik getIdUzytkownik() {
        return idUzytkownik;
    }

    public void setIdUzytkownik(Uzytkownik idUzytkownik) {
        this.idUzytkownik = idUzytkownik;
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
        if (!(object instanceof Zamowienie)) {
            return false;
        }
        Zamowienie other = (Zamowienie) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "pl.pawww.hurtowniasecond.Zamowienie[ id=" + id + " ]";
    }
    
}
