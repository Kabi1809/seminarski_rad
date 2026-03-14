/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domen;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author Alexa
 */
public class Rezervacija implements ApstraktniDomenskiObjekat{
    private int idRezervacija;
    private Date datum;
    private int satOd;
    private int satDo;
    private double popust;
    private double ukupanIznos;
    private Vlasnik idVlasnik;
    private Osoba idOsoba;
    private List<StavkaRezervacije> stavke=new ArrayList<>();
    public Rezervacija() {
    }

    public Rezervacija(int idRezervacija, Date datum, int satOd, int satDo, double popust, double ukupanIznos, Vlasnik idVlasnik, Osoba idOsoba) {
        this.idRezervacija = idRezervacija;
        this.datum = datum;
        this.satOd = satOd;
        this.satDo = satDo;
        this.popust = popust;
        this.ukupanIznos = ukupanIznos;
        this.idVlasnik = idVlasnik;
        this.idOsoba = idOsoba;
    }

    public int getIdRezervacija() {
        return idRezervacija;
    }

    public void setIdRezervacija(int idRezervacija) {
        this.idRezervacija = idRezervacija;
    }

    public Date getDatum() {
        return datum;
    }

    public void setDatum(Date datum) {
        this.datum = datum;
    }

    public int getSatOd() {
        return satOd;
    }

    public void setSatOd(int satOd) {
        this.satOd = satOd;
    }

    public int getSatDo() {
        return satDo;
    }

    public void setSatDo(int satDo) {
        this.satDo = satDo;
    }

    public double getPopust() {
        return popust;
    }

    public void setPopust(double popust) {
        this.popust = popust;
    }

    public double getUkupanIznos() {
        return ukupanIznos;
    }

    public void setUkupanIznos(double ukupanIznos) {
        this.ukupanIznos = ukupanIznos;
    }

    public Vlasnik getIdVlasnik() {
        return idVlasnik;
    }

    public void setIdVlasnik(Vlasnik idVlasnik) {
        this.idVlasnik = idVlasnik;
    }

    public Osoba getIdOsoba() {
        return idOsoba;
    }

    public void setIdOsoba(Osoba idOsoba) {
        this.idOsoba = idOsoba;
    }

    public List<StavkaRezervacije> getStavke() {
        return stavke;
    }

    public void setStavke(List<StavkaRezervacije> stavke) {
        this.stavke = stavke;
    }

    @Override
    public String toString() {
        return "Rezervacija{" + "idRezervacija=" + idRezervacija + ", datum=" + datum + ", satOd=" + satOd + ", satDo=" + satDo + ", popust=" + popust + ", ukupanIznos=" + ukupanIznos + ", idVlasnik=" + idVlasnik + ", idOsoba=" + idOsoba + '}';
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
        final Rezervacija other = (Rezervacija) obj;
        if (this.idRezervacija != other.idRezervacija) {
            return false;
        }
        if (Double.doubleToLongBits(this.ukupanIznos) != Double.doubleToLongBits(other.ukupanIznos)) {
            return false;
        }
        if (!Objects.equals(this.datum, other.datum)) {
            return false;
        }
        if (!Objects.equals(this.idVlasnik, other.idVlasnik)) {
            return false;
        }
        return Objects.equals(this.idOsoba, other.idOsoba);
    }

    @Override
    public String vratiNazivTabele() {
        return "rezervacija";
    }

    @Override
    public List<ApstraktniDomenskiObjekat> vratiListu(ResultSet rs) throws Exception {
        List<ApstraktniDomenskiObjekat> lista=new ArrayList<>();
        while (rs.next()) {
            // **Rezervacija
            int idRezervacija = rs.getInt("rezervacija.idRezervacija");
            double ukupanIznos = rs.getDouble("rezervacija.ukupanIznos");
            int satOd=rs.getInt("rezervacija.satOd");
            int satDo=rs.getInt("rezervacija.satDo");
            double popust=rs.getDouble("rezervacija.popust");
            Date datum = rs.getDate("rezervacija.datum");
//         
           // ** Vlasnik** 
            int idVlasnik=rs.getInt("v.idVlasnik");
            String ime=rs.getString("v.ime");
            String prezime=rs.getString("v.prezime");   
            Vlasnik v=new Vlasnik(idVlasnik, ime, prezime, null, null);
           
           // **Osoba **
           int idOsoba=rs.getInt("o.idOsoba");
           String imeO=rs.getString("o.ime");
           String prezimeO=rs.getString("o.prezime");
           String broj=rs.getString("o.broj");
           String email=rs.getString("o.email");
           Osoba o=new Osoba(idOsoba, imeO, prezimeO, broj, email, null);
           
           Rezervacija r=new Rezervacija(idRezervacija,datum,satOd,satDo,popust,ukupanIznos,v,o);
           r.setStavke(new ArrayList<>()); // prazna lista stavki
           lista.add(r);
            
            

         
         }
         return lista;
    }

    @Override
    public String vratiKoloneZaUbacivanje() {
       return "datum,satOd,satDo,popust,ukupanIznos,idVlasnik,idOsoba";
    }

    @Override
    public String vratiVrednostiZaUbacivanje() {
       java.sql.Date sqlDatum = new java.sql.Date(datum.getTime());
        //return sqlDatum + ",'" + satOd + "','" + satDo + "','" + popust + "','" + ukupanIznos + "'," + idVlasnik.getIdVlasnik() + "," + idOsoba.getIdOsoba();
        return "'" + sqlDatum + "','" + satOd + "','" + satDo + "','" + popust + "','" + ukupanIznos + "'," 
           + idVlasnik.getIdVlasnik() + "," + idOsoba.getIdOsoba();
    }
    @Override
    public String vratiPrimarniKljuc() {
        return "rezervacija.idRezervacija="+idRezervacija;
    }

    @Override
    public ApstraktniDomenskiObjekat vratiObjekatIzRS(ResultSet rs) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String vratiVrednostiZaIzmenu() {
        java.sql.Date sqlDatum = new java.sql.Date(datum.getTime());
        return "satOd='" + satOd + 
       "', satDo='" + satDo + 
       "', popust=" + popust + 
       ", ukupanIznos=" + ukupanIznos + 
       ", datum='" + sqlDatum + "'" + 
       ", idVlasnik=" + idVlasnik.getIdVlasnik() + 
       ", idOsoba=" + idOsoba.getIdOsoba();
    }
    
    
}
