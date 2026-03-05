/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacija.smena;

import domen.Smena;
import java.util.List;
import operacija.ApstraktnaGenerickaOperacija;

/**
 *
 * @author Alexa
 */
public class UcitajSmeneSO extends ApstraktnaGenerickaOperacija {
    List<Smena>smene;

    public List<Smena> getSmene() {
        return smene;
    }

    public void setSmene(List<Smena> smene) {
        this.smene = smene;
    }
    @Override
    protected void preduslovi(Object param) throws Exception {
        
    }

    @Override
    protected void izvrsiOperaciju(Object param, String kljuc) throws Exception {
        smene=broker.getAll(new Smena(), "");
    }
    
}
