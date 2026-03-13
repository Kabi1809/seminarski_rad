/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package kontroleri;

import cordinator.Cordinator;
import domen.Kategorija;
import domen.Osoba;
import domen.Rezervacija;
import domen.StavkaRezervacije;
import domen.Usluga;
import domen.Vlasnik;
import forme.GlavnaForma;
import forme.model.ModelTabeleStavkaRezervacije;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
public class GlavnaFormaController {
    private final GlavnaForma gf;
    private double ukupnoSacuvano = 0;
    private int sat = 0;
    public GlavnaFormaController(GlavnaForma gf) {
        this.gf = gf;
        addActionListeners();
    }

    private void addActionListeners() {
       gf.getCmbOsoba().addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
        gf.getTxtKategorija().setText("");
        gf.getTxtPopust().setText("");
        gf.getTxtKategorija().setEditable(false);
        gf.getTxtPopust().setEditable(false);
        gf.getTxtIdRezervacija().setEditable(false);
        Osoba izabranaOsoba = (Osoba) gf.getCmbOsoba().getSelectedItem();

        if (izabranaOsoba != null) {
            Kategorija kategorija = izabranaOsoba.getIdKategorija();

            
            gf.getTxtKategorija().setText(kategorija.getNazivKategorije());

            
            double popust = 0.0;
            if(kategorija.getNazivKategorije().equals("student")){
                popust=0.8;
            }
            else if(kategorija.getNazivKategorije().equals("deca")){
                popust=0.7;
            }
            else{
                popust=1.0;
            }
            gf.getTxtPopust().setText(String.valueOf(popust));
            

        } 
    }
         });
       
    gf.dodajStavkuAddActionListener(e -> {
            if (gf.getCmbUsluga().getSelectedItem() == null
                    || gf.getTxtbrojUsluga().getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(gf, "Morate uneti uslugu i broj usluga", "UPOZORENJE", JOptionPane.WARNING_MESSAGE);
                return;
            }

            Usluga u = (Usluga) gf.getCmbUsluga().getSelectedItem();
            int brojUsluga;
            try {
                brojUsluga = Integer.parseInt(gf.getTxtbrojUsluga().getText().trim());
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(gf, "Broj usluga mora biti ceo broj!", "UPOZORENJE", JOptionPane.WARNING_MESSAGE);
                return;
            }

            double popust = Double.parseDouble(gf.getTxtPopust().getText());
            int satOd = Integer.parseInt(gf.getTxtSatOd().getText());
            int satDo = Integer.parseInt(gf.getTxtSatDo().getText());

            ModelTabeleStavkaRezervacije mts = (ModelTabeleStavkaRezervacije) gf.getTblStavkeRezervacije().getModel();

            double iznosZaTabelu = u.getCena() * brojUsluga;

            StavkaRezervacije sr = new StavkaRezervacije();
            sr.setCenaUsluge(u.getCena());
            sr.setBrojUsluga(brojUsluga);
            sr.setIznos(iznosZaTabelu);
            sr.setIdUsluga(u);
            mts.dodajStavku(sr);

            // Satnica samo za prvu stavku
            if (mts.getRowCount() == 1) {
                sat = (satDo - satOd) * 2000;
            }

            // UkupnoSacuvano = sve stavke + satnica prve stavke
            ukupnoSacuvano += iznosZaTabelu;
            if (mts.getRowCount() == 1) {
                ukupnoSacuvano += sat;
            }

            // Primena popusta i na satnicu
            double iznosSaPopustom = ukupnoSacuvano * popust;

            gf.getTxtukupanIznos().setText(String.valueOf(iznosSaPopustom));
        });
         gf.obrisiStavkuAddActionListener(e -> {
            int red = gf.getTblStavkeRezervacije().getSelectedRow();
            if (red == -1) {
                JOptionPane.showMessageDialog(gf, "Morate odabrati stavku za brisanje", "UPOZORENJE", JOptionPane.WARNING_MESSAGE);
                return;
            }
            ModelTabeleStavkaRezervacije mts = (ModelTabeleStavkaRezervacije) gf.getTblStavkeRezervacije().getModel();
            StavkaRezervacije sr = mts.getLista().get(red);
            if (red == 0) {
                    ukupnoSacuvano -= sr.getIznos() + sat;
                    sat = 0; // resetuj satnicu
                } else {
                    ukupnoSacuvano -= sr.getIznos();
                }
            mts.obrisiStavku(mts.getLista().get(red));
            double popust = Double.parseDouble(gf.getTxtPopust().getText());
            double iznosSaPopustom = ukupnoSacuvano * popust;
            gf.getTxtukupanIznos().setText(String.valueOf(iznosSaPopustom));
        });
        gf.kreirajRezervacijuAddActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent ee) {
               try {
                   dodaj(ee);
               } catch (Exception ex) {
                   Logger.getLogger(GlavnaFormaController.class.getName()).log(Level.SEVERE, null, ex);
               }
           }
           private void dodaj(ActionEvent e){
            try{
                Rezervacija r=new Rezervacija();
            
            ModelTabeleStavkaRezervacije mts= (ModelTabeleStavkaRezervacije) gf.getTblStavkeRezervacije().getModel();
            List<StavkaRezervacije> stavke=mts.getLista();
            r.setStavke(stavke);
            r.setIdVlasnik(cordinator.Cordinator.getInstance().getUlogovaniVlasnik());
            r.setIdOsoba((Osoba) gf.getCmbOsoba().getSelectedItem());
            r.setPopust(Double.parseDouble(gf.getTxtPopust().getText().trim()));
            r.setSatOd(Integer.parseInt(gf.getTxtSatOd().getText().trim()));
            r.setSatDo(Integer.parseInt(gf.getTxtSatDo().getText().trim()));
            r.setUkupanIznos(Double.parseDouble(gf.getTxtukupanIznos().getText().trim()));
            String datumm=gf.getTxtDatum().getText();
            SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
            Date datum=sdf.parse(datumm);
            r.setDatum(datum);
            Komunikacija.getInstance().kreirajRezervaciju(r);
            JOptionPane.showMessageDialog(null, "Sistem je uspesno kreirao rezervaciju", "USPEH", JOptionPane.INFORMATION_MESSAGE);
            }
            catch(Exception ex){
                ex.printStackTrace();
               JOptionPane.showMessageDialog(null, "Sistem ne moze da kreira rezervaciju", "GRESKA", JOptionPane.ERROR_MESSAGE);
            }
        }

    });
     
                }
    public void otvoriFormu() {
       
        try {
            Vlasnik ulogovani=Cordinator.getInstance().getUlogovaniVlasnik();
            gf.setVisible(true);
            gf.getLabelaUlogovan().setText(ulogovani.getIme() + " " + ulogovani.getPrezime());
            popuniComboBoxeve();
            gf.getTblStavkeRezervacije().setModel(new ModelTabeleStavkaRezervacije());
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
