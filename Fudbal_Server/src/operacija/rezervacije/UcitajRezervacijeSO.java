/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacija.rezervacije;

import domen.Rezervacija;
import domen.StavkaRezervacije;
import java.util.List;
import operacija.ApstraktnaGenerickaOperacija;

/**
 *
 * @author Alexa
 */
public class UcitajRezervacijeSO extends ApstraktnaGenerickaOperacija{
     List<Rezervacija> rezervacije;

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
          String uslov = " JOIN vlasnik v ON v.idVlasnik=rezervacija.idVlasnik " +
                   "JOIN osoba o ON o.idOsoba=rezervacija.idOsoba";
         rezervacije = broker.getAll(new Rezervacija(), uslov);
         for (Rezervacija r : rezervacije) {
        String uslovStavke = " JOIN usluga u ON u.idUsluga = stavkarezervacije.idUsluga " +
                             "WHERE idRezervacija=" + r.getIdRezervacija();
        List<StavkaRezervacije> stavke = broker.getAll(new StavkaRezervacije(), uslovStavke);
        r.setStavke(stavke);
    }
    }
    
}
