/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import niti.ObradaKlijentskihZahteva;

/**
 *
 * @author Alexa
 */
public class Server extends Thread {
        
    boolean kraj=false;
      ServerSocket serverSoket;
      List<ObradaKlijentskihZahteva> vlasnici; //

    public Server() {
        vlasnici=new ArrayList<>();
    }

    @Override
    public void run() { // kao pokreni server

        try {
            serverSoket = new ServerSocket(9000);
            while (!kraj) { 
                System.out.println("Cekanje klijenta....");
                Socket s = serverSoket.accept(); 
                System.out.println("Klijent se povezao!");

                ObradaKlijentskihZahteva okz = new ObradaKlijentskihZahteva(s);
                vlasnici.add(okz);
                okz.start(); 

            }
        } catch (IOException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }

    }


    
    public void zaustaviServer() {

        kraj = true;
        try {
            for (ObradaKlijentskihZahteva v : vlasnici) { 
                v.prekini();
                
            }
            serverSoket.close();
        } catch (IOException ex) {
            ex.printStackTrace();
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
