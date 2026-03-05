/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package kontroleri;

import cordinator.Cordinator;
import domen.Vlasnik;
import forme.GlavnaForma;

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
       // URADI KASNIJE !!!
    }

    public void otvoriFormu() {
        Vlasnik ulogovani=Cordinator.getInstance().getUlogovaniVlasnik();
        
        gf.setVisible(true);
        gf.getLabelaUlogovan().setText(ulogovani.getIme() + " " + ulogovani.getPrezime());
        
    }
}
