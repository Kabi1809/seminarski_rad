/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package kontroleri;

import cordinator.Cordinator;
import domen.Osoba;
import domen.Usluga;
import domen.Vlasnik;
import forme.GlavnaForma;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import komunikacija.Komunikacija;

/**
 *
 * @author Alexa
 */
public class GlavnaFormaController {
    private final GlavnaForma gf;
    public GlavnaFormaController(GlavnaForma gf) {
        this.gf = gf;
        addActionListeners();
    }

    private void addActionListeners() {
       //
    }

    public void otvoriFormu() {
       
        try {
            Vlasnik ulogovani=Cordinator.getInstance().getUlogovaniVlasnik();
            gf.setVisible(true);
            gf.getLabelaUlogovan().setText(ulogovani.getIme() + " " + ulogovani.getPrezime());
            popuniComboBoxeve();
        } catch (Exception ex) {
            Logger.getLogger(GlavnaFormaController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    } 
    private void popuniComboBoxeve() throws Exception {
        List<Vlasnik> sviVlasnici = Komunikacija.getInstance().ucitajVlasnika();
        gf.getCmbVlasnik().removeAllItems();
        for (Vlasnik v : sviVlasnici) {
            gf.getCmbVlasnik().addItem(v);
        }
        gf.getCmbVlasnik().setSelectedItem(Cordinator.getInstance().getUlogovaniVlasnik());

        List<Osoba> sveOsobe = Komunikacija.getInstance().ucitajOsobe();
        gf.getCmbOsoba().removeAllItems();
        for (Osoba o : sveOsobe) {
            gf.getCmbOsoba().addItem(o);
        }
        gf.getCmbOsoba().setSelectedItem(null);

        List<Usluga> sveUsluge = Komunikacija.getInstance().ucitajUsluge();
        gf.getCmbUsluga().removeAllItems();
        for (Usluga u : sveUsluge) {
            gf.getCmbUsluga().addItem(u);
        }
        gf.getCmbUsluga().setSelectedItem(null);
    }
}
