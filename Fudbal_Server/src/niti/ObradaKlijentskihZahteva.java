/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package niti;

import controller.Controller;
import domen.Kategorija;
import domen.Osoba;
import domen.Smena;
import domen.VlSm;
import domen.Vlasnik;
import java.io.IOException;
import java.net.Socket;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import komunikacija.KlijentskiZahtev;
import komunikacija.Posiljalac;
import komunikacija.Primalac;
import komunikacija.ServerskiOdgovor;

/**
 *
 * @author Alexa
 */
public class ObradaKlijentskihZahteva extends Thread{
    Socket socket;
    Posiljalac posiljalac;
    Primalac primalac;
    boolean kraj = false;
    public ObradaKlijentskihZahteva() {
    }

    public ObradaKlijentskihZahteva(Socket socket) { 
        this.socket = socket;
        posiljalac = new Posiljalac(socket);
        primalac = new Primalac(socket);

    }

    @Override
    public void run() {
        while(!kraj){
            KlijentskiZahtev zahtev= (KlijentskiZahtev) primalac.primi();
            ServerskiOdgovor odgovor=new ServerskiOdgovor();
            try {
                switch(zahtev.getOperacija()){
                    case LOGIN:
                        Vlasnik v = (Vlasnik) zahtev.getParametar();
                        v = Controller.getInstance().login(v);  
                        odgovor.setOdgovor(v);
                        break;
                    case UCITAJ_VLASNIKE:
                        List<Vlasnik> vlasnici = Controller.getInstance().prikaziVlasnike();
                        odgovor.setOdgovor(vlasnici);
                        break;
                    case OBRISI_VLASNIKA:
                        try {
                        Vlasnik vlasnik = (Vlasnik) zahtev.getParametar();
                        Controller.getInstance().obrisiVlasnika(vlasnik);
                        odgovor.setOdgovor(null);
                    } catch (Exception e) {
                        odgovor.setOdgovor(e);
                    }
                    break;
                    case DODAJ_VLASNIKA:
                        Vlasnik vlasnikD = (Vlasnik) zahtev.getParametar();
                        Controller.getInstance().dodajVlasnika(vlasnikD);
                        odgovor.setOdgovor(null); // u klij str u komunikac
                        break;
                    case AZURIRAJ_VLASNIKA:
                        Vlasnik vlasnikA = (Vlasnik) zahtev.getParametar();
                        Controller.getInstance().azurirajVlasnika(vlasnikA);
                        odgovor.setOdgovor(null);
                        break;
                    case UCITAJ_SMENE:
                        List<Smena> smene = Controller.getInstance().ucitajSmene();
                        odgovor.setOdgovor(smene);
                        break;
                    case UBACI_VlSm:
                        VlSm vs = (VlSm) zahtev.getParametar();
                        Controller.getInstance().ubaciVlSm(vs);
                        odgovor.setOdgovor(null);
                        break;
                    case UCITAJ_KATEGORIJE:
                        List<Kategorija> kategorija = Controller.getInstance().ucitajKategorije();
                        odgovor.setOdgovor(kategorija);
                        break;
                    case DODAJ_OSOBU:
                        Osoba o = (Osoba) zahtev.getParametar();
                        Controller.getInstance().dodajOsobu(o);
                        odgovor.setOdgovor(null);
                        break;
                    case PROMENI_OSOBU:
                        Osoba oPromeni = (Osoba) zahtev.getParametar();
                        Controller.getInstance().PromeniOsobu(oPromeni);
                        break;
                    default:
                        System.out.println("Greska! Ta operacija ne postoji");  
                }
                posiljalac.posalji(odgovor); 
            } catch (Exception ex) {
                odgovor.setOdgovor(ex.getMessage());
                posiljalac.posalji(odgovor);
            }
            
        }
    }
        public void prekini() {
        

        kraj = true;
        try {
            socket.close();
        } catch (IOException ex) {
            Logger.getLogger(ObradaKlijentskihZahteva.class.getName()).log(Level.SEVERE, null, ex);
        }
        interrupt(); 

    }
    
}
