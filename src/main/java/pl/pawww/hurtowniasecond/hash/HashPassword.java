/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.pawww.hurtowniasecond.hash;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 *
 * @author R
 */
public class HashPassword {

    public static String hash(String haslo) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        if (haslo != null) {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(haslo.getBytes("UTF-8"));
            byte[] hashhaslo = md.digest();
            return String.format(String.format("%064x", new java.math.BigInteger(1, hashhaslo)));
        } else {
            return " ";
        }
    }
}
