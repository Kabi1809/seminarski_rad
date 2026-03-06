/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package komunikacija;

import cordinator.Cordinator;
import domen.Kategorija;
import domen.Osoba;
import domen.Smena;
import domen.VlSm;
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

    public void azurirajVlasnika(Vlasnik v) throws Exception {
        posaljiZahtevSaExceptionom(Operacija.AZURIRAJ_VLASNIKA, v);
        Cordinator.getInstance().osveziFormu();
    }

    public List<Smena> vratiSmene() {
        KlijentskiZahtev zahtev = new KlijentskiZahtev(Operacija.UCITAJ_SMENE, null);
        posiljalac.posalji(zahtev);
        ServerskiOdgovor odg = (ServerskiOdgovor) primalac.primi();
        if (odg.getOdgovor() == null) return new ArrayList<>();
        return (List<Smena>) odg.getOdgovor();
    }

    public void UbaciVlSm(VlSm vs) throws Exception {
        posaljiZahtevSaExceptionom(Operacija.UBACI_VlSm, vs);
    }

    public List<Kategorija> ucitajKategorije() {
        KlijentskiZahtev zahtev = new KlijentskiZahtev(Operacija.UCITAJ_KATEGORIJE, null);
        posiljalac.posalji(zahtev);
        ServerskiOdgovor odg = (ServerskiOdgovor) primalac.primi();
        if (odg.getOdgovor() == null) return new ArrayList<>();
        return (List<Kategorija>) odg.getOdgovor();
    }

    public void dodajOsobu(Osoba o) throws Exception {
        posaljiZahtevSaExceptionom(Operacija.DODAJ_OSOBU, o);
    }

    public void PromeniOsobu(Osoba o) throws Exception {
        posaljiZahtevSaExceptionom(Operacija.PROMENI_OSOBU, o);
        Cordinator.getInstance().osveziFormu();
    }

    
    }
    

