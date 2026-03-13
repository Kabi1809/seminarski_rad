/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacija.rezervacije;

import domen.Rezervacija;
import domen.StavkaRezervacije;
import java.util.List;
import operacija.ApstraktnaGenerickaOperacija;

/**
 *
 * @author Alexa
 */
public class KreirajRezervacijuSO extends ApstraktnaGenerickaOperacija {

    @Override
    protected void preduslovi(Object param) throws Exception {
        if(param==null || !(param instanceof Rezervacija)){
            throw new Exception("Sistem ne moze da kreira rezervaciju");
        }
    }

    @Override
    protected void izvrsiOperaciju(Object param, String kljuc) throws Exception {
        Rezervacija r=(Rezervacija) param;
        int idRezervacija=broker.addReturnKey(r);
        List<StavkaRezervacije> stavke=r.getStavke();
        for(StavkaRezervacije sr:stavke){
            sr.setIdRezervacija(idRezervacija);
            broker.add(sr);
        }
    }
    
}
