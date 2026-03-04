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
public class Osoba implements ApstraktniDomenskiObjekat{
    private int idOsoba;
    private String ime;
    private String prezime;
    private String broj;
    private String email;
    private Kategorija idKategorija;

    public Osoba() {
    }

    public Osoba(int idOsoba, String ime, String prezime, String broj, String email, Kategorija idKategorija) {
        this.idOsoba = idOsoba;
        this.ime = ime;
        this.prezime = prezime;
        this.broj = broj;
        this.email = email;
        this.idKategorija = idKategorija;
    }

    public int getIdOsoba() {
        return idOsoba;
    }

    public void setIdOsoba(int idOsoba) {
        this.idOsoba = idOsoba;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    public String getBroj() {
        return broj;
    }

    public void setBroj(String broj) {
        this.broj = broj;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Kategorija getIdKategorija() {
        return idKategorija;
    }

    public void setIdKategorija(Kategorija idKategorija) {
        this.idKategorija = idKategorija;
    }

    @Override
    public String toString() {
        return ime+" "+prezime;
    }

    @Override
    public int hashCode() {
        int hash = 5;
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
        final Osoba other = (Osoba) obj;
        if (this.idOsoba != other.idOsoba) {
            return false;
        }
        if (!Objects.equals(this.ime, other.ime)) {
            return false;
        }
        return Objects.equals(this.prezime, other.prezime);
    }

    @Override
    public String vratiNazivTabele() {
        return "osoba";
    }

    @Override
    public List<ApstraktniDomenskiObjekat> vratiListu(ResultSet rs) throws Exception {
      List<ApstraktniDomenskiObjekat> lista = new ArrayList<>();
        while (rs.next()) {
            int idOsoba = rs.getInt("idOsoba");
            String ime = rs.getString("ime");
            String prezime = rs.getString("prezime");
            String broj= rs.getString("broj");
            String email=rs.getString("email");
            int idKategorija=rs.getInt("idKategorija");
            String nazivKategorije=rs.getString("nazivKategorije");
            
            Kategorija k=new Kategorija(idKategorija, nazivKategorije);
            
            Osoba o=new Osoba(idOsoba, ime, prezime, broj,email, k);
            lista.add(o);

        }

        return lista;
    }

    @Override
    public String vratiKoloneZaUbacivanje() {
        return "ime,prezime,broj,email,idKategorija";
    }

    @Override
    public String vratiVrednostiZaUbacivanje() {
        return "'"+ime+"', '"+prezime+"', '"+broj+"', '"+email+"','"+idKategorija.getIdKategorija();
    }

    @Override
    public String vratiPrimarniKljuc() {
         return "idOsoba="+idOsoba;
    }

    @Override
    public ApstraktniDomenskiObjekat vratiObjekatIzRS(ResultSet rs) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String vratiVrednostiZaIzmenu() {
        return "ime='"+ime+"', prezime='"+prezime+"', brojtelefona='"+broj+"',email='"+email+"', idKategorija="+idKategorija.getIdKategorija();
    }
    
    
}
