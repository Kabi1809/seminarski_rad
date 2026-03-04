/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domen;

import java.sql.ResultSet;
import java.util.List;
import java.util.Objects;
import java.util.ArrayList;
/**
 *
 * @author Alexa
 */
public class Kategorija implements ApstraktniDomenskiObjekat{
    private int idKategorija;
    private String nazivKategorije;

    public Kategorija() {
    }

    public Kategorija(int idKategorija, String nazivKategorije) {
        this.idKategorija = idKategorija;
        this.nazivKategorije = nazivKategorije;
    }

    public int getIdKategorija() {
        return idKategorija;
    }

    public void setIdKategorija(int idKategorija) {
        this.idKategorija = idKategorija;
    }

    public String getNazivKategorije() {
        return nazivKategorije;
    }

    public void setNazivKategorije(String nazivKategorije) {
        this.nazivKategorije = nazivKategorije;
    }

    @Override
    public String toString() {
        return nazivKategorije;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Kategorija other = (Kategorija) obj;
        if (this.idKategorija != other.idKategorija) {
            return false;
        }
        return Objects.equals(this.nazivKategorije, other.nazivKategorije);
    }

    @Override
    public String vratiNazivTabele() {
        return "kategorija";
    }

    @Override
    public List<ApstraktniDomenskiObjekat> vratiListu(ResultSet rs) throws Exception {
        List<ApstraktniDomenskiObjekat> lista = new ArrayList<>();
        while (rs.next()) {
            int idKategorija = rs.getInt("idKategorija");
            String nazivKategorije = rs.getString("nazivKategorije");

            Kategorija k = new Kategorija(idKategorija, nazivKategorije);
            lista.add(k);

        }

        return lista;
    }

    @Override
    public String vratiKoloneZaUbacivanje() {
        return "nazivKategorije";
    }

    @Override
    public String vratiVrednostiZaUbacivanje() {
        return "'"+nazivKategorije+"'";
    }

    @Override
    public String vratiPrimarniKljuc() {
        return "idKategorija="+idKategorija;
    }

    @Override
    public ApstraktniDomenskiObjekat vratiObjekatIzRS(ResultSet rs) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String vratiVrednostiZaIzmenu() {
        return "nazivKategorije='"+nazivKategorije+"'";
    }
    
    
}
