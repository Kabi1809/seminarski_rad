/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package kontroleri;

import cordinator.Cordinator;
import domen.Vlasnik;
import forme.PrikazVlasnikaForma;
import forme.model.ModelTabeleVlasnici;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import komunikacija.Komunikacija;

/**
 *
 * @author Alexa
 */
public class PrikazVlasnikaController {
    private final PrikazVlasnikaForma pvf;

    public PrikazVlasnikaController(PrikazVlasnikaForma pvf) {
        this.pvf = pvf;
        addActionListeners();
    }

    public void otvoriFormu() {
        pripremiFormu();
        pvf.setVisible(true);
    }

    private void pripremiFormu() {
         try {
            List<Vlasnik> vlasnici = komunikacija.Komunikacija.getInstance().ucitajVlasnika();
             ModelTabeleVlasnici mtv = new ModelTabeleVlasnici(vlasnici);
            pvf.getTblVlasnici().setModel(mtv);
        } catch (Exception ex) {
            Logger.getLogger(PrikazVlasnikaController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void addActionListeners() {
        pvf.addBtnObrisiActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                int red = pvf.getTblVlasnici().getSelectedRow();
                if (red == -1) {
                    JOptionPane.showMessageDialog(pvf, "Niste selektovali red, sistem ne moze da obrise vlasnika","UPOZORENJE", JOptionPane.WARNING_MESSAGE);
                } else {
                    ModelTabeleVlasnici mtv = (ModelTabeleVlasnici) pvf.getTblVlasnici().getModel();
                    Vlasnik v= mtv.getLista().get(red);

                    try {
                        Komunikacija.getInstance().obrisiVlasnika(v);
                        JOptionPane.showMessageDialog(pvf, "Sistem je uspesno obrisao vlasnika.",
                                "USPESNO", JOptionPane.INFORMATION_MESSAGE);
                        pripremiFormu(); 
                    } catch (Exception exp) {
                        JOptionPane.showMessageDialog(pvf, "Sistem ne moze da obrise vlasnika.",
                                "GRESKA", JOptionPane.ERROR_MESSAGE);
                    }

                }

            }
        });
        pvf.addBtnAzurirajActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int red = pvf.getTblVlasnici().getSelectedRow();
                if (red == -1) {
                    JOptionPane.showMessageDialog(pvf, "Niste selektovali red, sistem ne moze da azurira vlasnika",
                            "UPOZORENJE", JOptionPane.WARNING_MESSAGE);
                } else {
                    ModelTabeleVlasnici mtg = (ModelTabeleVlasnici) pvf.getTblVlasnici().getModel();
                    Vlasnik v = mtg.getLista().get(red);
                    Cordinator.getInstance().dodajParam("vlasnik", v);
                    Cordinator.getInstance().otvoriIzmeniVlasnikaFormu();

                }
            }
        });
         pvf.addBtnPretraziActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String ime = pvf.getTxtIme().getText().trim();
                String prezime = pvf.getTxtPrezime().getText().trim();

                if (ime.equals("") && prezime.equals("")) {
                    JOptionPane.showMessageDialog(pvf, "Morate uneti barem jedan kriterijum",
                            "UPOZORENJE", JOptionPane.WARNING_MESSAGE);
                }

                
                ModelTabeleVlasnici mtv = (ModelTabeleVlasnici) pvf.getTblVlasnici().getModel();
                mtv.pretrazi(ime, prezime);
            }
        });
         pvf.addBtnResetujActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                osveziFormu();
            }
        });
        
    }

    public void osveziFormu() {
        pripremiFormu();
    }
}
