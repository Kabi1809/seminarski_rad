/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacija.vlasnici;

import domen.Vlasnik;
import java.util.List;
import operacija.ApstraktnaGenerickaOperacija;

/**
 *
 * @author Alexa
 */
public class PrikaziVlasnikeOperacija extends ApstraktnaGenerickaOperacija {
    private List<Vlasnik> vlasnici;

    public List<Vlasnik> getVlasnici() {
        return vlasnici;
    }
    @Override
    protected void preduslovi(Object param) throws Exception {
        
    }

    @Override
    protected void izvrsiOperaciju(Object param, String kljuc) throws Exception {
        vlasnici=broker.getAll(new Vlasnik(), "");
    }
    
}
