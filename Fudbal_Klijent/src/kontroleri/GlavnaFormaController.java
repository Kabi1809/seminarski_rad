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
import forme.FormaMod;
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
    private double pocetna=0;
    private boolean unosZakljucan = false;
    public GlavnaFormaController(GlavnaForma gf) {
        this.gf = gf;
        gf.getBtnIzmeniRezervaciju().setVisible(false);
        gf.getBtnZavrsi().setVisible(false);
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
        gf.getTxtukupanIznos().setEditable(false);
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

            //double popust = Double.parseDouble(gf.getTxtPopust().getText());
            //int satOd = Integer.parseInt(gf.getTxtSatOd().getText());
            //int satDo = Integer.parseInt(gf.getTxtSatDo().getText());
            ModelTabeleStavkaRezervacije mts = (ModelTabeleStavkaRezervacije) gf.getTblStavkeRezervacije().getModel();
            List<StavkaRezervacije> lista = mts.getLista();
            //double dodatniIznos = u.getCena() * brojUsluga*popust;
            for (StavkaRezervacije s : lista) {

               if (s.getIdUsluga().getIdUsluga() == u.getIdUsluga()) {
                s.setBrojUsluga(s.getBrojUsluga() + brojUsluga);
                s.setIznos(s.getBrojUsluga() * s.getCenaUsluge());
                mts.fireTableDataChanged();
                //ukupnoSacuvano += dodatniIznos;
                //double iznosSaPopustom = ukupnoSacuvano * popust;
                //.getTxtukupanIznos().setText(String.valueOf(iznosSaPopustom));
                izracunajUkupno();
                return;
            }
        }
            //double iznosZaTabelu = u.getCena() * brojUsluga;
            StavkaRezervacije sr = new StavkaRezervacije();
            sr.setCenaUsluge(u.getCena());
            sr.setBrojUsluga(brojUsluga);
            sr.setIznos(u.getCena() * brojUsluga);
            sr.setIdUsluga(u);
            mts.dodajStavku(sr);

            // Satnica samo za prvu stavku
            //if (mts.getRowCount() == 1) {
             //   sat = (satDo - satOd) * 2000;
           // }

            // UkupnoSacuvano = sve stavke + satnica prve stavke
            //ukupnoSacuvano += iznosZaTabelu;
            //if (mts.getRowCount() == 1) {
             //   ukupnoSacuvano += sat;
            //}

            // Primena popusta i na satnicu
            //double iznosSaPopustom = ukupnoSacuvano * popust;
            izracunajUkupno();
            //gf.getTxtukupanIznos().setText(String.valueOf(iznosSaPopustom));
        });
         gf.obrisiStavkuAddActionListener(e -> {
            int red = gf.getTblStavkeRezervacije().getSelectedRow();
            if (red == -1) {
                JOptionPane.showMessageDialog(gf, "Morate odabrati stavku za brisanje", "UPOZORENJE", JOptionPane.WARNING_MESSAGE);
                return;
            }
            ModelTabeleStavkaRezervacije mts = (ModelTabeleStavkaRezervacije) gf.getTblStavkeRezervacije().getModel();
            StavkaRezervacije sr = mts.getLista().get(red);
            //if (red == 0) {
              //      ukupnoSacuvano -= sr.getIznos() + sat;
                //    sat = 0; // resetuj satnicu
                //} else {
                  //  ukupnoSacuvano -= sr.getIznos();
                //}
            mts.obrisiStavku(mts.getLista().get(red));
            //double popust = Double.parseDouble(gf.getTxtPopust().getText());
            //double iznosSaPopustom = ukupnoSacuvano * popust;
            //gf.getTxtukupanIznos().setText(String.valueOf(iznosSaPopustom));
            izracunajUkupno();
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
        gf.izmeniRezervacijuAddActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent ee) {
               try {
                   promeni(ee);
               } catch (Exception ex) {
                   Logger.getLogger(GlavnaFormaController.class.getName()).log(Level.SEVERE, null, ex);
               }
           }
           private void promeni(ActionEvent e){
            try {
            Rezervacija r = pripremiRezervaciju(true);
            if (r == null) {
                return;
            }

            Komunikacija.getInstance().promeniRezervaciju(r);
            JOptionPane.showMessageDialog(gf, "Sistem je zapamtio rezervaciju", "USPEH", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception ex) {
            Logger.getLogger(GlavnaFormaController.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(gf, "Sistem ne moze da zapamti rezervaciju", "GRESKA", JOptionPane.ERROR_MESSAGE);
        }
           
        }

    });
        gf.getBtnZavrsi().addActionListener(e -> {
    try {
        ModelTabeleStavkaRezervacije mts = (ModelTabeleStavkaRezervacije) gf.getTblStavkeRezervacije().getModel();
        List<StavkaRezervacije> stavke = mts.getLista();
        if (stavke.isEmpty()) {
            JOptionPane.showMessageDialog(gf, "Morate dodati barem jednu stavku", "UPOZORENJE", JOptionPane.WARNING_MESSAGE);
            return;
        }

        //double popust = Double.parseDouble(gf.getTxtPopust().getText().trim());
        //double ukupno = 0;
        //for (StavkaRezervacije sr : stavke) {
          //  ukupno += sr.getIznos();  // cena * broj
        //}

        //int satOd = Integer.parseInt(gf.getTxtSatOd().getText().trim());
        //int satDo = Integer.parseInt(gf.getTxtSatDo().getText().trim());
        //ukupno += (satDo - satOd) * 2000; // satnica

        //ukupno *= popust; // primena popusta
        //gf.getTxtukupanIznos().setText(String.valueOf(ukupno));
        izracunajUkupno();
        // zaključavanje forme
        gf.getTxtSatOd().setEditable(false);
        gf.getTxtSatDo().setEditable(false);
        gf.getTblStavkeRezervacije().setEnabled(false);
        gf.getBtnZavrsi().setEnabled(false);

        unosZakljucan = true; // flag da je unos zakljucan

        JOptionPane.showMessageDialog(gf, "Ukupno je zaključano. Sada možete koristiti Izmeni rezervaciju.", "INFO", JOptionPane.INFORMATION_MESSAGE);
    } catch (Exception ex) {
        ex.printStackTrace();
        JOptionPane.showMessageDialog(gf, "Greška pri zaključavanju ukupnog iznosa", "GRESKA", JOptionPane.ERROR_MESSAGE);
    }
});
     
                }
 private void izracunajUkupno() { // ISPRAVLJENO: centralizovano izračunavanje
        ModelTabeleStavkaRezervacije mts = (ModelTabeleStavkaRezervacije) gf.getTblStavkeRezervacije().getModel();
        double ukupno = 0;
        for (StavkaRezervacije sr : mts.getLista()) {
            ukupno += sr.getIznos();
        }
        int satOd = Integer.parseInt(gf.getTxtSatOd().getText());
        int satDo = Integer.parseInt(gf.getTxtSatDo().getText());
        ukupno += (satDo - satOd) * 2000;
        double popust = Double.parseDouble(gf.getTxtPopust().getText());
        ukupno *= popust;
        gf.getTxtukupanIznos().setText(String.valueOf(ukupno));
    }
    private Rezervacija pripremiRezervaciju(boolean izmena) {
        if (gf.getCmbVlasnik().getSelectedItem() == null
                || gf.getCmbOsoba().getSelectedItem() == null
                || gf.getTxtDatum().getText().trim().isEmpty() || gf.getTxtSatOd().getText().trim().isEmpty() || gf.getTxtSatDo().getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(gf, "Morate popuniti sva polja", "UPOZORENJE", JOptionPane.WARNING_MESSAGE);
            return null;
        }

        ModelTabeleStavkaRezervacije mtr = (ModelTabeleStavkaRezervacije) gf.getTblStavkeRezervacije().getModel();
        List<StavkaRezervacije> stavke = mtr.getLista();
        if (stavke.isEmpty()) {
            JOptionPane.showMessageDialog(gf, "Morate dodati barem jednu stavku", "UPOZORENJE", JOptionPane.WARNING_MESSAGE);
            return null;
        }

        Rezervacija r = new Rezervacija();
        r.setStavke(stavke);
        r.setIdVlasnik(Cordinator.getInstance().getUlogovaniVlasnik());
        r.setIdOsoba((Osoba) gf.getCmbOsoba().getSelectedItem());
        r.setPopust(Double.parseDouble(gf.getTxtPopust().getText().trim()));
        r.setSatOd(Integer.parseInt(gf.getTxtSatOd().getText().trim()));
        r.setSatDo(Integer.parseInt(gf.getTxtSatDo().getText().trim()));
        if (izmena) {
            try {
                r.setIdRezervacija(Integer.parseInt(gf.getTxtIdRezervacija().getText().trim()));
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(gf, "ID rezervacije nije validan broj", "UPOZORENJE", JOptionPane.WARNING_MESSAGE);
                return null;
            }
        }
        //double ukupno = 0;
        //double popust = Double.parseDouble(gf.getTxtPopust().getText().trim());
    // saberi sve stavke sa popustom
        //for (StavkaRezervacije sr : stavke) {
            //ukupno += sr.getIznos() * popust*sr.getBrojUsluga();  // popust je već decimalni faktor, npr. 0.9
    //}

        // dodaj satnicu
        // += (r.getSatDo() - r.getSatOd()) * 2000 * popust;
        
        // postavi u rezervaciju i na formu
        //r.setUkupanIznos(ukupno);
        //gf.getTxtukupanIznos().setText(String.valueOf(ukupno));
        double ukupno;

    if (unosZakljucan) {
        // ako je dugme kliknuto, samo uzmi iz forme
        ukupno = Double.parseDouble(gf.getTxtukupanIznos().getText().trim());
    } else {
        // inace, saberi stavke i satnicu i primeni popust
        double popust = r.getPopust();
        ukupno = 0;
        for (StavkaRezervacije sr : stavke) {
            ukupno += sr.getIznos();  // cena * broj
        }
        ukupno += (r.getSatDo() - r.getSatOd()) * 2000;
        ukupno *= popust;
    }

    r.setUkupanIznos(ukupno);
        
        String datumString = gf.getTxtDatum().getText().trim();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date datum = sdf.parse(datumString);
            r.setDatum(datum);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(gf, "Datum nije validan! Format: yyyy-MM-dd", "UPOZORENJE", JOptionPane.WARNING_MESSAGE);
            return null;
        }

        return r;
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

    public void otvoriFormu(FormaMod formaMod) throws Exception {
        popuniComboBoxeve();
        Vlasnik ulogovani = Cordinator.getInstance().getUlogovaniVlasnik();
        gf.getLabelaUlogovan().setText(ulogovani.getIme() + " " + ulogovani.getPrezime());
        gf.setVisible(true);

        ModelTabeleStavkaRezervacije mts = new ModelTabeleStavkaRezervacije(new ArrayList<>());
        gf.getTblStavkeRezervacije().setModel(mts);

    if (formaMod == FormaMod.PROMENI) {
        gf.getBtnkreirajRezervaciju().setVisible(false);
        gf.getBtnIzmeniRezervaciju().setVisible(true);
        gf.getBtnZavrsi().setVisible(true);
        Rezervacija r = (Rezervacija) Cordinator.getInstance().vratiParam("rezervacija");
        mts.setLista(r.getStavke());
        gf.getTxtIdRezervacija().setEnabled(false);
        gf.getTxtIdRezervacija().setText(r.getIdRezervacija() + "");
        gf.getCmbVlasnik().setSelectedItem(r.getIdVlasnik());
        gf.getCmbOsoba().setSelectedItem(r.getIdOsoba());
        SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd");
        gf.getTxtDatum().setText(formater.format(r.getDatum()));
        gf.getTxtSatDo().setText(r.getSatDo()+ "");
        gf.getTxtSatOd().setText(r.getSatOd()+ "");
        gf.getTxtukupanIznos().setText(r.getUkupanIznos()+"");
        unosZakljucan=true; //promena
    }
    }

      }
   
