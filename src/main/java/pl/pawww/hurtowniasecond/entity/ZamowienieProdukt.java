/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.pawww.hurtowniasecond.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author R
 */
@Entity
@Table(name = "ZAMOWIENIE_PRODUKT", catalog = "", schema = "APP")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ZamowienieProdukt.findAll", query = "SELECT z FROM ZamowienieProdukt z"),
    @NamedQuery(name = "ZamowienieProdukt.findByIdProdukt", query = "SELECT z FROM ZamowienieProdukt z WHERE z.zamowienieProduktPK.idProdukt = :idProdukt"),
    @NamedQuery(name = "ZamowienieProdukt.findByIdZamowienie", query = "SELECT z FROM ZamowienieProdukt z WHERE z.zamowienieProduktPK.idZamowienie = :idZamowienie"),
    @NamedQuery(name = "ZamowienieProdukt.findByCena", query = "SELECT z FROM ZamowienieProdukt z WHERE z.cena = :cena"),
    @NamedQuery(name = "ZamowienieProdukt.findByIlosc", query = "SELECT z FROM ZamowienieProdukt z WHERE z.ilosc = :ilosc")})
public class ZamowienieProdukt implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected ZamowienieProduktPK zamowienieProduktPK;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "CENA")
    private BigDecimal cena;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ILOSC")
    private int ilosc;
    @JoinColumn(name = "ID_PRODUKT", referencedColumnName = "ID", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Produkt produkt;
    @JoinColumn(name = "ID_ZAMOWIENIE", referencedColumnName = "ID", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Zamowienie zamowienie;

    public ZamowienieProdukt() {
    }

    public ZamowienieProdukt(ZamowienieProduktPK zamowienieProduktPK) {
        this.zamowienieProduktPK = zamowienieProduktPK;
    }

    public ZamowienieProdukt(ZamowienieProduktPK zamowienieProduktPK, int ilosc) {
        this.zamowienieProduktPK = zamowienieProduktPK;
        this.ilosc = ilosc;
    }

    public ZamowienieProdukt(int idProdukt, int idZamowienie) {
        this.zamowienieProduktPK = new ZamowienieProduktPK(idProdukt, idZamowienie);
    }

    public ZamowienieProduktPK getZamowienieProduktPK() {
        return zamowienieProduktPK;
    }

    public void setZamowienieProduktPK(ZamowienieProduktPK zamowienieProduktPK) {
        this.zamowienieProduktPK = zamowienieProduktPK;
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

    public Produkt getProdukt() {
        return produkt;
    }

    public void setProdukt(Produkt produkt) {
        this.produkt = produkt;
    }

    public Zamowienie getZamowienie() {
        return zamowienie;
    }

    public void setZamowienie(Zamowienie zamowienie) {
        this.zamowienie = zamowienie;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (zamowienieProduktPK != null ? zamowienieProduktPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ZamowienieProdukt)) {
            return false;
        }
        ZamowienieProdukt other = (ZamowienieProdukt) object;
        if ((this.zamowienieProduktPK == null && other.zamowienieProduktPK != null) || (this.zamowienieProduktPK != null && !this.zamowienieProduktPK.equals(other.zamowienieProduktPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "pl.pawww.hurtowniasecond.ZamowienieProdukt[ zamowienieProduktPK=" + zamowienieProduktPK + " ]";
    }
    
}
