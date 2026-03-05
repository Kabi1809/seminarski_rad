/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import domen.Vlasnik;
import operacija.login.LoginOperacija;

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
}
