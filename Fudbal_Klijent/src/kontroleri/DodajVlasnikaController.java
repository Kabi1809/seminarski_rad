/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package kontroleri;

import cordinator.Cordinator;
import domen.Vlasnik;
import forme.DodajVlasnikaForma;
import forme.FormaMod;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import komunikacija.Komunikacija;

/**
 *
 * @author Alexa
 */
public class DodajVlasnikaController {
    private final DodajVlasnikaForma dvf;

    public DodajVlasnikaController(DodajVlasnikaForma dvf) {
        this.dvf = dvf;
       addActionListener();
    }
    public void otvoriFormu(FormaMod mod){
        pripremiFormu(mod);    
        dvf.setVisible(true);
    }

    private void pripremiFormu(FormaMod mod) {
        switch (mod) {
            case DODAJ:
                dvf.getBtnAzuziraj().setVisible(false);
                dvf.getBtnDodaj().setVisible(true); 
                dvf.getBtnDodaj().setEnabled(true);
                
                dvf.getTxtID().setEnabled(false);
                break;
                
            case PROMENI:
                dvf.getBtnDodaj().setVisible(false);
                dvf.getBtnAzuziraj().setVisible(true);
                dvf.getBtnAzuziraj().setEnabled(true);
                
                dvf.getTxtID().setEnabled(true);
                
                Vlasnik v = (Vlasnik) Cordinator.getInstance().vratiParam("vlasnik"); 
                dvf.getTxtID().setText(String.valueOf(v.getIdVlasnik()));
                dvf.getTxtIme().setText(v.getIme());
                dvf.getTxtPrezime().setText(v.getPrezime());
                dvf.getTxtkorIme().setText(v.getKorisničkoIme());
                dvf.getTxtSifra().setText(v.getŠifra());
                break;
                
            default:
                throw new AssertionError();
        }
    }

    private void addActionListener() {
          dvf.dodajAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dodajVlasnika(e);
            }

            private void dodajVlasnika(ActionEvent e) {
                String ime = dvf.getTxtIme().getText().trim();
                String prezime = dvf.getTxtPrezime().getText().trim();
                String korisnickoIme = dvf.getTxtkorIme().getText().trim();
                String lozinka = dvf.getTxtSifra().getText().trim();
                
                if(ime.equals("") || prezime.equals("") || korisnickoIme.equals("") || lozinka.equals("")){
                    JOptionPane.showMessageDialog(dvf, "Morate popuniti sva polja", "UPOZORENJE", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                Vlasnik v = new Vlasnik(-1, ime, prezime, korisnickoIme, lozinka);
                try {
                    Komunikacija.getInstance().dodajVlasnika(v);
                    JOptionPane.showMessageDialog(dvf, "Sistem je uspeo da doda vlasnika", "USPEH", JOptionPane.INFORMATION_MESSAGE);
                    dvf.dispose();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(dvf, "Sistem nije uspeo da doda vlasnika", "GRESKA", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
          dvf.azurirajAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                azuriraj(e);
            }

            private void azuriraj(ActionEvent e) {
                int id = Integer.parseInt(dvf.getTxtID().getText());
                String ime = dvf.getTxtIme().getText().trim();
                String prezime = dvf.getTxtPrezime().getText().trim();
                String korisnickoIme = dvf.getTxtkorIme().getText().trim();
                String lozinka = dvf.getTxtSifra().getText().trim();

                Vlasnik v = new Vlasnik(id, ime, prezime, korisnickoIme, lozinka);
                try {
                    Komunikacija.getInstance().azurirajVlasnika(v);
                    JOptionPane.showMessageDialog(dvf, "Sistem je uspeo da azurira vlasnika", "USPEH", JOptionPane.INFORMATION_MESSAGE);
                    dvf.dispose();
                } catch (Exception exp) {
                    exp.printStackTrace();
                    JOptionPane.showMessageDialog(dvf, "Sistem nije uspeo da azurira vlasnika", "GRESKA", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
          
    }
    }
