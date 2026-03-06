/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package forme.model;

import domen.Kategorija;
import domen.Osoba;
import java.util.List;
import java.util.stream.Collectors;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Alexa
 */
public class ModelTabeleOsobe extends AbstractTableModel {
    List<Osoba> lista;
    String[] kolone = {"idOsoba", "ime", "prezime", "broj", "email","nazivKategorije"};

    public ModelTabeleOsobe(List<Osoba> lista) {
        this.lista = lista;
    }
    public List<Osoba> getLista() {
        return lista;
    }

    public void setLista(List<Osoba> lista) {
        this.lista = lista;
    }
    @Override
    public int getRowCount() {
        return lista.size();
    }

    @Override
    public int getColumnCount() {
        return kolone.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Osoba o = lista.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return o.getIdOsoba();
            case 1:
                return o.getIme();
            case 2:
                return o.getPrezime();
            case 3:
                return o.getBroj();
            case 4:
                return o.getEmail();
            case 5:
                return o.getIdKategorija().getNazivKategorije();
            default:
                return "NA";
        }
    }

    @Override
    public String getColumnName(int column) {
        return kolone[column];
           
                }

    public void pretrazi(String ime, String prezime, String brojTelefona, String email, Kategorija k) {
        List<Osoba> filteredList = lista.stream()
        .filter(v -> (ime == null || ime.isEmpty() || v.getIme().toLowerCase().contains(ime.toLowerCase())))
        .filter(v -> (prezime == null || prezime.isEmpty() || v.getPrezime().toLowerCase().contains(prezime.toLowerCase())))
        .filter(v -> (brojTelefona == null || brojTelefona.isEmpty() || v.getBroj().contains(brojTelefona)))
        .filter(v -> (email == null || email.isEmpty() || v.getEmail().contains(email)))
        .filter(v -> (k == null 
                      || v.getIdKategorija() != null 
                      && v.getIdKategorija().getNazivKategorije().toLowerCase().contains(k.getNazivKategorije().toLowerCase())))
        .collect(Collectors.toList());

    this.lista = filteredList;
    fireTableDataChanged();
    }

  
    
    
}
