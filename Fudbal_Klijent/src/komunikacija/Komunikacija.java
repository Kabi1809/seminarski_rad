/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package komunikacija;

import domen.Vlasnik;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Alexa
 */
public class Komunikacija {
    private Socket soket;
    private Posiljalac posiljalac;
    private Primalac primalac;

    private static Komunikacija instance;
    private Komunikacija(){
        
    }
    public static Komunikacija getInstance(){
        if(instance==null){
            instance=new Komunikacija();
        }
        return instance;
    }
    public void konekcija() {
        try {
        soket = new Socket("localhost", 9000);
        posiljalac = new Posiljalac(soket);
        primalac = new Primalac(soket);
        
        } catch (Exception e) {
            System.out.println("Server nije povezan");
        }
    }

    public Vlasnik login(String username, String password) throws Exception {
        Vlasnik vlasnik = new Vlasnik();
        vlasnik.setKorisničkoIme(username);
        vlasnik.setŠifra(password);

        KlijentskiZahtev zahtev = new KlijentskiZahtev(Operacija.LOGIN, vlasnik);
        posiljalac.posalji(zahtev);

        ServerskiOdgovor odg = (ServerskiOdgovor) primalac.primi();
        if (odg.getOdgovor() instanceof Exception) {
            throw (Exception) odg.getOdgovor();
        }
        return (Vlasnik) odg.getOdgovor();
    }

    public List<Vlasnik> ucitajVlasnika() {
        KlijentskiZahtev zahtev = new KlijentskiZahtev(Operacija.UCITAJ_VLASNIKE, null);
        posiljalac.posalji(zahtev);
        ServerskiOdgovor odg = (ServerskiOdgovor) primalac.primi();
        if (odg.getOdgovor() == null) return new ArrayList<>();
        return (List<Vlasnik>) odg.getOdgovor();
    }

    public void obrisiVlasnika(Vlasnik v) throws Exception {
        posaljiZahtevSaExceptionom(Operacija.OBRISI_VLASNIKA, v);
    }

    private void posaljiZahtevSaExceptionom(Operacija operacija, Object param) throws Exception {
        KlijentskiZahtev zahtev = new KlijentskiZahtev(operacija, param);//vidi???
        posiljalac.posalji(zahtev);
        ServerskiOdgovor odg = (ServerskiOdgovor) primalac.primi();
        if (odg.getOdgovor() != null) {
            throw (odg.getOdgovor() instanceof Exception) 
                  ? (Exception) odg.getOdgovor() 
                  : new Exception((String) odg.getOdgovor());
        }
    }

    public void dodajVlasnika(Vlasnik v) throws Exception {
        posaljiZahtevSaExceptionom(Operacija.DODAJ_VLASNIKA, v);
    }
    }
    

