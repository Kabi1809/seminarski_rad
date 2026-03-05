/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package komunikacija;

import domen.Vlasnik;
import java.io.IOException;
import java.net.Socket;

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
    
}
