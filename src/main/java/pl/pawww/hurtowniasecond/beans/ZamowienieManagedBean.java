/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.pawww.hurtowniasecond.beans;

import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.faces.bean.ManagedBean;
import pl.pawww.hurtowniasecond.entity.Zamowienie;

/**
 *
 * @author R
 */
@ManagedBean(name = "zamowienieManagedBean")
@SessionScoped
public class ZamowienieManagedBean implements Serializable{
    private Zamowienie zamowienie = new Zamowienie();

    public ZamowienieManagedBean() {
    }

    public Zamowienie getZamowienie() {
        return zamowienie;
    }

    public void setZamowienie(Zamowienie zamowienie) {
        this.zamowienie = zamowienie;
    }
    
}
