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
public class Vlasnik implements ApstraktniDomenskiObjekat {
    private int idVlasnik;
    private String ime;
    private String prezime;
    private String korisničkoIme;
    private String šifra;

    public Vlasnik() {
    }

    public Vlasnik(int idVlasnik, String ime, String prezime, String korisničkoIme, String šifra) {
        this.idVlasnik = idVlasnik;
        this.ime = ime;
        this.prezime = prezime;
        this.korisničkoIme = korisničkoIme;
        this.šifra = šifra;
    }

    public int getIdVlasnik() {
        return idVlasnik;
    }

    public void setIdVlasnik(int idVlasnik) {
        this.idVlasnik = idVlasnik;
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

    public String getKorisničkoIme() {
        return korisničkoIme;
    }

    public void setKorisničkoIme(String korisničkoIme) {
        this.korisničkoIme = korisničkoIme;
    }

    public String getŠifra() {
        return šifra;
    }

    public void setŠifra(String šifra) {
        this.šifra = šifra;
    }

    @Override
    public String toString() {
        return ime+" "+prezime;
    }

    @Override
    public int hashCode() {
        int hash = 3;
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
        final Vlasnik other = (Vlasnik) obj;
        if (!Objects.equals(this.korisničkoIme, other.korisničkoIme)) {
            return false;
        }
        return Objects.equals(this.šifra, other.šifra);
    }

    @Override
    public String vratiNazivTabele() {
        return "vlasnik";
    }

    @Override
    public List<ApstraktniDomenskiObjekat> vratiListu(ResultSet rs) throws Exception {
        List<ApstraktniDomenskiObjekat> lista=new ArrayList<>();
        while(rs.next()){
            int idVlasnik=rs.getInt("vlasnik.idVlasnik");
            String ime=rs.getString("vlasnik.ime");
            String prezime=rs.getString("vlasnik.prezime");
            String korisničkoIme=rs.getString("vlasnik.korisničkoIme");
            String šifra=rs.getString("vlasnik.šifra");
            
            Vlasnik v=new Vlasnik(idVlasnik, ime, prezime, korisničkoIme, šifra);
            lista.add(v);
        
        }     
        return lista;
    }

    @Override
    public String vratiKoloneZaUbacivanje() {
        return "ime,prezime,korisničkoIme,šifra";
    }

    @Override
    public String vratiVrednostiZaUbacivanje() {
        return "'" +ime+ "', '" +prezime+ "', '" +korisničkoIme +"', '" +šifra+"'";
    }

    @Override
    public String vratiPrimarniKljuc() {
        return "vlasnik.idVlasnik="+idVlasnik;
    }

    @Override
    public ApstraktniDomenskiObjekat vratiObjekatIzRS(ResultSet rs) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String vratiVrednostiZaIzmenu() {
        return "ime='"+ime+"', prezime='"+prezime+"', korisničkoime='"+korisničkoIme+"', šifra='"+šifra+"'";
    }
    
    
}
