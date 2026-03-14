/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package kontroleri;

import cordinator.Cordinator;
import domen.Kategorija;
import domen.Osoba;
import forme.DodajOsobuForma;
import forme.FormaMod;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JOptionPane;
import komunikacija.Komunikacija;

/**
 *
 * @author Alexa
 */
public class DodajOsobuController {
    private final DodajOsobuForma dof;

    public DodajOsobuController(DodajOsobuForma dof) {
        this.dof = dof;
        addActionListener();

    }
     public void otvoriFormu(FormaMod mod) throws Exception {
        pripremiFormu(mod); // metoda koja ce da ucitava listu svih pacijenata
        dof.setVisible(true);
        /// KLIJ ZAHTEV da ucita vlasnike iz baze
    
     }
      private void pripremiFormu(FormaMod mod) throws Exception {

        List<Kategorija> kategorije = komunikacija.Komunikacija.getInstance().ucitajKategorije();
        for (Kategorija k : kategorije) {
            dof.getCmbKategorije().addItem(k);
        }

        switch (mod) {
            case DODAJ:
                dof.getBtnPromeni().setVisible(false);
                dof.getBtnObrisi().setVisible(false);
                dof.getBtnDodaj().setVisible(true);
                dof.getBtnDodaj().setEnabled(true);
                dof.getCmbKategorije().setSelectedItem(null);
                dof.getTxtId().setEnabled(false);
                break;

            case PROMENI:
                dof.getBtnDodaj().setVisible(false);
                dof.getBtnObrisi().setVisible(false);
                dof.getBtnPromeni().setVisible(true);

                Osoba oPromeni = (Osoba) Cordinator.getInstance().vratiParam("osoba");
                dof.getTxtIme().setText(oPromeni.getIme());
                dof.getTxtPrezime().setText(oPromeni.getPrezime());
                dof.getTxtBroj().setText(oPromeni.getBroj());
                dof.getCmbKategorije().setSelectedItem(oPromeni.getIdKategorija());
                dof.getTxtId().setText(oPromeni.getIdOsoba() + "");
                dof.getTxtEmail().setText(oPromeni.getEmail());
                break;

            case OBRISI:
                dof.getBtnDodaj().setVisible(false);
                dof.getBtnPromeni().setVisible(false);
                dof.getBtnObrisi().setVisible(true);

                Osoba oObrisi = (Osoba) Cordinator.getInstance().vratiParam("osoba");
                dof.getTxtIme().setText(oObrisi.getIme());
                dof.getTxtPrezime().setText(oObrisi.getPrezime());
                dof.getTxtBroj().setText(oObrisi.getBroj());
                dof.getCmbKategorije().setSelectedItem(oObrisi.getIdKategorija());
                dof.getTxtId().setText(oObrisi.getIdOsoba() + "");
                dof.getTxtEmail().setText(oObrisi.getEmail());
                
                dof.getTxtIme().setEditable(false);
                dof.getTxtPrezime().setEditable(false);
                dof.getTxtBroj().setEditable(false);
                dof.getCmbKategorije().setEnabled(false);
                dof.getTxtEmail().setEditable(false);
                break;
            default:
                throw new AssertionError();
        }
    }
      private void addActionListener() {
        dof.dodajAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dodaj(e);
            }

            private void dodaj(ActionEvent e) {
                String ime = dof.getTxtIme().getText().trim();
                String prezime = dof.getTxtPrezime().getText().trim();
                String brojTelefona = dof.getTxtBroj().getText().trim();
                String email=dof.getTxtEmail().getText().trim();
                Kategorija k = (Kategorija) dof.getCmbKategorije().getSelectedItem();

                if (ime.equals("") || prezime.equals("") || brojTelefona.equals("") || email.equals("") || k == null ) {
                    JOptionPane.showMessageDialog(dof, "Morate popuniti sva polja", "UPOZORENJE", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                Osoba o = new Osoba(-1, ime, prezime, brojTelefona, email,k);

                try {
                    Komunikacija.getInstance().dodajOsobu(o);
                    JOptionPane.showMessageDialog(dof, "Sistem je zapamtio osobu", "USPEH", JOptionPane.INFORMATION_MESSAGE);
                    dof.dispose();
                } catch (Exception exp) {
                    JOptionPane.showMessageDialog(dof, exp.getMessage(), "GRESKA", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
         dof.promeniAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                promeni(e);
            }

            private void promeni(ActionEvent e) {
                int id = Integer.parseInt(dof.getTxtId().getText());
                String ime = dof.getTxtIme().getText().trim();
                String prezime = dof.getTxtPrezime().getText().trim();
                String brojTelefona = dof.getTxtBroj().getText().trim();
                String email=dof.getTxtEmail().getText().trim();
                Kategorija k= (Kategorija) dof.getCmbKategorije().getSelectedItem();

                Osoba o = new Osoba(id, ime, prezime, brojTelefona,email, k);
                try {
                    Komunikacija.getInstance().promeniOsobu(o);
                    JOptionPane.showMessageDialog(dof, "Sistem je zapamtio osobu", "USPEH", JOptionPane.INFORMATION_MESSAGE);
                    dof.dispose();
                } catch (Exception exp) {
                    JOptionPane.showMessageDialog(dof, exp.getMessage(), "GRESKA", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
         dof.obrisiAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                obrisi(e);
            }

            private void obrisi(ActionEvent e) {
                int id = Integer.parseInt(dof.getTxtId().getText());
                String ime = dof.getTxtIme().getText().trim();
                String prezime = dof.getTxtPrezime().getText().trim();
                String brojTelefona = dof.getTxtBroj().getText().trim();
                String email=dof.getTxtEmail().getText().trim();
                Kategorija k = (Kategorija) dof.getCmbKategorije().getSelectedItem();

                Osoba o = new Osoba(id, ime, prezime, brojTelefona, email,k);

                try {
                    Komunikacija.getInstance().obrisiOsobu(o);
                    JOptionPane.showMessageDialog(dof, "Sistem je obrisao osobu", "USPEH", JOptionPane.INFORMATION_MESSAGE);
                    dof.dispose();
                } catch (Exception exp) {
                    JOptionPane.showMessageDialog(dof, exp.getMessage(), "GRESKA", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
}
}
