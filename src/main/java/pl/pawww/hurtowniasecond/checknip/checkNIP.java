/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.pawww.hurtowniasecond.checknip;

/**
 *
 * @author R
 */
public class checkNIP {
    public static Boolean validNIP(String nip) {
        Integer suma = 0;
        if (nip.length() == 10) {
            //http://www.krs-online.com.pl/sprawdzanie_nip.html
            Integer cyfra = Character.getNumericValue(nip.charAt(0));
            suma += cyfra * 6;

            cyfra = Character.getNumericValue(nip.charAt(1));
            suma += cyfra * 5;

            cyfra = Character.getNumericValue(nip.charAt(2));
            suma += cyfra * 7;

            cyfra = Character.getNumericValue(nip.charAt(3));
            suma += cyfra * 2;

            cyfra = Character.getNumericValue(nip.charAt(4));
            suma += cyfra * 3;

            cyfra = Character.getNumericValue(nip.charAt(5));
            suma += cyfra * 4;

            cyfra = Character.getNumericValue(nip.charAt(6));
            suma += cyfra * 5;

            cyfra = Character.getNumericValue(nip.charAt(7));
            suma += cyfra * 6;

            cyfra = Character.getNumericValue(nip.charAt(8));
            suma += cyfra * 7;

            cyfra = Character.getNumericValue(nip.charAt(9));
            suma = suma % 11;
            if (suma.equals(cyfra)) {
                return Boolean.TRUE;
            }
        }
        return Boolean.FALSE;
    }
}
