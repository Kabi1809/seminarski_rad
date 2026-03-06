/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cordinator;

import domen.Vlasnik;
import forme.DodajOsobuForma;
import forme.DodajVlasnikaForma;
import forme.FormaMod;
import forme.GlavnaForma;
import forme.LoginForma;
import forme.PrikazOsobeForma;
import forme.PrikazVlasnikaForma;
import forme.UbaciSmenaForma;
import java.util.HashMap;
import java.util.Map;
import kontroleri.DodajOsobuController;
import kontroleri.DodajVlasnikaController;
import kontroleri.GlavnaFormaController;
import kontroleri.LoginController;
import kontroleri.PrikazOsobeController;
import kontroleri.PrikazVlasnikaController;
import kontroleri.UbaciSmenuController;

/**
 *
 * @author Alexa
 */
public class Cordinator {
     private static Cordinator instance;
     private Vlasnik ulogovaniVlasnik;
     private LoginController loginController;
     private GlavnaFormaController glavnaFormaController;
     private PrikazVlasnikaController prikazVlasnikaController;
     private DodajVlasnikaController dodajVlasnikaController;
     private Map<String, Object> parametri;
     private UbaciSmenuController uscontroller;
     private DodajOsobuController dodajOsobuController;
     private PrikazOsobeController prikazOsobeController;
     private Cordinator(){
         parametri = new HashMap<>();
     }
     public static Cordinator getInstance() {
        if (instance == null) {
            instance = new Cordinator();
        }
        return instance;
    }

    public void otvoriLoginFormu() {
        loginController=new LoginController(new LoginForma());
        loginController.otvoriFormu();
    }

    public void otvoriGlavnuFormu() {
        glavnaFormaController=new GlavnaFormaController(new GlavnaForma());
        glavnaFormaController.otvoriFormu();
    }

    public Vlasnik getUlogovaniVlasnik() {
        return ulogovaniVlasnik;
    }

    public void setUlogovaniVlasnik(Vlasnik ulogovaniVlasnik) {
        this.ulogovaniVlasnik = ulogovaniVlasnik;
    }

    public void otvoriPrikazVlasnikaFormu() {
        prikazVlasnikaController = new PrikazVlasnikaController(new PrikazVlasnikaForma());
        prikazVlasnikaController.otvoriFormu();
    }

    public void otvoriDodajVlasnikaFormu() {
        dodajVlasnikaController = new DodajVlasnikaController(new DodajVlasnikaForma());
        dodajVlasnikaController.otvoriFormu(FormaMod.DODAJ);
    }
    public Object vratiParam(String s) {
        return parametri.get(s);
    }
    public void dodajParam(String s, Object o) {
        parametri.put(s, o);
    }

    public void otvoriIzmeniVlasnikaFormu() {
        dodajVlasnikaController = new DodajVlasnikaController(new DodajVlasnikaForma());
        dodajVlasnikaController.otvoriFormu(FormaMod.PROMENI);
    }

    public void osveziFormu() {
        if(prikazVlasnikaController!=null){
            prikazVlasnikaController.osveziFormu();
        }
    }

    public void otvoriDodajSmenuForma() {
        uscontroller = new UbaciSmenuController(new UbaciSmenaForma());
        uscontroller.otvoriFormu();
    }

    public void otvoriDodajOsobuFormu() throws Exception {
        dodajOsobuController=new DodajOsobuController(new DodajOsobuForma());
        dodajOsobuController.otvoriFormu(FormaMod.DODAJ);
    }

    public void otvoriPromeniOsobuFormu() throws Exception {
        dodajOsobuController = new DodajOsobuController(new DodajOsobuForma());
        dodajOsobuController.otvoriFormu(FormaMod.PROMENI);
    }
    public void otvoriObrisiOsobuFormu() throws Exception {
        dodajOsobuController = new DodajOsobuController(new DodajOsobuForma());
        dodajOsobuController.otvoriFormu(FormaMod.OBRISI);
    }

    public void otvoriPrikazOsobeFormu() {
        prikazOsobeController = new PrikazOsobeController(new PrikazOsobeForma());
        prikazOsobeController.otvoriFormu();
    }

    public PrikazOsobeController getPrikazOsobeController() {
        return prikazOsobeController;
    }

    public void setPrikazOsobeController(PrikazOsobeController prikazOsobeController) {
        this.prikazOsobeController = prikazOsobeController;
    }

}
