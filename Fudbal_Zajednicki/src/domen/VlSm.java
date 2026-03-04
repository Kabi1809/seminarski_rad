/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domen;

import java.sql.ResultSet;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Alexa
 */
public class VlSm implements ApstraktniDomenskiObjekat{
    private Date datumSmene;
    private Vlasnik idVlasnik;
    private Smena idSmena;

    public VlSm() {
    }

    public VlSm(Date datumSmene, Vlasnik idVlasnik, Smena idSmena) {
        this.datumSmene = datumSmene;
        this.idVlasnik = idVlasnik;
        this.idSmena = idSmena;
    }

    public Date getDatumSmene() {
        return datumSmene;
    }

    public void setDatumSmene(Date datumSmene) {
        this.datumSmene = datumSmene;
    }

    public Vlasnik getIdVlasnik() {
        return idVlasnik;
    }

    public void setIdVlasnik(Vlasnik idVlasnik) {
        this.idVlasnik = idVlasnik;
    }

    public Smena getIdSmena() {
        return idSmena;
    }

    public void setIdSmena(Smena idSmena) {
        this.idSmena = idSmena;
    }

    @Override
    public String toString() {
        return "VlSm{" + "datumSmene=" + datumSmene + ", idVlasnik=" + idVlasnik + ", idSmena=" + idSmena + '}';
    }

    @Override
    public String vratiNazivTabele() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<ApstraktniDomenskiObjekat> vratiListu(ResultSet rs) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String vratiKoloneZaUbacivanje() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String vratiVrednostiZaUbacivanje() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String vratiPrimarniKljuc() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public ApstraktniDomenskiObjekat vratiObjekatIzRS(ResultSet rs) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String vratiVrednostiZaIzmenu() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    
}
