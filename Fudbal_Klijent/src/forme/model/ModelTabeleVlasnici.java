/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package forme.model;

import domen.Vlasnik;
import java.util.List;
import java.util.stream.Collectors;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Alexa
 */
public class ModelTabeleVlasnici extends AbstractTableModel {
    List<Vlasnik> lista;
    String []kolone={"ID","Ime","Prezime","Korisnicko ime"};
    public ModelTabeleVlasnici(List<Vlasnik> lista) {
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
    public List<Vlasnik> getLista() {
        return lista;
    }

    public void setLista(List<Vlasnik> lista) {
        this.lista = lista;
    }
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        
        Vlasnik v = lista.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return v.getIdVlasnik();
            case 1:
                return v.getIme();
            case 2:
                return v.getPrezime();
            case 3:
                return v.getKorisničkoIme();
            default:
                return "NA";
        }
    }

    @Override
    public String getColumnName(int column) {
        return kolone[column];
    }

    public void pretrazi(String ime, String prezime) {
        List<Vlasnik> filteredList = lista.stream()
                .filter(p -> (ime == null || ime.isEmpty() || p.getIme().toLowerCase().contains(ime.toLowerCase())))
                .filter(p -> (prezime == null || prezime.isEmpty() || p.getPrezime().toLowerCase().contains(prezime.toLowerCase())))
                .collect(Collectors.toList());
        this.lista = filteredList;
        fireTableDataChanged();
    }
    
}
