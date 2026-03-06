/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import domen.Kategorija;
import domen.Osoba;
import domen.Smena;
import domen.VlSm;
import domen.Vlasnik;
import java.util.List;
import operacija.login.LoginOperacija;
import operacija.osoba.KreirajOsobuSO;
import operacija.osoba.PromeniOsobuSO;
import operacija.smena.UbaciVlSmSO;
import operacija.smena.UcitajSmeneSO;
import operacija.vlasnici.AzurirajVlasnikaSO;
import operacija.vlasnici.DodajVlasnikaSO;
import operacija.vlasnici.ObrisiVlasnikaSO;
import operacija.vlasnici.PrikaziVlasnikeOperacija;
import operacije.kategorija.UcitajKategorijeSO;

/**
 *
 * @author Alexa
 */
public class Controller {
    private static Controller instance;
    
    private Controller(){
        
    }
    public static Controller getInstance(){
        if(instance==null){
            instance=new Controller();
        }
        return instance;
    }

    public Vlasnik login(Vlasnik v) throws Exception { 
        LoginOperacija operacija = new LoginOperacija();
        operacija.izvrsi(v, null);
        System.out.println("KLASA CONTROLLER login: " + operacija.getVlasnik());
        return operacija.getVlasnik();
        
    }

    public List<Vlasnik> prikaziVlasnike() throws Exception {
      PrikaziVlasnikeOperacija operacija=new PrikaziVlasnikeOperacija();
      operacija.izvrsi(null, null);
      
      System.out.println("KLASA KONTROLER prikaziVlasnike: "+operacija.getVlasnici());
      return operacija.getVlasnici();
    }

    public void obrisiVlasnika(Vlasnik vlasnik) throws Exception {
        ObrisiVlasnikaSO operacija=new ObrisiVlasnikaSO();
        operacija.izvrsi(vlasnik, null);
    }

    public void dodajVlasnika(Vlasnik vlasnik) throws Exception {
        DodajVlasnikaSO operacija=new DodajVlasnikaSO();
        operacija.izvrsi(vlasnik, null); //--- ova metoda se izvrsava u okz
    }

    public void azurirajVlasnika(Vlasnik vlasnikA) throws Exception {
        AzurirajVlasnikaSO operacija=new AzurirajVlasnikaSO();
        operacija.izvrsi(vlasnikA, null);
    }

    public List<Smena> ucitajSmene() throws Exception {
        UcitajSmeneSO operacija=new UcitajSmeneSO();
        operacija.izvrsi(null, null);
        System.out.println("KLASA CONTROLLER ucitajSmene: " + operacija.getSmene());
        return operacija.getSmene();
    }

    public void ubaciVlSm(VlSm vs) throws Exception {
        UbaciVlSmSO operacija = new UbaciVlSmSO();
        operacija.izvrsi(vs, null);
        System.out.println("KASA CONTROLLER ubaciGrumerSmena: " + vs);
    }

    public List<Kategorija> ucitajKategorije() throws Exception {
        UcitajKategorijeSO operacija=new UcitajKategorijeSO();
        operacija.izvrsi(null, null);
        System.out.println("KLASA CONTROLLER ucitajKategorije: " + operacija.getListaKategorija());
        return operacija.getListaKategorija();
    }

    public void dodajOsobu(Osoba o) throws Exception {
        KreirajOsobuSO operacija=new KreirajOsobuSO();
        operacija.izvrsi(o, null);
        System.out.println("KLASA CONTROLLER dodajVlasnika: " + o);
    }

    public void PromeniOsobu(Osoba oPromeni) throws Exception {
        PromeniOsobuSO operacija=new PromeniOsobuSO();
        operacija.izvrsi(oPromeni, null);
    }
}
