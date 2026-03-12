/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package kontroleri;

import domen.Rezervacija;
import forme.PrikazRezervacijeForma;
import forme.model.ModelTabeleRezervacije;
import java.util.List;
import komunikacija.Komunikacija;

/**
 *
 * @author Alexa
 */
public class PrikazRezervacijaController {
    private final PrikazRezervacijeForma prf;

    public PrikazRezervacijaController(PrikazRezervacijeForma prf) {
        this.prf = prf;
        //addActionListener();
        //addMouseListener();
    }
    public void otvoriFormu() {
        pripremiFormu();
        prf.setVisible(true);
    }

    private void pripremiFormu() {
        List<Rezervacija> rezervacije = Komunikacija.getInstance().ucitajRezervacije(); // iz baze izvlaci sve racune
        ModelTabeleRezervacije mtr = new ModelTabeleRezervacije(rezervacije);
        prf.getTblRezervacije().setModel(mtr);
    }
}
