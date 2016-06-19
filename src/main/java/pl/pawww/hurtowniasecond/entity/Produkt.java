/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.pawww.hurtowniasecond.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collection;
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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author R
 */
@Entity
@Table(name = "PRODUKT", catalog = "", schema = "APP")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Produkt.findAll", query = "SELECT p FROM Produkt p"),
    @NamedQuery(name = "Produkt.findById", query = "SELECT p FROM Produkt p WHERE p.id = :id"),
    @NamedQuery(name = "Produkt.findByCena", query = "SELECT p FROM Produkt p WHERE p.cena = :cena"),
    @NamedQuery(name = "Produkt.findByIlosc", query = "SELECT p FROM Produkt p WHERE p.ilosc = :ilosc"),
    @NamedQuery(name = "Produkt.findByNazwa", query = "SELECT p FROM Produkt p WHERE p.nazwa = :nazwa")})
public class Produkt implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "CENA")
    private BigDecimal cena;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ILOSC")
    private int ilosc;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "NAZWA")
    private String nazwa;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "produkt")
    private Collection<ZamowienieProdukt> zamowienieProduktCollection;
    @JoinColumn(name = "ID_JEDNOSTKA", referencedColumnName = "ID")
    @ManyToOne
    private Jednostka idJednostka;
    @JoinColumn(name = "ID_KATEGORIA", referencedColumnName = "ID")
    @ManyToOne
    private Kategoria idKategoria;

    public Produkt() {
    }

    public Produkt(Integer id) {
        this.id = id;
    }

    public Produkt(Integer id, int ilosc, String nazwa) {
        this.id = id;
        this.ilosc = ilosc;
        this.nazwa = nazwa;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public BigDecimal getCena() {
        return cena;
    }

    public void setCena(BigDecimal cena) {
        this.cena = cena;
    }

    public int getIlosc() {
        return ilosc;
    }

    public void setIlosc(int ilosc) {
        this.ilosc = ilosc;
    }

    public String getNazwa() {
        return nazwa;
    }

    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }

    @XmlTransient
    public Collection<ZamowienieProdukt> getZamowienieProduktCollection() {
        return zamowienieProduktCollection;
    }

    public void setZamowienieProduktCollection(Collection<ZamowienieProdukt> zamowienieProduktCollection) {
        this.zamowienieProduktCollection = zamowienieProduktCollection;
    }

    public Jednostka getIdJednostka() {
        return idJednostka;
    }

    public void setIdJednostka(Jednostka idJednostka) {
        this.idJednostka = idJednostka;
    }

    public Kategoria getIdKategoria() {
        return idKategoria;
    }

    public void setIdKategoria(Kategoria idKategoria) {
        this.idKategoria = idKategoria;
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
        if (!(object instanceof Produkt)) {
            return false;
        }
        Produkt other = (Produkt) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "pl.pawww.hurtowniasecond.Produkt[ id=" + id + " ]";
    }
    
}
