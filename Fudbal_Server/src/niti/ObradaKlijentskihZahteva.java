/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package niti;

import java.io.IOException;
import java.net.Socket;
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
