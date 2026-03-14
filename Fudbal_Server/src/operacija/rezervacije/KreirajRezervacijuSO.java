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
        Rezervacija r=(Rezervacija)param;
        if(r.getDatum()==null){
            throw new Exception("Sistem ne moze da zapamti osobu");
        }
        if(r.getSatOd()<8 || r.getSatOd()>=23){
            throw new Exception("Sistem ne moze da zapamti osobu");
        }
        if(r.getSatDo()<=8 || r.getSatOd()>23){
            throw new Exception("Sistem ne moze da zapamti osobu");
        }
        if(r.getSatOd()>=r.getSatDo()){
            throw new Exception("Sistem ne moze da zapamti osobu");
        }
        if(r.getPopust()==0.0 || r.getPopust()>1.0){
             throw new Exception("Sistem ne moze da zapamti osobu");
        }
        if(r.getUkupanIznos()<=0){
            throw new Exception("Sistem ne moze da zapamti osobu");
        }
        if (r.getIdVlasnik()== null ) {
            throw new Exception("Sistem ne moze da zapamti osobu");
        }
        if(r.getIdOsoba()==null){
            throw new Exception("Sistem ne moze da zapamti osobu");
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
