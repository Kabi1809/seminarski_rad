/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacije.kategorija;

import domen.Kategorija;
import java.util.List;
import operacija.ApstraktnaGenerickaOperacija;

/**
 *
 * @author Alexa
 */
public class UcitajKategorijeSO extends ApstraktnaGenerickaOperacija {
    List<Kategorija> listaKategorija;

    public List<Kategorija> getListaKategorija() {
        return listaKategorija;
    }

    public void setListaKategorija(List<Kategorija> listaKategorija) {
        this.listaKategorija = listaKategorija;
    }
    
    @Override
    protected void preduslovi(Object param) throws Exception {
        
    }

    @Override
    protected void izvrsiOperaciju(Object param, String kljuc) throws Exception {
        listaKategorija=broker.getAll(new Kategorija(), "");
    }
    
}
