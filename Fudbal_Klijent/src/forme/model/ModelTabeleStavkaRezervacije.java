/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package forme.model;

import domen.StavkaRezervacije;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Alexa
 */
public class ModelTabeleStavkaRezervacije extends AbstractTableModel{
    List<StavkaRezervacije> lista;
    String []kolone={"rb","cena usluge","broj usluga","iznos","ID Rezervacija","ID Usluga"};
    public ModelTabeleStavkaRezervacije(List<StavkaRezervacije> lista) {
        this.lista = lista;
    }
    public ModelTabeleStavkaRezervacije() {
        this.lista = new ArrayList<>();
    }
    public List<StavkaRezervacije> getLista() {
        return lista;
    }

    public void setLista(List<StavkaRezervacije> lista) {
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
         StavkaRezervacije sr = lista.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return sr.getRb();
            case 1:
                return sr.getCenaUsluge();
            case 2:
                return sr.getBrojUsluga();
            case 3:
                return sr.getIznos();
            case 4:
                return sr.getIdRezervacija();
            case 5:
                return sr.getIdUsluga().getNaziv();
            default:
                return "NA";
        }
    }

    @Override
    public String getColumnName(int column) {
        return kolone[column];
    }

    public void dodajStavku(StavkaRezervacije sr) {
        int trRb=lista.size()+1;
        sr.setRb(trRb);
        lista.add(sr);
        fireTableDataChanged();
    }

    public void obrisiStavku(StavkaRezervacije get) {
        lista.remove(get);
        fireTableDataChanged();
    }
    }
    
