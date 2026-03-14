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
public class PromeniRezervacijuSO extends ApstraktnaGenerickaOperacija{

    @Override
    protected void preduslovi(Object param) throws Exception {
        if (param == null || !(param instanceof Rezervacija)) {
            throw new Exception("Sistem ne moze da nadje rezervaciju");
        }
    }

    @Override
    protected void izvrsiOperaciju(Object param, String kljuc) throws Exception {
        Rezervacija r = (Rezervacija) param;
        broker.edit(r);
        String uslov = "  JOIN usluga u ON u.idUsluga =stavkarezervacije.idUsluga "
                + "WHERE idRezervacija=" + r.getIdRezervacija()+ " ";
        List<StavkaRezervacije> stareStavke = broker.getAll(new StavkaRezervacije(), uslov);
        for (StavkaRezervacije sr : stareStavke) {
            broker.delete(sr);
        }

        List<StavkaRezervacije> noveStavke = r.getStavke();
        for (StavkaRezervacije sR : noveStavke) {
            sR.setIdRezervacija(r.getIdRezervacija());
            broker.add(sR);
        }

        System.out.println("Uspesno promenjen racun PROMENIREZERVACIJUSO");
    }
    
}
