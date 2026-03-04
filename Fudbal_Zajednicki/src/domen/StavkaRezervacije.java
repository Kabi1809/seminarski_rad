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
    private int idRezervacija;
    private Usluga idUsluga;

    public StavkaRezervacije() {
    }

    public StavkaRezervacije(int rb, double cenaUsluge, int brojUsluga, double iznos, Usluga idUsluga, int idRezervacija) {
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

    public int getIdRezervacija() {
        return idRezervacija;
    }

    public void setIdRezervacija(int idRezervacija) {
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
        return "stavkarezervacije";
    }

    @Override
    public List<ApstraktniDomenskiObjekat> vratiListu(ResultSet rs) throws Exception {
         List<ApstraktniDomenskiObjekat> lista = new ArrayList<>();
        while (rs.next()) {

            int rb = rs.getInt("rb");
            double cenaUsluge = rs.getDouble("cenaUsluge");
            int brojUsluga = rs.getInt("brojUsluga");
            double iznos = rs.getDouble("iznos");
            int idRezervacija = rs.getInt("idRezervacija");

            int idUsluga = rs.getInt("idUsluga");
            double cena = rs.getDouble("cena");
            String naziv = rs.getString("naziv");

            Usluga u = new Usluga(idUsluga, cena, naziv);
            StavkaRezervacije stavka = new StavkaRezervacije(rb, cenaUsluge, brojUsluga, iznos, u,idRezervacija);

            lista.add(stavka);
        }
        return lista;
    }

    
    

    @Override
    public String vratiKoloneZaUbacivanje() {
        return "rb,cenaUsluge,brojUsluga,iznos,idRezervacija,idUsluga";
    }

    @Override
    public String vratiVrednostiZaUbacivanje() {
       return rb+", "+cenaUsluge+", "+brojUsluga+", "+iznos+","+idRezervacija+","+idUsluga.getIdUsluga();
    }

    @Override
    public String vratiPrimarniKljuc() {
        return "rb="+rb+" AND idRezervacija="+idRezervacija;
    }

    @Override
    public ApstraktniDomenskiObjekat vratiObjekatIzRS(ResultSet rs) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String vratiVrednostiZaIzmenu() {
         return "cenaUsluge="+cenaUsluge+" brojUsluga="+brojUsluga+" iznos="+iznos+", idRezervacija="+idRezervacija+", idUsluga="+idUsluga.getIdUsluga();
    }

}
    
