/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacija.vlasnici;

import domen.Vlasnik;
import operacija.ApstraktnaGenerickaOperacija;

/**
 *
 * @author Alexa
 */
public class DodajVlasnikaSO extends ApstraktnaGenerickaOperacija {

    @Override
    protected void preduslovi(Object param) throws Exception {
         if (param == null || !(param instanceof Vlasnik)) {
            throw new Exception("Sistem nije mogao da doda vlasnika");
        }
        
        Vlasnik v= (Vlasnik)param;
        if(v.getIme()==null || v.getIme().isEmpty() || v.getŠifra().length()<3){
             throw new Exception("Sistem nije mogao da doda vlasnika, nisu ispunjeni uslovi");
        }
    }

    @Override
    protected void izvrsiOperaciju(Object param, String kljuc) throws Exception {
        broker.add((Vlasnik)param);
    }
    
}
