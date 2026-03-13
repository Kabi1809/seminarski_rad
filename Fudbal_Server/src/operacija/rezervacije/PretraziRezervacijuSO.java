/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacija.rezervacije;

import domen.Osoba;
import domen.Rezervacija;
import domen.Vlasnik;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import operacija.ApstraktnaGenerickaOperacija;

/**
 *
 * @author Alexa
 */
public class PretraziRezervacijuSO extends ApstraktnaGenerickaOperacija {
    List<Rezervacija> rezervacije=new ArrayList<>();

    public List<Rezervacija> getRezervacije() {
        return rezervacije;
    }

    public void setRezervacije(List<Rezervacija> rezervacije) {
        this.rezervacije = rezervacije;
    }
    
    @Override
    protected void preduslovi(Object param) throws Exception {
        
    }

    @Override
    protected void izvrsiOperaciju(Object param, String kljuc) throws Exception {
         List<Rezervacija> lista = broker.getAll(param,
    " JOIN osoba o ON rezervacija.idOsoba = o.idOsoba " +
    " JOIN vlasnik v ON v.idVlasnik = rezervacija.idVlasnik");
        Rezervacija uneteVrednosti=(Rezervacija) param;
        
        for (Rezervacija r : lista) {
            Date datum=uneteVrednosti.getDatum();
            double ukupanIznos=uneteVrednosti.getUkupanIznos();
            int satOd=uneteVrednosti.getSatOd();
            int satDo=uneteVrednosti.getSatDo();
            double popust=uneteVrednosti.getPopust();
            Vlasnik vlasnik=uneteVrednosti.getIdVlasnik();
            Osoba osoba=uneteVrednosti.getIdOsoba();
            int idRezervacija=uneteVrednosti.getIdRezervacija();
            System.out.println("ID je "+idRezervacija);
        boolean uslov = true;
        if (ukupanIznos != 0) {
           if (r.getUkupanIznos() != ukupanIznos) {
           uslov = false;
           System.out.println("Uslo je u uslov za kategoriju ");
            }
        }
        if (satOd != 0) {
           if (r.getSatOd()!= satOd) {
           uslov = false;
           System.out.println("Uslo je u uslov za kategoriju ");
            }
        }
        if (satDo != 0) {
           if (r.getSatDo() != satDo) {
           uslov = false;
           System.out.println("Uslo je u uslov za kategoriju ");
            }
        }
        if (popust!= 0) {
           if (r.getPopust() !=popust) {
           uslov = false;
           System.out.println("Uslo je u uslov za kategoriju ");
            }
        }
        if (idRezervacija!= 0) {
           if (r.getIdRezervacija()!=idRezervacija) {
           uslov = false;
           System.out.println("Uslo je u uslov za kategoriju ");
            }
        }
        if (datum != null) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String d1 = sdf.format(r.getDatum());
            String d2 = sdf.format(datum);
            if (!d1.equals(d2)) {
                uslov = false;
            }
        }
        if (vlasnik!= null) {
            if (r.getIdVlasnik().getIdVlasnik()!=vlasnik.getIdVlasnik()) {
                uslov = false;
               System.out.println("Uslo je u uslov za kategoriju ");
            }
            
        }
        if (osoba!= null) {
            if (r.getIdOsoba().getIdOsoba()!=osoba.getIdOsoba()) {
                uslov = false;
               System.out.println("Uslo je u uslov za kategoriju ");
            }
            
        }
        
        if (uslov) {
            rezervacije.add(r);
        }
        
        
    }
        System.out.println("Rezultat pretrage je "+rezervacije);
    }
    
}
