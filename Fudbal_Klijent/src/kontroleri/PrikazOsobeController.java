/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package kontroleri;

import cordinator.Cordinator;
import domen.Kategorija;
import domen.Osoba;
import forme.PrikazOsobeForma;
import forme.model.ModelTabeleOsobe;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Alexa
 */
public class PrikazOsobeController {
    private final PrikazOsobeForma pof;

    public PrikazOsobeController(PrikazOsobeForma pof) {
        this.pof = pof;
        addActionListener();

    }
     public void otvoriFormu() {
        pripremiFormu();
        pof.setVisible(true);
        
    }
    public void pripremiFormu() {
        try {
            
            List<Osoba> osobe = komunikacija.Komunikacija.getInstance().ucitajOsobe();
            ModelTabeleOsobe mto = new ModelTabeleOsobe(osobe);
            pof.getTblOsoba().setModel(mto);

            List<Kategorija> kategorije = komunikacija.Komunikacija.getInstance().ucitajKategorije();
            for (Kategorija k : kategorije) {
                pof.getCmbKategorija().addItem(k);
            }
            pof.getCmbKategorija().setSelectedItem(null);
        } catch (Exception ex) {
            Logger.getLogger(PrikazOsobeController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    private void addActionListener() {
        
        pof.addBtnPromeniActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int red =pof.getTblOsoba().getSelectedRow();
                if (red == -1) {
                    JOptionPane.showMessageDialog(pof, "Morate odabrati osobu", "UPOZORENJE", JOptionPane.WARNING_MESSAGE);
                } else {
                    try {
                        ModelTabeleOsobe mto = (ModelTabeleOsobe) pof.getTblOsoba().getModel();
                        Osoba o= mto.getLista().get(red); 

                        JOptionPane.showMessageDialog(pof, "Sistem je nasao osobu", "USPEH", JOptionPane.INFORMATION_MESSAGE);

                        cordinator.Cordinator.getInstance().dodajParam("osoba", o);

                        Cordinator.getInstance().otvoriPromeniOsobuFormu();
                        
                    } catch (Exception ex) {
                        Logger.getLogger(PrikazOsobeController.class.getName()).log(Level.SEVERE, null, ex);
                    }

                }

            }
        });
        pof.addBtnResetujActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                pripremiFormu();
                pof.getTxtIme().setText("");
                pof.getTxtPrezime().setText("");
                pof.getTxtBroj().setText("");
                pof.getTxtEmail().setText("");

            }
        });
       pof.addBtnObrisiActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int red = pof.getTblOsoba().getSelectedRow();
                if (red == -1) {
                    JOptionPane.showMessageDialog(pof, "Morate odabrati osobu", "UPOZORENJE", JOptionPane.WARNING_MESSAGE);
                } else {
                    try {
                        ModelTabeleOsobe mto = (ModelTabeleOsobe) pof.getTblOsoba().getModel();
                        Osoba o = mto.getLista().get(red);

                        JOptionPane.showMessageDialog(pof, "Sistem je našao osobu", "USPEH", JOptionPane.INFORMATION_MESSAGE);

                        cordinator.Cordinator.getInstance().dodajParam("osoba", o);

                        Cordinator.getInstance().otvoriObrisiOsobuFormu();
                        

                    } catch (Exception ex) {
                        Logger.getLogger(PrikazOsobeController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        });
       pof.addBtnPretraziActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String ime = pof.getTxtIme().getText().trim();
                String prezime = pof.getTxtPrezime().getText().trim();
                String brojTelefona = pof.getTxtBroj().getText().trim();
                String email=pof.getTxtEmail().getText().trim();
                Kategorija k = (Kategorija) pof.getCmbKategorija().getSelectedItem();

                if (ime.equals("") && prezime.equals("") && brojTelefona.equals("") && email.equals("")&& k == null) {
                    JOptionPane.showMessageDialog(pof, "Morate uneti barem jedan kriterijum", "UPOZORENJE", JOptionPane.WARNING_MESSAGE);
                }
                Osoba o=new Osoba(ime,prezime,brojTelefona,email,k);
                List<Osoba> rezultat=komunikacija.Komunikacija.getInstance().pretraziOsobe(o);
                ModelTabeleOsobe mtw=new ModelTabeleOsobe(rezultat);
                pof.getTblOsoba().setModel(mtw);
                //ModelTabeleOsobe mto = (ModelTabeleOsobe) pof.getTblOsoba().getModel();
                //mto.pretrazi(ime, prezime, brojTelefona,email, k);
                //List<Osoba> lista = mto.getLista();
                if (rezultat.isEmpty()) { //stajala je lista
                    JOptionPane.showMessageDialog(pof, "Sistem ne moze da nadje osobu po zadatim kriterjumima", "GRESKA", JOptionPane.ERROR_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(pof, "Sistem je nasao osobu po zadatim kriterjumima", "USPEH", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });
    }
    public void osveziFormu() {
        pripremiFormu();
    }
    }
