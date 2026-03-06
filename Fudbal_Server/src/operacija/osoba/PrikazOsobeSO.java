/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacija.osoba;

import domen.Osoba;
import java.util.List;
import operacija.ApstraktnaGenerickaOperacija;

/**
 *
 * @author Alexa
 */
public class PrikazOsobeSO extends ApstraktnaGenerickaOperacija{
    List<Osoba> osobe;

    public List<Osoba> getOsobe() {
        return osobe;
    }
    @Override
    protected void preduslovi(Object param) throws Exception {
        
    }

    @Override
    protected void izvrsiOperaciju(Object param, String kljuc) throws Exception {
        String uslov =" JOIN kategorija k ON k.idKategorija=osoba.idKategorija";
        osobe=broker.getAll(new Osoba(), uslov);  
    }
    
}
