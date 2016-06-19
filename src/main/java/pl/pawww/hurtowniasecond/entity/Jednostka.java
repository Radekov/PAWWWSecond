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
@Table(name = "JEDNOSTKA", catalog = "", schema = "APP")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Jednostka.findAll", query = "SELECT j FROM Jednostka j"),
    @NamedQuery(name = "Jednostka.findById", query = "SELECT j FROM Jednostka j WHERE j.id = :id"),
    @NamedQuery(name = "Jednostka.findByJednostka", query = "SELECT j FROM Jednostka j WHERE j.jednostka = :jednostka")})
public class Jednostka implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "JEDNOSTKA")
    private String jednostka;
    @OneToMany(mappedBy = "idJednostka")
    private Collection<Produkt> produktCollection;

    public Jednostka() {
    }

    public Jednostka(Integer id) {
        this.id = id;
    }

    public Jednostka(Integer id, String jednostka) {
        this.id = id;
        this.jednostka = jednostka;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getJednostka() {
        return jednostka;
    }

    public void setJednostka(String jednostka) {
        this.jednostka = jednostka;
    }

    @XmlTransient
    public Collection<Produkt> getProduktCollection() {
        return produktCollection;
    }

    public void setProduktCollection(Collection<Produkt> produktCollection) {
        this.produktCollection = produktCollection;
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
        if (!(object instanceof Jednostka)) {
            return false;
        }
        Jednostka other = (Jednostka) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "pl.pawww.hurtowniasecond.Jednostka[ id=" + id + " ]";
    }
    
}
