/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacija.osoba;

import domen.Kategorija;
import domen.Osoba;
import java.util.ArrayList;
import java.util.List;
import operacija.ApstraktnaGenerickaOperacija;

/**
 *
 * @author Alexa
 */
public class PretraziOsobeSO extends ApstraktnaGenerickaOperacija{
    List<Osoba> rezultatPretrage=new ArrayList<>();

    public List<Osoba> getRezultatPretrage() {
        return rezultatPretrage;
    }

    public void setRezultatPretrage(List<Osoba> rezultatPretrage) {
        this.rezultatPretrage = rezultatPretrage;
    }
    
    @Override
    protected void preduslovi(Object param) throws Exception {
        
    }

    @Override
    protected void izvrsiOperaciju(Object param, String kljuc) throws Exception {
        List <Osoba> lista=broker.getAll(param, " JOIN kategorija k ON k.idKategorija=osoba.idKategorija");
        Osoba uneteVrednosti=(Osoba) param;
        
        for (Osoba o : lista) {
            
            String ime=uneteVrednosti.getIme();
            String prezime=uneteVrednosti.getPrezime();
            String brojTelefona=uneteVrednosti.getBroj();
            String email=uneteVrednosti.getEmail();
            Kategorija kategorija=uneteVrednosti.getIdKategorija();
           
            System.out.println("Ime je "+ime);
             System.out.println("Prezime je "+prezime);
        boolean uslov = true;

        if (ime != null && !ime.isEmpty()) {
            if (!o.getIme().toLowerCase().equals(ime.toLowerCase())) {
                uslov = false;
                System.out.println("Uslo je u uslov za ime ");
            }
        }

        if (prezime != null && !prezime.isEmpty()) {
            if (!o.getPrezime().toLowerCase().equals(prezime.toLowerCase())) {
                uslov = false;
              System.out.println("Uslo je u uslov za prezime ");
            }
        }

        if (email != null && !email.isEmpty()) {
            if (!o.getEmail().toLowerCase().equals(email.toLowerCase())) {
                uslov = false;
               System.out.println("Uslo je u uslov za email ");
            }
        }

        if (kategorija != null) {
            if (o.getIdKategorija().getIdKategorija()!=  kategorija.getIdKategorija()) {
                uslov = false;
               System.out.println("Uslo je u uslov za kategoriju ");
            }
            
        }
        
        if (uslov) {
            rezultatPretrage.add(o);
        }
        
        
    }
        System.out.println("Rezultat pretrage je "+rezultatPretrage);
    }
    
}
