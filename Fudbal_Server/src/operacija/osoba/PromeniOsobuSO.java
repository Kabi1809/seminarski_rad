/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacija.osoba;

import domen.Osoba;
import operacija.ApstraktnaGenerickaOperacija;

/**
 *
 * @author Alexa
 */
public class PromeniOsobuSO extends ApstraktnaGenerickaOperacija{
    
    @Override
    protected void preduslovi(Object param) throws Exception {
              if (param == null || !(param instanceof Osoba)) {
            throw new Exception("Sistem ne moze da kreira osobu");
        }
        Osoba o = (Osoba) param;
        // **IME **
        if (o.getIme() == null || o.getIme().isEmpty()) {
            throw new Exception("Sistem ne moze da zapamti osobu 1");
        }
        for (int i = 0; i < o.getIme().length(); i++) {
            char c = o.getIme().charAt(i);
            if (!Character.isLetter(c)) {
                throw new Exception("Sistem ne moze da zapamti osobu IME");
            }
        }
        // ** PREZIME **
        if (o.getPrezime() == null || o.getPrezime().isEmpty()) {
            throw new Exception("Sistem ne moze da zapamti osobu 2");
        }
        for (int i = 0; i < o.getPrezime().length(); i++) {
            char c = o.getPrezime().charAt(i);
            if (!Character.isLetter(c)) {
                throw new Exception("Sistem ne moze da zapamti osobu PREZIME");
            }
        }
        // **BROJ TELEFONA **
        if (o.getBroj() == null || o.getBroj().isEmpty() || o.getBroj().length()<12 || o.getBroj().length()>14) {
            throw new Exception("Sistem ne moze da zapamti osobu 3");
        }
        for (int i = 0; i < o.getBroj().length(); i++) {
            char c = o.getBroj().charAt(i);
            if (!Character.isDigit(c)) {
                throw new Exception("Sistem ne moze da zapamti osobu -BROJ");
            }
        }
        
        // **EMAIL**
        if (o.getEmail()== null || o.getEmail().isEmpty() || o.getEmail().length()<=10) {
            throw new Exception("Sistem ne moze da zapamti osobu EMAIL 4 ");
        }
        if (o.getIdKategorija()== null ) {
            throw new Exception("Sistem ne moze da zapamti osobu");
        }
    }

    @Override
    protected void izvrsiOperaciju(Object param, String kljuc) throws Exception {
        broker.edit((Osoba)param);
    }
    
}
