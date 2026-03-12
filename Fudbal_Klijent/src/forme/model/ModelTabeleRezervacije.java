/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package forme.model;

import domen.Rezervacija;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Alexa
 */
public class ModelTabeleRezervacije extends AbstractTableModel {
    List<Rezervacija> lista;
    String []kolone={"ID","datum","satOd","satDo","popust","ukupan iznos","Vlasnik","Osoba"};
    public ModelTabeleRezervacije(List<Rezervacija> lista) {
        this.lista = lista;
    }  

    public List<Rezervacija> getLista() {
        return lista;
    }

    public void setLista(List<Rezervacija> lista) {
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
        Rezervacija r = lista.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return r.getIdRezervacija();
            case 1:
                return r.getDatum();
            case 2:
                return r.getSatOd();
            case 3:
                return r.getSatDo();
            case 4:
                return r.getPopust();
            case 5:
                return r.getUkupanIznos() ;
            case 6:
                return r.getIdVlasnik() ;
            case 7:
                return r.getIdOsoba();
            default:
                return "NA";
        }
    }

    @Override
    public String getColumnName(int column) {
        return kolone[column];
    }
    
    
}
