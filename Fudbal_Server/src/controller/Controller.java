/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import domen.Vlasnik;
import java.util.List;
import operacija.login.LoginOperacija;
import operacija.vlasnici.DodajVlasnikaSO;
import operacija.vlasnici.ObrisiVlasnikaSO;
import operacija.vlasnici.PrikaziVlasnikeOperacija;

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
}
