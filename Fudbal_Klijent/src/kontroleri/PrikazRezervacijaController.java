/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package kontroleri;

import cordinator.Cordinator;
import domen.Osoba;
import domen.Rezervacija;
import domen.Vlasnik;
import forme.FormaMod;
import forme.PrikazRezervacijeForma;
import forme.model.ModelTabeleRezervacije;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.System.Logger;
import java.lang.System.Logger.Level;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;
import komunikacija.Komunikacija;

/**
 *
 * @author Alexa
 */
public class PrikazRezervacijaController {
    private final PrikazRezervacijeForma prf;

    public PrikazRezervacijaController(PrikazRezervacijeForma prf) {
        this.prf = prf;
        addActionListener();
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
        
            List<Vlasnik> sviVlasnici = komunikacija.Komunikacija.getInstance().ucitajVlasnika();
            prf.getCmbOsoba().removeAllItems();

            for (Vlasnik v : sviVlasnici) {
                prf.getCmbVlasnik().addItem(v);
            }
            prf.getCmbVlasnik().setSelectedItem(null);

            List<Osoba> sveOsobe = komunikacija.Komunikacija.getInstance().ucitajOsobe();
            prf.getCmbOsoba().removeAllItems();
            for (Osoba o : sveOsobe) {
                prf.getCmbOsoba().addItem(o);
            }
            prf.getCmbOsoba().setSelectedItem(null);
    }

    private void addActionListener() {
        prf.pretraziRezervacijuAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                  try {
                    Rezervacija r = new Rezervacija();

                    if (prf.getTxtIDRezervacije().getText().isEmpty() && prf.getTxtUkupanIznos().getText().isEmpty()
                            && (prf.getTxtGodina().getText().isEmpty() || prf.getTxtMesec().getText().isEmpty() || prf.getTxtDan().getText().isEmpty())
                            && prf.getCmbVlasnik().getSelectedItem() == null && prf.getCmbOsoba().getSelectedItem()== null && prf.getTxtSatOd().getText().isEmpty() && prf.getTxtSatDo().getText().isEmpty()
                            && prf.getTxtPopust().getText().isEmpty()) {
                        JOptionPane.showMessageDialog(prf, "Morate uneti barem jedan kriterijum", "UPOZORENJE", JOptionPane.WARNING_MESSAGE);
                        return;
                    }

                    try {
                        if (!prf.getTxtIDRezervacije().getText().isEmpty()) {
                            r.setIdRezervacija(Integer.parseInt(prf.getTxtIDRezervacije().getText()));
                        }
                        if (!prf.getTxtUkupanIznos().getText().isEmpty()) {
                            r.setUkupanIznos(Double.parseDouble(prf.getTxtUkupanIznos().getText()));
                        }
                        if(!prf.getTxtSatDo().getText().isEmpty()){
                            r.setSatDo(Integer.parseInt(prf.getTxtSatDo().getText()));
                        }
                        if(!prf.getTxtSatOd().getText().isEmpty()){
                            r.setSatOd(Integer.parseInt(prf.getTxtSatOd().getText()));
                        }
                        if(!prf.getTxtPopust().getText().isEmpty()){
                            r.setPopust(Double.parseDouble(prf.getTxtPopust().getText()));
                        } 
                        }
                     catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(prf, "Neispravan format numerickih vrednosti", "UPOZORENJE", JOptionPane.WARNING_MESSAGE);
                        return;
                    }

                    if (!prf.getTxtGodina().getText().isEmpty() && !prf.getTxtMesec().getText().isEmpty() && !prf.getTxtDan().getText().isEmpty()) {
                        String godina = prf.getTxtGodina().getText();
                        String mesec = prf.getTxtMesec().getText();
                        String dan = prf.getTxtDan().getText();

                        String datumString = godina + "-" + mesec + "-" + dan;
                        LocalDate localDate = LocalDate.parse(datumString, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                        Date datum = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
                        r.setDatum(datum);

                    }

                    r.setIdVlasnik((Vlasnik) prf.getCmbVlasnik().getSelectedItem());
                    r.setIdOsoba((Osoba) prf.getCmbOsoba().getSelectedItem());
                    List<Rezervacija> rezervacije = Komunikacija.getInstance().pretraziRezervacije(r);
                     ModelTabeleRezervacije mtr=new ModelTabeleRezervacije(rezervacije);
                    prf.getTblRezervacije().setModel(mtr);
                    if (rezervacije.isEmpty()) {
                        JOptionPane.showMessageDialog(prf, "Sistem ne moze da nadje rezervacije po zadatim kriterjumima", "GRESKA", JOptionPane.ERROR_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(prf, "Sistem je nasao rezervacije po zadatim kriterjumima", "USPEH", JOptionPane.INFORMATION_MESSAGE);
                    }
                    
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });
         prf.izmeniRezervacijuAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                   int red =prf.getTblRezervacije().getSelectedRow();
                if (red == -1) {
                    JOptionPane.showMessageDialog(prf, "Morate odabrati rezervaciju", "UPOZORENJE", JOptionPane.WARNING_MESSAGE);
                } else {
                    try {
                        ModelTabeleRezervacije mtr = (ModelTabeleRezervacije) prf.getTblRezervacije().getModel();
                        Rezervacija r=mtr.getLista().get(red);

                        JOptionPane.showMessageDialog(prf, "Sistem je nasao rezervaciju", "USPEH", JOptionPane.INFORMATION_MESSAGE);

                        cordinator.Cordinator.getInstance().dodajParam("rezervacija", r);

                        Cordinator.getInstance().otvoriGlavnuFormu(FormaMod.PROMENI);
                        
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(prf, "Sistem ne moze da nadje rezervaciju", "GRESKA", JOptionPane.ERROR_MESSAGE);
                    }

                }
            }
        });

}
}
