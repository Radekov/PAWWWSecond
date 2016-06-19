/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.pawww.hurtowniasecond.converters;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;
import javax.persistence.EntityManager;
import pl.pawww.hurtowniasecond.entity.Jednostka;
import pl.pawww.hurtowniasecond.util.DBManager;

/**
 *
 * @author R
 */
@FacesConverter("jednostkaConverter")
public class JednostkaKonwerter implements Converter{
    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object o) {
        if (!(o instanceof Jednostka)) {
            throw new ConverterException(new FacesMessage("Nie udalo sie dokonac konwersji"));
        }
        Jednostka jednostka = (Jednostka) o;
        return jednostka.getId().toString();
    }

    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String string) {
        Integer i = Integer.valueOf(string);
        EntityManager em = DBManager.getManager().createEntityManager();
        Jednostka jednostka = em.find(Jednostka.class, i);
        em.close();
        return jednostka;
    }
}
