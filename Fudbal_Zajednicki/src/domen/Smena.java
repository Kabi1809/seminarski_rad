/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domen;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Alexa
 */
public class Smena implements ApstraktniDomenskiObjekat{
    private int idSmena;
    private String nazivSmena;

    public Smena() {
    }

    public Smena(int idSmena, String nazivSmena) {
        this.idSmena = idSmena;
        this.nazivSmena = nazivSmena;
    }

    public int getIdSmena() {
        return idSmena;
    }

    public void setIdSmena(int idSmena) {
        this.idSmena = idSmena;
    }

    public String getNazivSmena() {
        return nazivSmena;
    }

    public void setNazivSmena(String nazivSmena) {
        this.nazivSmena = nazivSmena;
    }

    @Override
    public String toString() {
        return nazivSmena;
    }

    @Override
    public String vratiNazivTabele() {
        return "smena";
    }

    @Override
    public List<ApstraktniDomenskiObjekat> vratiListu(ResultSet rs) throws Exception {
         List<ApstraktniDomenskiObjekat> lista = new ArrayList<>();

        while (rs.next()) {
            int idSmena = rs.getInt("smena.idSmena");
            String nazivSmena = rs.getString("smena.nazivSmena");
            Smena s = new Smena(idSmena, nazivSmena);
            lista.add(s);
        }

        return lista;
    }

    @Override
    public String vratiKoloneZaUbacivanje() {
        return "nazivSmena";
    }

    @Override
    public String vratiVrednostiZaUbacivanje() {
         return "'"+nazivSmena+"', ";
    }

    @Override
    public String vratiPrimarniKljuc() {
        return "smena.idSmena="+idSmena;
    }

    @Override
    public ApstraktniDomenskiObjekat vratiObjekatIzRS(ResultSet rs) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String vratiVrednostiZaIzmenu() {
        return "nazivSmena='" + nazivSmena + "'";
    }
    
    
    
}
