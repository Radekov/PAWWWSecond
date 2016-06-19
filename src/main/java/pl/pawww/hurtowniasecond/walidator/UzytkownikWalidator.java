/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.pawww.hurtowniasecond.walidator;

import javax.faces.validator.FacesValidator;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import pl.pawww.hurtowniasecond.entity.Uzytkownik;
import pl.pawww.hurtowniasecond.util.DBManager;

/**
 *DO poprawy
 * @author R
 */
@FacesValidator("uzytkownikWalidator")
public class UzytkownikWalidator {

    public static Boolean checkLogin(String login) {
        EntityManager em = DBManager.getManager().createEntityManager();
        TypedQuery<Uzytkownik> query = em.createNamedQuery("Uzytkownik.findByLogin", Uzytkownik.class);
        query.setParameter("login", login);
        Uzytkownik result = (Uzytkownik) query.getSingleResult();
        em.close();
        if (result == null) {
            return Boolean.TRUE;
        } else {
            return Boolean.FALSE;
        }
    }
}
