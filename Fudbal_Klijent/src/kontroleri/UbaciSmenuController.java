/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package kontroleri;

import domen.Smena;
import domen.VlSm;
import domen.Vlasnik;
import forme.UbaciSmenaForma;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import komunikacija.Komunikacija;

/**
 *
 * @author Alexa
 */
public class UbaciSmenuController {
     private final UbaciSmenaForma usf;

    public UbaciSmenuController(UbaciSmenaForma usf) {
        this.usf = usf;
        addActionListener();
    }

    public void otvoriFormu() {
        pripremiFormu();
        usf.setVisible(true);
    }
    private void pripremiFormu() {
        try {
            List<Smena> smene = Komunikacija.getInstance().vratiSmene();
            usf.getCmbSmena().removeAllItems();
            for (Smena s : smene) {
                usf.getCmbSmena().addItem(s);
            }
            usf.getCmbSmena().setSelectedItem(null); // default prazno
            
            List<Vlasnik> vlasnici = Komunikacija.getInstance().ucitajVlasnika();
            usf.getCmbVlasnik().removeAllItems();
            for (Vlasnik v : vlasnici) {
                usf.getCmbVlasnik().addItem(v);
            }
            usf.getCmbVlasnik().setSelectedItem(null); // default prazno
        } catch (Exception ex) {
            Logger.getLogger(UbaciSmenuController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    private void addActionListener() {
        usf.ubaciSmenuAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    dodaj(e);
                } catch (Exception ex) {
                    Logger.getLogger(UbaciSmenuController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            private void dodaj(ActionEvent e) {
                try {
                    Smena s = (Smena) usf.getCmbSmena().getSelectedItem();
                    Vlasnik v = (Vlasnik) usf.getCmbVlasnik().getSelectedItem();
                    String datumString = usf.getTxtDatum().getText().trim();

                    if (datumString.isEmpty() || s == null || v == null) {
                        JOptionPane.showMessageDialog(usf, "Morate popuniti sva polja!", "UPOZORENJE", JOptionPane.WARNING_MESSAGE);
                        return;
                    }
                    Date datum;
                    try {
                        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
                        sdf.setLenient(false);
                        datum = sdf.parse(datumString);
                    } catch (ParseException pe) {
                        JOptionPane.showMessageDialog(usf, "Datum mora biti u formatu dd.MM.yyyy!", "UPOZORENJE", JOptionPane.WARNING_MESSAGE);
                        return;
                    }

                    VlSm vs = new VlSm(datum, v, s);
                    Komunikacija.getInstance().UbaciVlSm(vs);

                    JOptionPane.showMessageDialog(usf, "Sistem je zapamtio smenu", "USPEH", JOptionPane.INFORMATION_MESSAGE);
                    usf.dispose();
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(usf, "Sistem ne moze da zapamti smenu", "GRESKA", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }
}
