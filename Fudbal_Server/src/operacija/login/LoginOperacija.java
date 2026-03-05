/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacija.login;

import domen.Vlasnik;
import java.util.List;
import operacija.ApstraktnaGenerickaOperacija;

/**
 *
 * @author Alexa
 */
public class LoginOperacija extends ApstraktnaGenerickaOperacija{
    Vlasnik vlasnik;
    @Override
    protected void preduslovi(Object param) throws Exception {
           if (param == null || !(param instanceof Vlasnik)) {
            throw new Exception("Šifra i korisničko ime nisu lepo uneti.");
        }
    }

    @Override
    protected void izvrsiOperaciju(Object param, String kljuc) throws Exception {
        List<Vlasnik> sviVlasnici = broker.getAll((Vlasnik) param, "");
        System.out.println("Klasa LoginOperacija " + sviVlasnici);

        if (sviVlasnici.contains((Vlasnik) param)) {
            for (Vlasnik v : sviVlasnici) {
                if (v.equals((Vlasnik) param)) {
                    vlasnik = v;
                    return;
                }
            }
        } else {
            vlasnik = null;
        }

    }

    public Vlasnik getVlasnik() {
        return vlasnik;
    }

    public void setVlasnik(Vlasnik vlasnik) {
        this.vlasnik = vlasnik;
    }
    
    }
    
