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
public class StavkaRezervacije implements ApstraktniDomenskiObjekat {
    private int rb;
    private double cenaUsluge;
    private int brojUsluga;
    private double iznos;
    private Rezervacija idRezervacija;
    private Usluga idUsluga;

    public StavkaRezervacije() {
    }

    public StavkaRezervacije(int rb, double cenaUsluge, int brojUsluga, double iznos, Usluga idUsluga, Rezervacija idRezervacija) {
        this.rb = rb;
        this.cenaUsluge = cenaUsluge;
        this.brojUsluga = brojUsluga;
        this.iznos = iznos;
        this.idUsluga = idUsluga;
        this.idRezervacija = idRezervacija;
    }

    public int getRb() {
        return rb;
    }

    public void setRb(int rb) {
        this.rb = rb;
    }

    public double getCenaUsluge() {
        return cenaUsluge;
    }

    public void setCenaUsluge(double cenaUsluge) {
        this.cenaUsluge = cenaUsluge;
    }

    public int getBrojUsluga() {
        return brojUsluga;
    }

    public void setBrojUsluga(int brojUsluga) {
        this.brojUsluga = brojUsluga;
    }

    public double getIznos() {
        return iznos;
    }

    public void setIznos(double iznos) {
        this.iznos = iznos;
    }

    public Usluga getIdUsluga() {
        return idUsluga;
    }

    public void setIdUsluga(Usluga idUsluga) {
        this.idUsluga = idUsluga;
    }

    public Rezervacija getIdRezervacija() {
        return idRezervacija;
    }

    public void setIdRezervacija(Rezervacija idRezervacija) {
        this.idRezervacija = idRezervacija;
    }


    @Override
    public String toString() {
        return "StavkaRezervacije{" + "rb=" + rb + ", cenaUsluge=" + cenaUsluge + ", brojUsluga=" + brojUsluga + ", iznos=" + iznos + ", idUsluga=" + idUsluga + ", idRezervacija=" + idRezervacija + '}';
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
        final StavkaRezervacije other = (StavkaRezervacije) obj;
        if (this.rb != other.rb) {
            return false;
        }
        if (Double.doubleToLongBits(this.cenaUsluge) != Double.doubleToLongBits(other.cenaUsluge)) {
            return false;
        }
        if (this.brojUsluga != other.brojUsluga) {
            return false;
        }
        if (Double.doubleToLongBits(this.iznos) != Double.doubleToLongBits(other.iznos)) {
            return false;
        }
        if (!Objects.equals(this.idRezervacija, other.idRezervacija)) {
            return false;
        }
        return Objects.equals(this.idUsluga, other.idUsluga);
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
