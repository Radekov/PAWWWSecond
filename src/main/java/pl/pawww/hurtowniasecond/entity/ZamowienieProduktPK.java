/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.pawww.hurtowniasecond.entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/**
 *
 * @author R
 */
@Embeddable
public class ZamowienieProduktPK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_PRODUKT")
    private int idProdukt;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_ZAMOWIENIE")
    private int idZamowienie;

    public ZamowienieProduktPK() {
    }

    public ZamowienieProduktPK(int idProdukt, int idZamowienie) {
        this.idProdukt = idProdukt;
        this.idZamowienie = idZamowienie;
    }

    public int getIdProdukt() {
        return idProdukt;
    }

    public void setIdProdukt(int idProdukt) {
        this.idProdukt = idProdukt;
    }

    public int getIdZamowienie() {
        return idZamowienie;
    }

    public void setIdZamowienie(int idZamowienie) {
        this.idZamowienie = idZamowienie;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) idProdukt;
        hash += (int) idZamowienie;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ZamowienieProduktPK)) {
            return false;
        }
        ZamowienieProduktPK other = (ZamowienieProduktPK) object;
        if (this.idProdukt != other.idProdukt) {
            return false;
        }
        if (this.idZamowienie != other.idZamowienie) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "pl.pawww.hurtowniasecond.ZamowienieProduktPK[ idProdukt=" + idProdukt + ", idZamowienie=" + idZamowienie + " ]";
    }
    
}
